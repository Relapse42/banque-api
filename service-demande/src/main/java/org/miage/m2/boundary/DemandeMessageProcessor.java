package org.miage.m2.boundary;

import java.util.Collections;
import org.miage.m2.entity.Demande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import org.springframework.cloud.stream.annotation.StreamListener;
@Component
public class DemandeMessageProcessor {
   private final DemandeRessource demandeRessource;
   
   @Autowired
   public DemandeMessageProcessor(DemandeRessource demandeRessource) {
       this.demandeRessource = demandeRessource;
   }
   @StreamListener("input")
   public void onMessage(Message<Demande> msg) {
       Demande d = new Demande(msg.getPayload());
       d.setActionsid(Collections.emptySet());
       this.demandeRessource.save(d);
   }
}
