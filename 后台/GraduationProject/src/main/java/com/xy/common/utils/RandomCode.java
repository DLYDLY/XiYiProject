package com.xy.common.utils;

import java.util.Random;

/**
 * @author GuoZhengYing
 * @ClassName RandomCode
 * @Description TDDO  获取随机验证码
 * @Date 2019/6/2 14:35
 * @Version 1.0
 */
public class RandomCode {

    public static String getRandStr(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 26) + 'a');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

}
