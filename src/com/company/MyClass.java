package com.company;

import java.util.List;

public class MyClass {

    private String name;
    private List<Property> properties;
    private List<Function> functions;

    public MyClass() {

    }

    public MyClass(String name, List<Property> properties, List<Function> functions) {
        this.name = name;
        this.properties = properties;
        this.functions = functions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }
}
