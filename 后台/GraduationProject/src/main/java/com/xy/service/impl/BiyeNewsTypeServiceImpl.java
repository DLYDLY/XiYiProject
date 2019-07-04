package com.xy.service.impl;

import com.xy.mapper.BiyeNewsTypeMapper;
import com.xy.pojo.BiyeNewsType;
import com.xy.service.BiyeNewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuoZhengYing
 * @ClassName BiyeNewsTypeServiceImpl
 * @Description TDDO
 * @Date 2019/5/17 10:46
 * @Version 1.0
 */
@Service
public class BiyeNewsTypeServiceImpl implements BiyeNewsTypeService {

    @Autowired
    private BiyeNewsTypeMapper biyeNewsTypeMapper;

    /**
     * @return java.util.List<com.xy.pojo.BiyeNewsType>
     * @Author GuoZhengYing
     * @Description //TODO 查询所有的新闻栏目
     * @Date 10:47 2019/5/17
     * @Param []
     **/
    @Override
    public List<BiyeNewsType> selectAll() {
        return biyeNewsTypeMapper.selectAll();
    }

    /**
     * @return java.util.List<com.xy.pojo.BiyeNewsType>
     * @Author GuoZhengYing
     * @Description //TODO 查询所有新闻栏目Id
     * @Date 10:48 2019/5/17
     * @Param []
     **/
    @Override
    public List<String> selectByTypeId() {
        List<String> list = new ArrayList<>();
        List<BiyeNewsType> newsTypes = biyeNewsTypeMapper.selectByTypeId();
        for (BiyeNewsType newsType : newsTypes) {
            list.add(newsType.getTypeId());
        }
        return list;
    }
}
