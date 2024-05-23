package datamock;

import com.robinfood.changestatusbc.entities.StatusEntity;

import java.math.BigDecimal;
import java.util.List;

public class StatusEntityDataMock {

    public List<StatusEntity> getDefaultDataList(){

        return List.of(new StatusEntity());
    }

    public StatusEntity getDefaultData(){

        return StatusEntity.builder()
                .id(1L)
                .code("CODE")
                .name("STATE")
                .order(BigDecimal.ONE)
                .parentId(1L)
                .build();
    }

    public StatusEntity getDefaultDataOrder(){

        return StatusEntity.builder()
                .id(1L)
                .code("CODE")
                .name("STATE")
                .order(BigDecimal.TEN)
                .parentId(1L)
                .build();
    }
}
