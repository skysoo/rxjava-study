package com.study.rxjava.chapter07;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableRetrySample01 {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(10, 12, 15, 16)
                .zipWith(Observable.just(1, 2, 0, 4), (a, b) -> {
                    int result;
                    try {
                        result = a / b;
                    } catch (Exception e) {
                        log.error("error : {}", e.getMessage());
                        throw e;
                    }
                    return result;
                })
                .retry(3)
                .onErrorReturn(throwable -> -1)
                .subscribe(
                        data -> log.info(String.valueOf(data)),
                        throwable -> log.error(throwable.getMessage()),
                        () -> log.info("complete")
                );
        Thread.sleep(6000L);
    }
}
