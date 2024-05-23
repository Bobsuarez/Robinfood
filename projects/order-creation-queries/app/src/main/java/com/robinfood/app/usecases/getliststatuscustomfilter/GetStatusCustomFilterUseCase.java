package com.robinfood.app.usecases.getliststatuscustomfilter;

import com.robinfood.core.dtos.StatusCustomFilterDTO;
import com.robinfood.core.enums.Result;
import com.robinfood.core.enums.StatusCustomFilterEnum;
import com.robinfood.core.mappers.StatusCustomFilterMappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GetStatusCustomFilterUseCase implements IGetStatusCustomFilterUseCase {

    @Override
    public Result<List<StatusCustomFilterDTO>> invoke() {

        return new Result.Success(
                Arrays.stream(StatusCustomFilterEnum.values()).distinct()
                        .map(StatusCustomFilterMappers::statusFilterEnumToStatusFilterDTO)
                        .collect(Collectors.toList())
        );
    }
}
