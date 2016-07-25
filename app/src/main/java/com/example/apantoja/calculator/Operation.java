package com.example.apantoja.calculator;

/**
 * Created by apantoja on 7/19/2016.
 */
public class Operation {
    final float num1;
    final float num2;

    public Operation(float num1, float num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public float getNum1() {
        return num1;
    }

    public float getNum2() {
        return num2;
    }

    public float sum() {
        return num1 + num2;
    }
}
