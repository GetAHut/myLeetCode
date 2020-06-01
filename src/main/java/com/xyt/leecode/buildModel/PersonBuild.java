package com.xyt.leecode.buildModel;

/**
 * 创建Build抽象接口，用来规范产品对象的每个部分的创建
 *
 * @Author: abby
 * @Date: 2020/6/1 11:30
 */
public interface PersonBuild {

    public void buildHead();
    public void buildBody();
    public void buildFoot();
    public Person buildPerson();
}
