package com.study.rxjava.chapter02;

import io.reactivex.rxjava3.core.BackpressureOverflowStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Slf4j
public class a_BackPressureBufferSample01 {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(
                        data -> log.info(getThread() + "" +data))
                // 버퍼 용량, 버퍼 전략
                .onBackpressureBuffer(
                        2,
                        () -> log.info("overflow!"),
                        BackpressureOverflowStrategy.DROP_LATEST
                )
                .doOnNext(data -> log.info("#onBackPressureBuffer doOnNext()", data))
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

        Thread.sleep(2800L);
    }

    public static String getThread() {
        return Thread.currentThread().getName() + " | " + LocalDateTime.now() + " | ";
    }
}
