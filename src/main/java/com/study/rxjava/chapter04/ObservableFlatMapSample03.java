package com.study.rxjava.chapter04;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableFlatMapSample03 {
    public static void main(String[] args) {
        Observable.range(2,1)
                .flatMap(num -> Observable.range(1,9),
                        ((sourceData, transformData) ->
                                sourceData + " * " +  transformData + " = " + sourceData * transformData)
                )
                .subscribe(data -> log.info(String.valueOf(data)));
    }
}
