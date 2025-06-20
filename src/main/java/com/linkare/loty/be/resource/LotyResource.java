package com.linkare.loty.be.resource;

import com.linkare.loty.be.model.Loty;
import com.linkare.loty.be.repository.LotyRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/v1/loty")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LotyResource {
    @Inject
    LotyRepository lotyRepository;

    @GET
    public List<Loty> listAll() {
        return lotyRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Loty get(@PathParam("id") Long id) {
        return lotyRepository.findById(id);
    }

    @GET
    @Path("/year/{year}")
    public Loty getByYear(@PathParam("year") Integer year) {
        return lotyRepository.find("year", year).firstResult();
    }

    @POST
    @Transactional
    public Response create(Loty loty) {
        lotyRepository.persist(loty);
        return Response.status(Response.Status.CREATED).entity(loty).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Loty loty) {
        Loty entity = lotyRepository.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        entity.setName(loty.getName());
        entity.setYear(loty.getYear());
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = lotyRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/year/{year}")
    @Transactional
    public Response deleteByYear(@PathParam("year") Integer year) {
        Loty entity = lotyRepository.find("year", year).firstResult();
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        lotyRepository.delete(entity);
        return Response.noContent().build();
    }
}
