/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.excelsheetcoltitle;

/**
 * https://leetcode-cn.com/problems/excel-sheet-column-title/
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 */
public class ExcelSheetColTitle {

    public static void main(String[] args) {
        ExcelSheetColTitle instance = new ExcelSheetColTitle();
        System.out.println(instance.convertToTitle(701));
    }

    private static final int NUM_OF_LETTERS = 26;

    /**
     * 很简单 实际上就是数字转26进制 然后每位转为对应的字母即可
     */
    public String convertToTitle(int columnNumber) {
        int colNum = columnNumber;
        StringBuilder colTitle = new StringBuilder();
        while (colNum > 0) {
            colNum--;
            int num = colNum % NUM_OF_LETTERS;
            char letter = (char) ('A' + num);
            colTitle.append(letter);
            colNum /= NUM_OF_LETTERS;
        }
        colTitle.reverse();
        return colTitle.toString();
    }

}
