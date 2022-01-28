package com.geekbrains.HomeWork;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTriangle {
    @Test
    void successCalculation() throws Exception {
        Assertions.assertEquals(Triangle.calculate(3, 4, 5), 6);
        Assertions.assertTrue(Math.abs(Triangle.calculate(3, 4, 5) - 6) < 0.0001);
        Assertions.assertEquals(6, Triangle.calculate(3, 4, 5), 0.0002);
    }

    @Test
    void negativeTestTriangle() {
        Assertions.assertThrows(Exception.class, () -> Triangle.calculate(-3, 4, 5));
    }
}
