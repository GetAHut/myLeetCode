package com.xyt.juc.jucdemo.threadactiveness;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fox
 * 活锁
 */
@Slf4j
public class LiveLockTest {

    /**
     * 定义一个勺子，ower 表示这个勺子的拥有者
     */
    static class Spoon {
        Diner owner;

        public Spoon(Diner diner) {
            this.owner = diner;
        }

        public String getOwnerName() {
            return owner.getName();
        }

        public void setOwner(Diner diner) {
            this.owner = diner;
        }

        //表示正在用餐
        public void use() {
            log.info( "{} 用这个勺子吃饭.",owner.getName());
        }
    }

    /**
     * 定义一个晚餐类
     */
    static class Diner {

        private boolean isHungry;
        //用餐者的名字
        private String name;

        public Diner(boolean isHungry, String name) {
            this.isHungry = isHungry;
            this.name = name;
        }

        //和某人吃饭
        public void eatWith(Diner diner, Spoon sharedSpoon) {
            try {
                synchronized (sharedSpoon) {
                    while (isHungry) {
                        //当前用餐者和勺子拥有者不是同一个人，则进行等待
                        while (!sharedSpoon.getOwnerName().equals(name)) {
                            sharedSpoon.wait();
                        }
                        if (diner.isHungry()) {
                            log.info( "{}：亲爱的我饿了，然后{}把勺子给了{}",
                                    diner.getName(),name,diner.getName());
                            sharedSpoon.setOwner(diner);
                            //唤醒等待的线程
                            sharedSpoon.notifyAll();
                        } else {
                            //用餐
                            sharedSpoon.use();
                            sharedSpoon.setOwner(diner);
                            isHungry = false;
                        }
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                log.info("{} is interrupted.",name);
            }
        }

        public boolean isHungry() {
            return isHungry;
        }

        public String getName() {
            return name;
        }

    }

    public static void main(String[] args) {
        final Diner husband = new Diner(true, "丈夫");
        final Diner wife = new Diner(true, "妻子");
        //最开始牛郎持有勺子
        final Spoon sharedSpoon = new Spoon(husband);

        //织女和牛郎吃饭
        Thread h = new Thread(()->wife.eatWith(husband, sharedSpoon));
        h.start();

        //牛郎和织女吃饭
        Thread w = new Thread(()->husband.eatWith(wife, sharedSpoon));
        w.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       h.interrupt();
       w.interrupt();

    }

}
