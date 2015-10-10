/**
 * 
 */
package com.digitalreasoning.thread;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tandon
 *
 */
public class ThreadPool {

	public static ExecutorService service = Executors.newFixedThreadPool(10);	

	public ThreadPool(String TaskId, Locale locale, Set NERWordSet,Map<String, String> configMap) {		
		service.execute(new Task(TaskId, locale, NERWordSet, configMap));		
	}
	public static void stopThreadPool(){		
	service.shutdown();
	}
}
