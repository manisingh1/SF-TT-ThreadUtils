package atomics;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {
//  public static volatile long count = 0;
  public static AtomicLong count = new AtomicLong(0);

  public static void main(String[] args) throws Throwable {
    Runnable r = () -> {
      for (int i = 0; i < 100_000_000; i++) {
//        count++;
        count.getAndIncrement();
      }
    };

    System.out.println("count before " + count);
    Thread t1 = new Thread(r);
    Thread t2 = new Thread(r);
    long start = System.nanoTime();
    t1.start();
    t2.start();
    t1.join();
    t2.join();
    long time = System.nanoTime() - start;
    System.out.println("count after " + count);
    System.out.println("time was " + (time / 1_000_000_000.0));
  }
}
