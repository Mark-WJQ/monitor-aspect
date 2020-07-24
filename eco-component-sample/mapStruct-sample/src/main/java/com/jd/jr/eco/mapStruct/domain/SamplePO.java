package com.jd.jr.eco.mapStruct.domain;

import java.time.LocalDateTime;

/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
public class SamplePO {

    private long id;

    private String name;

    private LocalDateTime createtime;

    private InPo inPo;

    public InPo getInPo() {
        return inPo;
    }

    public void setInPo(InPo inPo) {
        this.inPo = inPo;
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
}
