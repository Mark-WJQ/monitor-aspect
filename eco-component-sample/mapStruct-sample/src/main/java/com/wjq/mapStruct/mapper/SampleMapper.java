package com.wjq.mapStruct.mapper;

import com.wjq.mapStruct.domain.SamplePO;
import com.wjq.mapStruct.domain.SampleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
@Mapper(uses = InMapper.class)
public interface SampleMapper {

    SampleMapper INSTANCE = Mappers.getMapper(SampleMapper.class);


    @Mapping(source = "inPo",target = "inVo")
    SampleVO map(SamplePO po);



}
