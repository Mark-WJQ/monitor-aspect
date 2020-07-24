package com.jd.jr.eco.mapStruct.domain;

import java.time.LocalDateTime;

/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
public class SampleVO {

    private long id;

    private String name;

    private LocalDateTime createtime;

    private InVo inVo;

    public InVo getInVo() {
        return inVo;
    }

    public void setInVo(InVo inVo) {
        this.inVo = inVo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"createtime\":")
                .append(createtime);
        sb.append(",\"inVo\":")
                .append(inVo);
        sb.append("}");
        return sb.toString();
    }
}
