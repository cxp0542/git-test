package com.jzl.cloud_exam.api.excel;

import com.jzl.cloud_exam.manager.ExcelManager;
import com.jzl.cloud_exam.manager.QuestionManager;
import com.jzl.cloud_framework.componet.annotation.CheckType;
import com.jzl.cloud_framework.componet.annotation.ParamField;
import com.jzl.cloud_framework.componet.body.Request;
import com.jzl.cloud_framework.componet.body.Response;
import com.jzl.cloud_framework.componet.body.ResponseBody;
import com.jzl.cloud_framework.componet.body.status.base.ResponseStatus;
import com.jzl.cloud_framework.componet.handler.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create by xiao
 * DateTime:2019-03-12 18:54
 */
@Component
public class ImportQuestionExcelService extends ServiceHandler
{
    @Autowired
    ExcelManager excelManager;

    @ParamField(name = "file", checkType = CheckType.NOT_NULL, message = "excel文件不能为空")
    private ThreadLocal<MultipartFile> excel = new ThreadLocal<>();

    @ParamField(name = "depot_id", checkType = CheckType.NOT_NULL_AND_BLANK, message = "题库id不能为空")
    private ThreadLocal<String> depotId = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "excel.upload_question_list";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .putData("instance", excelManager.importQuestionByExcel(Long.valueOf(depotId.get()), excel.get()))
           //     .putData("instance", questionManager.importQuestionByExcel(31321L, excel.get()))
                .setStatus(ResponseStatus.STATUS_SUCCESS)
                .bulid();
    }
}
