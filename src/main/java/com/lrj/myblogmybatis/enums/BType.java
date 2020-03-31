package com.lrj.myblogmybatis.enums;

public enum BType {
    LIKED_ARTICLE("文章点赞"), //private static final BType LIKE_ARTICLE = new BType("文章点赞");
    DISLIKED_ARTICLE("文章踩"),
    ;

    private String bType;

    BType(String bType) {
        this.bType = bType;
    }

    public String getbType() {
        return bType;
    }

    public void setbType(String bType) {
        this.bType = bType;
    }
}
