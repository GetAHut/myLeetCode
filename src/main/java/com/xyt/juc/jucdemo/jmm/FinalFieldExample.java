package com.xyt.juc.jucdemo.jmm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author  Fox
 *
 * https://docs.oracle.com/javase/specs/jls/se14/html/jls-17.html#jls-17.5
 */
public class FinalFieldExample {
    final int x;
    int y; 
    static FinalFieldExample f;

    public FinalFieldExample() {
        x = 3; 
        y = 4; 
    } 

    static void writer() {
        f = new FinalFieldExample();
    } 

    static void reader() {
        if (f != null) {
            int i = f.x;  // guaranteed to see 3  
            int j = f.y;  // could see 0
        }

    }

}