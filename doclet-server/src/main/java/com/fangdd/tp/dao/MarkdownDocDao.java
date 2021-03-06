package com.fangdd.tp.dao;

import com.fangdd.tp.doclet.pojo.entity.MarkdownDoc;
import org.springframework.stereotype.Repository;

/**
 * @author xuwenzhen
 * @date 18/1/23
 */
@Repository
public class MarkdownDocDao extends BaseDuoDocEntityDao<MarkdownDoc> {
    @Override
    protected String getCollectionName() {
        return "markdown_doc";
    }
}
