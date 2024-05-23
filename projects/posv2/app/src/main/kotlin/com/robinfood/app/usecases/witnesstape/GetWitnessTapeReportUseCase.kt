package com.robinfood.app.usecases.witnesstape

import com.robinfood.app.usecases.getstoreconfigurations.IGetStoreConfigurationUseCase
import com.robinfood.core.constants.GlobalConstants.FORMAT_DATE_WITH_HOUR_AND_PM_AM
import com.robinfood.core.constants.GlobalConstants.NAME_REPORT_WITNESS_TAPE
import com.robinfood.core.constants.ReportConstants
import com.robinfood.core.constants.ReportConstants.Companion.getUrlReport
import com.robinfood.core.dtos.report.ReportResponseDTO
import com.robinfood.core.dtos.store.StoreConfigurationsDTO
import com.robinfood.core.dtos.posresolutionsequence.PosResolutionSequenceDTO
import com.robinfood.core.dtos.witnesstape.StoreOrderRequestDTO
import com.robinfood.core.dtos.witnesstape.StoreOrdersDTO
import com.robinfood.core.dtos.witnesstape.WitnessTapePosDTO
import com.robinfood.core.dtos.witnesstape.WitnessTapeTableSalesDTO
import com.robinfood.core.dtos.witnesstape.WitnessTapeTransformDTO
import com.robinfood.core.helpers.JasperReportManagerHelper
import com.robinfood.core.mappers.witnesstape.toWitnessTapePosDTOs
import com.robinfood.core.mappers.witnesstape.toWitnessTapeTableSalesDTO
import com.robinfood.core.utils.getCurrentDateTimeInTimeZone
import java.util.ArrayList
import org.springframework.stereotype.Service

