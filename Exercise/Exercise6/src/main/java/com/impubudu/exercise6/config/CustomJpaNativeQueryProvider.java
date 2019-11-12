package com.impubudu.exercise6.config;

import org.springframework.batch.item.database.orm.AbstractJpaQueryProvider;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Query;

public class CustomJpaNativeQueryProvider extends AbstractJpaQueryProvider {
    private String entityClass;
    private String sqlQuery;

    public CustomJpaNativeQueryProvider() {
    }

    public Query createQuery() {
        return this.getEntityManager().createNativeQuery(this.sqlQuery, this.entityClass);
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(StringUtils.hasText(this.sqlQuery), "Native SQL query cannot be empty");
        Assert.notNull(this.entityClass, "Entity class cannot be NULL");
    }
}
