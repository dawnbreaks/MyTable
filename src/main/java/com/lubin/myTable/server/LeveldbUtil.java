package com.lubin.myTable.server;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.ReadOptions;
import org.iq80.leveldb.WriteOptions;

public class LeveldbUtil {
	
	private static LeveldbUtil instance;

	private DB db=null;
	
	private WriteOptions writeOptions;
	private ReadOptions readOptions;
	
	public static LeveldbUtil getInstance(){
		if (instance == null){
			synchronized (LeveldbUtil.class){
				if (instance == null){
					instance = new LeveldbUtil("default");
				}
			}
		}
		return instance;
	}

	LeveldbUtil(String dbName){
		try {
			Options options = new Options()
				.createIfMissing(true)
				.cacheSize(1000*1000*500)
				.writeBufferSize(8 << 20);
			

			writeOptions = new WriteOptions().sync(false);
			readOptions = new ReadOptions().fillCache(true).verifyChecksums(true);
	        
			db = factory.open(new File(dbName), options);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void put(String key, byte[] value){
		db.put(bytes(key), value, writeOptions);
	}
	
	public byte[] get(String key){
		return db.get(bytes(key), readOptions);
	}
	
	public void delete(String key){
		db.delete(bytes(key), writeOptions);
	}
	
	//[start, limit)
	public Map<String, byte[]> range(String start, String limit){
		DBIterator iterator = db.iterator(readOptions);
		
		if(start.length()>0){
			iterator.seek(bytes(start));
		}else{
			iterator.seekToFirst();
		}
		
		Map<String, byte[]> res = new TreeMap<String, byte[]>();
		try {
		  for(;iterator.hasNext(); iterator.next()) {
			  String key = asString(iterator.peekNext().getKey());
			  if(limit.length()>0 && key.compareTo(limit) >= 0 ){
				  break;
			  }
			  byte[] value = iterator.peekNext().getValue();
			  
			  res.put(key, value);
		  }
		} finally {
			close(iterator);
		}
		
		return res;
	}
	
	
	void close(DBIterator iterator){
		try {
			iterator.close();
		} catch (IOException e) {
			throw new RuntimeException("close(DBIterator iterator)", e);
		}
	}
	
	byte[] bytes(String str){
		try {
			return str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}
	
	String asString(byte[] bytes){
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("asString", e);
		}
	}
}
