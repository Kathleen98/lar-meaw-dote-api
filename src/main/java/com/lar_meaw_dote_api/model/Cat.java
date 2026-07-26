package com.lar_meaw_dote_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Cat {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String color;
    private Boolean altered;

    protected Cat(){}

    public Cat(String name, Integer age, String color, Boolean altered){
        this.name = name;
        this.age = age;
        this.color = color;
        this.altered = altered;
    }

    @Override
    public String toString(){
        return String.format("Cat[id=%d, name='%s', age='%d', color='%s', altered='%b']", id, name, age, color, altered );
    }


    public Long getId(){return id;}
    public String getName(){return name;}
    public Integer getAge(){return age;}
    public String getColor(){return color;}
    public Boolean getAltered(){return altered;}
}
