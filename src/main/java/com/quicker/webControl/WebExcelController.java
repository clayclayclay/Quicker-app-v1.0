package com.quicker.webControl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quicker.common.ConstantPath;
import com.quicker.dao.FormDao;
import com.quicker.daoImp.ActivitiesDaoImpl;
import com.quicker.database.ExcelInfo;
import com.quicker.service.*;
import com.quicker.util.GsonUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.quicker.entity.json.BasicJson;
import com.quicker.util.ExcelUtil;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web")
public class WebExcelController {


	@Autowired
	private FormDao formDao;
	@Autowired
	private FormService formService;

	private List<String> excelTitlelList = new ArrayList<String>();



	//web端excel上传控制接口
	@RequestMapping(value = "/upload",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUploadFile(@RequestParam("file") MultipartFile[] files,HttpServletRequest request)
			throws Exception {

		List<String> tableNameList = new ArrayList<String>();

		BasicJson basicJson = new BasicJson();


		ExcelUtil excelUtil = new ExcelUtil();
		System.out.println("file[]的大小为：" + files.length);
		try {
			for (int i = 0; i < files.length; i++) {

				//获取每一个MultipartFile对象
				MultipartFile fileItem = files[i];

				//获取文件名
				String fileName = fileItem.getOriginalFilename();
				System.out.println("获取到的文件名为：" + fileName);

				//调用FileUtiles工具类将文件上传到指定目录。
				FileUtils.copyInputStreamToFile(fileItem.getInputStream(),
						new File(ConstantPath.excelPath, fileName));

				//获取文件名和上传路径
				String excelName = fileName.substring(0, fileName.length() - 5);
				String excelPath = ConstantPath.excelPath + fileName;

				//上传excel信息的Dao层调用
				formDao.excelUpload(excelName, excelPath);

				//返回单张excel表处理的信息（各个字段）
				List<String> excelParseResult = excelUtil.parseExcel(fileItem.getInputStream());

				//根据处理结果的字段数来动态创建数据表（与每张excel表一一建立映射）
				String table = formDao.excelCreate(excelParseResult.size());

				//将数据表名称放入List中，为后边建立学生与excel表之间的关系（stu,excelTitle,tableName）
				tableNameList.add(table);

				//将excel表的名字添加到字段数组中，形成一个完整的excel表格
				excelParseResult.add(excelName);

				//将每张表的各个字段首先写入与之对应的动态数据表里边
				formDao.excelInit(excelParseResult,table);

				//利用Map<String,String>对象来建立起excel表名和table表的一一映射
				formDao.setTableAndExcelName(excelName,table);

				//将每个excel表的标题信息存储在excelList数组中
				excelTitlelList.add(excelName);

			}
			System.out.println("上传文件成功，准备进入init方法中");
			System.out.println("excelTitleList.size大小为():" +excelTitlelList.size());
			formDao.initStuExcelRelation(excelTitlelList,tableNameList);
			basicJson.setStatus(true);
			System.out.println("==============");
		} catch (Exception e) {
			basicJson.setStatus(false);
		}
		String json = GsonUtil.toJson(basicJson);
		System.out.println("doUploadFile接口测试返回的json为：" + json);
		return json;
	}



	//web端excel导出控制接口,跳转到查看表格填写情况页面，并可以选择一键导出。
	@RequestMapping(value = "/check_export")
	public ModelAndView checkExcel(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/pages_counselor/check_export");
		List<List<Map<String,String>>> formInfoList = formService.getStudentInfoByForm();
		modelAndView.addObject("formInfoList",formInfoList);
		modelAndView.addObject("classNums",formInfoList.get(0).size());
		return modelAndView;
	}






	//导出指定excel表格的接口
	@RequestMapping(value = "/download",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String excelDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		String[] tableList = request.getParameterValues("tableList");
		System.out.println("excelDownload:"  + tableList.length);
		BasicJson basicJson = formService.excelOutputService(tableList, response);
		if (basicJson.isStatus()) {
			System.out.println("导出成功");
		}
		else {
			System.out.println("导出失败");
		}
		String json = GsonUtil.toJson(basicJson);
		System.out.println("excelDownload接口测试返回的json为:" + json);
		return json;
	}

//	导出指定excel表格的接口
//	@RequestMapping(value = "/download1",produces = "application/json;charset=UTF-8")
//	@ResponseBody
//	public void excelDownload1(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//		request.setCharacterEncoding("utf-8");
//		String[] tableList = request.getParameterValues("table");
//		System.out.println(tableList.length);
//		System.out.println(tableList[0]);
//
//	}



//	@RequestMapping("/web/test")
//	public void test() {
//		System.out.println("~~~~");
//	}
}


