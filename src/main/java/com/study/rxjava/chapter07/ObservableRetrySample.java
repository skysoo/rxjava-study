package com.study.rxjava.chapter07;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableRetrySample {
    private final static int RETRY_MAX = 5;

    public static void main(String[] args) throws InterruptedException {
        Observable.just(5)
                .flatMap(
                        num -> Observable
                                .interval(200L, TimeUnit.MILLISECONDS)
                                .map(i -> {
                                            long result;
                                            try {
                                                result = num / i;
                                            } catch (ArithmeticException e) {
                                                log.error("error : {}", e.getMessage());
                                                throw e;
                                            }
                                            return result;
                                        }
                                )
                                .retry(
                                        (retryCount, ex) -> {
                                            log.info("# 재시도 횟수 : {}", retryCount);
                                            Thread.sleep(1000L);
                                            return retryCount < RETRY_MAX ? true : false;
                                        }
                                )
                                .onErrorReturn(throwable -> 1L)
                )
                .subscribe(
                        data -> log.info(String.valueOf(data)),
                        throwable -> log.error(throwable.getMessage()),
                        () -> log.info("Complete")
                );
        Thread.sleep(6000L);
    }
}
