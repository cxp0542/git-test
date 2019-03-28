package com.jzl.cloud_exam.api.excel;

import com.jzl.cloud_exam.manager.ExcelManager;
import com.jzl.cloud_exam.manager.PlanPerformanceManager;
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
 * 获取考生成绩汇总信息excel
 *
 * @author cxp
 */
@Component
public class FindPersonPerformanceExcelService extends ServiceHandler
{
    @Autowired
    private ExcelManager excelManager;

    @ParamField(name = "plan_id", checkType = CheckType.NOT_NULL_AND_BLANK, message = "考试计划id不能为空")
    private ThreadLocal<Long> planId = new ThreadLocal<>();

    @Override
    public String supportServiceName()
    {
        return "excel.plan_performance";
    }

    @Override
    public Response handle(Request request) throws Exception
    {
        return new ResponseBody()
                .putData("instance", excelManager
                        .findPersonPerformanceExcel(planId.get()))
                .setStatus(ResponseStatus.STATUS_SUCCESS)
                .bulid();
    }
}
