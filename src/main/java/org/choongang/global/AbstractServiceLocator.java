package org.choongang.global;

import org.choongang.global.constants.Menu;

import java.util.HashMap;
import java.util.Map;

public class AbstractServiceLocator implements ServiceLocator{
    protected static ServiceLocator instance;

    protected Map<Menu, Service> services;


    protected AbstractServiceLocator() {
        services = new HashMap<>();
    }

    @Override
    public Service find(Menu menu) {
        return null;
    }
}
