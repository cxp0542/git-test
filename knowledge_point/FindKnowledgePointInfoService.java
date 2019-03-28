package com.jzl.cloud_exam.api.knowledge_point;

import com.jzl.cloud_data.vo.exam.KnowledgePointVo;
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
 * 获取知识点记录
 *
 * @author chenjianing
 */
@Component
public class FindKnowledgePointInfoService extends ServiceHandler
{
    @Autowired
    private KnowledgePointManager knowledgePointManager;

    @ParamField(name = "id",checkType = CheckType.NOT_NULL_AND_BLANK,message = "知识点id不能为空")
    private ThreadLocal<Long> id = new ThreadLocal<>();

    @ParamField(name = "is_count_question")
    private ThreadLocal<Integer> isCountQuestion = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "knowledge_point.get";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        KnowledgePointVo knowledgePointVo = knowledgePointManager.findInfo(id.get(), isCountQuestion.get());
        return new ResponseBody()
                .putData("instance", knowledgePointVo)
                .setStatus(ResponseStatus.STATUS_SUCCESS)
                .bulid();
    }
}
