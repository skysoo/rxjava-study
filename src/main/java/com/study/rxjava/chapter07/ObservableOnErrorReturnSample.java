package com.study.rxjava.chapter07;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableOnErrorReturnSample {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .doOnNext(data -> log.info(String.valueOf(data)))
                        .map(i -> i / 0)
                        .onErrorReturn(throwable -> {
                            if (throwable instanceof ArithmeticException)
                                log.error("계산 에러 처리 발생 : {}", throwable.getMessage());
                            return -1L;
                        }))
                .subscribe(
                        data -> {
                            if(data<0)
                                log.error("# 예외를 알리는 데이터: {}", data);
                            else
                                log.info(String.valueOf(data));
                        },
                        throwable -> log.error("# 에러 처리가 필요 : " + throwable.getMessage()),
                        () -> log.info("Complete")
                );
        Thread.sleep(1000L);
    }
}
