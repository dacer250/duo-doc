package com.fangdd.tp.dao;

import com.fangdd.tp.entity.OAuth2Conf;
import org.springframework.stereotype.Repository;

/**
 * @auth ycoe
 * @date 18/11/28
 */
@Repository
public class OAuth2ConfDao extends TpDocBaseEntityDao<OAuth2Conf> {
    /**
     * 获取Collection名称
     *
     * @return
     */
    @Override
    protected String getCollectionName() {
        return "oauth2_conf";
    }
}
