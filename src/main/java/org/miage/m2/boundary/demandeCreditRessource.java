package org.miage.m2.boundary;

import java.util.Optional;
import org.miage.m2.entity.demandeCredit;
import org.miage.m2.entity.statutDemande;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface demandeCreditRessource extends CrudRepository<demandeCredit,String>{

    
}

