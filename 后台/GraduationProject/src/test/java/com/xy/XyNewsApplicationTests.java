package com.xy;

import com.google.common.collect.Lists;
import com.xy.common.utils.RandomCode;
import com.xy.common.utils.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XyNewsApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate ;

    @Test
    public void contextLoads() {

        System.out.println(redisTemplate.opsForValue().get("0209cadb5771e10359c6031f9857e855"));
    }

    @Test
    public void test(){
        String title ="女子夫鞭打致死 父母放弃赔偿求重判";
        if (title.contains("\"")==false){
            System.out.println("这条mei有问题+");
        }
    }

    @Test
    public void test12(){
        SensitiveFilter filter = SensitiveFilter.DEFAULT;
        String sentence = "然鸡巴后，市长在婚礼上唱春天操你妈```垃圾在哪里";
        String filted = filter.filter(sentence, '*');
        System.out.println(filted);
    }

    @Test
    public void test4(){
        System.out.println(RandomCode.getRandNum(6));
    }

}
