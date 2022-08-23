package utils;

import com.codefans.springboot.utils.Base64Utils;

import java.io.IOException;

/**
 * @Author: codefans
 * @Date: 2022-07-28 17:09
 */

public class Base64UtilsTest {

    public static void main(String[] args) {
        String imgPath = "C:\\Users\\caishengzhi\\Videos\\img_404.png";
        String imgBase64Str = "";
        try {
            imgBase64Str = Base64Utils.encodeFile(imgPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(imgBase64Str);
    }

}
