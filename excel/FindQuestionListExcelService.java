package com.jzl.cloud_exam.api.excel;

import com.jzl.cloud_data.vo.common.FileVo;
import com.jzl.cloud_exam.manager.ExcelManager;
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
 * 获取试题excel(根据条件)
 *
 * @author cxp
 */
@Component
public class FindQuestionListExcelService extends ServiceHandler
{
    @Autowired
    private ExcelManager excelManager;

    @ParamField(name = "depot_id", checkType = CheckType.NOT_NULL_AND_BLANK, message = "题库id不能为空")
    private ThreadLocal<String> depotId = new ThreadLocal<>();

    @ParamField(name = "knowledge_point")
    private ThreadLocal<String> knowledgePoint = new ThreadLocal<>();

    @ParamField(name = "question_type")
    private ThreadLocal<String> questionType = new ThreadLocal<>();

    @ParamField(name = "difficulty")
    private ThreadLocal<String> difficulty = new ThreadLocal<>();

    @ParamField(name = "key_word")
    private ThreadLocal<String> keyWord = new ThreadLocal<>();

    @ParamField(name = "is_delete")
    private ThreadLocal<String> isDelete = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "excel.question_list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        FileVo fileVo = excelManager.
                findQuestionExcel(1,
                        999999999,
                        knowledgePoint.get(),
                        questionType.get(), difficulty.get(),
                        keyWord.get(), depotId.get(), isDelete.get());
        return new ResponseBody()
                .putData("instance", fileVo)
                .setStatus(ResponseStatus.STATUS_SUCCESS)
                .bulid();
    }
}
