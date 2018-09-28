## Java 核心技术36讲笔记--第二讲：Exception 和Error 有什么区别
[TOC]

### 问：请对比Exception 和 Error，另外运行时异常与一般异常有什么区别？
1. Exception 和 Error 都是继承了 Throwable 类，在Java 中只有 Throwable 的实例才能被抛出或者捕捉。
2. Exception 和 Error 体现了Java 中对异常处理的2种不同的思路（区分点在于是否可恢复）：
	+ Exception 是程序正常运行中，可以被预料到的意外情况，代码处理中应尽可能的捕捉，并进行处理。
	+ Error 指正常运行时，不大可能出现的情况，绝大部分 Error 都会导致程序处于非正常状态、不可恢复状态。通常无需捕捉Error.
3. Exception 又可以分为2类（区分点在于是否能在代码编译期间被检测）：
	+ 可检查异常：在代码中必须显示的捕捉，并处理，这可以在编译就检查出的异常。
	+ 不检查异常（运行时异常）：通常是可以通过编码避免的逻辑错误，是否需要捕捉看具体情况而定，例如 NullPointException、ArrayIndexOutOfBoundsException等等。

### Java 中对异常的实践：
1. try-catch-finally : 捕捉异常
2. throw Throwable：抛出异常
3. throws : 方法向上继续抛出异常
4. try-with-resources：Java 新加的语法糖，一些IO操作将会自动关闭流。
5. multiple catch: 
```
try (BufferedReader br = new BufferedReader(…);
     BufferedWriter writer = new BufferedWriter(…)) {// Try-with-resources
// do something
catch ( IOException | XEception e) {// Multiple catch
   // Handle it
} 
```

