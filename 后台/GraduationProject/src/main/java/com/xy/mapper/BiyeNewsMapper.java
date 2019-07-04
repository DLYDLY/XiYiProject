package com.xy.mapper;

import com.xy.pojo.BiyeNews;
import com.xy.pojo.BiyeNewsImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: xyh
 * @Date: 2019/5/15 19:22
 * @Description:
 */
public interface BiyeNewsMapper {

    void insert(BiyeNews biyeNews);

    List<String> selectnews(String newsTitle);

    void userinsert(BiyeNews biyeNews);

    void addimg(BiyeNewsImage biyeNewsImage);

    String selectimagePath(String imageId);

    List<String> selectimagePathByNewsid(String newsId);

    List<String> selectimageId(String newsId);

    BiyeNews selectonenews(String newsId);

    /**
     * @return void
     * @Author GuoZhengYing
     * @Description //TODO 置顶
     * @Date 10:32 2019/5/24
     * @Param [newsId]
     **/
    void stickNews(String newsId);


    /**
     * @return java.util.List<com.xy.pojo.BiyeNews>
     * @Author GuoZhengYing
     * @Description //TODO 显示所有新闻
     * @Date 18:47 2019/5/29
     * @Param []
     **/
    List<BiyeNews> seeTopNews();

    /**
     * @return java.util.List<com.xy.pojo.BiyeNews>
     * @Author GuoZhengYing
     * @Description //TODO 查询显示新闻搜索框
     * @Date 11:18 2019/5/29
     * @Param [newsName]
     **/
    List<BiyeNews> searchBox(String Title);

    /**
     * @return java.util.List<com.xy.pojo.BiyeNews>
     * @Author GuoZhengYing
     * @Description //TODO 查看所有置顶的新闻前台
     * @Date 9:56 2019/5/30
     * @Param []
     **/
    List<String> checkTopNews();

    /**
     * @Author GuoZhengYing
     * @Description //TODO 查看所有置顶的新闻后台
     * @Date 17:32 2019/5/31
     * @Param []
     * @return java.util.List<com.xy.pojo.BiyeNews>
     **/
    List<BiyeNews> lookTopNews();

    /**
     * @return void
     * @Author GuoZhengYing
     * @Description //TODO 新闻下架
     * @Date 10:20 2019/5/30
     * @Param [newsId]
     **/
    void soldOut(String newsId);


    /**
     * @return void
     * @Author GuoZhengYing
     * @Description //TODO 取消置顶
     * @Date 16:58 2019/5/31
     * @Param [newsId]
     **/
    void cancelNew(String newsId);
}
