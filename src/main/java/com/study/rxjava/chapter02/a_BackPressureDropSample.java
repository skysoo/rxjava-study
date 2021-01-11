package com.study.rxjava.chapter02;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
public class a_BackPressureDropSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> log.info(getThread() + "" +data))
                .onBackpressureDrop(data -> log.info(data + " Drop!"))
                // 소비자쪽에서 처리하는 스레드 별도 생성 bufferSize : 소비자쪽에서 처리할 갯수
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(
                        data -> {
                            System.out.print(getThread());
                            TimeUnit.MILLISECONDS.sleep(1000L);
                            log.info(String.valueOf(data));
                        },
                        error -> log.error(String.valueOf(error))
                );

        Thread.sleep(5500L);
    }

    public static String getThread() {
        return Thread.currentThread().getName() + " | " + LocalDateTime.now() + " | ";
    }
}
