package com.robinfood.repository.deductions;

import java.util.Map;

public interface IDeductionsRemoteDataSource {
    Map<Long,String> getAllActiveTypeDeductions(String token);

}
