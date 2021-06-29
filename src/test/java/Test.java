/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 1; i ++) {
            test();
        }

    }

    private static void test() {
        int x=2;
        switch (x){
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
            default:
                System.out.println(4);
        }

    }

}
