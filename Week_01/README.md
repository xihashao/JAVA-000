学习笔记
#字节码
##按照指令性质分类：
    1.栈操作指令，包括与局部变量交互的指令
    2.程序流程控制指令
    3.对象操作指令，包括方法调用指令
    4.算数运算以及类型转换指令
##查看字节码命令：
    `javap -c hello.java`
    `javap -c -verbose hello.java（可以看到更多的信息）`
    
#字节码分析
虚拟机中所有的计算都是在栈上计算，从本地变量表load变量到栈进行计算

源代码如下：
```aidl
public class HelloWorld {
    public static void main(String[] args) {
        int a= 1;
        String str = "spp";
        char ch = 'a';
        byte b = 0;
        boolean mark = true;
        short s = 6;
        long l = 6;
        double d = 3.3;
        float f = 4.4F;

        if(mark){
            for(int i=0;i<s;i++){
                a++;
            }
            int add = a+1;
            double mul = a*s;
            int sub = a-1;
            int div = a/1;
            System.out.println(add);
            System.out.println(mul);
            System.out.println(sub);
            System.out.println(div);
        }
    }
}

```
对应的字节码为：
```
E:\workspace\JAVA-000\Week_01\src>javap -c -verbose HelloWorld
Classfile /E:/workspace/JAVA-000/Week_01/src/HelloWorld.class
  Last modified 2020-10-19; size 582 bytes
  MD5 checksum 234dfe02b600a9a594fa77fa32641570
  Compiled from "HelloWorld.java"
public class HelloWorld
  minor version: 0
  major version: 52                       //代表版本号：52.0  --->java8，每上一个大版本major+1
  flags: ACC_PUBLIC, ACC_SUPER       //标识类是否是 public
Constant pool:                       //常量池  
   #1 = Methodref          #12.#21        // java/lang/Object."<init>":()V
   #2 = String             #22            // spp
   #3 = Long               6l
   #5 = Double             3.3d
   #7 = Float              4.4f
   #8 = Fieldref           #23.#24        // java/lang/System.out:Ljava/io/PrintStream;
   #9 = Methodref          #25.#26        // java/io/PrintStream.println:(I)V
  #10 = Methodref          #25.#27        // java/io/PrintStream.println:(D)V
  #11 = Class              #28            // HelloWorld
  #12 = Class              #29            // java/lang/Object
  #13 = Utf8               <init>
  #14 = Utf8               ()V
  #15 = Utf8               Code
  #16 = Utf8               LineNumberTable
  #17 = Utf8               main
  #18 = Utf8               ([Ljava/lang/String;)V
  #19 = Utf8               SourceFile
  #20 = Utf8               HelloWorld.java
  #21 = NameAndType        #13:#14        // "<init>":()V
  #22 = Utf8               spp
  #23 = Class              #30            // java/lang/System
  #24 = NameAndType        #31:#32        // out:Ljava/io/PrintStream;
  #25 = Class              #33            // java/io/PrintStream
  #26 = NameAndType        #34:#35        // println:(I)V
  #27 = NameAndType        #34:#36        // println:(D)V
  #28 = Utf8               HelloWorld
  #29 = Utf8               java/lang/Object
  #30 = Utf8               java/lang/System
  #31 = Utf8               out
  #32 = Utf8               Ljava/io/PrintStream;
  #33 = Utf8               java/io/PrintStream
  #34 = Utf8               println
  #35 = Utf8               (I)V
  #36 = Utf8               (D)V
{
  public HelloWorld();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 1: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=3, locals=17, args_size=1
         0: iconst_1                         //表示常量 1
         1: istore_1                         //1 表示本地变量表中的第三列槽位，表示将栈上的值写回到本地变量表
         2: ldc           #2                  // String spp
         4: astore_2                           //a表示对象引用                
         5: bipush        97
         7: istore_3                 
         8: iconst_0
         9: istore        4
        11: iconst_1
        12: istore        5
        14: bipush        6
        16: istore        6                    //1、2、3可以直接istore_1,再大的话变成两个字节
        18: ldc2_w        #3                  // long 6l
        21: lstore        7
        23: ldc2_w        #5                  // double 3.3d
        26: dstore        9
        28: ldc           #7                  // float 4.4f
        30: fstore        11
        32: iload_1                             //将本地变量的值加载到栈上进行计算
        33: iconst_1
        34: iadd
        35: istore        12
        37: iload_1
        38: iload         6
        40: imul
        41: i2d                            //类型转换
        42: dstore        13
        44: iload_1
        45: iconst_1
        46: isub
        47: istore        15
        49: iload_1
        50: iconst_1
        51: idiv
        52: istore        16
        54: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
        57: iload         12
        59: invokevirtual #9                  // Method java/io/PrintStream.println:(I)V
        62: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
        65: dload         13
        67: invokevirtual #10                 // Method java/io/PrintStream.println:(D)V
        70: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
        73: iload         15
        75: invokevirtual #9                  // Method java/io/PrintStream.println:(I)V
        78: getstatic     #8                  // Field java/lang/System.out:Ljava/io/PrintStream;
        81: iload         16
        83: invokevirtual #9                  // Method java/io/PrintStream.println:(I)V
        86: return
      LineNumberTable:
        line 3: 0
        line 4: 2
        line 5: 5
        line 6: 8
        line 7: 11
        line 8: 14
        line 9: 18
        line 10: 23
        line 11: 28
        line 13: 32
        line 14: 37
        line 15: 44
        line 16: 49
        line 18: 54
        line 19: 62
        line 20: 70
        line 21: 78
        line 24: 86
}

```

    对字节码来说，int是最小单位（32位），byte和bool都是用int来表示（32位）
    在编译的时候就会进行类型转换


