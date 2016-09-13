package com.quicker.appControl;

import com.quicker.entity.json.BasicJson;
import com.quicker.service.FormService;
import com.quicker.util.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nanguoyu on 2016/7/10.
 */
@Controller
@RequestMapping("/app")
public class AppFormNoticeController {

    @Autowired
    private FormService formService;

    //获取未处理的提醒表
    public List<String> getNotFinishedFormNotice(String id) {
        List<String> excelName = formService.getNotFinishedFormNotice(id);
        return excelName;
    }


    //获取已处理的提醒表
    public List<String> getFinishedFormNotice(String id) {
        List<String> excelName = formService.getFinishedFormNotice(id);
        return excelName;
    }

    //获取两种状态的提醒表
    @RequestMapping(value = "/getAllStageFormNotice/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllStageFormNotice(@PathVariable String id) {
        List<String> excelNot = getNotFinishedFormNotice(id);
        List<String> excelDone = getFinishedFormNotice(id);
        List<List<String>> excelList = new ArrayList<List<String>>();
        excelList.add(excelNot);
        excelList.add(excelDone);
        BasicJson basicJson = new BasicJson();
        basicJson.setStatus(true);
        basicJson.setJsonString(excelList);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getAllStageFormNotice接口返回的Json数据是:" + json);


        return json;

    }
}
