package com.impubudu.constructorInjection;

public class Company {

    private Manager manager;

    public Company(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }
}
