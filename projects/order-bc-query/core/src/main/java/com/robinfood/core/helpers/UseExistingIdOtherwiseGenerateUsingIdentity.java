package com.robinfood.core.helpers;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * Identity Generator Dynamic id generator
 */
public class UseExistingIdOtherwiseGenerateUsingIdentity extends IdentityGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Serializable id = session.getEntityPersister(
                null, object
        ).getClassMetadata().getIdentifier(object, session);
        
        return id != null ? id : super.generate(session, object);
    }

}
