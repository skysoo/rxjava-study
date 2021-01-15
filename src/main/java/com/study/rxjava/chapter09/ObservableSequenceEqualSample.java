package com.study.rxjava.chapter09;

import com.study.rxjava.util.CarMaker;
import com.study.rxjava.util.SampleData;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableSequenceEqualSample {
    public static void main(String[] args) throws InterruptedException {
        Observable<CarMaker> observable1 = Observable
                .fromArray(SampleData.carMakers)
                .subscribeOn(Schedulers.computation())
                .delay(carMaker -> {
                    Thread.sleep(500L);
                    return Observable.just(carMaker);
                })
                .doOnNext(data -> log.info("observable1 : {}",data));

        Observable<CarMaker> observable2 = Observable
                .fromArray(SampleData.carMakersDuplicated)
                .subscribeOn(Schedulers.computation())
                .delay(carMaker -> {
                    Thread.sleep(1000L);
                    return Observable.just(carMaker);
                })
                .doOnNext(data -> log.info("observable2 : {}",data));

        Observable.sequenceEqual(observable1, observable2)
                .subscribe(data -> log.info(String.valueOf(data)));

        Thread.sleep(5000l);
    }
}
