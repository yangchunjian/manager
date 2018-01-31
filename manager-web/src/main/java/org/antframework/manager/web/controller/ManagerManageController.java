/* 
 * 作者：钟勋 (e-mail:zhongxunking@163.com)
 */

/*
 * 修订记录:
 * @author 钟勋 2018-01-28 14:44 创建
 */
package org.antframework.manager.web.controller;

import org.antframework.common.util.facade.EmptyResult;
import org.antframework.manager.facade.api.ManagerService;
import org.antframework.manager.facade.enums.ManagerType;
import org.antframework.manager.facade.order.*;
import org.antframework.manager.facade.result.QueryManagerResult;
import org.antframework.manager.web.common.ManagerSessionAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员管理controller
 */
@RestController
@RequestMapping("/manager/manage")
public class ManagerManageController {
    @Autowired
    private ManagerService managerService;

    /**
     * 新增管理员
     *
     * @param managerId 管理员id（必填）
     * @param type      类型（必填）
     * @param name      名称（必填）
     * @param password  密码（必填）
     * @return 新增管理员结果
     */
    @RequestMapping("/add")
    public EmptyResult add(String managerId, ManagerType type, String name, String password) {
        ManagerSessionAccessor.assertAdmin();
        AddManagerOrder order = new AddManagerOrder();
        order.setManagerId(managerId);
        order.setType(type);
        order.setName(name);
        order.setPassword(password);

        return managerService.addManager(order);
    }

    /**
     * 删除管理员
     *
     * @param managerId 被删除的管理员id（必填）
     * @return 删除管理员结果
     */
    @RequestMapping("/delete")
    public EmptyResult delete(String managerId) {
        ManagerSessionAccessor.assertAdmin();
        DeleteManagerOrder order = new DeleteManagerOrder();
        order.setManagerId(managerId);

        return managerService.deleteManager(order);
    }

    /**
     * 修改管理员密码
     *
     * @param managerId   被修改的管理员id（必填）
     * @param newPassword 新密码（必填）
     * @return 修改结果
     */
    @RequestMapping("/modifyPassword")
    public EmptyResult modifyPassword(String managerId, String newPassword) {
        ManagerSessionAccessor.assertAdminOrMyself(managerId);
        ModifyManagerPasswordOrder order = new ModifyManagerPasswordOrder();
        order.setManagerId(managerId);
        order.setNewPassword(newPassword);

        return managerService.modifyManagerPassword(order);
    }

    /**
     * 修改管理员类型
     *
     * @param managerId 被修改的管理员id（必填）
     * @param newType   新类型（必填）
     * @return 修改结果
     */
    @RequestMapping("/modifyType")
    public EmptyResult modifyType(String managerId, ManagerType newType) {
        ManagerSessionAccessor.assertAdmin();
        ModifyManagerTypeOrder order = new ModifyManagerTypeOrder();
        order.setManagerId(managerId);
        order.setNewType(newType);

        return managerService.modifyManagerType(order);
    }

    /**
     * 修改名称
     *
     * @param managerId 被修改的管理员id（必填）
     * @param newName   新名称（必填）
     * @return 修改结果
     */
    @RequestMapping("/modifyName")
    public EmptyResult modifyName(String managerId, String newName) {
        ManagerSessionAccessor.assertAdminOrMyself(managerId);
        ModifyManagerNameOrder order = new ModifyManagerNameOrder();
        order.setManagerId(managerId);
        order.setNewName(newName);

        return managerService.modifyManagerName(order);
    }

    /**
     * 查询管理员
     *
     * @param pageNo    页码（必填）
     * @param pageSize  每页大小（必填）
     * @param managerId 管理员id（选填）
     * @param type      类型（选填）
     * @param name      名称（选填）
     * @return 查询结果
     */
    @RequestMapping("/query")
    public QueryManagerResult query(int pageNo, int pageSize, String managerId, ManagerType type, String name) {
        ManagerSessionAccessor.assertAdmin();
        QueryManagerOrder order = new QueryManagerOrder();
        order.setPageNo(pageNo);
        order.setPageSize(pageSize);
        order.setManagerId(managerId);
        order.setType(type);
        order.setName(name);

        return managerService.queryManager(order);
    }
}