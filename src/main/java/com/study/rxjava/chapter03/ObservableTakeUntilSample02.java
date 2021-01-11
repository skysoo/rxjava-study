package com.study.rxjava.chapter03;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableTakeUntilSample02 {
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .takeUntil(Observable.timer(5500L, TimeUnit.MILLISECONDS))
                .subscribe(data -> log.info(String.valueOf(data)));

        Thread.sleep(5500L);
    }
}
