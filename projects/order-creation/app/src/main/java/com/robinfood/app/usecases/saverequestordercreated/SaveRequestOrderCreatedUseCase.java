package com.robinfood.app.usecases.saverequestordercreated;

import com.robinfood.core.util.SaveDataInMemoryUtil;
import com.robinfood.core.dtos.transactionrequestdto.TransactionRequestDTO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SaveRequestOrderCreatedUseCase implements ISaveRequestOrderCreatedUseCase {

    @Override
    public void invoke(@NonNull TransactionRequestDTO transactionRequestDTO) {

        SaveDataInMemoryUtil.setData(transactionRequestDTO.getUuid().toString(), transactionRequestDTO);
    }
}
