package com.study.rxjava.chapter03;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;

/**
 * defer()는 호출 시점 데이터 반영
 **/
@Slf4j
public class ObservableDeferSample {
    public static void main(String[] args) throws InterruptedException {
        Observable<LocalTime> observable = Observable.defer(()->{
            LocalTime localTime = LocalTime.now();
            return Observable.just(localTime);
        });

        Observable<LocalTime> observableJust = Observable.just(LocalTime.now());

        observable.subscribe(time->log.info("# defer() 구독1의 구독 시간 : "+time));
        observableJust.subscribe(time->log.info("# just() 구독1의 구독 시간 : "+time));

        Thread.sleep(3000);

        observable.subscribe(time->log.info("# defer() 구독2의 구독 시간 : "+time));
        observableJust.subscribe(time->log.info("# just() 구독3의 구독 시간 : "+time));

    }
}
