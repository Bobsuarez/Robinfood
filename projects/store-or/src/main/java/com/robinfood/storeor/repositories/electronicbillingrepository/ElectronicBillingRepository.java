package com.robinfood.storeor.repositories.electronicbillingrepository;

import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingRequestDTO;
import com.robinfood.storeor.dtos.electronicbilling.CreateElectronicBillingResponseDTO;
import com.robinfood.storeor.dtos.electronicbilling.ElectronicBillDTO;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingEntity;
import com.robinfood.storeor.entities.electronicbilling.ElectronicBillingRequestEntity;
import com.robinfood.storeor.mappers.IElectronicBillingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of IElectronicBillingRepository
 */
@Repository
public class ElectronicBillingRepository implements IElectronicBillingRepository {

    private final IElectronicBillingMapper electronicBillingMapper;
    private final IElectronicBillingRemoteDataSource electronicBillingRemoteDataSource;

    public ElectronicBillingRepository(
            IElectronicBillingMapper electronicBillingMapper,
            IElectronicBillingRemoteDataSource electronicBillingRemoteDataSource
    ) {
        this.electronicBillingMapper = electronicBillingMapper;
        this.electronicBillingRemoteDataSource = electronicBillingRemoteDataSource;
    }

    @Override
    public CreateElectronicBillingResponseDTO save(
            String token,
            CreateElectronicBillingRequestDTO createElectronicBillingRequestDTO) {

        ElectronicBillingRequestEntity electronicBillingRequestEntity = electronicBillingMapper
                .createElectronicBillingRequestDTOToElectronicBillingRequestEntity(
                        createElectronicBillingRequestDTO
                );

        return electronicBillingMapper.electronicBillingResponseEntityToCreateElectronicBillingResponseDTO(
                electronicBillingRemoteDataSource.save(token, electronicBillingRequestEntity).getData()
        );
    }

    public List<ElectronicBillDTO> findAllByOrderIdIn(
            String token,
            List<Long> orderId
    ) {

        List<ElectronicBillingEntity> responseDataSource =
                electronicBillingRemoteDataSource
                        .findAllByOrderIdIn(token, orderId)
                        .getData();

        return electronicBillingMapper.electronicBillingEntityListToElectronicBillDTOList(responseDataSource);
    }
}
