package com.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class MyUtil {

	public static boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}

	
	public static Map<String, String> getmapFromPUTStringBufferReader(String data) {
		if (StringUtils.isNotBlank(data)){
			Map<String, String> mappa = new HashMap<>();
			String[] parametri = data.split("&");
			for (String parametro : parametri){
				String[] parametroEValore = parametro.split("=");
				if (parametroEValore.length==2)
					mappa.put(parametroEValore[0], parametroEValore[1]);
			}
			return mappa;
		}
		return null;
	}  
}
