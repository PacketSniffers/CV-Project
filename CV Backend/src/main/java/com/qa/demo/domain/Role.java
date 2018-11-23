package com.qa.demo.domain;

public class Role {
    String name;
    Permissions permissions;

    public Role(String name, Permissions permissions)
    {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Permissions getPermissions() {
        return permissions;
    }
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }
}
