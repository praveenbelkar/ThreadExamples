package com.praveen;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTaskExample {

	private static final ExecutorService threadPool = Executors.newFixedThreadPool(3);
	
	private static class FactorialCalculator implements Callable<Long> {
		private final Long number;
		
		public FactorialCalculator(Long number){
			this.number = number;
		}
		
		@Override
		public Long call(){
			return factorial(number);
		}
		
		private Long factorial(Long number){
			if(number ==0 || number ==1 ){
				return 1L;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return number * factorial(number-1);
			
		}

	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FactorialCalculator task = new FactorialCalculator(10L);
		Future<Long> future = threadPool.submit(task);
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		while(!future.isDone()){
			System.out.println("Waiting....");
		};
		
		System.out.println("value got "+future.get());
		
	}
}
