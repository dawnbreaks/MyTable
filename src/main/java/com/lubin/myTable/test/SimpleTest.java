package com.lubin.myTable.test;

import static org.fusesource.leveldbjni.JniDBFactory.bytes;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.lubin.myTable.client.MyTableClient;
import com.lubin.myTable.obj.IMyTable;

public class SimpleTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		
		 IMyTable client = MyTableClient.getInstance();
		 String key = "hello";
		 client.put(key,  bytes("myTable"));
		 byte[] value = client.get(key);
		 System.out.println("key="+key+"	value=" + new String(value, "UTF-8")+"\n");
		 client.delete(key);
		 
		 
		 client.put("a", bytes("myTable:"+ "a"));
		 client.put("b", bytes("myTable:"+ "b"));
		 client.put("c", bytes("myTable:"+ "c"));
		 client.put("d", bytes("myTable:"+ "d"));
		 client.put("e", bytes("myTable:"+ "e"));
		 Map<String, byte[]> res = client.range("a", "d");
		 for(Map.Entry<String, byte[]> entry :res.entrySet()){
			 System.out.println("key="+entry.getKey() + "	value="+new String(entry.getValue(), "UTF-8"));
		 }
	}
}
