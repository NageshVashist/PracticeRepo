package executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyExecutor {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Task task1 = new MyExecutor().new Task("Task 1");
		Task task2 = new MyExecutor().new Task("Task 2");
		Task task3 = new MyExecutor().new Task("Task 3");
		Task task4 = new MyExecutor().new Task("Task 4");
		Task task5 = new MyExecutor().new Task("Task 5");

		Future future1 = threadPool.submit(task1);
		Future future2 = threadPool.submit(task2);
		Future future3 = threadPool.submit(task3);
		Future future4 = threadPool.submit(task4);
		Future future5 = threadPool.submit(task5);
		try {
			System.out.println(future1.get().toString());
			System.out.println(future2.get().toString());
			System.out.println(future3.get().toString());
			System.out.println(future4.get().toString());
			System.out.println(future5.get().toString());

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadPool.shutdown();
	}

	class Task implements Callable {
		private String name;

		public Task(String name) {
			this.name = name;
		}

		@Override
		public Object call() throws Exception {
			Thread.sleep(1000);
			return "I have compleated my task " + name;
		}
	}
}
