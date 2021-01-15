package com.study.rxjava.chapter07;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableOnErrorResumeSample {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .doOnNext(data -> log.info(String.valueOf(data)))
                        .map(i -> i / 0)
                        .onErrorResumeNext(throwable -> {
                            log.error("# 운영자에게 메일을 발송 : {}", throwable.getMessage());
                            return Observable.interval(200L, TimeUnit.MILLISECONDS)
                                    .take(5)
                                    .skip(1)
                                    .map(i -> num/i);
                        }))
                .subscribe(
                        data -> log.info(String.valueOf(data))
                );
        Thread.sleep(1000L);
    }
}
