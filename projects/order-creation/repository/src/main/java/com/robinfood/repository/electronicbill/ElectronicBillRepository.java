package com.robinfood.repository.electronicbill;

import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ElectronicBillRepository implements IElectronicBillRepository {

    private final IElectronicBillRemoteDataSource electronicBillRemoteDataSource;

    public ElectronicBillRepository(IElectronicBillRemoteDataSource electronicBillRemoteDataSource) {
        this.electronicBillRemoteDataSource = electronicBillRemoteDataSource;
    }

    @Override
    public Boolean sendElectronicBill(String token, TransactionRequestDTO transactionRequestDTO) {
        return electronicBillRemoteDataSource.sendElectronicBill(token, transactionRequestDTO);
    }
}
