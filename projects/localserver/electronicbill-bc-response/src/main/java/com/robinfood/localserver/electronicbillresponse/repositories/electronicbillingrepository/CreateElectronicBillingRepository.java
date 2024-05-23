package com.robinfood.localserver.electronicbillresponse.repositories.electronicbillingrepository;

import com.robinfood.localserver.commons.dtos.electronicbill.RequestElectronicBillingDTO;
import com.robinfood.localserver.commons.dtos.electronicbill.ResponseElectronicBillingDTO;
import com.robinfood.localserver.commons.entities.electronicbill.ElectronicBillingRequestEntity;
import com.robinfood.localserver.commons.mappers.electronicbill.IElectronicBillingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * Implementation of ICreateOrdersElectronicBillingRepository
 */
@Repository
@Slf4j
public class CreateElectronicBillingRepository implements ICreateElectronicBillingRepository {

    private final IElectronicBillingMapper electronicBillingMapper;
    private final ICreateElectronicBillingRemoteDataSource createElectronicBillingRemoteDataSource;

    public CreateElectronicBillingRepository(
            IElectronicBillingMapper electronicBillingMapper,
            ICreateElectronicBillingRemoteDataSource createElectronicBillingRemoteDataSource
    ) {
        this.electronicBillingMapper = electronicBillingMapper;
        this.createElectronicBillingRemoteDataSource = createElectronicBillingRemoteDataSource;
    }

    @Override
    public ResponseElectronicBillingDTO invoke(
            RequestElectronicBillingDTO createElectronicBillingRequestDTO,
            String tokenUser
    ) {

        log.debug("Execute create electronic billing repository {}", createElectronicBillingRequestDTO);

        ElectronicBillingRequestEntity electronicBillingRequestEntity = electronicBillingMapper
                .createElectronicBillingRequestDTOToElectronicBillingRequestEntity(
                        createElectronicBillingRequestDTO
                );

        return electronicBillingMapper.electronicBillingResponseEntityToCreateElectronicBillingResponseDTO(
                createElectronicBillingRemoteDataSource.invoke(electronicBillingRequestEntity, tokenUser).getData()
                );
    }
}
