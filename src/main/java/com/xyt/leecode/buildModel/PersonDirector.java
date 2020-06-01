package com.xyt.leecode.buildModel;

/**
 * 调用具体的建造者来创建复杂对象的各个部分，在指导中不涉及具体的产品信息，
 *  只涉及保证对象的各个部分完整创建或者按某种顺序执行创建
 *
 * @Author: abby
 * @Date: 2020/6/1 12:04
 */
public class PersonDirector {

    public Person creatPerson(PersonBuild personBuild){
        personBuild.buildHead();
        personBuild.buildBody();
        personBuild.buildFoot();
        return personBuild.buildPerson();
    }
}
