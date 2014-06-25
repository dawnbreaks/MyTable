myTable
========

Yet another NoSql database based on leveldb.


Features
========

  * Simple, small code base, easy to learn API
  * Very fast, high performance

  
Simple tutorial
========
1. Start MyTable server 
```java
	com.lubin.myTable.server.MyTableServer
```

2. Create a client and try basic operation...
```java
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
```
output:
```
key=hello	value=myTable

key=a	value=myTable:a
key=b	value=myTable:b
key=c	value=myTable:c
```

Build
========

To build the JAR file of myTable, you need to install Maven (http://maven.apache.org), then type the following command:

    $ mvn package

To generate project files (.project, .classpath) for Eclipse, do

    $ mvn eclipse:eclipse

then import the folder from your Eclipse.


========
Oh, that's all! Easy to understand, right? Please feel free to contact me(2005dawnbreaks@gmail.com) if you have any questions.
