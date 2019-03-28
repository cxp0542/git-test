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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * 获取知识点记录列表
 *
 * @author chenjianing
 */
@Component
public class FindKnowledgePointListService extends ServiceHandler
{
    @Autowired
    private KnowledgePointManager knowledgePointManager;

    @ParamField(name = "page",checkType = CheckType.NOT_NULL_AND_BLANK,message = "查询页数不能为空")
    private ThreadLocal<Integer> page = new ThreadLocal<>();

    @ParamField(name = "page_size",checkType = CheckType.NOT_NULL_AND_BLANK,message = "查询每页条数不能为空")
    private ThreadLocal<Integer> pageSize = new ThreadLocal<>();

    @ParamField(name = "keyword")
    private ThreadLocal<String> keyword = new ThreadLocal<>();

    @ParamField(name = "depot_id")
    private ThreadLocal<Long> depotId = new ThreadLocal<>();

    @ParamField(name = "is_count_question")
    private ThreadLocal<Integer> isCountQuestion = new ThreadLocal<>();

    @ParamField(name = "is_delete")
    private ThreadLocal<String> isDelete = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "knowledge_point.list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        Page<KnowledgePointVo> knowledgePointVoPage = knowledgePointManager
                .findList(page.get(), pageSize.get(), keyword.get(), depotId.get(), isCountQuestion.get(), isDelete.get());
        return new ResponseBody()
                .putData("instance",knowledgePointVoPage.getContent())
                .putData("total_page",knowledgePointVoPage.getTotalPages())
                .putData("total_size",knowledgePointVoPage.getTotalElements())
                .setStatus(ResponseStatus.STATUS_SUCCESS)
                .bulid();
    }
}
