package com.model;

/**
 * Created by Junsuk on 2016-08-09.
 */
abstract public class Person {
    private String id;
    private String passwd;
    private String name;
    protected String type;

    public String getId() {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getType();
}
