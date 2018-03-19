package org.miage.m2.ressource;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.math.ec.NafL2RMultiplier;
import org.miage.m2.entity.*;
import org.miage.m2.boundary.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Comparator;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EntityToRessource {

    public static Resources<Resource<Demande>> demandeToResource(Iterable<Demande> demandes, statutDemande statut) {
        Link selfLink = linkTo(methodOn(DemandeController.class).getAllDemandes(null)).withSelfRel();
        List<Resource<Demande>> demandeResources = new ArrayList<>();
        demandes.forEach(
            demande -> {
                if(statut!=null) {
                    if (demande.getEtatcourantdemande().toString()==statut.toString()) {
                        demandeResources.add(demandeToResource(demande, false));
                    }
                }
                else {
                    demandeResources.add(demandeToResource(demande, false));
                }

            }
    );
        
        return new Resources<>(demandeResources,selfLink);
    }
    /**
     * Construction de la structure du résultat d'une demande
     * avec les liens HATEOAS
     */
    public static Resource<Demande> demandeToResource(Demande demande, Boolean collection) {
        Resource<Demande> ressource = new Resource<Demande>(demande);
        /**
         * Ajout du lien sur la demande elle-même
         */
        Link selfLink = linkTo(DemandeController.class).slash(demande.getId()).withSelfRel();
        ressource.add(selfLink);
        /**
         * Ajout des liens de toutes les actions
         * ayant déja été effectué sur la demande
         */
        for (Action action : demande.getActions()) {
            ressource.add(linkTo(methodOn(ActionController.class).getAction(action.getId()))
            .withRel("Historique actions"));
        }
        /**
         * Ajout du lien vers toutes les demandes
         */
        if (collection) {
            Link collectionLink = linkTo(methodOn(DemandeController.class).getAllDemandes(null)).withRel("Collection");
            ressource.add(collectionLink);
        }
        return ressource;
    }
    public static Resources<Resource<Action>> actionToResource(Iterable<Action> actions){
        Link selfLink = linkTo(methodOn(ActionController.class).getAllActions()).withSelfRel();
        List<Resource<Action>> actionResources = new ArrayList<>();
        actions.forEach(action -> 
            actionResources.add(actionToResource(action, false)));
        
        return new Resources<>(actionResources,selfLink);
    }
    public static Resource<Action> actionToResource(Action action, Boolean collection) {
        Link selfLink = linkTo(ActionController.class).slash(action.getId()).withSelfRel();
        if (collection) {
            Link collectionLink = linkTo(methodOn(ActionController.class).getAllActions())
                    .withRel("collection");
            return new Resource<>(action, selfLink, collectionLink);
        } else {
            return new Resource<>(action, selfLink);
        }
    }
    /**
     * Ajout d'une action sur une demande en fonction
     * de l'etat actuel de la demande
     */
    public static Resource<Action> newActionDemandeToResource(Demande demande, Action action) {
        Resource<Demande> ressource = new Resource<Demande>(demande);
        Action newAction=new Action();
       
        if((statutDemande.valueOf(demande.getEtatcourantdemande().toString()).getNumero())==statutDemande.valueOf(action.getNom().toString()).getNumero()-1) {
            newAction = new Action(UUID.randomUUID().toString(), statutDemande.valueOf(action.getNom().toString()).getNumero(), action.getNom(), action.getPersonnecharge(), "En cours", "18-03-2018");
            newAction.setDemande(demande);
            demande.addActions(newAction);
            if(action.getNom().equals("Acceptation" ) || action.getNom().equals("Rejet" )) {
                
                demande.setEtatcourantdemande(statutDemande.valueOf("Fin"));
            }
            else {
                demande.setEtatcourantdemande(statutDemande.valueOf(action.getNom().toString()));
            }
            
        }
        return new Resource<>(action);
    }
}