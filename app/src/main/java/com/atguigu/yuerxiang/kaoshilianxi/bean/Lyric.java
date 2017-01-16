package com.atguigu.yuerxiang.kaoshilianxi.bean;

/**
 * 作者：尚硅谷-杨光福 on 2016/11/25 14:24
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：一句歌词
 */
public class Lyric {
    //歌词内容
    private String content;
    //时间戳
    private long timePoint;
    //高亮持续时间
    private long sleepTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(long timePoint) {
        this.timePoint = timePoint;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public String toString() {
        return "Lyric{" +
                "content='" + content + '\'' +
                ", timePoint=" + timePoint +
                ", sleepTime=" + sleepTime +
                '}';
    }
}
