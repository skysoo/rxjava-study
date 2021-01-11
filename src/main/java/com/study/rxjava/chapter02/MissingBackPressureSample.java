package com.study.rxjava.chapter02;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;


/**
 * 배압이 안맞을 때 생기는 이슈 확인
 *
 * 통지되는 데이터보다 소비되는 데이터가 1000배 느리다..
 **/
@Slf4j
public class MissingBackPressureSample {
    public static void main(String[] args) throws InterruptedException {
        Flowable.interval(1L, TimeUnit.MILLISECONDS)
                .doOnNext(
                        data -> log.info(getThread() + "" +data))
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                            System.out.print(getThread());
                            System.out.println(" # 소비자 처리 대기 중..");
                            TimeUnit.MILLISECONDS.sleep(1000L);
                            log.info(String.valueOf(data));
                        },
                        error -> log.error(String.valueOf(error)),
                        () -> log.info("Success")
                );

       Thread.sleep(2000L);
    }

    public static String getThread() {
      return Thread.currentThread().getName() + " | ";
    }
}
