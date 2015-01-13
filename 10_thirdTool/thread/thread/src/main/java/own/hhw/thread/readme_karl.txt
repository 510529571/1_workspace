1.定义线程类，有两中方式，一种是继承Thread类，一种是implements Runnable类
	用第二钟方法创建的对象obj2如果要运行线程需要用这种方式new Thread(obj2).start()
也就是用obj2实例化了一个Thread对象
	而用第一种方法创建的对象obj1如果要运行线程，可以直接obj1.start()启动线程
同时也可以用new Thread(obj2).start()方式来启动，因为Thread类是runnable的实现
类，所以也可以那样用
	问：提供 Thread(obj1)这种构造方法有什么意义呢？其中obj1的类和Thread都继承
了同一接口

2.一个线程对象只能调用一次start方法，如果这个对象的一个线程还没有运行结束的时候
继续调用start方法不会报错，但是如果这个对象的第一个线程已经结束了，之后在调用start
方法就会报 java.lang.IllegalThreadStateException的异常