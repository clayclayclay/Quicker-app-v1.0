package com.quicker.daoImp;

import java.text.SimpleDateFormat;
import java.util.*;

import com.quicker.dao.UserDao;
import com.quicker.database.*;
import com.quicker.service.FormService;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.quicker.dao.FormDao;

@Repository
public class FormDaoImpl implements FormDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FormService formService;


    //获取session
    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }


    //上传excel表
    public void excelUpload(String excelName, String excelPath) {
        Session session = getSession();
        ExcelInfo excel = new ExcelInfo();

        excel.setExcelName(excelName);
        excel.setExcelPath(excelPath);
        String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        excel.setUploadTime(time);
        session.save(excel);
    }



        //获得excelInfo表信息（包含excel的标题，存储地址）
        public List<ExcelInfo> excelGet() {
            Session session = getSession();
            String hql = "from ExcelInfo excel";
            List<ExcelInfo> list = session.createQuery(hql).list();
            System.out.println("查询数据库得知，表格总数量为:  " + list.size());
            return list;

    }


    //动态创建excel表(包括id,flag0,flag1,flag2,...stu_id)
    public String excelCreate(int num) {
        Session session = getSession();
        StringBuffer str = new StringBuffer();
        int id;
        Random rand = new Random();
        String table = "table" + rand.nextInt(1000);

        str.append("CREATE TABLE " + table);
        str.append(" ( id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT");
        for (int i = 1; i < num + 1; i++) {
            str.append(",flag" + String.valueOf(i) + " VARCHAR(20) NOT NULL");
        }
        str.append(",stu_id VARCHAR(20)");
        str.append(");");
        System.out.println(str.toString());
        SQLQuery query = session.createSQLQuery(str.toString());
        query.executeUpdate();
        return table;
    }


    //初始化数据表信息（将excel表的各个字段写入到数据表的第一行）
    @Override
    public void excelInit(List<String> excel, String tableName) {
        Session session = getSession();
        StringBuffer str = new StringBuffer();
        String table = tableName;
        int length = excel.size();
        str.append("INSERT INTO " + table + "(");
        str.append("flag1");
        for (int i = 2; i < length; i++) {
            str.append(",flag" + i);
        }
        str.append(") VALUES(");
        str.append("'" + excel.get(0) + "'");
        for (int i = 1; i < length - 1; i++) {
            str.append(",'" + excel.get(i) + "'");
        }
        str.append(");");
        System.out.println(str.toString());
        SQLQuery sql = session.createSQLQuery(str.toString());
        sql.executeUpdate();
    }


    //插入excel表填写的数据
    public void excelWrite(List<String> excel, String tableName, String stuId) {
        Session session = getSession();
        StringBuffer str = new StringBuffer();
        String table = tableName;
        int length = excel.size();
        str.append("INSERT INTO " + table + "(");
        str.append("flag1");
        for (int i = 2; i < length; i++) {
            str.append(",flag" + i);
        }
        str.append(", stu_id");
        str.append(") VALUES(");

        str.append("'" + excel.get(0) + "'");
        for (int i = 1; i < length - 1; i++) {
            str.append(",'" + excel.get(i) + "'");
        }
        str.append(",'" + stuId + "'");
        str.append(");");
        System.out.println(str.toString());
        SQLQuery sql = session.createSQLQuery(str.toString());
        sql.executeUpdate();
    }


    //获取单个未填表格信息
    public List<Map<String, String>> getUnfinishedOrAllInfo(String table) {
        Session session = getSession();

        String sql = "SELECT * FROM " + table + ";";
        SQLQuery query = session.createSQLQuery(sql);

        List<Map<String, String>> list;

		/*通过query.setResultTransformet(Transformers.ALIAS_TO_ENTITY_MAP).list()
          将取得的数据转化成List<Map<Object,Objct>>类型，注意Map的key值类型不一样都一样
		  比如自增的主键为Integer，而其他字段则为String。所以如果调用Itertor来迭代Map的时候，
		  注意类型的不一样。
		 */
        list = (List) query.setResultTransformer(
                Transformers.ALIAS_TO_ENTITY_MAP).list();
        return list;
    }

    //获取若干张表格已填信息
    @Override
    public List<List<Map<String, String>>> getSomeFinishedInfo(List<String> tableList) {
        Session session = getSession();
        List<List<Map<String,String>>> someTablesInfoList = new ArrayList<List<Map<String, String>>>();
        try {
            for (int i = 0; i < tableList.size(); i++) {
                String tableName = tableList.get(i);
                String hql = "SELECT * FROM " + tableName + ";";
                SQLQuery sqlQuery = session.createSQLQuery(hql);
                List<Map<String,String>> oneTableInfo = (List) sqlQuery.setResultTransformer(
                        Transformers.ALIAS_TO_ENTITY_MAP).list();
                someTablesInfoList.add(oneTableInfo);
            }
            return someTablesInfoList;
        } catch (Exception e) {
            return null;
        }

    }


    //获取单个已填表格信息
    public List<List<Map<String, String>>> getFinishedInfo(String table, String stuId) {
        Session session = getSession();

        //获取用户填写的数据
        String sql = "SELECT * FROM " + table + " WHERE stu_id=:stuId";
        SQLQuery query = session.createSQLQuery(sql);

        List<Map<String, String>> excelValue;
        List<Map<String, String>> excelField;
        List<List<Map<String, String>>> excelFieldAndValue = new ArrayList<List<Map<String, String>>>();

		/*通过query.setResultTransformet(Transformers.ALIAS_TO_ENTITY_MAP).list()
          将取得的数据转化成List<Map<Object,Objct>>类型，注意Map的key值类型不一样都一样
		  比如自增的主键为Integer，而其他字段则为String。所以如果调用Itertor来迭代Map的时候，
		  注意类型的不一样。
		 */
        excelValue = (List) query.setParameter("stuId", stuId)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();


        //获取该表格的字段名
        String sql1 = "SELECT * FROM " + table + " WHERE id=1";
        query = session.createSQLQuery(sql1);
        excelField = (List) query
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();


        excelFieldAndValue.add(excelField);
        excelFieldAndValue.add(excelValue);
        return excelFieldAndValue;
    }


    //测试！！！！！
    public void getFinishedInfo111(String table, String stuId) {
        Session session = getSession();

        //获取用户填写的数据
        String sql = "SELECT * FROM " + table + " WHERE stu_id=:stuId";
        SQLQuery query = session.createSQLQuery(sql);

        List<Map<String, String>> list1;
        List<Map<String, String>> list2;
        List<List<Object>> list3 = new ArrayList<List<Object>>();
		/*通过query.setResultTransformet(Transformers.ALIAS_TO_ENTITY_MAP).list()
		  将取得的数据转化成List<Map<Object,Objct>>类型，注意Map的key值类型不一样都一样
		  比如自增的主键为Integer，而其他字段则为String。所以如果调用Itertor来迭代Map的时候，
		  注意类型的不一样。
		 */
        list1 = (List) query.setParameter("stuId", stuId)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();


        System.out.print("===获取的表格字段为===:    ");
        for (int i = 0; i < list1.get(0).size() - 1; i++) {
            String key = "flag" + (i + 1);
            System.out.print(list1.get(0).get(key));
        }

        System.out.println();


        //获取该表格的字段名
        String sql1 = "SELECT * FROM " + table + " WHERE id=1";
        query = session.createSQLQuery(sql1);
        list2 = (List) query
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();

        System.out.print("===获取的表格各表格的字段值为===:    ");
        for (int i = 0; i < list2.get(0).size() - 1; i++) {
            String key = "flag" + (i + 1);
            System.out.print(list2.get(0).get(key));
        }

//		list3.add(list2);
//		list3.add(list1);
//		return list3;
    }


    //建立数据表名与excel表名的映射关系
    public void setTableAndExcelName(String excelName, String table) {
        Session session = getSession();
        String sql = "INSERT INTO excel_and_table(table_names,excel_name) VALUES(?,?)";
        SQLQuery query = session.createSQLQuery(sql);

        //注意下标是从0开始
        query.setString(0, table);
        query.setString(1, excelName);
        query.executeUpdate();
    }


    //获取数据表名与excel表名的映射关系
    public List<ExcelAndTable> getTableAndExcelName() {
        Session session = getSession();
        String sql = "SELECT * FROM excel_and_table";
        SQLQuery query = session.createSQLQuery(sql).addEntity(ExcelAndTable.class);
        List<ExcelAndTable> list = query.list();
        return list;
    }


    //通过excel表(excelTitle)的名称来获取数据表的名称(tableName)
    @Override
    public String getTableNameByExcel(String excelTitle) {
        Session session = getSession();
        String hql = "FROM ExcelAndTable e WHERE e.excelName=:excelTitle";
        List<ExcelAndTable> tableName = session.createQuery(hql).setParameter("excelTitle",excelTitle).list();
        if (tableName.size() > 0) {
            return tableName.get(0).getTableName();
        } else {
            return null;
        }
    }

    ////获取所有excel的数据表名
    @Override
    public List<ExcelAndTable> getTables() {
        Session session = getSession();
        String hql = "FROM ExcelAndTable";
        List<ExcelAndTable> excelAndTableList = session.createQuery(hql).list();
        if (excelAndTableList.size() > 0) {
            return excelAndTableList;
        }
        else {
            return null;
        }
    }


    //初始化学生与表格之间的关系：1.未填写，2，未收藏，3，未通知。
    @Override
    public void initStuExcelRelation(List<String> excelTitleList, List<String> tableNameList) {
        System.out.println("进入Init方法中:");
        Session session = getSession();
        StuExcel stuExcel;
        List<StudentInfo> studentList = userDao.getAllUser();
        System.out.println("studentList的大小为：" + studentList.size());
        for (int i = 0; i < excelTitleList.size(); i++) {
            String excelTitle = excelTitleList.get(i);
            String tableName = tableNameList.get(i);
            for (int j = 0; j < studentList.size(); j++) {
                String stuId = studentList.get(i).getUsername();
                System.out.println("stuId为：" + stuId);
                stuExcel = new StuExcel();
                stuExcel.setExcelName(excelTitle);
                stuExcel.setStuId(stuId);
                stuExcel.setTableName(tableName);
                System.out.println("~~~~~~");
                session.save(stuExcel);
                System.out.println("-------------");
            }
            excelTitleList.remove(i);
        }
        session.flush();
        System.out.println("init完成");
    }


    //获取还未填写表格的列表
    @Override
    public List<StuExcel> getUnfinishedForms(String id) {
        Session session = getSession();
        String hql = "FROM StuExcel s WHERE s.stuId=:id and s.isFinished=0";
        Query query = session.createQuery(hql);
        query.setString("id", id);
        List<StuExcel> stuExcelList = query.list();
        return stuExcelList;
    }


    //获取已经填写的表格的列表
    @Override
    public List<StuExcel> getFinishedForms(String id) {
        Session session = getSession();
        String hql = "FROM StuExcel s WHERE s.stuId=:id and s.isFinished=1";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<StuExcel> stuExcelList = query.list();
        System.out.println(stuExcelList.size());
        return stuExcelList;
    }


    //获取已收藏表格列表
    @Override
    public List<StuExcel> getCollectedForms(String id) {
        Session session = getSession();
        String hql = "FROM StuExcel s WHERE s.stuId=:id and s.isCollected=1";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<StuExcel> stuExcelList = query.list();
        return stuExcelList;
    }


    //获取我的提醒（未处理）表格列表
    @Override
    public List<StuExcel> getNotFinishedFormNotice(String id) {
        Session session = getSession();
        String hql = "FROM StuExcel s WHERE s.stuId=:id and s.isReminded=1 and s.isFinished=0";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<StuExcel> stuExcelList = query.list();
        return stuExcelList;
    }

    //获取我的提醒（已处理）表格列表
    @Override
    public List<StuExcel> getFinishedFormNotice(String id) {
        Session session = getSession();
        String hql = "FROM StuExcel s WHERE s.stuId=:id and s.isReminded=1 and s.isFinished=1";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<StuExcel> stuExcelList = query.list();
        return stuExcelList;
    }


    //将表格的状态置为“已填”
    @Override
    public void excelChangeToWrited(String table, String stuId) {
        Session session = getSession();
        String hql = "FROM StuExcel s WHERE s.tableName=:table and s.stuId=:stuId";
        Query query = session.createQuery(hql);
        query.setParameter("table", table);
        query.setParameter("stuId", stuId);
        List<StuExcel> list = query.list();
        if (list.size() > 0) {
            list.get(0).setIsFinished(1);
            session.saveOrUpdate(list.get(0));
            session.flush();
        } else {
            System.out.println("状态转换出错！");
        }
    }


    //将表格的状态置为“已收藏”
    @Override
    public boolean excelChangeToCollected(String stuId, String tableName) {
        Session session = getSession();
        String hql = "FROM StuExcel s WHERE s.tableName=:table and s.stuId=:stuId";
        Query query = session.createQuery(hql);
        query.setParameter("table", tableName);
        query.setParameter("stuId", stuId);
        List<StuExcel> list = query.list();
        if (list.size() > 0) {
            list.get(0).setIsCollected(1);
            session.saveOrUpdate(list.get(0));
            session.flush();
            return true;
        } else {
            System.out.println("状态转换出错！");
            return false;
        }
    }


    //统计填写表格信息（包括班级，人数，上传日期等）
    @Override
    public List<List<Map<String,String>>>  getStudentInfoByForm() {
        Session session = getSession();

        List<List<Map<String,String>>> formAllClassCheckOutList = new ArrayList<List<Map<String, String>>>();

        String hql1 = "FROM ExcelInfo e";
        List<ExcelInfo> excelInfoList = session.createQuery(hql1).list();

        System.out.println("1.excelInfoList的大小为:" + excelInfoList.size());

        for (int i = 0; i < excelInfoList.size(); i++) {


            //初始化每个班级
            List<Map<String,String>> classMap = getAllClassInfo();

            int[] nums = new int[classMap.size()];

            System.out.println("2.初始化地classMap的大小为：" + classMap.size());

            String excelName = excelInfoList.get(i).getExcelName();
            String uploadTime = excelInfoList.get(i).getUploadTime();

            Map<String,String> formInfo = new HashMap<String, String>();

            formInfo.put("表格名称",excelName);
            formInfo.put("上传时间",uploadTime);
            classMap.add(0,formInfo);

            String hql2 = "FROM StuExcel s WHERE s.excelName=:excelName and s.isFinished=1 ";
            List<StuExcel> stuExcelList = session.createQuery(hql2).setParameter("excelName", excelName).list();


            //如果该表格还没有人填写
            if (stuExcelList.size() == 0) {
                for (int j = 1; j < classMap.size(); j++) {
                    classMap.get(j).put("已填人数","0");
                }

                System.out.println("3.已经完成“还没有人填写”的初始化");

            }




            //如果该表格有人填写了
            else {

                System.out.println("4.进入到“有人填写”的模块");

                for (int j = 0; j < stuExcelList.size(); j++) {

                    String hql3 = "FROM StudentInfo s WHERE s.username=:username";

                    List<StudentInfo> student = session.createQuery(hql3).setParameter("username", stuExcelList.get(j).getStuId()).list();
                    String classss = formService.JudgeBelongToClass(student.get(0));
                    nums[Integer.valueOf(classss) - 1] ++;

                //将某个表格的班级已填人数统计下来
                }
                for (int n = 1; n < classMap.size(); n++) {
                    Map<String,String> map = classMap.get(n);
                    Integer hasStu = Integer.valueOf(map.get("班级编码"));
                    map.put("已填人数",String.valueOf(nums[hasStu - 1]));
                }
            }


            //查询每个班级的总人数
            String hql4 = "FROM StudentInfo";

            int[] eachClassNums = new int[22];
            List<StudentInfo> studentInfoList = session.createQuery(hql4).list();
            for (int m = 0; m < studentInfoList.size(); m++) {
                String classss = formService.JudgeBelongToClass(studentInfoList.get(m));
                eachClassNums[Integer.valueOf(classss) - 1] ++;
            }
            for (int n = 1; n < classMap.size(); n++) {
                Map<String,String> map = classMap.get(n);
                Integer hasStu = Integer.valueOf(map.get("班级编码"));
                map.put("总人数",String.valueOf(eachClassNums[hasStu - 1]));
            }

            formAllClassCheckOutList.add(classMap);

            System.out.println("=================测试====================");

            System.out.print(classMap.get(0).get("表格名称"));
            System.out.println("  " + classMap.get(0).get("上传时间"));

            for (int k = 1; k < classMap.size() ; k++) {
                Map<String,String> map = classMap.get(k);
                System.out.print(map.get("班级") + " : " + map.get("已填人数") + "/" + map.get("总人数"));
            }
            System.out.println();
            System.out.println();
        }
        return formAllClassCheckOutList;
    }



    //获取所有班级信息
    @Override
    public List<Map<String, String>> getAllClassInfo() {
        Session session = getSession();
        String hql = "FROM ClassInfo";
        List<ClassInfo> classInfoList = session.createQuery(hql).list();
        List<Map<String,String>> classInfoMapList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < classInfoList.size(); i++) {
            HashMap<String,String> hashMap = new HashMap<String, String>();
            hashMap.put("班级",classInfoList.get(i).getClassName());
            hashMap.put("班级编码",classInfoList.get(i).getClassCode());
            classInfoMapList.add(hashMap);
        }
        return classInfoMapList;
    }
}

