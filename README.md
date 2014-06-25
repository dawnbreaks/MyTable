myTable
========

Yet another NoSql database based on leveldb.


Features
========

  * Simple, small code base, easy to learn API
  * Very fast, high performance

  
Simple tutorial
========
####1. Start MyTable server 
```java
	com.lubin.myTable.server.MyTableServer
```

####2. Create a client and try basic operation...
```java
	 IMyTable client = MyTableClient.getInstance();
	 String key = "hello";
	 client.put(key,  bytes("myTable"));
	 byte[] value = client.get(key);
	 System.out.println("key="+key+"	value=" + asString(value)+"\n");
	 client.delete(key);
	 
	 
	 client.put("a", bytes("myTable:"+ "a"));
	 client.put("b", bytes("myTable:"+ "b"));
	 client.put("c", bytes("myTable:"+ "c"));
	 client.put("d", bytes("myTable:"+ "d"));
	 client.put("e", bytes("myTable:"+ "e"));
	 Map<String, byte[]> res = client.range("a", "d");
	 for(Map.Entry<String, byte[]> entry :res.entrySet()){
		 System.out.println("key="+entry.getKey() + "	value="+asString(entry.getValue()));
	 }
```
output:
```
key=hello	value=myTable

key=a	value=myTable:a
key=b	value=myTable:b
key=c	value=myTable:c
```

####3. Config file for myTable server
```javascript
server {
	
	port = 9090
	backlog = 1000
	
	async = false	//handling request in business logic thread pool
	asyncThreadPoolSize = 4
    ioThreadNum = 1   
	
	objects = [
		com.lubin.myTable.obj.MyTableObj
	]

}

leveldb {
	dataDir = "default"
	createIfMissing = true
    cacheSize = 500	//500M
    writeBufferSize = 16	//16M
    WriteOptions {
    	sync = false
    }
    ReadOptions {
    	fillCache = true
    	verifyChecksums = true
    }
}
```


####4. Config file for myTable client
```javascript
client {

	reconnInterval = 1000	//time interval(million second) for reconnecting to server
	asyncThreadPoolSize = 1   //thread pool for excuting Async callback
    ioThreadNum = 2   
    serializer = 0      //0 kryo 1 json
    objects = [ 
		{ 
			name = com.lubin.myTable.obj.IMyTable
			servers ="127.0.0.1:9090"
		}
	]
}
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
