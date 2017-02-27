package cn.getcube.develop.utils;

import java.util.ArrayList;

/**
 * Created by Rainbow on 16-6-3.
 */
public class ArrayNumberUtils {

    public static int[] getArrayNumber(String startNumber, String endNumber) {
        int start = 0;
        int end = 0;
        if (startNumber != null) start = Integer.parseInt(startNumber);
        if (endNumber != null) end = Integer.parseInt(endNumber);

        int[] ints = new int[end - start + 1];

        for (int i = start; i <= end; i++) {
            ints[i - start] = i;
        }
        return ints;
    }

}
