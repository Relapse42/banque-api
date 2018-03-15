package org.miage.m2.boundary;

import java.util.Collections;
import org.miage.m2.entity.Demande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import org.springframework.cloud.stream.annotation.StreamListener;
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
import org.springframework.web.bind.annotation.RestController;
@Component
public class DemandeMessageProcessor {
   private final DemandeRessource demandeRessource;
   
   @Autowired
   public DemandeMessageProcessor(DemandeRessource demandeRessource) {
       this.demandeRessource = demandeRessource;
   }
    // GET one
    @GetMapping(value = "/demandes/peers")
    public ResponseEntity<?> getIntervenant() {
        System.out.println("POUEEETTTT ");
        return new ResponseEntity<>(null, HttpStatus.OK);
    
    }
   @StreamListener("input")
   public void onMessage(Message<Demande> msg) {
       Demande d = new Demande(msg.getPayload());
       d.setActionsid(Collections.emptySet());
       this.demandeRessource.save(d);
   }
}
