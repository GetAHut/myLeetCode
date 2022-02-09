package com.xyt.leecode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Author: xyt
 * @Date: 2022/1/24 9:46
 * @version: 1.0.0
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        Random random = new Random();

        for(int i = 0; i < 16; i++) {
            set.add(random.nextInt(20));
        }
        log.info("set : -> {}", set.toString());

    }
}
