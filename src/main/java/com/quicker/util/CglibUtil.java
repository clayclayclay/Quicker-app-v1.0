package com.quicker.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.cglib.beans.BeanGenerator;

import org.springframework.cglib.beans.BeanMap;

public class CglibUtil {

	public Object object = null;

	public BeanMap beanMap = null;

	public CglibUtil(Map propertyMap) {
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
}
