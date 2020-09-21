package com.mad.trafficclient.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TimerThread {
	public static Thread newIntsance(final int time, final Runnable task) {
		 return new Thread(new Runnable() {

			@Override
			public void run() {
				Executor executor = Executors.newSingleThreadExecutor();
				while (true) {
					executor.execute(task);
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		 });
	}
}
