package com.quicker.util;

import com.google.gson.Gson;
import com.google.gson.JsonNull;

public class GsonUtil {
	private static Gson gson = new Gson();
	
	public static String toJson(Object obj) {
		if (obj == null) {
			return gson.toJson(JsonNull.INSTANCE);
		}
		else {
			return gson.toJson(obj);
		}
	}

//	public static Object toObject(String json,Class classType) {
//		return gson.fromJson(json,classType);
//	}
}
