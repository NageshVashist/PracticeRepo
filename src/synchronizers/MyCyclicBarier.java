package synchronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCyclicBarier {
	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(2);
		Task task1 = new MyCyclicBarier().new Task("Task 1", cb);
		Task task2 = new MyCyclicBarier().new Task("Task 2", cb);

		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.submit(task1);
		threadPool.submit(task2);

		threadPool.submit(task1);
		threadPool.submit(task2);

		threadPool.submit(task1);
		threadPool.submit(task2);

		if (cb.getNumberWaiting() <= 0) {

			System.out.println(cb.isBroken());
		}

		threadPool.shutdown();
	}

	class Task implements Callable {
		private String name;
		private CyclicBarrier cb;

		public Task(String name, CyclicBarrier cb) {
			this.name = name;
			this.cb = cb;
		}

		@Override
		public Object call() throws Exception {
			Thread.sleep(1000);
			cb.await();
			System.out.println(name + " I am done");
			return "I have compleated my task " + name;
		}
	}
}
