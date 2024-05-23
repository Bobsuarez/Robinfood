package com.robinfood.configurations.repositories.jpa;

import com.robinfood.configurations.dto.v1.ChannelDTO;
import com.robinfood.configurations.models.Channel;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChannelRepository extends SoftDeleteRepository<Channel, Long> {

    Page<Channel> findByNameContainingIgnoreCaseAndDeletedAtIsNull(String filter, Pageable pageable);

    @Query("SELECT DISTINCT "
        + "NEW com.robinfood.configurations.dto.v1.ChannelDTO(e.id, e.name, bcc.icon) "
        + "FROM Channel e "
        + "INNER JOIN BrandCompanyChannel bcc ON e.id = bcc.channel.id "
        + "INNER JOIN StoreBrand sb ON bcc.brandCompany.id = sb.brandCompany.id "
        + "WHERE sb.store.id = :id")
    List<ChannelDTO> findByStoreId(@Param("id") Long id);
}
