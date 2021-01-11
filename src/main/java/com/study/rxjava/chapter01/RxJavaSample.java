package com.study.rxjava.chapter01;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxJavaSample {
    public static void main(String[] args) throws InterruptedException {
        Observable.just(100,200,300,400,500) // 발행
                .doOnNext(data -> System.out.println(getThreadName() + " : #doOnNext() : "+ data))
                .subscribeOn(Schedulers.io()) // main Thread가 아닌 다른 스레드에서 실행한다.
                .observeOn(Schedulers.computation())
                .filter(number->number > 300) // 가공
                .subscribe(num -> System.out.println(getThreadName() + " : result : "+num)); // 처리

        Thread.sleep(500);
    }

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }
}
