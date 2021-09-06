package com.xyt.leecode.designMode.builder;

/**
 * @Author: xyt
 * @Description:
 * @Date: 2021/9/6 18:19
 */
public class BuilderTest{

    public static void main(String[] args) {

        Product prod = new Product.Builder().name("xxx").build();
        System.out.println(prod);
    }
}

class Product{

    private String name;
    private String sex;
    private String logo;
    private String remark;
    //...等复杂的对象。亦可是final修饰的不可变对象
    //final修饰的对象 可以用来存储配置信息等


    public Product(String name, String sex, String logo, String remark) {
        this.name = name;
        this.sex = sex;
        this.logo = logo;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", logo='" + logo + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    static class Builder{
        private String name;
        private String sex;
        private String logo;
        private String remark;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder sex(String sex){
            this.sex = sex;
            return this;
        }

        public Builder logo(String logo){
            this.logo = logo;
            return this;
        }

        public Builder remark(String remark){
            this.remark = remark;
            return this;
        }

        Product build(){
            return new Product(name, sex, logo, remark);
        }
    }
}