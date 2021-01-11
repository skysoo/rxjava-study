package com.study.rxjava.chapter05;

import com.study.rxjava.chapter03.Car;
import com.study.rxjava.chapter03.CarMaker;
import com.study.rxjava.chapter03.SampleData;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.GroupedObservable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ObservableGroupBySample02 {
    public static void main(String[] args) {
        Observable<GroupedObservable<CarMaker, Car>> observable =
                Observable.fromIterable(SampleData.carList)
                        .groupBy(car -> car.getCarMaker());

        observable.subscribe(groupedObservable -> groupedObservable
                .filter(car -> groupedObservable.getKey().equals(CarMaker.쉐보레))
                .subscribe(
                        car -> log.info("Group: " + groupedObservable.getKey()
                                + " Car Name: " + car.getCarName())
                ));

    }
}
