/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-25 21:34 创建
 */
package org.antframework.manager.biz.service;

import lombok.AllArgsConstructor;
import org.antframework.common.util.facade.EmptyResult;
import org.antframework.common.util.query.annotation.QueryParams;
import org.antframework.manager.dal.dao.RelationDao;
import org.antframework.manager.dal.entity.Relation;
import org.antframework.manager.facade.order.DeleteRelationsOrder;
import org.bekit.service.annotation.service.Service;
import org.bekit.service.annotation.service.ServiceExecute;
import org.bekit.service.engine.ServiceContext;

import java.util.List;

/**
 * 删除关系服务
 */
@Service(enableTx = true)
@AllArgsConstructor
public class DeleteRelationsService {
    // 关系dao
    private final RelationDao relationDao;

    @ServiceExecute
    public void execute(ServiceContext<DeleteRelationsOrder, EmptyResult> context) {
        DeleteRelationsOrder order = context.getOrder();

        List<Relation> relations = relationDao.query(QueryParams.parse(order));
        for (Relation relation : relations) {
            relationDao.delete(relation);
        }
    }
}
