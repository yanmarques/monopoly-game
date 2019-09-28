package com.prj.commom;

import java.util.Random;

public class Dice {
    public static int play() {
        return nextPositiveInt(6) + nextPositiveInt(6);
    }

    public static int nextPositiveInt(int bound) {
        Random randGen = new Random();
        return randGen.nextInt(bound) + 1;
    }
}
