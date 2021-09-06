package com.xyt.leecode.designMode.factory;

/**
 * @Author: xyt
 * @Description: 抽象工厂模式
 * @Date: 2021/9/6 17:48
 */
public class AbstractFactoryTest {

    /**
     * 与工厂模式区别 则是  抽象工厂模式是一系列的工厂方法。
     * 而工厂模式往往只有一个方法
     *
     * 优点：
     *  1. 可以确信你从工厂得到的产品彼此之间都是兼容的
     *  2. 可以避免产品与客户端代码之间的耦合
     *  3. 符合单一职责原则
     *  4. 符合开闭原则
     */
    public static void main(String[] args) {
        BaseUtilsFactory mysql = new MysqlBase();
        ICommand command = mysql.getCommand();
        command.command();
        IConnect connect = mysql.getConnect();
        connect.connect();

        //
        BaseUtilsFactory oracle = new OracleBase();
        ICommand oracleCommand = oracle.getCommand();
        oracleCommand.command();
        IConnect oracleConnect = oracle.getConnect();
        oracleConnect.connect();
    }
}
/**
 * 场景模拟
 *  设计一个数据库切换装置
 *      数据库有 mysql oracle
 *      数据库都需要连接 connect、都需要发送命令 command...
 */

interface IConnect{
    void connect();
}
interface ICommand{
    void command();
}

/**
 * 含有多个共同功能方法的工厂
 */
interface BaseUtilsFactory{
    IConnect getConnect();
    ICommand getCommand();
}

class MysqlConnect implements IConnect{

    @Override
    public void connect() {
        System.out.println("mysql connect");
    }
}

class MysqlCommand implements ICommand{

    @Override
    public void command() {
        System.out.println("mysql command");
    }
}

class MysqlBase implements BaseUtilsFactory{

    @Override
    public IConnect getConnect() {
        return new MysqlConnect();
    }

    @Override
    public ICommand getCommand() {
        return new MysqlCommand();
    }
}

//oracle场景
class OracleConnect implements IConnect{

    @Override
    public void connect() {
        System.out.println("oracle connect");
    }
}

class OracleCommand implements ICommand{

    @Override
    public void command() {
        System.out.println("oracle command");
    }
}

class OracleBase implements BaseUtilsFactory{

    @Override
    public IConnect getConnect() {
        return new OracleConnect();
    }

    @Override
    public ICommand getCommand() {
        return new OracleCommand();
    }
}
