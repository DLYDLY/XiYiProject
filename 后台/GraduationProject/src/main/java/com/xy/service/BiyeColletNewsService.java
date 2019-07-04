package com.xy.service;

import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.pojo.BiyeCollectNews;
import com.xy.pojo.NumPojo;

public interface BiyeColletNewsService {

    E3Result addColletNews(BiyeCollectNews biyeCollectNews);

    E3Result deleteColletNews(BiyeCollectNews biyeCollectNews);

    E3Result selectOne(String newID, String userId);

    EasyUIDataGridResult selectNewsByUser(int page, int rows, String userId);

    NumPojo selectNum();
}
