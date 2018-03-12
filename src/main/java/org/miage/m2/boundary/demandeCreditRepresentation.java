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
        return new ResponseEntity<>(demandeCreditToResource(allDemandes), HttpStatus.OK);
    }
    // GET one
    @GetMapping(value = "/{demandeId}")
    public ResponseEntity<?> getDemande(@PathVariable("demandeId") String id) {
        return Optional.ofNullable(dc.findOne(id))
                .map(u -> new ResponseEntity<>(demandeCreditToResource(u, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // POST
    @PostMapping
    public ResponseEntity<?> saveDemande(@RequestBody demandeCredit demande) {
        demande.setId(UUID.randomUUID().toString());
        demandeCredit saved = dc.save(demande);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(linkTo(demandeCreditRepresentation.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    // PUT
    @PutMapping(value = "/{demandeId}")
    public ResponseEntity<?> updateInscription(@RequestBody demandeCredit demande,
            @PathVariable("demandeId") String demandeId) {
        Optional<demandeCredit> body = Optional.ofNullable(demande);
        if (!body.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!dc.exists(demandeId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        demande.setId(demandeId);
        demandeCredit result = dc.save(demande);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    private Resources<Resource<demandeCredit>> demandeCreditToResource(Iterable<demandeCredit> demandes) {
        //Link selfLink = linkTo(methodOn(demandeCreditRepresentation.class).getAllDemandes())
              //  .withSelfRel();
        List<Resource<demandeCredit>> demandeResources = new ArrayList<>();
        demandes.forEach(demande ->
            demandeResources.add(demandeCreditToResource(demande, false)));
        return new Resources<>(demandeResources); //, selfLink
    }

    private Resource<demandeCredit> demandeCreditToResource(demandeCredit demande, Boolean collection) {
        //Link selfLink = linkTo(demandeCreditRepresentation.class)
             //   .slash(demande.getId())
              //  .withSelfRel();
        if (collection) {
            Link collectionLink = linkTo(methodOn(demandeCreditRepresentation.class).getAllDemandes(null))
                    .withRel("collection");
            return new Resource<>(demande);  //, selfLink, collectionLink);
        } else {
            return new Resource<>(demande); // , selfLink);
        }
    }
}
