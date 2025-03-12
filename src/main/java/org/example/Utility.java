package org.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Utility {
    public static <T extends Comparable<T>> void gSort(T[] array) {
        Arrays.sort(array);
    }

    public String printCollection (Collection<?> collection) {
        StringBuilder sb = new StringBuilder();
        for (Object item : collection) {
            sb.append(item.toString());
        }
        return sb.toString();
    }

    public Number sumOfNumberList(List<? extends Number> list) {
        if (list == null || list.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }


}