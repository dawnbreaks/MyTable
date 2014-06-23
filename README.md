myTable
========

Yet another NoSql database based on [leveldb].


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
	byte[] key = bytes("hello world");
	byte[] value = bytes("leveldb");
	client.put(key, value);
	client.get(key);
	client.delete(key);
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
