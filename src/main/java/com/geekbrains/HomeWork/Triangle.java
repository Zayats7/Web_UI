package com.geekbrains.HomeWork;

public class Triangle {
    public static double calculate(double A, double B, double C) throws Exception {
        if (A < 0 || B < 0 || C < 0) throw new Exception();
        double half = (A + B + C) / 2;
        double square = (half * (half - A) * (half - B) * (half - C));
        return Math.sqrt(square);
    }
}
