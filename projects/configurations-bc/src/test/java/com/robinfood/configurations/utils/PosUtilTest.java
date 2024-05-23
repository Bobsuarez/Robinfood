package com.robinfood.configurations.utils;

import com.robinfood.configurations.dto.v1.UpdatePosDTOMock;
import com.robinfood.configurations.dto.v1.models.CreatePosDTO;
import com.robinfood.configurations.models.Pos;
import com.robinfood.configurations.models.Resolution;
import com.robinfood.configurations.samples.PosSample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class PosUtilTest {

    @Test
    void testGeneratePosFromPosNewDTOStatusTrue() {

        Pos pos = PosUtil.buildFromPosDTOToPos(CreatePosDTO.builder()
                .status(true)
                .build());

        Assertions.assertNotNull(pos);
    }

    @Test
    void testGeneratePosFromPosNewDTOStatusFalse() {

        Pos pos = PosUtil.buildFromPosDTOToPos(CreatePosDTO.builder()
                .status(false)
                .build());

        Assertions.assertNotNull(pos);
    }

    @Test
    void testUpdatePosFieldStatusTrue() {

        PosUtil.buildFromUpdateDTOToPos(
                UpdatePosDTOMock.getUpdatePosDTOStatusTrue()
        );
    }

    @Test
    void testUpdatePosFieldStatusFalse() {

        PosUtil.buildFromUpdateDTOToPos(
                UpdatePosDTOMock.getUpdatePosDTOStatusFalse()
        );
    }

    @Test
    void tesPosFieldResolutions() {

        Pos pos = PosSample.getPosWithResolutions();
        PosUtil.buildPosResponse(pos);
    }

    @Test
    void tesPosFieldResolutionsStatusZero() {

        Pos pos = PosSample.getPosWithResolutions();
        Resolution resolution = pos.getResolutionList().get(0);
        resolution.setStatus(0);
        PosUtil.buildPosResponse(pos);
    }

    @Test
    void testPosField_Not_Resolutions() {

        Pos pos = PosSample.getPosWithResolutions();
        pos.setResolutionList(new ArrayList<>());
        PosUtil.buildPosResponse(pos);
    }
}
