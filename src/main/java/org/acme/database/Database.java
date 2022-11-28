package org.acme.database;

import java.util.ArrayList;
import java.util.List;

import org.acme.models.User;

public final class Database {

    public static List<User> user(){

        List<User> users = new ArrayList<User>();
        var user = new User();
        user.id=1;
        user.username="admin";
        user.email="admin@gmail.com";
        user.password="admin123";
        users.add(user);

        return users;

    }
    
}
