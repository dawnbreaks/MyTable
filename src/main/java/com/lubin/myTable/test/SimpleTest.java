package com.lubin.myTable.test;

import static org.fusesource.leveldbjni.JniDBFactory.bytes;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.lubin.myTable.client.MyTableClient;
import com.lubin.myTable.obj.IMyTable;

public class SimpleTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		
		 IMyTable client = MyTableClient.getInstance();
		 String key = "hello world";
		 byte[] value = bytes("leveldb");
		 client.put(key, value);
		 value = client.get(key);
		 System.out.println(new String(value, "UTF-8"));
		 client.delete(key);
		 
		 
		 client.put("a", bytes("leveldb"+ "a"));
		 client.put("b", bytes("leveldb"+ "b"));
		 client.put("c", bytes("leveldb"+ "c"));
		 client.put("d", bytes("leveldb"+ "d"));
		 client.put("e", bytes("leveldb"+ "e"));
		 Map<String, byte[]> res = client.range("a", "d");
		 for(Map.Entry<String, byte[]> entry :res.entrySet()){
			 System.out.println("key="+entry.getKey()+"|value="+new String(entry.getValue(), "UTF-8"));
		 }
	}
}
