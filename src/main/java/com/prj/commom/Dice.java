package com.prj.commom;

import java.util.Random;

public class Dice {
    private static Random randGen;

    static {
        setRandGenerator(new Random());
    }

    public static int play() {
        return nextPositiveInt(6) + nextPositiveInt(6);
    }

    public static boolean tryLuck() {
        return nextInt(6) == nextInt(6);
    }

    public static int nextInt(int bound) {
        return randGen.nextInt(bound);
    }

    public static int nextPositiveInt(int bound) {
        return nextInt(bound) + 1;
    }

    public static void setRandGenerator(Random generator) {
        randGen = generator;
    }
}
