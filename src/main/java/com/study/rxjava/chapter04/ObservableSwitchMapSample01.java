package com.study.rxjava.chapter04;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 원본 소스의 처리 속도가 빨라서 현재 처리 중이던 작업을 중단하는 예제
 **/
@Slf4j
public class ObservableSwitchMapSample01 {
    public static void main(String[] args) throws InterruptedException {
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .skip(2)
                .doOnNext(data -> log.info(String.valueOf(data)))
                .switchMap(num -> Observable.interval(300L, TimeUnit.MILLISECONDS)
                                            .take(10)
                                            .skip(1)
                                            .map(row -> num + " * " + row + " = " + num * row))
                .subscribe(data -> log.info(data));

        Thread.sleep(5000);
    }
}
