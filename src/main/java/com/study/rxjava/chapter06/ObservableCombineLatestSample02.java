package com.study.rxjava.chapter06;

import com.study.rxjava.util.SampleData;
import com.study.rxjava.util.NumberUtil;
import io.reactivex.rxjava3.core.Observable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObservableCombineLatestSample02 {
    public static void main(String[] args) throws InterruptedException {
        // 랜덤 온도
        Observable<Integer> observable1 = Observable.interval(NumberUtil.randomRange(100, 500), TimeUnit.MILLISECONDS)
                .take(10)
                .map(notUse -> SampleData.temperatureOfSeoul[NumberUtil.randomRange(0, 5)]);

        // 랜덤 습도
        Observable<Integer> observable2 = Observable.interval(NumberUtil.randomRange(100, 500), TimeUnit.MILLISECONDS)
                .take(10)
                .map(notUse -> SampleData.humidityOfSeoul[NumberUtil.randomRange(0, 5)]);

        // 통지되는 시점의 가장 마지막(최신) 데이터를 가져온다.
        Observable.combineLatest(observable1, observable2,
                (data1, data2) -> "최신 온습도 데이터 - 온도 : " + data1 + "도\t습도 : "+data2 + "%")
                .subscribe(data -> log.info(data));

        Thread.sleep(3000L);
    }
}
