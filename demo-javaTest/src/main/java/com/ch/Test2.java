package com.ch;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;

/**
 * @author: ch
 * @date: 2022/6/22 21:13
 * @description: TODO
 */
public class Test2 {
    public static void main(String[] args) {
        String[] words = {" abch", "baz", "foo", "bar", "xtfy", " abcdef"};
        System.out.println(maxProduct(words));

    }


    public static int bitNumber(char ch) {
        return (int) ch - (int) 'a';
    }

    public static int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];
        int[] len = new int[n];
        int bitmask = 0;
        for (int i = 0; i < n; i++) {
            bitmask = 0;
            for (char ch : words[i].toCharArray()) {
                bitmask |= (1 << bitNumber(ch));
            }
            masks[i] = bitmask;
            len[i] = words[i].length();
        }
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    maxVal = Math.max(maxVal, len[i] * len[j]);
                }
            }
        }
        return maxVal;
    }


}
