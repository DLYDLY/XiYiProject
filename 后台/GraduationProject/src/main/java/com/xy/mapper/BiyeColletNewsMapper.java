package com.xy.mapper;

import com.xy.pojo.BiyeCollectNews;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author GuoZhengYing
 * @ClassName BiyeColletNewsMapper
 * @Description TDDO
 * @Date 2019/6/3 12:00
 * @Version 1.0
 */
public interface BiyeColletNewsMapper {
    //添加
    void userAddColletNews(BiyeCollectNews biyeCollectNews);

    //删除
    void deleteCollectNews(Long Id);

    //查询
    List<String> selectCollectNews(String userId);

    //查询
    Long selectByNews(BiyeCollectNews biyeCollectNews);

    BiyeCollectNews selectOne(@Param("newsID") String newsID, @Param("userId") String userId);

    //显示数据库新闻的条数
    int selectNewsNum();

    //显示数据库用户的个数
    int selectUserNum();
}
