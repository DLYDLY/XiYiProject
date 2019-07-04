package com.xy.service;

import com.xy.pojo.BiyeNewsType;

import java.util.List;

public interface BiyeNewsTypeService {

    List<BiyeNewsType> selectAll();

    List<String> selectByTypeId();
}
