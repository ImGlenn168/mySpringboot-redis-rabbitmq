package com.java.myspringbootdemo02.App.utils;

import java.util.concurrent.*;

/**
 * 线程池工具
 */
public class ThreadPoolUtil {
    public static ThreadPoolExecutor threadPoolExecutor;
    public volatile static ExecutorService executorService;

    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    private static ThreadPoolExecutor getThreadPool() {
        if (threadPoolExecutor != null) {
            return threadPoolExecutor;
        } else {
            synchronized (ThreadPoolUtil.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS,
                            new LinkedBlockingDeque<>(32), new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }
        return threadPoolExecutor;
    }

    public static ExecutorService getExecutorService() {
        if (executorService != null) {
            return executorService;
        } else {
            synchronized (ThreadPoolUtil.class) {
                if (executorService == null) {
                    executorService = Executors.newFixedThreadPool(10);
                }
                return executorService;
            }
        }
    }
}
