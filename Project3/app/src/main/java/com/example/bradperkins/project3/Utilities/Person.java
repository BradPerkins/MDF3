package com.example.bradperkins.project3.Utilities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bradperkins on 1/19/16.
 */
public class Person extends ArrayList<String> implements Serializable {

    private static final long serialVersionUID = -7791154359356162736L;

    public String name;
    public String age;
    public String state;


    public Person(String _name, String _age,  String _state) {
        name = _name;
        age = _age;
        state = _state;

    }

    public Person() {

    }

    public String setName(String _name){
        return name;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public void setData(Person data){
        name = data.getName();
    }


}


