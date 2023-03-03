package com.xujin;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author XuJ
 */
public class PhoneCheck {
    private static final int PRE_LENGTH = 3;
    private static final String PRE_NR = "234";
    private static final String PRE_LWD = "250";
    private static final String PRE_KNY = "254";
    private static final String PRE_WGD = "256";
    private static final char PLUS = '+';
    private static final char ZERO = '0';
    private static final String[][] RULES = {
            {"+", PRE_NR, "10"},
            {"+", PRE_LWD, "9"},
            {"", PRE_KNY, "9"},
            {"+", PRE_WGD, "9"}
    };

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String phone = scan.next();
        if (phone.charAt(0) == PLUS) {
            phone = phone.substring(1);
        }
        //校验输入数据
        if (!checkInputAllOfNumber(phone)) {
            for (String[] rule : RULES) {
                formatOutput(rule[0], rule[1], null);
            }
            return;
        }
        for (String[] rule : RULES) {
            formatOutput(rule[0], rule[1], getPhone(rule[1], Integer.parseInt(rule[2]), phone));
        }

    }

    /**
     * @param phoneLength 当前国家手机号的长度
     * @param pre         根据前缀区分当前生成电话号的国家
     * @param target      包含区号的手机号
     * @return 格式化好的手机号
     */
    private static String getPhone(String pre, int phoneLength, String target) {
        String result;
        //在这个位置如果本身位数不够, 所以就返回null
        if (target.length() < phoneLength) {
            return null;
        }
        String phone = target.substring(target.length() - phoneLength);
        String prePhone = target.substring(0, target.length() - phoneLength);
        boolean areaCodeAndSomeZero = prePhone.length() >= PRE_LENGTH && checkPrefix(prePhone.substring(0, PRE_LENGTH)) && checkAllOfZero(prePhone.substring(PRE_LENGTH));
        if (checkAllOfZero(prePhone) || areaCodeAndSomeZero) {
            if (PRE_LWD.equals(pre) && phone.charAt(0) == ZERO) {
                result = null;
            } else {
                result = phone;
            }
        } else {
            result = null;
        }
        return result;
    }

    private static Boolean checkPrefix(String pre) {
        return pre.equals(PRE_NR) || pre.equals(PRE_LWD) || pre.equals(PRE_KNY) || pre.equals(PRE_WGD);
    }

    private static Boolean checkAllOfZero(String phone) {
        for (char aChar : phone.toCharArray()) {
            if (aChar != ZERO) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private static Boolean checkInputAllOfNumber(String phone) {
        //校验是否都是数字
        for (char aChar : phone.toCharArray()) {
            if (!Character.isDigit(aChar)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private static void formatOutput(String plus, String pre, String phone) {
        System.out.println(Objects.isNull(phone) ? "无" : plus + pre + phone);
    }
}
