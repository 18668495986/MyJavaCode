package utils.allUtil;

import java.io.UnsupportedEncodingException;

/**
 * Description：xxx源程序<br/>
 * Copyright (c) ，2020 ， xu <br/>
 * This program is protected by copyright laws. <br/>
 * Date： 2020年01月01日
 *
 * @author 徐威
 * @version : 1.0
 */
public class MyEncode { //解决乱码的方法类

    public static String ISO2UFT8(String word) {
        byte[] bytes = null;
        String wordUtf8 = null;
        try {
            bytes = word.getBytes("ISO-8859-1");
            wordUtf8 = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return wordUtf8;
    }
}
