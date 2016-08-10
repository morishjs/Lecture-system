package com.model;

/**
 * Created by Junsuk on 2016-08-09.
 */
abstract public class Person {
    String id;
    String passwd;
    String name;

    public String getId() {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public abstract String getType();
}
