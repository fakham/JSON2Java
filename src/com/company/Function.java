package com.company;

import java.util.List;

public class Function extends BaseClass {

    private List<Argument> arguments;

    public Function() {

    }

    public Function(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public Function(String name, String type, List<Argument> arguments) {
        super(name, type);
        this.arguments = arguments;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }
}
