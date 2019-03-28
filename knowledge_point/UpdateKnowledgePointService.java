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
 * 修改知识点记录
 *
 * @author chenjianing
 */
@Component
public class UpdateKnowledgePointService extends ServiceHandler
{
    @Autowired
    private KnowledgePointManager knowledgePointManager;

    @ParamField(name = "id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "知识点id不能为空")
    private ThreadLocal<Long> id = new ThreadLocal<>();

    @ParamField(name = "point_name")
    private ThreadLocal<String> pointName = new ThreadLocal<>();

    @ParamField(name = "point_en_name")
    private ThreadLocal<String> pointEnName = new ThreadLocal<>();

    @ParamField(name = "point_weight")
    private ThreadLocal<Double> pointWeight = new ThreadLocal<>();

    @ParamField(name = "depot_id")
    private ThreadLocal<Long> depotId = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "knowledge_point.update";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        knowledgePointManager.update(id.get(), pointName.get(),
                pointEnName.get(), pointWeight.get(), depotId.get());
        return new ResponseBody()
                .setStatus(ResponseStatus.STATUS_SUCCESS)
                .bulid();
    }}
