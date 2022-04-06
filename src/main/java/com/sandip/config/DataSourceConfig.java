package com.sandip.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Configuration;


import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * 
 */
@Configuration
public class DataSourceConfig {

    @PersistenceUnit
    private EntityManagerFactory emf;

    protected SessionFactory getSessionFactory() {
        return emf.unwrap(SessionFactory.class);
    }
}
