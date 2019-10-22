package com.leyou.common.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lujianrong
 */
public class ThreadUtils {

    private static final ExecutorService es = Executors.newFixedThreadPool(10);

    public static void execute(Runnable runnable) {
        es.submit(runnable);
    }
}