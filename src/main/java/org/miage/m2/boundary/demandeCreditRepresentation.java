package org.miage.m2.boundary;
import org.miage.m2.entity.statutDemande;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.miage.m2.entity.demandeCredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/demandes", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(demandeCredit.class)
public class demandeCreditRepresentation {

    private final demandeCreditRessource dc;

    @Autowired
    public demandeCreditRepresentation(demandeCreditRessource dc) {
      this.dc = dc;
    }

    // GET all demandes
    @GetMapping
    public ResponseEntity<?> getAllDemandes(@RequestParam(name = "status", required = false) Optional<statutDemande> status) {
        Iterable<demandeCredit> allDemandes = dc.findAll();
        return new ResponseEntity<>(demandeCreditToResource(allDemandes, status), HttpStatus.OK);
    }


    private Resources<Resource<demandeCredit>> demandeCreditToResource(Iterable<demandeCredit> demandes, Optional<statutDemande> status) {
        //Link selfLink = linkTo(methodOn(demandeCreditRepresentation.class).getAllDemandes())
              //  .withSelfRel();
        List<Resource<demandeCredit>> demandeResources = new ArrayList<>();
        demandes.forEach(demande ->
            demandeResources.add(demandeCreditToResource(demande, false,status)));
        return new Resources<>(demandeResources); //, selfLink
    }

    private Resource<demandeCredit> demandeCreditToResource(demandeCredit demande, Boolean collection, Optional<statutDemande> status) {
        //Link selfLink = linkTo(demandeCreditRepresentation.class)
             //   .slash(demande.getId())
              //  .withSelfRel();
        if (collection) {
            Link collectionLink = linkTo(methodOn(demandeCreditRepresentation.class).getAllDemandes(status))
                    .withRel("collection");
            return new Resource<>(demande);  //, selfLink, collectionLink);
        } else {
            return new Resource<>(demande); // , selfLink);
        }
    }
    
    
}
