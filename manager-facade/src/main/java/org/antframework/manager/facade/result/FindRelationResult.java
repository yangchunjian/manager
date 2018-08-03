/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 23:52 创建
 */
package org.antframework.manager.facade.result;

import org.antframework.common.util.facade.AbstractResult;
import org.antframework.manager.facade.info.RelationInfo;

/**
 * 查找关系result
 */
public class FindRelationResult extends AbstractResult {
    // 关系（null表示不存在该关系）
    private RelationInfo relation;

    public RelationInfo getRelation() {
        return relation;
    }

    public void setRelation(RelationInfo relation) {
        this.relation = relation;
    }
}
