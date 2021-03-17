/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.spiralmatrix;

import jakarta.validation.constraints.NotNull;
import xyz.lucis.leetcode.spiralmatrix.area.Area;
import xyz.lucis.leetcode.spiralmatrix.area.Matrix;

import java.util.Locale;

/**
 * LeetCode 59 螺旋矩阵2
 * 给你一个正整数n ，生成一个包含 1 到n平方所有元素，且元素按顺时针顺序螺旋排列的n x n 正方形矩阵 matrix 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SpiralMatrix {

    public int[][] generateMatrix(int n) {
        // init the matrix
        Matrix matrix = new Matrix(n, n);
        // use searcher to traverse every index and init the
        initSpiralMatrix(matrix);
        // matrix.printValues();
        return matrix.getValues();
    }

    public void initSpiralMatrix(Matrix matrix) {
        Searcher searcher = new Searcher(Direction.RIGHT, new Location(0, 0), matrix);
        boolean[][] searched = new boolean[matrix.getRowLen()][matrix.getColLen()];
        int numOfMatrix = matrix.getColLen() * matrix.getRowLen();
        for (int i = 1; i <= numOfMatrix; i++) {
            int y = searcher.getLocation().getY();
            int x = searcher.getLocation().getX();
            matrix.setValues(y, x, i);
            searched[y][x] = true;
            if (i != numOfMatrix) {
                searcherMove(searcher, searched);
            }
        }
    }

    public void searcherMove(Searcher searcher, boolean[][] searched) {
        Location nextStep = searcher.getNextStep();
        if (searcher.getMatrix().isLegalLocation(nextStep) && !searched[nextStep.getY()][nextStep.getX()]) {
            searcher.move();
        } else {
            searcher.turnRight().move();
        }
    }

}

/**
 * Matrix Searcher
 */
class Searcher {
    private Direction direction;

    private Location location;

    private Area matrix;

    Searcher(@NotNull Direction direction, @NotNull Location startLocation, @NotNull Area desMatrix) {
        this.direction = direction;
        this.location = startLocation;
        this.matrix = desMatrix;
    }

    public Location getNextStep() {
        Location locationElem = new Location(this.location.getX(), this.location.getY());
        switch (direction) {
            case UP -> locationElem.setY(locationElem.getY() - 1);
            case DOWN -> locationElem.setY(locationElem.getY() + 1);
            case LEFT -> locationElem.setX(locationElem.getX() - 1);
            case RIGHT -> locationElem.setX(locationElem.getX() + 1);
        }
        return locationElem;
    }

    public Searcher turnRight() {
        switch (direction) {
            case UP -> direction = Direction.RIGHT;
            case DOWN -> direction = Direction.LEFT;
            case LEFT -> direction = Direction.UP;
            case RIGHT -> direction = Direction.DOWN;
        }
        return this;
    }

    public Searcher move() {
        location = getNextStep();
        if (!matrix.isLegalLocation(location)) {
            throw new RuntimeException(
                String.format(Locale.ENGLISH, "move out of area!x = %s, y = %s.", location.getX(), location.getY()));
        }
        return this;
    }

    public Direction getDirection() {
        return direction;
    }

    public Location getLocation() {
        return location;
    }

    public Area getMatrix() {
        return matrix;
    }
}