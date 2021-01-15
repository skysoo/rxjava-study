package com.study.rxjava.chapter06;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableCombineLatestSample01 {
    public static void main(String[] args) throws InterruptedException {
        Observable<Long> observable1 = Observable.interval(500L, TimeUnit.MILLISECONDS)
//                .doOnNext(data -> log.info("# observable 1 : "+data))
                .take(4);

        Observable<Long> observable2 = Observable.interval(700L, TimeUnit.MILLISECONDS)
//                .doOnNext(data -> log.info("# observable 2 : "+data))
                .take(4);

        Observable.combineLatest(
                observable1,
                observable2,
                (data1, data2) -> "data1 : " + data1 + "\tdata2 : " + data2)
                .subscribe(data -> log.info(data));

        Thread.sleep(3000);
    }
}
