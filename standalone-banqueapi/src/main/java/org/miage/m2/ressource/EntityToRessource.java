package org.miage.m2.ressource;
import java.util.ArrayList;
import java.util.List;
import org.miage.m2.entity.*;
import org.miage.m2.boundary.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Comparator;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EntityToRessource {

    public static Resources<Resource<Demande>> demandeToResource(Iterable<Demande> demandes){
        Link selfLink = linkTo(methodOn(DemandeController.class).getAllDemandes()).withSelfRel();
        List<Resource<Demande>> demandeResources = new ArrayList<>();
        demandes.forEach(demande -> 
            demandeResources.add(demandeToResource(demande, false)));
        
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
            Link collectionLink = linkTo(methodOn(DemandeController.class).getAllDemandes()).withRel("Collection");
            ressource.add(collectionLink);
        }
        /**
         * Si la demande comporte des actions alors on définit
         * la prochaine action à mener en fonction du numéro
         * de la dernière action effectué.
         * Exemple : 1 => Debut
         *           2 => Etude etc
         */
        Link linkNextAction=null;
        if(demande.getActions().size()>0){
            Optional<Action> highestAction = demande.getActions().stream().collect(Collectors.maxBy(Comparator.comparing(Action::getNumero)));
            Integer highestNumero = highestAction.get().getNumero();
            linkNextAction=statutDemande.values()[highestNumero].construireLien(demande);
        }
        else {
            linkNextAction=statutDemande.values()[0].construireLien(demande);
        }
        ressource.add(linkNextAction);
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
        Link selfLink = linkTo(DemandeController.class).slash(action.getId()).withSelfRel();
        if (collection) {
            Link collectionLink = linkTo(methodOn(ActionController.class).getAllActions())
                    .withRel("collection");
            return new Resource<>(action, selfLink, collectionLink);
        } else {
            return new Resource<>(action, selfLink);
        }
    }
}
