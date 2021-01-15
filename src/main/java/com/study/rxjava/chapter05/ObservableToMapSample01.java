package com.study.rxjava.chapter05;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

@Slf4j
public class ObservableToMapSample01 {
    public static void main(String[] args) {
        Single<Map<String, String>> single = Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                .toMap(data -> data.split("-")[0]);// 반환값은 Map의 Key가 된다.

        single.subscribe(map -> {
            Set<String> set = map.keySet();
            for (String s : set) {
                String value = map.get(s);
                log.info("key: " + s + " value: "+ value);
            }
        });
    }
}
