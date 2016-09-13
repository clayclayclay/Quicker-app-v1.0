package com.quicker.test;

import org.springframework.util.Assert;


public class DynamicDataSourceHolder {
	// �̱߳��ػ���  
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
	// ��������Դ����  
	public static void setDataSourceType(String dataSourceType) {  
	    Assert.notNull(dataSourceType, "DataSourceType cannot be null");  
	    contextHolder.set(dataSourceType);  
	}  
	  
	// ��ȡ����Դ����  
	public static String getDataSourceType() {  
	    return (String) contextHolder.get();  
	}  
	  
	// �������Դ����  
	public static void clearDataSourceType() {  
	    contextHolder.remove();  
	}  
}


