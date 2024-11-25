package hw2.task1;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Class<?> clazz = String.class;
        Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods).forEach(System.out::println);
    }
}
