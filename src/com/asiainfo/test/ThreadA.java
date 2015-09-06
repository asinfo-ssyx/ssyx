package com.asiainfo.test;

public class ThreadA {
	public static void main(String[] args) {

        ThreadB b = new ThreadB();// ThreadB status: new

        b.start();// ThreadB status: runnable

        synchronized (b) {//主线程和 新线程同时在b对象上加锁
            try {
                System.out.println("等待对象b完成计算。。。");
                Thread.sleep(10000);// sleep（不释放锁） 期间b  run方法无法执行
                b.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b对象计算的总和是：" + b.total);
        }
    }

}

    class ThreadB extends Thread {

    int total;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
            	System.out.println("执行循环！");
                total += i;
            }
            notifyAll();//不释放锁
            try {
            	System.out.println("11111");
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
    }

}
