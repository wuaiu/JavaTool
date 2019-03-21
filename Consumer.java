import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Consumer implements Runnable {
    private String name;

    private BlockingQueue<String> s = null;

    public Consumer(String name, BlockingQueue<String> s) {
        this.name = name;
        this.s = s;
    }

    public void run() {
        try {
            while (true) {
                String product = s.poll();
                if (product == null) {
                    break;
                }
                int t = (new Random()).nextInt(10);
                System.out.println(name + "消费(" + product + ").");
                System.out.println(name + " ==== sleep " + t + " secs ====");
                Thread.sleep(t * 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

public class HelloJava8 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> s = new LinkedBlockingQueue<String>();
        s.put("java");
        s.put("python");
        s.put("php");
        s.put("c++");
        s.put("c");

        ArrayList<Thread> threadList = new ArrayList<Thread>();

        Thread t1 = new Thread(new Consumer("zhangsan", s));
        t1.start();
        threadList.add(t1);
        Thread t2 = new Thread(new Consumer("lisi", s));
        t2.start();
        threadList.add(t2);
        Thread t3 = new Thread(new Consumer("wangwu", s));
        t3.start();
        threadList.add(t3);
        Thread t4 = new Thread(new Consumer("afei", s));
        t4.start();
        threadList.add(t4);
        Thread t5 = new Thread(new Consumer("jb", s));
        t5.start();
        threadList.add(t5);

        for (Thread thread : threadList) {
            thread.join();
        }

        System.out.println("主线程执行完毕");
    }
}
