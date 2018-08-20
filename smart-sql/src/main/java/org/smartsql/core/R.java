package org.smartsql.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class R {
	private final Map<String, String> sqlMap = new HashMap<String, String>();

	public R(String sqlPath) throws IOException {
		File dir = new File(sqlPath);
		if (dir.isDirectory()) {
			for (File sqlf : dir.listFiles()) {
				if(!sqlf.getName().endsWith(".$"))continue;
				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(sqlf), "utf-8"));
				String str = null;
				String file$=sqlf.getName().replace(".$", "");
				String key$=null;
				StringBuffer sql=null;
				while ((str = in.readLine()) != null) {
					if(str.startsWith("#")) {
						if(!sqlMap.containsKey(key$)&&sql!=null) {
							sqlMap.put(key$, sql.toString());
						}
						key$=file$+str.trim().replaceAll("\r|\n|\t", "");
						sql=null;
					}else {
						sql=sql==null?new StringBuffer():sql;
						sql.append(str);
					}
				}
				sqlMap.put(key$, sql.toString());
				in.close();
			}
		}
	}
	public String get(String target) {
		return sqlMap.get(target);
	}

}
