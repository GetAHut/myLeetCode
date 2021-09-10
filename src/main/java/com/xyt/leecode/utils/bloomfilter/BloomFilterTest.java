package com.xyt.leecode.utils.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Author: xyt
 * @Description: 布隆过滤器测试
 * @Date: 2021/9/10 13:00
 */
public class BloomFilterTest {

    public static void main(String[] args) {
        int dataSize = 100000000;   //数据个数
        double fpp = 0.001;     //误判率

        //创建布隆过滤器
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), dataSize, fpp);

        //加入
        for (int i = 0; i < dataSize; i++) {
            bloomFilter.put(i);
        }
        
        //测试误判率
        int count = 0;
        for (int i = 100000001; i < 200000001; i++) {
            //mightContain 判断是否存在过滤器中
            if (bloomFilter.mightContain(i)){
                count ++;
            }
        }
        //误判个数：100121
        System.out.println(count);
    }
}
