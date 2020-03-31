package com.lrj.myblogmybatis.common;

import java.util.UUID;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/7 20:30
 */


public class UUIDUtils {
    /**
     * 生成指定数量的uuid
     * 字符数组
     * @param number
     * @return
     */
    public static String[] getUUid(int number){
        if(number < 1){
            return null;
        }
        String[] strings = new String[number];
        for (int i=0; i< number; i++){
            strings[i] = getUUID32();
        }
        return strings;
    }
    //得到32位的
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }


    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };

    /**
     * 生成8位UUId
     *
     * @return
     */
    public static  String generateUuid8() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    public static void main(String[] args) {
       /* String[] uUid = UUIDUtils.getUUid(6);
        for (int i=0; i<uUid.length;i++){
            System.out.println(uUid[i]);
        }*/
        String s = generateUuid8();
        System.out.println(s);
    }
}
