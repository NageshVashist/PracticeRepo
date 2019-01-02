package synchronizers;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCountDownLatch {
	public static void main(String[] args) {
		CountDownLatch cb = new CountDownLatch(2);
		Task task1 = new MyCountDownLatch().new Task("Task 1", cb);
		Task task2 = new MyCountDownLatch().new Task("Task 2", cb);

		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.submit(task1);
		threadPool.submit(task2);

		threadPool.submit(task1);
		threadPool.submit(task2);

		threadPool.submit(task1);
		threadPool.submit(task2);

		threadPool.shutdown();
	}

	class Task implements Callable {
		private String name;
		private CountDownLatch cb;

		public Task(String name, CountDownLatch cb) {
			this.name = name;
			this.cb = cb;
		}

		@Override
		public Object call() throws Exception {
			Thread.sleep(1000);
			cb.countDown();
			cb.await();
			System.out.println(name + " I am done");
			return "I have compleated my task " + name;
		}
	}
}
