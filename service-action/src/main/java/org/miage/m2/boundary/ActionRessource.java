package org.miage.m2.boundary;

import org.miage.m2.entity.Action;
import org.springframework.data.repository.CrudRepository;

public interface ActionRessource extends CrudRepository<Action,String> {
    
}

