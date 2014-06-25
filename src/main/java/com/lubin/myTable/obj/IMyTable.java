package com.lubin.myTable.obj;

import java.util.Map;

public interface IMyTable {
	boolean put(String key, byte[] value);
	byte[] get(String key);
	boolean delete(String key);
	
	//[start, limit)
	Map<String, byte[]> range(String start, String limit);
}
