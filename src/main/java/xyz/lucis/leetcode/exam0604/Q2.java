/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.exam0604;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class Q2 {

    /**
     * 获取各个行人通过的时间
     *
     * @param arrTime 乘客到达的时间
     * @param direction 乘客的方向
     * @return 乘客实际通过的时间
     */
    public int[] getTimes(int[] arrTime, int[] direction) {

        LinkedList<Traveler> in = new LinkedList<>();
        LinkedList<Traveler> out = new LinkedList<>();

        for (int i = 0; i < arrTime.length; i++) {
            Traveler traveler = new Traveler();
            traveler.setArrTime(arrTime[i]);
            traveler.setDirection(direction[i]);
            traveler.setNo(i);
            if (direction[i] == 0) {
                out.add(traveler);
            } else {
                in.add(traveler);
            }
        }
        // init machine
        Machine machine = new Machine();
        machine.setLastUseAt(-1);
        machine.setLastUseDirection(1);

        ArrayList<Traveler> travelerList = new ArrayList<>(arrTime.length);
        while (!in.isEmpty() || !out.isEmpty()) {
            Traveler traveler = handle(machine, in, out);
            travelerList.add(traveler);
        }
        travelerList.sort(Comparator.comparing(Traveler::getNo));
        int[] corssTimes = new int[travelerList.size()];
        for (int i = 0; i < corssTimes.length; i++) {
            corssTimes[i] = travelerList.get(i).getCrossAt();
        }
        return corssTimes;
    }

    private Traveler handle(Machine machine, LinkedList<Traveler> inList, LinkedList<Traveler> outList) {
        if (inList.isEmpty()) {
            // 只有人出
            Traveler out = outList.removeFirst();
            int crossAt = Integer.max(machine.getLastUseAt() + 1, out.getArrTime());
            updateStatus(machine, out, crossAt);
            return out;
        }

        if (outList.isEmpty()) {
            // 只有人进
            Traveler in = inList.removeFirst();
            int crossAt = Integer.max(machine.getLastUseAt() + 1, in.getArrTime());
            updateStatus(machine, in, crossAt);
            return in;
        }

        // 进出都有人
        Traveler in = inList.getFirst();
        Traveler out = outList.getFirst();
        if (in.getArrTime() < machine.getLastUseAt() + 1) {
            in.setArrTime(machine.getLastUseAt() + 1);
        }
        if (out.getArrTime() < machine.getLastUseAt() + 1) {
            out.setArrTime(machine.getLastUseAt() + 1);
        }

        if (in.getArrTime() == out.getArrTime()) {
            // 比较优先级
            int arrTime = in.getArrTime();
            if (getMachineFirstDirection(machine, arrTime) == 1) {
                in = inList.removeFirst();
                int crossAt = Integer.max(machine.getLastUseAt() + 1, in.getArrTime());
                updateStatus(machine, in, crossAt);
                return in;
            } else {
                out = outList.removeFirst();
                int crossAt = Integer.max(machine.getLastUseAt() + 1, out.getArrTime());
                updateStatus(machine, out, crossAt);
                return out;
            }
        } else {
            Traveler passed;
            if (in.getArrTime() < out.getArrTime()) {
                passed = in;
                inList.removeFirst();
            } else {
                passed = out;
                outList.removeFirst();
            }
            int crossAt = Integer.max(machine.getLastUseAt() + 1, passed.getArrTime());
            updateStatus(machine, passed, crossAt);
            return passed;
        }
    }

    private void updateStatus(Machine machine, Traveler traveler, int crossAt) {
        traveler.setCrossAt(crossAt);
        machine.setLastUseAt(crossAt);
        machine.setLastUseDirection(traveler.getDirection());
    }

    /**
     * 获取指定时间 闸机那边的乘客可以优先通过
     *
     * @param machine 闸机
     * @param arrTime 通过的时间
     * @return 优先通过的方向
     */
    private int getMachineFirstDirection(Machine machine, int arrTime) {
        if (arrTime - machine.getLastUseAt() > 1) {
            return 1;
        } else {
            return machine.getLastUseDirection();
        }
    }

}

/**
 * 闸机
 */
class Machine {

    private int lastUseAt;

    private int lastUseDirection;

    public int getLastUseAt() {
        return lastUseAt;
    }

    public void setLastUseAt(int lastUseAt) {
        this.lastUseAt = lastUseAt;
    }

    public int getLastUseDirection() {
        return lastUseDirection;
    }

    public void setLastUseDirection(int lastUseDirection) {
        this.lastUseDirection = lastUseDirection;
    }
}

/**
 * 乘客
 */
class Traveler {
    private int no;

    private int arrTime;

    private int direction;

    private int crossAt;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getArrTime() {
        return arrTime;
    }

    public void setArrTime(int arrTime) {
        this.arrTime = arrTime;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCrossAt() {
        return crossAt;
    }

    public void setCrossAt(int crossAt) {
        this.crossAt = crossAt;
    }
}

