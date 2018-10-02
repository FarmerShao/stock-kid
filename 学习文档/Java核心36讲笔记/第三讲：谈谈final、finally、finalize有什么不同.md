## Java 核心技术36讲笔记--第三讲：谈谈final、finally、 finalize有什么不同？
[TOC]

## final 
final 可以修饰类、方法和属性，分别有不同的含义：

+ 修饰类： 表示类不可以被继承。
+ 方法： 表示方法不可以被子类重写。
+ 属性： 表示属性被赋值后不可以被修改。

## finally
finally 是 Java 保证重点代码一定被执行的一种保护机制，例如网络连接资源的回收等。finalle都是配合 try-fiannly 或者 try-catch-finally 的形式出现。
注： 如果在try 包含的代码时System.exit(1)，则finally里的代码不会被执行。

## finalize
finalize 与 final 、finalize 是有本质上的区别的，final 、finalize这2个都是Java 语义关键字，而finalize是Object 对象的一个方法。
这个方法被设计出来的目的是保证对象在被垃圾收集前完成特定的资源的回收，即在垃圾回收前执行。
但是这个方法不建议被使用，JDK 9 也被标记为 deprecated。

### 为什么不推荐使用finalize呢？
因为我们无法保证finalize 什么时候执行，执行的是否符合预期。使用不当会影响性能，导致程序死锁、挂起等。
实践中，因为 finalize 拖慢垃圾收集，导致大量对象堆积，也是一种典型的导致 OOM 的原因。
finalize 还会掩盖资源回收时的出错信息。

### Java 对 finalize 的替换方案--Cleaner

Cleaner 的实现利用了幻想引用。每个Cleaner的操作都是独立的，它有自己的运行线程，所以可以避免意外死锁等问题。

```
public class CleaningExample implements AutoCloseable {
        // A cleaner, preferably one shared within a library
        private static final Cleaner cleaner = <cleaner>;
        static class State implements Runnable { 
            State(...) {
                // initialize State needed for cleaning action
            }
            public void run() {
                // cleanup action accessing State, executed at most once
            }
        }
        private final State;
        private final Cleaner.Cleanable cleanable
        public CleaningExample() {
            this.state = new State(...);
            this.cleanable = cleaner.register(this, state);
        }
        public void close() {
            cleanable.clean();
        }
    }

```

