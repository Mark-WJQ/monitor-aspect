package com.wjq.mapStruct.mapper;

import com.wjq.mapStruct.domain.InPo;
import com.wjq.mapStruct.domain.SamplePO;
import com.wjq.mapStruct.domain.SampleVO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
class SampleMapperTest {

    static Logger logger = LoggerFactory.getLogger(SampleMapperTest.class);

    @Test
    void map() {
        SamplePO po = new SamplePO();
        po.setId(1);
        po.setCreatetime(LocalDateTime.now());
        po.setName("sample");

        InPo inPo = new InPo();
        inPo.setDate(new Date());
        po.setInPo(inPo);

        SampleVO vo = SampleMapper.INSTANCE.map(po);
        logger.info("{}",vo);

    }
}