package com.renshihan.study.archaius.study.scheduler;

import com.netflix.config.AbstractPollingScheduler;

public class MyScheduler extends AbstractPollingScheduler {
    @Override
    protected void schedule(Runnable runnable) {
        //可运行
    }

    @Override
    public void stop() {
        //停止运行
    }
}
