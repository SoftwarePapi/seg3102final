package com.developer.finalprojectseg3102.controllers;

/**
 * Created by Parastoo on 12/1/2019.
 */
public class Singleton {

    private static Singleton single_instance = null;

    // Setting default to params
    public int min = 1;
    public int max = 4;

    private Singleton()
    {
        min = 1;
        max = 5;
    }

    // static method to create instance of Singleton class
    public static Singleton getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
