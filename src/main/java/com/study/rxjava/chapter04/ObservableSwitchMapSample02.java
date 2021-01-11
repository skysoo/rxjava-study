package com.study.rxjava.chapter04;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableSwitchMapSample02 {
    public static void main(String[] args) throws InterruptedException {
        LocalTime startTime = LocalTime.now();

        Searcher searcher = new Searcher();
        final List<String> keywords = Arrays.asList("M", "Ma", "Mal", "Malay");

        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .concatMap(data -> {
                    String keyword =  keywords.get(data.intValue());

                    return Observable.just(searcher.search(keyword))
                            .doOnNext(notUse -> System.out.println("==========================="))
                            .delay(1000L, TimeUnit.MILLISECONDS);
                })
                .flatMap(resultList -> Observable.fromIterable(resultList))
                .subscribe(data -> log.info(data),
                        error -> {},
                        () -> {
                        });
        Thread.sleep(6000L);
    }
}
