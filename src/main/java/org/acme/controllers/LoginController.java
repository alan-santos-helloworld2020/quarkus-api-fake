package org.acme.controllers;


import java.util.Optional;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.database.Database;
import org.acme.models.User;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @POST
    @Transactional
    public Optional<User> findByUser(User user) {
        Optional<User> us = Database.user().stream().filter(x -> x.email.equals(user.email)).findFirst();
        if (us.isPresent()) {
            if (us.get().password.equals(user.password)) {
                return us;
            } else {
                throw new NotFoundException();
            }
        } else {
            throw new NotFoundException();
        }
   }

}
