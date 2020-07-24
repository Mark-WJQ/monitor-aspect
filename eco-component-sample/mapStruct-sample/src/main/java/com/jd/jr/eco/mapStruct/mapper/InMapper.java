package com.jd.jr.eco.mapStruct.mapper;

import com.jd.jr.eco.mapStruct.domain.InPo;
import com.jd.jr.eco.mapStruct.domain.InVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wangjianqiang24
 * @date 2020/7/24
 */
@Mapper
public interface InMapper {

    InMapper INSTANCE = Mappers.getMapper(InMapper.class);

    InVo map(InPo inPo);

}
