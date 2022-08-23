package com.codefans.springboot.utils;

import java.io.*;
import java.util.Base64;

/**
 * @Author: codefans
 * @Date: 2022-07-28 14:47
 */

public class Base64Utils {

    private static final String CHARSET_NAME = "UTF-8";

    public static String encode(String content) throws UnsupportedEncodingException {
        return encode(content.getBytes(CHARSET_NAME));
    }

    public static String encode(byte[] content) throws UnsupportedEncodingException {
        byte[] byteBuff = Base64.getEncoder().encode(content);
        return new String(byteBuff, CHARSET_NAME);
    }

    public static String encode(File file) throws IOException {
        InputStream is = null;
        byte[] byteContent = new byte[0];
        try {
            is = new FileInputStream(file);
            int len = toNum((int)file.length());
            byteContent = new byte[len];
            is.read(byteContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                is.close();
                is = null;
            }
        }

        return encode(byteContent);
    }

    /**
     * 将n转成比n大的最小的2的倍数的数
     * @param n
     * @return
     */
    private static int toNum(int n) {
        return ((n & 1) != 0) ? (n + 1) : n;
    }

    public static String encodeFile(String filePath) throws IOException {
        File file = new File(filePath);
        return encode(file);
    }
}
