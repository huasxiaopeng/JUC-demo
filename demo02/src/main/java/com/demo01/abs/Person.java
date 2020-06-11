package com.demo01.abs;

/**
 * @ClassName Person
 * @Description TODO
 * @Author lktbz
 * @Date 2020/5/29
 */
public  abstract  class Person {
    private String name;
    private String gender;

    public Person(String nm, String gen){
        this.name=nm;
        this.gender=gen;
    }
    //abstract method
    public abstract void work();

    @Override
    public String toString(){
        return "Name="+this.name+"::Gender="+this.gender;
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}
