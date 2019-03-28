package com.jzl.cloud_exam.api.knowledge_point;

import com.jzl.cloud_exam.manager.KnowledgePointManager;
import com.jzl.cloud_framework.componet.annotation.CheckType;
import com.jzl.cloud_framework.componet.annotation.ParamField;
import com.jzl.cloud_framework.componet.body.Request;
import com.jzl.cloud_framework.componet.body.Response;
import com.jzl.cloud_framework.componet.body.ResponseBody;
import com.jzl.cloud_framework.componet.body.status.base.ResponseStatus;
import com.jzl.cloud_framework.componet.handler.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 添加知识点记录
 *
 * @author chenjianing
 */
@Component
public class AddKnowledgePointService extends ServiceHandler
{
    @Autowired
    private KnowledgePointManager knowledgePointManager;

    @ParamField(name = "point_name",checkType = CheckType.NOT_NULL_AND_BLANK,message = "知识点名称不能为空")
    private ThreadLocal<String> pointName = new ThreadLocal<>();

    @ParamField(name = "point_en_name")
    private ThreadLocal<String> pointEnName = new ThreadLocal<>();

    @ParamField(name = "point_weight")
    private ThreadLocal<Double> pointWeight = new ThreadLocal<>();

    @ParamField(name = "depot_id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "题库id不能为空")
    private ThreadLocal<Long> depotId = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "knowledge_point.add";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Long id = knowledgePointManager.add(pointName.get(),
                pointEnName.get(), pointWeight.get(), depotId.get());
        return new ResponseBody()
                .putData("id", id)
                .setStatus(ResponseStatus.STATUS_SUCCESS)
                .bulid();
    }}
