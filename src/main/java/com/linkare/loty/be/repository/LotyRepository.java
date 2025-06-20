package com.linkare.loty.be.repository;

import com.linkare.loty.be.model.Loty;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LotyRepository implements PanacheRepository<Loty> {
}
