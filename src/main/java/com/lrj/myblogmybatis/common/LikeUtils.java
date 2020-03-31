package com.lrj.myblogmybatis.common;

import com.lrj.myblogmybatis.enums.BType;

/**
 * @author ：xiximai
 * @description：TODO
 * @date ：2020/3/20 21:27
 */


public class LikeUtils {
    public static String getLikeKey(BType bType, Object subjectId){
        return bType + ":" + subjectId;
    }

    public static String getDisLikeKey(BType bType, Object subjectId){
        return bType + ":" + subjectId;
    }

}
