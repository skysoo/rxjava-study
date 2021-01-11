package com.study.rxjava.chapter03;

import io.reactivex.rxjava3.core.Single;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class SingleJustSample {
    public static void main(String[] args) {
        Single<String> single = Single.just(String.valueOf(LocalDateTime.now()));
        single.subscribe(
                data -> log.info("# 날짜 시간 {}",data),
                error -> log.error(String.valueOf(error))
        );
    }
}
