package com.lpan.top;

/**
 * Created by 刘攀
 * //类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
 */
public enum NewsType {

    TOP("top"), SHEHUI("shehui"), GUONEI("guonei"), GUOJI("guoji"), YULE("yule"), TIYU("tiyu"), JUNSHI("junshi"), KEJI("keji"),
    CAIJING("caijing"), SHISHANG("shishang");

    private String type;

    NewsType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
