package org.miage.m2.boundary;

import org.miage.m2.entity.Demande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel="demandes", path="demandes")
public interface DemandeRessource extends CrudRepository<Demande,String>{

    
}

