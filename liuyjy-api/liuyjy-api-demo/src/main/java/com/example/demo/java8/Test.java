package com.example.demo.java8;

/**
 * @Author liuyjy
 * @Date 2019/8/19
 * @Description: TODO
 **/
public class Test {



    public static void main(String[] args) {
        Test test=new Test();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println("10 + 5 = " + test.operate(10, 5, addition));

    }
    interface MathOperation {
        int operation(int a, int b);
    }
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
