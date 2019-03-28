package com.jzl.cloud_exam.api.knowledge_point;

import com.jzl.cloud_data.vo.exam.DeleteInfoVo;
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
import java.util.List;

/**
 * 删除知识点记录
 *
 * @author chenjianing
 */
@Component
public class DeleteKnowledgePointService extends ServiceHandler
{
    @Autowired
    private KnowledgePointManager knowledgePointManager;

    @ParamField(name = "ids",checkType = CheckType.NOT_NULL_AND_BLANK,message = "知识点id不能为空")
    private ThreadLocal<String> ids = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "knowledge_point.delete";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        List<DeleteInfoVo> deleteInfoVos = knowledgePointManager.delete(ids.get());
        return new ResponseBody()
                .putData("instance", deleteInfoVos)
                .setStatus(ResponseStatus.STATUS_SUCCESS)
                .bulid();
    }
}