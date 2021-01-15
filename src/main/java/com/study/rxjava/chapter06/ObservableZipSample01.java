package com.study.rxjava.chapter06;

import com.study.rxjava.chapter03.SampleData;
import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
public class ObservableZipSample01 {
    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable.fromIterable(SampleData.seoulPM10List);
        Observable<Integer> observable2 = Observable.fromIterable(SampleData.busanPM10List);
        Observable<Integer> observable3 = Observable.fromIterable(SampleData.incheonPM10List);

        Observable<Integer> observable4 = Observable.range(1, 24);

        Observable.zip(observable1, observable2, observable3, observable4,
                (data1, data2, data3, hour) -> hour + " |: "+ Collections.max(Arrays.asList(data1, data2, data3)))
                .subscribe(data -> log.info(data));
    }
}
