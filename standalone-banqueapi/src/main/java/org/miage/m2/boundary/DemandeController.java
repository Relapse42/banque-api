package org.miage.m2.boundary;
import org.miage.m2.entity.statutDemande;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.miage.m2.entity.Action;
import org.miage.m2.entity.Demande;
import org.miage.m2.ressource.*;
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
@ExposesResourceFor(Demande.class)
public class DemandeController {

    private final DemandeRessource dr;
    private final ActionRessource ar;
    @Autowired
    public DemandeController(DemandeRessource dr, ActionRessource ar) {
      this.dr = dr;
      this.ar = ar;
    }
    @GetMapping
    // GET all demandes
    public ResponseEntity<?> getAllDemandes() {
        Iterable<Demande> allDemandes = dr.findAll();
        return new ResponseEntity<>(EntityToRessource.demandeCreditToResource(allDemandes), HttpStatus.OK);
    }

   // GET une demande
    @GetMapping(value = "/{demandeId}")
    public ResponseEntity<?> getDemande(@PathVariable("demandeId") String id) {
        return Optional.ofNullable(dr.findOne(id))
                .map(u -> new ResponseEntity<>(EntityToRessource.demandeCreditToResource(u, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // POST
    @PostMapping
    public ResponseEntity<?> saveDemande(@RequestBody Demande demande) {
        Demande saved = dr.save(demande);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(linkTo(DemandeController.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    // PUT
    @PutMapping(value = "/{demandeId}")
    public ResponseEntity<?> updateInscription(@RequestBody Demande demande,
            @PathVariable("demandeId") String demandeId) {
        Optional<Demande> body = Optional.ofNullable(demande);
        if (!body.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!dr.exists(demandeId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        demande.setId(demandeId);
        Demande result = dr.save(demande);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

}