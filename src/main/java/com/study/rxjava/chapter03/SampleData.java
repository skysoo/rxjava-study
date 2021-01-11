package com.study.rxjava.chapter03;

import java.util.Arrays;
import java.util.List;

public class SampleData {
    public static List<Car> carList =
            Arrays.asList(
                    new Car(CarMaker.쉐보레, "말리부", 23_000_000),
                    new Car(CarMaker.현대, "그랜저", 23_000_000),
                    new Car(CarMaker.쉐보레, "트랙스", 23_000_000),
                    new Car(CarMaker.아우디, "A6", 23_000_000),
                    new Car(CarMaker.벤츠, "E-CLASS", 23_000_000)
            );
}
