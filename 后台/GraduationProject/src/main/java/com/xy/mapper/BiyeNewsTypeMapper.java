package com.xy.mapper;

import com.xy.pojo.BiyeNewsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BiyeNewsTypeMapper {

    List<BiyeNewsType> selectAll();

    List<BiyeNewsType> selectByTypeId();
}
