/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.spiralmatrix.area;

import xyz.lucis.leetcode.spiralmatrix.Location;

import java.util.Arrays;

public class Matrix implements Area {

    // num of row
    private final int rowLen;

    // num of cols
    private final int colLen;

    private final int[][] values;

    /**
     * init area
     *
     * @param rowLen num of rows
     * @param colLen num of cols
     */
    public Matrix(int rowLen, int colLen) {
        this.rowLen = rowLen;
        this.colLen = colLen;
        values = new int[rowLen][colLen];
    }

    @Override
    public boolean isLegalLocation(Location location) {
        int x = location.getX();
        int y = location.getY();
        return x >= 0 && x < colLen && y >= 0 && y < rowLen;
    }

    public void setValues(int y, int x, int value) {
        values[y][x] = value;
    }

    public void printValues() {
        System.out.println(Arrays.deepToString(values));
    }

    public int getRowLen() {
        return rowLen;
    }

    public int getColLen() {
        return colLen;
    }

    public int[][] getValues() {
        return values;
    }
}
