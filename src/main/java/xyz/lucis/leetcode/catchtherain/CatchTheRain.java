/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.catchtherain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CatchTheRain {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);) {
            String line = sc.nextLine();
            String arrStr = line.substring(line.indexOf('[') + 1, line.indexOf(']'));

            String[] arr = arrStr.split(",");
            List<Integer> nums = Arrays.stream(arr).map(Integer::valueOf).collect(Collectors.toList());
            List<Rain> rains = new ArrayList<>(nums.size());
            for (int i = 0; i < nums.size(); i++) {
                Rain rain = new Rain();
                rain.setIndex(i);
                rain.setHeight(nums.get(i));
                rains.add(rain);
            }
        }
    }

    private static int trap(int[] height) {
        List<Rain> rains = new ArrayList<>(height.length);
        for (int i = 0; i < height.length; i++) {
            Rain rain = new Rain();
            rain.setIndex(i);
            rain.setHeight(height[i]);
            rains.add(rain);
        }

        int captureRain = 0;
        LinkedList<Rain> stack = new LinkedList<>();
        for (Rain rain : rains) {
            while (!stack.isEmpty()) {
                Rain top = stack.getLast();
                if (top.height >= rain.height) {
                    if (top.height == rain.height) {
                        stack.removeLast();
                    }
                    break;
                }

                if (rain.getHeight() > top.getHeight()) {

                    if (stack.size() <= 1) {
                        stack.removeLast();
                        continue;
                    }

                    // catch the rainbow!
                    Rain left = stack.get(stack.size() - 2);
                    int h = Math.min(left.height, rain.height) - top.height;
                    int width = rain.index - left.index - 1;
                    captureRain += h * width;

                    // pop stack
                    stack.removeLast();
                }
            }
            stack.add(rain);
        }

        return captureRain;
    }

    static class Rain {
        private int index;

        private int height;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

}
