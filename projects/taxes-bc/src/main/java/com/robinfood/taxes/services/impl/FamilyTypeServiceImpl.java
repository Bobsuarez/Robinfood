package com.robinfood.taxes.services.impl;

import com.robinfood.taxes.annotations.BasicLog;
import com.robinfood.taxes.models.FamilyType;
import com.robinfood.taxes.repositories.FamilyTypeRepository;
import com.robinfood.taxes.services.FamilyTypeService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FamilyTypeServiceImpl implements FamilyTypeService {

    private final FamilyTypeRepository familyTypeRepository;

    public FamilyTypeServiceImpl(FamilyTypeRepository familyTypeRepository) {
        this.familyTypeRepository = familyTypeRepository;
    }

    @BasicLog
    @Override
    public List<FamilyType> findAll() {
        return familyTypeRepository.findAll();
    }
}
