package com.robinfood.repository.mocks;

import com.robinfood.core.entities.transactionrequestentities.BrandEntity;
import com.robinfood.core.entities.transactionrequestentities.CompanyEntity;
import com.robinfood.core.entities.transactionrequestentities.CorporateEntity;
import com.robinfood.core.entities.transactionrequestentities.DeviceEntity;
import com.robinfood.core.entities.transactionrequestentities.FinalProductArticleEntity;
import com.robinfood.core.entities.transactionrequestentities.FinalProductCategoryEntity;
import com.robinfood.core.entities.transactionrequestentities.FinalProductRequestEntity;
import com.robinfood.core.entities.transactionrequestentities.FinalProductSizeEntity;
import com.robinfood.core.entities.transactionrequestentities.FlagEntity;
import com.robinfood.core.entities.transactionrequestentities.GroupRequestEntity;
import com.robinfood.core.entities.transactionrequestentities.NewUserEntity;
import com.robinfood.core.entities.transactionrequestentities.OrderRequestEntity;
import com.robinfood.core.entities.transactionrequestentities.OriginEntity;
import com.robinfood.core.entities.transactionrequestentities.PitsEntity;
import com.robinfood.core.entities.transactionrequestentities.PortionProductEntity;
import com.robinfood.core.entities.transactionrequestentities.PortionRequestEntity;
import com.robinfood.core.entities.transactionrequestentities.StoreEntity;
import com.robinfood.core.entities.transactionrequestentities.SubmarineEntity;
import com.robinfood.core.entities.transactionrequestentities.TogoEntity;
import com.robinfood.core.entities.transactionrequestentities.TransactionRequestEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRequestEntityMocks {

    private final CompanyEntity companyEntity = new CompanyEntity(
            "COP",
            1L
    );

    private final DeviceEntity deviceEntity = new DeviceEntity(
            "1.0.0",
            1L,
            "America/Bogota",
            "1.0"
    );

    private final BrandEntity brandEntity = new BrandEntity(
            1L,
            1L,
            "RobinFood"
    );

    private final CorporateEntity corporateEntity = new CorporateEntity(
            false
    );

    private final PitsEntity pitsEntity = new PitsEntity(
            "ABC123",
            false
    );

    private final SubmarineEntity submarineEntity = new SubmarineEntity(
            false
    );

    private final TogoEntity togoEntity = new TogoEntity(
            true,
            1L
    );

    private final FlagEntity flagEntity = new FlagEntity(
            corporateEntity,
            pitsEntity,
            submarineEntity,
            togoEntity
    );

    private final OriginEntity originEntity = new OriginEntity(
            1L,
            "POSv2"
    );

    private final StoreEntity storeEntity = new StoreEntity(
            1L,
            "Store test",
            1L
    );

    private final FinalProductArticleEntity finalProductArticleEntity = new FinalProductArticleEntity(
            1L,
            1L,
            1L
    );

    private final FinalProductCategoryEntity categoryEntity = new FinalProductCategoryEntity(
            1L,
            "Category"
    );

    private final FinalProductSizeEntity sizeEntity = new FinalProductSizeEntity(
            1L,
            "MUY"
    );

    private final GroupRequestEntity groupRequestEntity = new GroupRequestEntity(
            1L,
            "Group",
            "1234"
    );

    private final PortionProductEntity portionProductEntity = new PortionProductEntity(
            1L,
            "Portion Product"
    );

    private final List<PortionRequestEntity> portionRequestEntities = Collections.singletonList(
            new PortionRequestEntity(
                    BigDecimal.ZERO,
                    1,
                    groupRequestEntity,
                    1L,
                    "Portion",
                    BigDecimal.valueOf(500.0),
                    portionProductEntity,
                    1,
                    null,
                    "1234",
                    1L,
                    1L
            )
    );

    private final List<FinalProductRequestEntity> finalProductRequestEntities = Collections.singletonList(
            new FinalProductRequestEntity(
                    finalProductArticleEntity,
                    brandEntity,
                    categoryEntity,
                    Collections.emptyList(),
                    Collections.emptyList(),
                    1L,
                    "image.png",
                    "Product",
                    portionRequestEntities,
                    1,
                    Collections.emptyList(),
                    sizeEntity,
                    "1234",
                    Collections.emptyList(),
                    BigDecimal.valueOf(10000.0),
                    BigDecimal.valueOf(100.0)
            )
    );

    private final List<OrderRequestEntity> orderRequestEntities = Collections.singletonList(
            new OrderRequestEntity(
                    1L,
                    brandEntity,
                    BigDecimal.ZERO,
                    1L,
                    Collections.emptyList(),
                    Collections.emptyList(),
                    finalProductRequestEntities,
                    flagEntity,
                    BigDecimal.ZERO,
                    false,
                    1L,
                    "Test notes",
                    originEntity,
                    true,
                    1L,
                    new ArrayList<>(),
                    storeEntity,
                    BigDecimal.valueOf(10000.0),
                    BigDecimal.valueOf(10000.0),
                    BigDecimal.valueOf(10000.0),
                    BigDecimal.valueOf(10000.0),
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    "epknvcdbwwby",
                    "ab5e2048-e41e-431b-8590-57ce941fc258",
                    1L,
                    BigDecimal.valueOf(100.0)
            )
    );

    private final NewUserEntity userEntity = new NewUserEntity(
            "test@test.com",
            1L,
            "Test",
            "9999999999",
            "User",
            "57"
    );

    public final TransactionRequestEntity transactionRequestEntity = new TransactionRequestEntity(
            false,
            companyEntity,
            null,
            deviceEntity,
            3L,
            1L,
            orderRequestEntities,
            new OrderFiscalIdentifierEntityMock().getDefaultData(),
            false,
            Collections.emptyList(),
            BigDecimal.valueOf(10000.0),
            userEntity
    );
}
