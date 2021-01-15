package com.study.rxjava.chapter06;

import com.study.rxjava.util.SampleData;
import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ObservableConcatSample01 {
    public static void main(String[] args) throws InterruptedException {
        List<Observable<String>> speedPerSectionList = Arrays.asList(
                SampleData.getSpeedPerSection("A", 50L, SampleData.speedOfSectionA),
                SampleData.getSpeedPerSection("B", 200L, SampleData.speedOfSectionB),
                SampleData.getSpeedPerSection("C", 100L, SampleData.speedOfSectionC)
        );

        Observable.concat(speedPerSectionList)
                .subscribe(data -> log.info(data));

        Thread.sleep(2000L);
    }
}
