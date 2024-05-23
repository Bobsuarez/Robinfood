package com.robinfood.taxes.services.impl;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.models.RuleType;
import com.robinfood.taxes.repositories.RuleTypeRepository;
import com.robinfood.taxes.services.RuleTypeService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class RuleTypeServiceImpl implements RuleTypeService {

    private final RuleTypeRepository ruleTypeRepository;

    public RuleTypeServiceImpl(RuleTypeRepository ruleTypeRepository) {
        this.ruleTypeRepository = ruleTypeRepository;
    }

    @BasicLog
    @Override
    public List<RuleType> findAll() {
        return ruleTypeRepository.findAll();
    }
}
