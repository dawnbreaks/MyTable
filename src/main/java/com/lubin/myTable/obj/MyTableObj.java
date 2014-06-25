package com.lubin.myTable.obj;

import java.util.List;
import java.util.Map;

import com.lubin.myTable.server.LeveldbUtil;

public class MyTableObj implements IMyTable {

	@Override
	public byte[] get(String key) {
		return LeveldbUtil.getInstance().get(key);
	}

	@Override
	public boolean put(String key, byte[] value) {
		LeveldbUtil.getInstance().put(key, value);
		return true;
	}

	@Override
	public boolean delete(String key) {
		LeveldbUtil.getInstance().delete(key);
		return true;
	}

	@Override
	public Map<String, byte[]> range(String start, String limit) {
		return LeveldbUtil.getInstance().range(start, limit);
	}
	
	
}
