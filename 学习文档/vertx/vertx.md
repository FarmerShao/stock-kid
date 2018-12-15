## Vertx 源码解析：
[TOC]

### 一、Vertx 接口:
#### 1.1：静态方法：
|方法|说明|
|:--|:--:|
|static Vertx vertx()|通过VertxFactory，使用默认配置，创建一个非集群vertx 实例 |
|static Vertx vertx(VertxOptions options)|通过VertxFactory，使用自定义配置，创建一个非集群vertx 实例  |
|static void clusteredVertx(VertxOptions options, Handler<AsyncResult<Vertx>> resultHandler)|通过VertxFactory，使用自定义配置，创建一个集群vertx 实例 |
|static Context currentContext()|获取当前的Context，返回值可以为null|

#### 1.2：环境配置相关的方法
|方法|说明|
|:--|:--:|
|Context getOrCreateContext()|获取当前的Context，若不存在，则创建一个 |
|FileSystem fileSystem()|获取操作系统对象（每个Vertx实例下--单例） |
|EventBus eventBus()|获取EventBus 对象（每个Vertx实例下--单例） |
|SharedData sharedData()|获取共享数据对象（每个Vertx实例下--单例） |
|void runOnContext(Handler<Void> action)|放入一个Handler到Context，这样就可以对所有的Event 执行指定的Handler（面向切面编程）|
|void close()|关闭Vertx 实例|
|void close(Handler<AsyncResult<Void>> completionHandler)|关闭Vertx 实例，完成后执行指定Hadnler|
|boolean isClustered()|Vertx 实例是否是集群模式|、
|EventLoopGroup nettyEventLoopGroup()|获取vertx实例处理事件的netty的EventLoopGroup|
|Vertx exceptionHandler(@Nullable Handler<Throwable> handler)|设置默认的Exception处理句柄|

#### 1.3：功能相关的方法
##### 1.3.1：TCP 服务器/客户端
|方法|说明|
|:--|:--:|
|NetServer createNetServer(NetServerOptions options)|创建一个 TCP/SSL 服务器，通过指定配置|
|NetServer createNetServer()|创建一个 TCP/SSL 服务器，通过默认配置|
|NetClient createNetClient(NetClientOptions options)|创建一个 TCP/SSL 客户端，通过指定配置|
|NetClient createNetClient()|创建一个 TCP/SSL 客户端，通过默认配置|

##### 1.3.2：HTTP/HTTPS 服务器/客户端
|方法|说明|
|:--|:--:|
|HttpServer createHttpServer(HttpServerOptions options)|创建一个 HTTP/HTTPS 服务器，通过指定配置|
|HttpServer createHttpServer()|创建一个 HTTP/HTTPS 服务器，通过默认配置|
|HttpClient createHttpClient(HttpClientOptions options)|创建一个 HTTP/HTTPS 客户端，通过指定配置|
|HttpClient createHttpClient()|创建一个 HTTP/HTTPS客户端，通过默认配置|

##### 1.3.3：UDP 服务
|方法|说明|
|:--|:--:|
|DatagramSocket createDatagramSocket(DatagramSocketOptions options)|创建一个 UDP 服务器，通过指定配置|
|DatagramSocket createDatagramSocket()|创建一个 HTTP/HTTPS 服务器，通过默认配置|

#### 1.3.4：DNS 客户端
|方法|说明|
|:--|:--:|
|DnsClient createDnsClient(int port, String host)|创建一个 UDP 客户端，连接DNS服务器|

##### 1.3.5：定时器
|方法|说明|
|:--|:--:|
|long setTimer(long delay, Handler<Long> handler)|创建一个一次性定时器，在指定毫秒数后执行任务，执行完后取消定时器， 返回定时器序号|
|TimeoutStream timerStream(long delay)|创建一个一次性定时器，在指定毫秒数后执行任务，执行完后取消定时器，返回定时器流|
|long setPeriodic(long delay, Handler<Long> handler)|创建一个永久定时器，每隔delay(ms)后执行任务， 返回定时器序号|
|TimeoutStream periodicStream(long delay)|创建一个永久定时器，每隔delay(ms)后执行任务， 返回定时器流|
|boolean cancelTimer(long id)|取消指定序号的定时器|

#### 1.4：Verticle 管理方法
|方法|说明|
|:--|:--:|
|void deployVerticle(Verticle verticle)|发布Verticle， 使用默认配置|
|void deployVerticle(Verticle verticle, Handler<AsyncResult<String>> completionHandler)|发布Verticle，发布完成后执行指定Handler|
|deployVerticle(Verticle verticle, DeploymentOptions options)|自定配置发布Verticle|
|void deployVerticle(Verticle verticle, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler)|自定配置发布Verticle，完成后执行指定Handler|
|void deployVerticle(String name)|根据全类名发布Verticle|
|void deployVerticle(String name, Handler<AsyncResult<String>> completionHandler)|根据全类名发布Verticle, 完成后执行指定Handler|
|void deployVerticle(String name, DeploymentOptions options)|根据全类名，自定配置发布Verticle|
|void deployVerticle(String name, DeploymentOptions options, Handler<AsyncResult<String>> completionHandler)|根据全类名，自定配置发布Verticle, 完成后执行指定Handler|
|void undeploy(String deploymentID)|取消发布指定deploymentID的Vertciel|
|void undeploy(String deploymentID, Handler<AsyncResult<Void>> completionHandler)|取消发布指定deploymentID的Vertciel，完成后执行指定Handler|
|Set<String> deploymentIDs()|获取当前已发布的Vertocles的deploymentIDs|
|void registerVerticleFactory(VerticleFactory factory)|注册VerticleFactory，VerticleFactory 通过ClassLoad和verticleName(全类名)创建对应的Verticle实例|	
|void unregisterVerticleFactory(VerticleFactory factory)|取消注册VerticleFactory|	
|Set<VerticleFactory> verticleFactories()|获取注册的VerticleFactory集合|	

#### 1.5：阻塞方法执行
|方法|说明|
|:--|:--:|
|<T> void executeBlocking(Handler<Future<T>> blockingCodeHandler, boolean ordered, Handler<AsyncResult<T>> resultHandler)|安全的执行阻塞方法，如果 ordered 为true，则当前的阻塞代码每次执行时保证在同一个Context下，反子，则不能保证每次执行时在同一个Context 下|
| <T> void executeBlocking(Handler<Future<T>> blockingCodeHandler, Handler<AsyncResult<T>> resultHandler)|安全的执行阻塞方法|
|WorkerExecutor createSharedWorkerExecutor(String name, int poolSize, long maxExecuteTime)|创建一个WorkerExecutor 线程池|
|WorkerExecutor createSharedWorkerExecutor(String name, int poolSize)|创建一个WorkerExecutor 线程池|
|WorkerExecutor createSharedWorkerExecutor(String name)|创建一个WorkerExecutor 线程池，使用默认的poolSize 和 maxExecuteTime，默认的poolSize 为20，maxExecuteTime 为 60s|


### 二、Vertx 实现VertxImpl
#### VertxImpl Filed:
|类型|变量名|说明|
|:--:|:--:|:--:|
|FileSystem|fileSystem|操作系统支持，例如文件的复制，移动等，若是windows系统则实例对象是WindowsFileSystem, 反之是FileSystemImpl|