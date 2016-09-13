package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.cglib.beans.BeanGenerator;

import org.junit.Test;
import org.springframework.cglib.beans.BeanMap;

import com.quicker.entity.json.BasicJson;
import com.quicker.util.CglibUtil;
import com.quicker.util.ExcelUtil;
import com.quicker.util.GsonUtil;

public class CglibTest {

	public Object object = null;

	public BeanMap beanMap = null;

	public CglibTest() {
	}

	public void init(Map propertyMap) {
		this.object = generateBean(propertyMap);
		this.beanMap = BeanMap.create(this.object);
	}

	@SuppressWarnings("unchecked")
	private Object generateBean(Map propertyMap) {
		BeanGenerator generator = new BeanGenerator();
		Set<Object> keySet = propertyMap.keySet();
		for (Iterator i = keySet.iterator(); i.hasNext();) {
			String key = (String) i.next();
			generator.addProperty(key, (Class) propertyMap.get(key));
		}
		return generator.create();
	}

	public void setValue(String property, Object value) {
		beanMap.put(property, value);
	}

	public Object getValue(String property) {
		return beanMap.get(property);
	}

	public Object getObject() {
		return this.object;
	}

	@Test
	public void test() throws ClassNotFoundException {
		// HashMap propertyMap = new HashMap();
		//
		// propertyMap.put("id", Class.forName("java.lang.Integer"));
		// propertyMap.put("name", Class.forName("java.lang.String"));
		// propertyMap.put("address", Class.forName("java.lang.String"));
		//
		// CglibTest bean = new CglibTest();
		//
		// bean.init(propertyMap);
		//
		// bean.setValue("id", new Integer(123));
		//
		// bean.setValue("name", "jack");
		//
		// bean.setValue("address", "河南省");
		//
		// System.out.println("id:" + bean.getValue("id"));
		//
		// System.out.println("name:" + bean.getValue("name"));
		//
		// System.out.println("address:" + bean.getValue("address"));

		FileInputStream in;
		File file = null;
		try {
			in = new FileInputStream("D:\\excel\\学生信息统计.xlsx");
			file = new File("D:\\excel\\学生信息统计.xlsx");
			ExcelUtil ex = new ExcelUtil();
			Object obj = null;
			List<String> result = null;
			Map<String,String> map = new HashMap<String,String>();
			int i;
			try {
				result = ex.parseExcel(in);
				for (i = 0; i < result.size(); i++) {
					map.put("flag" + (i + 1),result.get(i));
				}
				String name = (file.getName().substring(0,file.getName().length() - 5));
				map.put("title", name);
				BasicJson basicJson = new BasicJson();
				basicJson.setStatus(true);
				basicJson.setJsonString(map);
				String json = GsonUtil.toJson(basicJson);
				System.out.println(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
