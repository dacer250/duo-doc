package com.fangdd.tp.dao;

import com.fangdd.tp.entity.ApiEntity;
import org.springframework.stereotype.Repository;

/**
 * @author xuwenzhen
 * @date 18/1/23
 */
@Repository
public class ApiDao extends BaseDuoDocEntityDao<ApiEntity> {
    @Override
    protected String getCollectionName() {
        return "api";
    }
}
