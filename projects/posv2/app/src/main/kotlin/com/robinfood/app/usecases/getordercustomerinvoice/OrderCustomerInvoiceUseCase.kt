package com.robinfood.app.usecases.getordercustomerinvoice

import com.fasterxml.jackson.databind.ObjectMapper
import com.robinfood.core.constants.GlobalConstants
import com.robinfood.core.constants.GlobalConstants.COLOMBIA
import com.robinfood.core.constants.GlobalConstants.DECIMAL_FORMAT
import com.robinfood.core.constants.GlobalConstants.DECIMAL_FORMAT_TRANSFORM
import com.robinfood.core.constants.GlobalConstants.DECIMAL_SEPARATOR
import com.robinfood.core.constants.GlobalConstants.EXTERNAL_ORDER
import com.robinfood.core.constants.GlobalConstants.GROUPING_SEPARATOR
import com.robinfood.core.constants.GlobalConstants.INTERNAL_ORDER
import com.robinfood.core.constants.GlobalConstants.QRCODE_ATTRIBUTE
import com.robinfood.core.constants.ReportConstants
import com.robinfood.core.constants.ReportInvoiceConstants
import com.robinfood.core.dtos.customerinvoice.ItemsServiceDTO
import com.robinfood.core.dtos.customerinvoice.OptionProductInvoiceDTO
import com.robinfood.core.dtos.customerinvoice.OrderCustomerInvoiceDTO
import com.robinfood.core.dtos.customerinvoice.OrderCustomerInvoiceDetailDTO
import com.robinfood.core.dtos.customerinvoice.OrderCustomerInvoiceDetailReportDTO
import com.robinfood.core.dtos.customerinvoice.OrderInvoiceDTO
import com.robinfood.core.dtos.customerinvoice.PrintFormatDTO
import com.robinfood.core.dtos.customerinvoice.ProductInvoiceDTO
import com.robinfood.core.dtos.customerinvoice.ServicesInvoiceDTO
import com.robinfood.core.dtos.report.ReportResponseDTO
import com.robinfood.core.helpers.JasperReportManagerHelper
import com.robinfood.core.helpers.QrGeneratorHelper
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.time.LocalDate
import java.util.concurrent.atomic.AtomicReference
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class OrderCustomerInvoiceUseCase(
    private val getOrderCustomerInvoiceUseCase: IGetOrderCustomerInvoiceUseCase,
    private val jasperReportManagerHelper: JasperReportManagerHelper
) : IOrderCustomerInvoiceUseCase {

    override suspend fun invoke(orderId: Long, token: String): ReportResponseDTO {

        val printFormatDTO = getOrderCustomerInvoiceUseCase.invoke(orderId, token)
            ?: throw  ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found")

        val orderCustomerInvoiceDetailDTOs: MutableList<OrderCustomerInvoiceDetailDTO> = ArrayList()

        buildOrderDetails(orderCustomerInvoiceDetailDTOs, printFormatDTO)
        val orderCustomerInvoiceDTO = setOrderCustomerInvoice(printFormatDTO)
        var orderCustomerInvoiceDetailReport = getInvoiceDetailReport(printFormatDTO, orderCustomerInvoiceDetailDTOs)

        val orderCustomerInvoiceMap: MutableMap<String, Any> = ObjectMapper().convertValue(
            orderCustomerInvoiceDTO,
            MutableMap::class.java
        ) as MutableMap<String, Any>

        orderCustomerInvoiceMap!![QRCODE_ATTRIBUTE] = getInputStreamByte()

        val result = jasperReportManagerHelper.export(
            ReportConstants.getUrlReport(
                COLOMBIA,
                ReportConstants.REPORT_CUSTOMER_INVOICE_DIR
            ),
            orderCustomerInvoiceMap as HashMap<String, Any>, listOf(orderCustomerInvoiceDetailReport)
        )

        return ReportResponseDTO.Builder()
            .reportByteArray(result.toByteArray().contentToString())
            .fileName(GlobalConstants.NAME_REPORT_CUSTOMER_INVOICE + "-" + LocalDate.now().toString())
            .build()
    }

    private fun getInvoiceDetailReport(
        printFormatDTO: PrintFormatDTO,
        orderCustomerInvoiceDetailDTOs: MutableList<OrderCustomerInvoiceDetailDTO>
    ): OrderCustomerInvoiceDetailReportDTO {

        val orderInvoiceDTO = printFormatDTO.order
        val services = buildServicesInvoice(orderInvoiceDTO)
        val currency = orderInvoiceDTO.detail.currency

        return OrderCustomerInvoiceDetailReportDTO
            .Builder()
            .orderCustomerInvoiceDetail(jasperReportManagerHelper
                .convertCollectionToJRBeanCollectionDataSource(orderCustomerInvoiceDetailDTOs))
            .subReportProducts(
                jasperReportManagerHelper.getSubReportToInputStream(
                    ReportConstants.getUrlReport(
                        COLOMBIA,
                        ReportConstants.SUB_REPORT_CUSTOMER_INVOICE_PRODUCT_DIR
                    )
                )
            )
            .hasCompensation(orderInvoiceDTO.hasCompensation)
            .legendCompensation(orderInvoiceDTO.compensation.copy)
            .listServicesOrder(
                jasperReportManagerHelper
                    .convertCollectionToJRBeanCollectionDataSource(services)
            ).subReportServices(
                jasperReportManagerHelper.getSubReportToInputStream(
                    ReportConstants.getUrlReport(
                        COLOMBIA,
                        ReportConstants.SUB_REPORT_CUSTOMER_INVOICE_SERVICE_DIR
                    )
                )
            )
            .totalToCollect(formatValueInvoice(orderInvoiceDTO.totalToCollect.value, currency))
            .build()
    }

    private fun getInputStreamByte(): InputStream {
        val qrImage = QrGeneratorHelper.getQRCodeImage(ReportInvoiceConstants.QR_INFO_CO.value)
        return ByteArrayInputStream(qrImage)
    }

    private fun buildOrderDetails(
        orderCustomerInvoiceDetailDTOs: MutableList<OrderCustomerInvoiceDetailDTO>,
        printFormatDTO: PrintFormatDTO
    ) {
        val orderInvoiceDTO = printFormatDTO.order

        val currency = orderInvoiceDTO.detail.currency;
        val df = DecimalFormat(DECIMAL_FORMAT)

        orderInvoiceDTO.products.forEach { productInvoiceDTO: ProductInvoiceDTO ->
            val orderCustomerInvoiceDetail = OrderCustomerInvoiceDetailDTO.Builder()
                .orderDetail(
                    String.format(
                        ReportInvoiceConstants.PRODUCT_ORDER.value,
                        productInvoiceDTO.quantity,
                        productInvoiceDTO.name,
                        String.format(ReportInvoiceConstants.PRICE_CURRENCY.value, currency,
                            df.format(productInvoiceDTO.total)))
                )
                .orderAddiction(buildProductsOptions(productInvoiceDTO.option))
                .build()
            orderCustomerInvoiceDetailDTOs.add(orderCustomerInvoiceDetail)
        }
    }

    private fun buildProductsOptions(optionProductInvoiceDTOs: List<OptionProductInvoiceDTO>): String {
        val optionResult = AtomicReference("")
        optionProductInvoiceDTOs.forEach { optionProductInvoiceDTO: OptionProductInvoiceDTO ->
            optionResult.set(
                optionResult.get() + String.format(
                    ReportInvoiceConstants.PRODUCT_ORDER_ADDICTION.value, optionProductInvoiceDTO.name
                )
            )
        }
        return optionResult.get()
    }

    private fun getNumOrderComposite(orderInvoiceDTO: OrderInvoiceDTO): String {

        var compositeOrderNumber = INTERNAL_ORDER + " " + orderInvoiceDTO.uid +
                " (" + orderInvoiceDTO.orderNumber + " )"

        if (orderInvoiceDTO.isExternalDelivery) {
            compositeOrderNumber = EXTERNAL_ORDER + " " + orderInvoiceDTO.externalOrderNumber +
                    " (" + orderInvoiceDTO.code + " )"
        }

        return compositeOrderNumber;

    }

    private fun setOrderCustomerInvoice(printFormatDTO: PrintFormatDTO): OrderCustomerInvoiceDTO {

        val orderInvoiceDTO = printFormatDTO.order
        val companyInvoiceDTO = printFormatDTO.company
        val compositeOrderNumber = getNumOrderComposite(orderInvoiceDTO)

        val currency = orderInvoiceDTO.detail.currency;

        return OrderCustomerInvoiceDTO.Builder()
            .advertisingSpace(ReportInvoiceConstants.ADVERTISING_SPACE.value)
            .barCodeImage(null)
            .barCodeInfo(null)
            .brandImage(ReportConstants.getUrlLogo(orderInvoiceDTO.brandId.toInt()))
            .customerName(orderInvoiceDTO.nameCustomer)
            .customerPhone(orderInvoiceDTO.phoneCustomer)
            .companyAddress(companyInvoiceDTO.address)
            .companyName(companyInvoiceDTO.name)
            .dianResolutionInfo(
                String.format(ReportInvoiceConstants.RESOLUTION.value,
                    orderInvoiceDTO.resTypeNumber + " ", orderInvoiceDTO.resNumber, orderInvoiceDTO.resDateInit,
                    orderInvoiceDTO.resDateEnd, orderInvoiceDTO.resNumberInit, orderInvoiceDTO.resNumberEnd))
            .discounts(formatValueInvoice(orderInvoiceDTO.detail.discount.value, currency))
            .invoice(String.format(
                ReportInvoiceConstants.INVOICE.value, orderInvoiceDTO.codeCaja, orderInvoiceDTO.consCaja))
            .invoiceOrder(orderInvoiceDTO.uid)
            .invoiceInfo(null)
            .invoiceDate(orderInvoiceDTO.resDateEnd)
            .isWalkers(orderInvoiceDTO.isWalkers)
            .nit(orderInvoiceDTO.infNit)
            .ie(null)
            .notesInfo(String.format(ReportInvoiceConstants.NOTE.value, orderInvoiceDTO.additionalNotes))
            .order(compositeOrderNumber)
            .paymentName(orderInvoiceDTO.cashPaymentName)
            .paymentValue(String.format(ReportInvoiceConstants.PRICE.value, orderInvoiceDTO.cashPaymentValue))
            .qrInfo(ReportInvoiceConstants.QR_INFO_CO.value)
            .storeAddress(orderInvoiceDTO.infAddress)
            .storeName(orderInvoiceDTO.storeName)
            .storeEmail("")
            .subTotal(formatValueInvoice(orderInvoiceDTO.detail.subtotal.value, currency))
            .taxes(formatValueInvoice(orderInvoiceDTO.detail.tax.value, currency))
            .total(formatValueInvoice(orderInvoiceDTO.detail.total.value, currency)).build()
    }

    private fun formatValueInvoice(value: String, currency: String): String {
        val symbols = DecimalFormatSymbols()
        symbols.decimalSeparator = DECIMAL_SEPARATOR
        symbols.groupingSeparator = GROUPING_SEPARATOR

        val formatDecimal = DecimalFormat(DECIMAL_FORMAT_TRANSFORM, symbols)
        formatDecimal.isParseBigDecimal = true

        var numberWithoutSeparators: String = value
        try {
            value.toDouble().toString()
        } catch (e: Exception) {
            numberWithoutSeparators = value.replace("[^\\d,]".toRegex(), "")
        }

        val numberDouble = numberWithoutSeparators.replace(',', '.').toDouble()
        return String.format(ReportInvoiceConstants.PRICE_CURRENCY.value, currency, formatDecimal.format(numberDouble))
    }


    private fun buildServicesInvoice(orderCustomer: OrderInvoiceDTO): List<ItemsServiceDTO> {

        val currency = orderCustomer.detail.currency

        val listItemsServiceReportDTO: MutableList<ItemsServiceDTO> = ArrayList()
        orderCustomer.services.forEach { servicesInvoiceDTO: ServicesInvoiceDTO ->
            setItemService(listItemsServiceReportDTO, servicesInvoiceDTO, currency)
        }

        if (orderCustomer.hasCompensation) {
            var itemService = ItemsServiceDTO(orderCustomer.compensation.compensationName,
                orderCustomer.compensation.title,
                formatValueInvoice(orderCustomer.compensation.value, currency))
            listItemsServiceReportDTO.add(itemService)
        }

        return listItemsServiceReportDTO
    }

    private fun setItemService(
        listItemsServiceReportDTO: MutableList<ItemsServiceDTO>, servicesInvoiceDTO: ServicesInvoiceDTO,
        currency: String
    ) {
        listItemsServiceReportDTO.add(ItemsServiceDTO(servicesInvoiceDTO.subtotal.name, servicesInvoiceDTO.name,
            formatValueInvoice(servicesInvoiceDTO.subtotal.value, currency)))
        listItemsServiceReportDTO.add(ItemsServiceDTO(servicesInvoiceDTO.discount.name, servicesInvoiceDTO.name,
            formatValueInvoice(servicesInvoiceDTO.discount.value, currency)))
        listItemsServiceReportDTO.add(ItemsServiceDTO(servicesInvoiceDTO.tax.name, servicesInvoiceDTO.name,
            formatValueInvoice(servicesInvoiceDTO.tax.value, currency)))
        listItemsServiceReportDTO.add(ItemsServiceDTO(servicesInvoiceDTO.total.name, servicesInvoiceDTO.name,
            formatValueInvoice(servicesInvoiceDTO.total.value, currency)))
    }

}