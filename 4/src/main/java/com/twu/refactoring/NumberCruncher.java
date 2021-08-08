package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        MathOperation operation = (a)->(a%2==0);
        return compute(operation);
    }

    private int compute(MathOperation operation) {
        int count = 0;
        for (int number : numbers) {
            if (operation.operation(number)) count++;
        }
        return count;
    }

    public int countOdd() {
        MathOperation operation = (a)->(a%2==1);
        return compute(operation);
    }

    public int countPositive() {
        MathOperation operation = (a)->(a>=0);
        return compute(operation);
    }

    public int countNegative() {
        MathOperation operation = (a)->(a<0);
        return compute(operation);
    }

    interface MathOperation {
        Boolean operation(int a);
    }
}
