package com.study.rxjava.chapter05;

import com.study.rxjava.chapter03.Car;
import com.study.rxjava.chapter03.CarMaker;
import com.study.rxjava.chapter03.SampleData;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.GroupedObservable;
import lombok.extern.slf4j.Slf4j;

/**
 * Car 제조사별로 그룹으로 묶어서 데이터를 통지하는 예제
 **/
@Slf4j
public class ObservableGroupBySample01 {
    public static void main(String[] args) {
        Observable<GroupedObservable<CarMaker, Car>> observable =
                Observable.fromIterable(SampleData.carList)
                .groupBy(car -> car.getCarMaker());

        observable.subscribe(groupedObservable -> groupedObservable.subscribe(
                        car -> log.info("Group: " + groupedObservable.getKey()
                                + " Car Name: " + car.getCarName())
                ));

    }
}
