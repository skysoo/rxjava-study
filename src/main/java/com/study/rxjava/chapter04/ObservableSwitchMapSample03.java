package com.study.rxjava.chapter04;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * switchMap 을 사용했기 때문에 새로운 데이터가 통지되는 경우 이전의 데이터는 중단된다.
 * 따라서 concatMap을 사용했을 때, M, Ma, Mal 등 모두 결과가 검색이 된다. 이것은 우리가 원하는 결과가 아니기 때문에 필요없다.
 * 이를 switchMap으로 사용함으로써 Malay 결과만 출력하므로 성능이 향상 된다.
 **/
@Slf4j
public class ObservableSwitchMapSample03 {
    public static void main(String[] args) throws InterruptedException {
        LocalTime startTime = LocalTime.now();

        Searcher searcher = new Searcher();
        final List<String> keywords = Arrays.asList("M", "Ma", "Mal", "Malay");

        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .switchMap(data -> {
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
