package org.miage.m2.boundary;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.miage.m2.entity.*;
import org.miage.m2.ressource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    /**
     * Toutes les demandes - GET
     */
    public ResponseEntity<?> getAllDemandes() {
        Iterable<Demande> allDemandes = dr.findAll();
        return new ResponseEntity<>(EntityToRessource.demandeToResource(allDemandes), HttpStatus.OK);
    }

    /**
     * Une demande - GET
     */
    @GetMapping(value = "/{demandeId}")
    public ResponseEntity<?> getDemande(@PathVariable("demandeId") String id) {
        return Optional.ofNullable(dr.findOne(id))
                .map(u -> new ResponseEntity<>(EntityToRessource.demandeToResource(u, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * Sauvegarde d'une demande - POST
     */
    @PostMapping
    public ResponseEntity<?> saveDemande(@RequestBody Demande demande) {
        HttpHeaders responseHeaders = new HttpHeaders();
        demande.setEtatcourantdemande(statutDemande.values()[0]);
        Demande saved = dr.save(demande);
        responseHeaders.setLocation(linkTo(DemandeController.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    /** 
     * Modification d'une demande - PUT
     */
    @PutMapping(value = "/{demandeId}")
    public ResponseEntity<?> updateDemande(@RequestBody Demande demande, @PathVariable("demandeId") String demandeId) {
        Demande oldDemande = dr.findOne(demandeId);
        Optional<Demande> body = Optional.ofNullable(demande);
        if (!body.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!dr.exists(demandeId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        /**
         * Si le statut de la demande que l'on souhaite modifier a un statut
         * inférieur à Etude on peut modifier sinon on ne fait rien
         */
        if(statutDemande.valueOf(oldDemande.getEtatcourantdemande().toString()).ordinal()<2){
            demande.setId(demandeId);
            demande.setEtatcourantdemande(oldDemande.getEtatcourantdemande());
            dr.save(demande);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    /**
     * Acceptation une demande - PUT
     */   
    @PutMapping(value = "/{demandeId}/accepter")
    
    public ResponseEntity<?>acceptDemande(@PathVariable("demandeId") String demandeId) {
        Demande demande = dr.findOne(demandeId);
        if(demande == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        new ResponseEntity<>(EntityToRessource.newActionDemandeToResource(demande,statutDemande.Depot), HttpStatus.OK);
        dr.save(demande);
        return new ResponseEntity<>(EntityToRessource.demandeToResource(demande, true), HttpStatus.OK);
    }

    /**
     * Attribuer une demande - PUT
     */   
    @PutMapping(value = "/{demandeId}/attribuer")
    
    public ResponseEntity<?>attribuerDemande(@PathVariable("demandeId") String demandeId) {
        Demande demande = dr.findOne(demandeId);
        if(demande == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        new ResponseEntity<>(EntityToRessource.newActionDemandeToResource(demande,statutDemande.Debut), HttpStatus.OK);
        dr.save(demande);
        return new ResponseEntity<>(EntityToRessource.demandeToResource(demande, true), HttpStatus.OK);
    }

    /**
     * Decider sur le devenir d'une demande - PUT
     */   
    @PutMapping(value = "/{demandeId}/decider")
    
    public ResponseEntity<?>deciderDemande(@PathVariable("demandeId") String demandeId) {
        Demande demande = dr.findOne(demandeId);
        if(demande == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        new ResponseEntity<>(EntityToRessource.newActionDemandeToResource(demande,statutDemande.Etude), HttpStatus.OK);
        dr.save(demande);
        return new ResponseEntity<>(EntityToRessource.demandeToResource(demande, true), HttpStatus.OK);
    }

     /**
     * Decider d'accepter une demande - PUT
     */   
    @PutMapping(value = "/{demandeId}/decider/acceptation")
    
    public ResponseEntity<?>deciderAccepterDemande(@PathVariable("demandeId") String demandeId) {
        Demande demande = dr.findOne(demandeId);
        if(demande == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        new ResponseEntity<>(EntityToRessource.newActionDemandeToResource(demande,statutDemande.Decision), HttpStatus.OK);
        dr.save(demande);
        return new ResponseEntity<>(EntityToRessource.demandeToResource(demande, true), HttpStatus.OK);
    }

     /**
     * Decider de refuser une demande - PUT
     */   
    @PutMapping(value = "/{demandeId}/decider/refus")
    
    public ResponseEntity<?>deciderRefusDemande(@PathVariable("demandeId") String demandeId) {
        Demande demande = dr.findOne(demandeId);
        if(demande == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        new ResponseEntity<>(EntityToRessource.newActionDemandeToResource(demande,statutDemande.Decision), HttpStatus.OK);
        
        dr.save(demande);
        return new ResponseEntity<>(EntityToRessource.demandeToResource(demande, true), HttpStatus.OK);
    }

}