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
```
output:
```
leveldb
key=a|value=leveldba
key=b|value=leveldbb
key=c|value=leveldbc
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
