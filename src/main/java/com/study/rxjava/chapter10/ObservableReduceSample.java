package com.study.rxjava.chapter10;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

/**
 * reduce 는 누적 값만을 리턴한다.
 *
 * scan 은 통지된 데이터의 누적되는 순간의 과정을 리턴한다.
 **/
@Slf4j
public class ObservableReduceSample {
    public static void main(String[] args) {
        Observable.just(1,2,3,4,5,6,7,8,9,10)
//                .map(data -> data * 2)
//                .doOnNext(System.out::println)
//                .reduce((x,y) -> x+y)
                .scan((x,y)->x+y)
                .subscribe(data -> log.info(String.valueOf(data)));
    }
}
