package com.zhou.aop;

import org.springframework.stereotype.Component;

@Component
public class MathCalculator {
    public int div(int i, int j) {
        return i / j;
    }
}
