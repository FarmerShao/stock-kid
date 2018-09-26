[TOC]

## 前言
最近因为工作上的需求，需要调用期货服务商提供的接口。由于期货公司提供的接口是c++的动态库，而公司的后台是用Java 语言，
经过网上的调查发现可以使用 Swig 这个软件工具将c++封装成Java的jar。
公司之前开发的股票相关的项目，股票接口服务商提供 dll 库，然后使用JNA调用，这种方式的缺点是，我们需要通过自己书写代码来加载对应的dll库。

股票使用的方式：
```
public class TradeAPI implements TradeService {
	//TradeTdxLibrary 是根据接口提供商提供的接口文档，我们定义的一个Java 版接口
	private static TradeTdxLibrary tdxLibrary = null;

    public TradeAPI(String account, String clientVersion, Integer poolSize) {
    	//系统加载dll文件
        DllLoader.preload(0);
        //通过jna 加载，转换成对应的Java 类
        tdxLibrary = (TradeTdxLibrary) Native.loadLibrary("TradeX-M", TradeTdxLibrary.class);
        if (TradePool.freeNum == -1) {
            try {
                TradePool.init(account, clientVersion, poolSize);
            } catch (TradeException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 通达信交易 API（多账户版）
 *
 * @author Shao Yu
 * @since 2018/4/25 9:50
 **/
public interface TradeTdxLibrary extends Library {

    /**
     * 打开通达信实例
     *
     * @param nClientType      券商客户端类型；使用固定参数为14
     * @param pszClientVersion 券商客户端的版本号；固定参数为“6.40”
     * @param nCliType         券商客户端指令接口类型；固定参数为12
     * @param nVipTermFlag     终端标志代码；固定参数为0
     * @param ErrInfo          此API 执行返回后，如果出错，保存了错误信息说明。一般要分配256 字节的空间；没出错时为空字符串。
     * @return 客户端ID，负值 失败时返回-1
     */
    int OpenTdx(int nClientType, String pszClientVersion, int nCliType, int nVipTermFlag, byte[] ErrInfo);

    ......
}
```

## 1.准备工作
+ 下载[swig](!http://www.sfit.com.cn/DocumentDown/api/63620160606API.zip)，并安装。
+ C++动态库（本文档使用的是顶点提供的期货动态库）
```
FixApi.dll
FixApi.lib
FixApiEx.h
```
+ 32位的JDK（我使用的是JDK 8）

## 2. 通过Swig 得到jar
### 2.1 首先第一步我们需要编写一个 SWIG的接口文件(.i)： (我创建的是 fixtraderapi.i )
```
%module FixTradeApi 
%{  
  #include "FixApiEx.h"
%} 
%include "FixApiEx.h" 
```

+ %module 指定模块名，必须定义在.i文件的首行，这个模块名最后对应的是Java 的文件名（在我的例子中就是会生成 FixTradeApi.java 文件）
+ %{...%} 块内的内容将被简单作为结果逐字拷贝到SWIG创建的wrapper(包装)文件中。这部分大部分被用来包括头文件和生成wrapper代码需要的其它声明。
+ %include ：为类中所有方法创建API，类中的所有公有方法都将在API在暴露。

### 2.2 执行 swig 命令
```
swig.exe -c++ -java -package com.fix.fixtraderapi -outdir src -o fixtraderapi_wrap.cpp -I ../ ixtraderapi.i
```

1. swig.exe：执行 swig 命令
2. \-c\+\+ \-java: 告诉swig将c\+\+接口转换为java接口。如果是将C接口转换为java接口，就不需要\-c\+\+，直接写 swig \-java就可以
3. \-package: 生成的java类的包的名称
4. \-I 指定swig 接口文件中include中的.h文件路径（若是cd 到对应的目录下，这个参数可以不用）
5. \-outdir: 指定输出文件目录（执行完命令后生产的java文件就会在这个文件夹下面）
注： 在执行命令之前要先创建好对应的文件夹，如上面的 'src', 'com',com下面的'fix'。

### 2.3 编译java 文件
由于我这里在上面命令行里指定的是 src 文件夹，需要切换到对应的 src路径下
然后执行 下面的java 编译命令：
```
javac *.java
```

### 2.4 打成jar
将所有上一步编译好的.class 文件复制到 'com.fix' 文件下；
然后执行下面的指令：
```
jar cf fix-trader-sdk.jar com
```

## 3. 项目引入jar， 直接调用 FixTradeApi 里的接口即可。