#jvm 命令
 `jps -lmv`  可打印详细信息，jps查看java进程    
 `jinfo pid`  当前java线程jvm基本信息  
 `jstat -gc pid 1000 1000`  按字节显示 1秒打一次，打印1000次  
 `jstat -gcutil pid 1000 1000` 显示百分比  
 `jmap -heap pid` 打印堆内存的配置和使用信息  
 `jmap -histo pid` 查看哪些类占用空间最多，直方图  
 `jmap -dump:format=b,file=hprof` dump堆内存  
 `jstack pid ` 线程详细信息以及锁信息 可以判断死锁  
 `jcmd pid <option>` 可以通过-help查看支持哪些指令  
#jvm 图形化工具
 1.jconsole
 2.jvisualvm
 3.IDEA插件 visualGC
 4.jmc
 
#gc原理
 1.引用计数；问题 ；出现循环引用 ，引用次数永远不为0 ，导致内存泄露，最终内存溢出  
 2.标记清除法（Mark and sweep）---压缩整理  
    mark:标记所有可达对象，并在本地内存（native）中分们别类记下
    Sweeping:这一步保证了不可达对应所占用内存，在之后进行重新内存分配的时候可以使用
    优势：可以解决循环依赖，只扫描部分对象
    
 分代假设；大部分新生对象很快无用；存活时间较长的对象，可能存活时间更长  
 1.对象首先分配的Eden区，标记阶段Eden区存活对象就被复制到存活区，两个存活区from 和to互换角色，
 对象存活到一定阶段就会晋升到老年代  
 晋升：-XX：MaxTenuringThreshold = 15  
 注意：老年代是通过移动的方式，新生代是通过复制的方式 
 
 可以作为GcRoots对象:
  1.当前正在执行方法里的局部变量和输入参数
  2.活动线程
  3.所有类的静态字段
  4.JNI引用 
  RememberSet:记录新老跨代的引用
  
  处理算法；  
  1.标记算法（标记清除）：内存会出现很多不连续的碎片
  2.整理算法 标记清除整理：大范围挪动对象，会比较慢
  3.复制算法 标记复制：Y区
 
 #GC算法
 1.串行GC
    -XX：UserSerialGC  
    年轻代：标记复制  老年代：标记 清除 整理  
    不能充分利用多核CPU，只能使用一个核心，暂停时间长
 2.ParNewGC
    在串行的基础上变成了多线程，配合CMS使用
 3.并行GC
   -XX：UserParallelGC
   -XX：UserParallelOldGC
   -XX：UserParallelGC -XX：UserParallelOldGC
   年轻代和老年代都会触发STW
   年轻代：标记复制  老年代：标记 清除 整理  
   -XX：ParallelGCThreads=N 执行GC线程数，默认CPU核心数
    