@Service
class GetWitnessTapeReportUseCase(
    private val getStoreConfigurationUseCase: IGetStoreConfigurationUseCase,
    private val getStoreResolutionSequenceUseCase: IGetStoreResolutionSequenceUseCase,
    private val getOrdersByStoreUseCase: IGetOrdersByStoreUseCase,
    private val jasperReportManagerHelper: JasperReportManagerHelper
) : IGetWitnessTapeReportUseCase {

    override suspend fun invoke(storeOrderRequestDTO: StoreOrderRequestDTO, token: String): ReportResponseDTO {

        val witnessTapeTransformDTOs: MutableList<WitnessTapeTransformDTO> = ArrayList()

        val storeConfigurationsDTO = getInfoStoreConfigurations(storeOrderRequestDTO, token)
        val witnessTapePosDTOs = generateWitnessTapePosTable(storeOrderRequestDTO, token)
        val witnessTapeTableSalesDTOs = generateWitnessTapeOrderDetails(storeOrderRequestDTO,
            witnessTapePosDTOs, storeConfigurationsDTO.company.country.id,token)

        val witnessTapeTransformDTO =
            generateWitnessTape(
                storeOrderRequestDTO,
                storeConfigurationsDTO,
                witnessTapePosDTOs,
                witnessTapeTableSalesDTOs
            )

        witnessTapeTransformDTOs.add(witnessTapeTransformDTO)

        val params = HashMap<String, Any>()

        val result = jasperReportManagerHelper.export(
            getUrlReport(
                storeConfigurationsDTO.company.country.id.toInt(),
                ReportConstants.MAGNETIC_WITNESS_TAPE_REPORT_DIR
            ),
            params, witnessTapeTransformDTOs
        )

        return ReportResponseDTO.Builder()
            .reportByteArray(result.toByteArray().contentToString())
            .fileName(NAME_REPORT_WITNESS_TAPE + "-" + storeOrderRequestDTO.localDateEnd.toString())
            .build()
    }

    private suspend fun getInfoStoreConfigurations(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): StoreConfigurationsDTO {
        return getStoreConfigurationUseCase.invoke(
            storeOrderRequestDTO.storeId,
            token
        )
    }

    private suspend fun getInfoPosResolutionsByStore(
        storeOrderRequestDTO: StoreOrderRequestDTO, token: String
    ): List<PosResolutionSequenceDTO> {
        return getStoreResolutionSequenceUseCase.invoke(
            storeOrderRequestDTO,
            token
        )
    }

    private suspend fun getInfoOrdersByStores(
        storeOrderRequestDTO: StoreOrderRequestDTO, token: String
    ): List<StoreOrdersDTO> {
        return getOrdersByStoreUseCase.invoke(
            storeOrderRequestDTO,
            token
        )
    }

    private suspend fun generateWitnessTapePosTable(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        token: String
    ): List<WitnessTapePosDTO> {
        val posResolutionSequenceDTOs = getInfoPosResolutionsByStore(storeOrderRequestDTO, token)

        return posResolutionSequenceDTOs.map { posResolutionSequenceDTO ->
            posResolutionSequenceDTO.toWitnessTapePosDTOs()
        }
    }

    private suspend fun generateWitnessTapeOrderDetails(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        witnessTapePosDTOs: List<WitnessTapePosDTO>,
        countryId: Long,
        token: String
    ): List<WitnessTapeTableSalesDTO> {

        val storeOrdersDTOs = getInfoOrdersByStores(storeOrderRequestDTO, token)

        val witnessTapeTableSalesDTOs = storeOrdersDTOs.map { storeOrdersDTO ->
            storeOrdersDTO.toWitnessTapeTableSalesDTO(countryId)
        }

        witnessTapeTableSalesDTOs.forEach { witnessTapeTableSalesDTO ->
            witnessTapePosDTOs.filter { it.posId == witnessTapeTableSalesDTO.posId }
                .forEach { witnessTapePosDTO ->
                    witnessTapeTableSalesDTO.prefix = witnessTapePosDTO.prefix
                    witnessTapeTableSalesDTO.box = witnessTapePosDTO.box
                }
        }

        return witnessTapeTableSalesDTOs.sortedWith(compareBy({ it.box }, { it.docEquivalent }))

    }

    private fun generateWitnessTape(
        storeOrderRequestDTO: StoreOrderRequestDTO,
        storeConfigurationsDTO: StoreConfigurationsDTO,
        witnessTapePosDTOs: List<WitnessTapePosDTO>,
        witnessTapeTableSalesDTOs: List<WitnessTapeTableSalesDTO>
    ): WitnessTapeTransformDTO {

        return WitnessTapeTransformDTO.Builder()
            .businessName(storeConfigurationsDTO.company.name)
            .businessNit(storeConfigurationsDTO.company.identification)
            .createAt(storeOrderRequestDTO.localDateEnd.toString())
            .datePrint(getCurrentDateTimeInTimeZone(storeConfigurationsDTO.timezone, FORMAT_DATE_WITH_HOUR_AND_PM_AM))
            .listOrderDet(
                jasperReportManagerHelper.convertCollectionToJRBeanCollectionDataSource(
                    witnessTapeTableSalesDTOs
                )
            )
            .listPosDet(
                jasperReportManagerHelper
                    .convertCollectionToJRBeanCollectionDataSource(witnessTapePosDTOs)
            )
            .storeAddress(storeConfigurationsDTO.address)
            .storeName(storeConfigurationsDTO.name)
            .subReportDetail(
                jasperReportManagerHelper.getSubReportToInputStream(
                    getUrlReport(
                        storeConfigurationsDTO.company.country.id.toInt(),
                        ReportConstants.SUB_REPORT_MAGNETIC_WITNESS_TAPE_DETAIL_DIR
                    )
                )
            )
            .subReportPos(
                jasperReportManagerHelper.getSubReportToInputStream(
                    getUrlReport(
                        storeConfigurationsDTO.company.country.id.toInt(),
                        ReportConstants.SUB_REPORT_MAGNETIC_WITNESS_TAPE_POS_DIR
                    )
                )
            )
            .build()
    }

}