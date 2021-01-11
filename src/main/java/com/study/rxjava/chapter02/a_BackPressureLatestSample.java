package com.study.rxjava.chapter02;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 통지된 데이터 중 가장 마지막 통지된 것을 처리함
 **/
@Slf4j
public class a_BackPressureLatestSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> log.info("통지 "+getThread() + "" +data))
                .onBackpressureLatest()
                // 소비자쪽에서 처리하는 스레드 별도 생성 bufferSize : 소비자쪽에서 처리할 갯수
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe(
                        data -> {
                            log.info("소비 "+getThread() + "" +data);
                            TimeUnit.MILLISECONDS.sleep(1000L);
                        },
                        error -> log.error(String.valueOf(error))
                );

        Thread.sleep(5500L);
    }

    public static String getThread() {
        return Thread.currentThread().getName() + " | " + LocalDateTime.now() + " | ";
    }
}
