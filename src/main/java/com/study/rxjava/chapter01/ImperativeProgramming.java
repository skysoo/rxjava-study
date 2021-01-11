package com.study.rxjava.chapter01;

import java.util.Arrays;
import java.util.List;

// 명령형 프로그래밍
public class ImperativeProgramming {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,3,21,10,8,11);
        int sum = 0;

        for(int number : numbers) {
            if(number > 6 && (number % 2 != 0)){
                sum += number;
            }
        }

        System.out.println("# 명령형 프로그래밍 사용 : "+ sum);
    }
}
