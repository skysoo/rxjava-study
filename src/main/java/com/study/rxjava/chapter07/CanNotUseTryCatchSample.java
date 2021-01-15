package com.study.rxjava.chapter07;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CanNotUseTryCatchSample {
    public static void main(String[] args) {
        try {
            Observable.just(2)
                    .map(num -> num /0)
                    .subscribe(System.out::println);
        } catch (Exception e) {
            log.error("# 에러 처리가 필요 : "+e);
        }
    }
}
