package com.study.rxjava.chapter02;

import io.reactivex.rxjava3.core.Flowable;

public class c_ColdPublisherSample {
    public static void main(String[] args) {
        Flowable<Integer> flowable = Flowable.just(1, 3, 5, 7);

        flowable.subscribe(data-> System.out.println("구독자1 : "+data));
        flowable.subscribe(data-> System.out.println("구독자2 : "+data));
    }
}
