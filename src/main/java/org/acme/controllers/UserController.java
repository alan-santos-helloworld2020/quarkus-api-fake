package org.acme.controllers;

import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.models.User;


@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    List<User> users = new ArrayList<User>();

    @GET
    public List<User> findAll() {
        return users;
    }

    @GET
    @Path("/{id}")
    public User findById(int id) {
        var us = users.stream().filter(x -> x.id == id).findFirst();
        if (us.isPresent()) {
            return us.get();
        } else {
            throw new NotFoundException();
        }
    }

    @POST
    @Transactional
    public User save(User user) {
        var res = users.add(user);
        if (res) {
            return user;
        } else {
            throw new UnsupportedOperationException();
        }

    }

    @PUT
    @Path("/{id}")
    @Transactional
    public User update(int id, User user) {
        var us = users.stream().filter(x -> x.id == id)
                .findFirst().map(u -> {
                    u.username = user.username;
                    u.email = user.email;
                    u.password = user.password;
                    return u;
                });
        if (us.isPresent()) {
            return us.get();

        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(int id) {
        var us = users.stream().filter(x -> x.id == id)
                .findFirst();
        if (us.isPresent()) {
            users.remove(us.get());
        } else {
            throw new NotFoundException();
        }
    }

}
