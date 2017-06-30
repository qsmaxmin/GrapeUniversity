package com.sugar.grapecollege.common.download.threadpoll;

import android.support.annotation.NonNull;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/3/21 20:14
 * @Description
 */

public class CustomThreadPoll extends ThreadPoolExecutor {

    CustomThreadPoll(int threadCount) {
        super(threadCount, threadCount, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory("CustomThreadPoll", true));
    }

    /**
     * 默认线程创建方式
     *
     * @param name   名称
     * @param daemon true 表示守护线程 false 用户线程
     *               说明：守护线程:主线程挂掉也跟着挂掉. 用户线程:主线程挂掉不会跟着挂掉
     */
    private static ThreadFactory threadFactory(final String name, final boolean daemon) {
        return new ThreadFactory() {
            @Override public Thread newThread(@NonNull Runnable runnable) {
                DownloadThread result = new DownloadThread(runnable, name);
                result.setDaemon(daemon);
                return result;
            }
        };
    }
}
