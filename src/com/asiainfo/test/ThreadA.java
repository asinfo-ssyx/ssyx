package com.asiainfo.test;

public class ThreadA {
	public static void main(String[] args) {

        ThreadB b = new ThreadB();// ThreadB status: new

        b.start();// ThreadB status: runnable

        synchronized (b) {//���̺߳� ���߳�ͬʱ��b�����ϼ���
            try {
                System.out.println("�ȴ�����b��ɼ��㡣����");
                Thread.sleep(10000);// sleep�����ͷ����� �ڼ�b  run�����޷�ִ��
                b.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b���������ܺ��ǣ�" + b.total);
        }
    }

}

    class ThreadB extends Thread {

    int total;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
            	System.out.println("ִ��ѭ����");
                total += i;
            }
            notifyAll();//���ͷ���
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
