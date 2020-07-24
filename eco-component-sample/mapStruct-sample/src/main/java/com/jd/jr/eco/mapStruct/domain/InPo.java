package com.jd.jr.eco.mapStruct.domain;

import java.util.Date;

/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
public class InPo {

    private Date date;

    private int id;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"date\":\"")
                .append(date).append('\"');
        sb.append(",\"id\":")
                .append(id);
        sb.append("}");
        return sb.toString();
    }
}
