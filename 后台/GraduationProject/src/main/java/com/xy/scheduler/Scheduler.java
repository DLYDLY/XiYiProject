package com.xy.scheduler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xy.service.BiyeNewsService;
import com.xy.service.BiyeNewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: xyh
 * @Date: 2019/5/15 10:36
 * @Description:
 */

@Component
public class Scheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private BiyeNewsService biyeNewsService;

    @Autowired
    private  BiyeNewsTypeService biyeNewsTypeService;

    //执行次数
    int all=0;
    //数据库存在的条数
    int exist=0;
    //上次插入
    int e0=0;

    //获得api新闻类别id
    public List<String> stringList (){
        List<String> list = biyeNewsTypeService.selectByTypeId();
        return list;
    }

    //30分钟执行一次爬新闻方法
    //@Scheduled(fixedRate = 1800000)
    public void testTasks() {
        System.out.println("正在执行...");
        e0=biyeNewsService.rm();
        //类别添加失败的新闻id
        List<String> errlist = new ArrayList<>();
        int sucess=0,err=0;
        //新闻类别id
          List<String> strings =stringList();
        for (String string : strings) {
            try {
               exist= biyeNewsService.addnews(string);
                sucess+=1;
            }catch (Exception e){
                errlist.add(string);
                err+=1;
            }
        }
        all+=1;
        System.out.println("第"+all+"次执行,共有42个新闻分类,成功了"+sucess+"个,失败了"+err+"个,共向数据库插入了："+(biyeNewsService.rm()-e0)+"条数据，共有："+exist+"条数据库已存在不插入的数据，执行时间：" + dateFormat.format(new Date()));
        //失败类别调用方法重新插入
        erradd(errlist);
    }
    //上次失败重新执行插入
    public void erradd(List<String> errlist){
        int all = errlist.size();
        int i = errlist.size();
        //尝试插入失败类别次数为30次
        for (int c=0;c<30;c++){
            if (i>0){
                for (String s : errlist) {
                    try {
                        biyeNewsService.addnews(s);
                        errlist.remove(s);
                        i-=1;
                        break;
                    }catch (Exception e){
                        break;
                    }
                }
            }
        }
        System.out.println("全部失败类别重新插入完成,共有"+all+"个类别,失败类别："+i+"，向数据库插入了："+(biyeNewsService.rm()-e0)+"条数据");
    }

}
