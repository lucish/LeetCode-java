/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2021-2021. All rights reserved.
 */

package xyz.lucis.leetcode.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileUtil
 */
public final class FileUtil {

    private FileUtil() {
    }

    /**
     * 读取文件内容
     *
     * @param filePath 文件路径
     * @return 文件内容
     * @throws IOException IOException
     */
    public static String getFileContent(String filePath) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            content.append(line).append(System.lineSeparator());
        }
        return content.toString();
    }

}
