package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Random random = new Random();
        RandomArray randomArray = new RandomArray(random);
        Scanner scanner = new Scanner(System.in);
        System.out.println("start?");
        int value = scanner.nextInt();
        System.out.println(value);
        int count = value;
        value = 0;
        randomArray.insert(count);
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(randomArray);
        System.out.println("finish?");
        String f = scanner.next();
        System.out.println("done!");

    }
}
