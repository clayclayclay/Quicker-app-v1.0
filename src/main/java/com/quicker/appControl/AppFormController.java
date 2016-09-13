package com.quicker.appControl;

import com.quicker.dao.FormDao;
import com.quicker.entity.json.BasicJson;
import com.quicker.entity.json.Errmsg;
import com.quicker.service.FormService;
import com.quicker.util.GsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nanguoyu on 2016/7/7.
 */
@Controller
@RequestMapping("/app")
public class AppFormController {


    @Autowired
    private FormDao excelDao;
    @Autowired
    private FormService excelService;




    //获取未完成表格列表
    public List<String> getUnfinishedForms(String id) {
        List<String> excelUnfinishedName = excelService.getUnfinishedForms(id);
        return excelUnfinishedName;
    }



    //获取已完成表格列表
    public List<String> getFinishedForms(String id) {
        List<String> excelCollectedName = excelService.getFinishedForms(id);
        return excelCollectedName;
    }


    //获取已收藏表格列表
    public List<String> getCollectedForms(String id) {
        List<String> excelFinishedName = excelService.getCollectedForms(id);
        return excelFinishedName;
    }



    //表格填写请求所有状态的表格列表（整合接口）
    @RequestMapping(value = "/getAllStageForms/{id}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAllStageForms(@PathVariable String id) {
        List<String> excelUnfinishedName = getUnfinishedForms(id);
        List<String> excelFinishedName = getFinishedForms(id);
        List<String> excelCollectedName = getCollectedForms(id);
        List<List<String>> allStageFormsList = new ArrayList<List<String>>();
        allStageFormsList.add(excelUnfinishedName);
        allStageFormsList.add(excelFinishedName);
        allStageFormsList.add(excelCollectedName);
        BasicJson basicJson = new BasicJson();
        basicJson.setJsonString(allStageFormsList);
        basicJson.setStatus(true);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getAllStageForms接口发出的json字符串是:" + json);
        return json;
    }


    //获取一张未填表格（包含标题和各个字段）
    @RequestMapping(value = "/getOneUnfinishedForm/{formTitle}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getOneUnfinishedForm(@PathVariable String formTitle) throws UnsupportedEncodingException {
        BasicJson basicJson;
        String formTitleGB = new String(formTitle.getBytes("GB2312"), "GB2312");
        System.out.println(formTitleGB);
        basicJson = excelService.getOneUnfinishedForm(formTitleGB);
        String json = GsonUtil.toJson(basicJson);
        System.out.println("getOneUnfinishedForm接口得到的json为：" + json);
        return json;
    }


    //提交已填写好的表格
    @RequestMapping(value = "/submit/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    //通过@RequestBody注解直接将前端传送过来的json输入流转化成String类型
    public String excelSubmit(@PathVariable String id, HttpServletRequest request) throws IOException {
        String stuId = id;
        BasicJson basicJson = new BasicJson();
        Errmsg errmsg = new Errmsg();
        request.setCharacterEncoding("utf-8");
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        String line = null;
        StringBuffer sb = new StringBuffer();
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        String json = sb.toString();

        System.out.println("-----------------");
        System.out.println(json);
        System.out.println("-----------------");

//        List<String> excelList;
        List<String> excelList = new ArrayList<String>();
        //下边三行代码将json字符串转化为list对象
//        JSONObject jsonObject = new JSONObject().fromObject(json);
//        JSONArray jsonArray = jsonObject.getJSONArray("formInfo");
//        excelList = JSONArray.toList(jsonArray, List.class);


        System.out.println(excelList);

        //将填写好的信息对象存储到数据库
        Boolean status = excelService.excelWrite(excelList, stuId);
        if (status) {
            basicJson.setStatus(true);
            errmsg.setDescription("提交成功");
            basicJson.setErrorMsg(errmsg);
            System.out.println("写入成功");
        } else {
            basicJson.setStatus(false);
            errmsg.setDescription("提交失败");
            basicJson.setErrorMsg(errmsg);
            System.out.println("写入失败");
        }
        json = GsonUtil.toJson(basicJson);
        System.out.println("excelSubmit接口得到的json为：" + json);
        return json;

    }

    //收藏已经填写好的表格
    @RequestMapping(value = "/collectForm",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String collectForm(HttpServletRequest request) {
        BasicJson basicJson;
        try {
            request.setCharacterEncoding("utf-8");
            String stuId = request.getParameter("id");
            System.out.println("id" + stuId);
            String formTitle = request.getParameter("formTitle");
            System.out.println("formTitle" + formTitle);
            basicJson = excelService.excelChangeToCollected(stuId,formTitle);
            String json = GsonUtil.toJson(basicJson);
            System.out.println("collectForm接口得到的json为：" + json);
            return json;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }



    //获取一张已填(已收藏)表格
    @RequestMapping(value = "/getOneFinishedForm",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getOneFinishedForm(HttpServletRequest request) {
        BasicJson basicJson;
        try {
            request.setCharacterEncoding("utf-8");
            String stuId = request.getParameter("id");
            String formTitle = request.getParameter("formTitle");
            basicJson = excelService.getOneFinishedForm(stuId,formTitle);
            String json = GsonUtil.toJson(basicJson);
            System.out.println("getOneFinishedForm接口的json格式为：" + json);
            return json;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }



    //测试java对象对应的json格式
//    @RequestMapping(value = "/getTest",produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public void test() {
//        BasicJson basicJson = new BasicJson();
//        List<String> listTest = new ArrayList<String>();
//        listTest.add("one");
//        listTest.add("two");
//        listTest.add("three");
//        basicJson.setJsonString(listTest);
//        String json1 = GsonUtil.toJson(basicJson);
//        System.out.println(json1);
//
//        Map<String,String > mapTest = new HashMap<String,String>();
//        mapTest.put("item1","apple");
//        mapTest.put("item2","banana");
//        mapTest.put("item3","pear");
//        basicJson.setJsonString(mapTest);
//        String json2 = GsonUtil.toJson(basicJson);
//        System.out.println(json2);
//        List<Map<String,String>> listMapTest = new ArrayList<Map<String, String>>();
//
//        Map<String,String > mapTest1 = new HashMap<String,String>();
//        mapTest1.put("item1","apple");
//        mapTest1.put("item2","banana");
//        mapTest1.put("item3","pear");
//        listMapTest.add(mapTest1);
//        listMapTest.add(mapTest1);
//        listMapTest.add(mapTest1);
//        basicJson.setJsonString(listMapTest);
//        String json3 = GsonUtil.toJson(basicJson);
//        System.out.println(json3);
//
//        List<List<String>> lists = new ArrayList<List<String>>();
//        List<String> list1 = new ArrayList<String>();
//        list1.add("list1.1");
//        list1.add("list1.2");
//        list1.add("list1.3");
//        lists.add(list1);
//
//        List<String> list2 = new ArrayList<String>();
//        list2.add("list2.1");
//        list2.add("list2.2");
//        list2.add("list2.3");
//        lists.add(list2);
//
//        List<String> list3 = new ArrayList<String>();
//        list3.add("list3.1");
//        list3.add("list3.2");
//        list3.add("list3.3");
//        lists.add(list3);
//
//        basicJson.setJsonString(lists);
//        String json4 = GsonUtil.toJson(basicJson);
//        System.out.println(json4);
//
//    }


}
