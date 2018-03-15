package org.miage.m2.ressource;
import java.util.ArrayList;
import java.util.List;
import org.miage.m2.entity.*;
import org.miage.m2.boundary.*;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EntityToRessource {

    public static Resources<Resource<Demande>> demandeCreditToResource(Iterable<Demande> demandes){
        Link selfLink = linkTo(methodOn(DemandeController.class).getAllDemandes()).withSelfRel();
        List<Resource<Demande>> demandeResources = new ArrayList<>();
        demandes.forEach(demande -> 
            demandeResources.add(demandeCreditToResource(demande, false)));
        
        return new Resources<>(demandeResources,selfLink);
    }
    public static Resource<Demande> demandeCreditToResource(Demande demande, Boolean collection) {
        Link selfLink = linkTo(DemandeController.class).slash(demande.getId()).withSelfRel();
        if (collection) {
            Link collectionLink = linkTo(methodOn(DemandeController.class).getAllDemandes())
                    .withRel("collection");
            return new Resource<>(demande, selfLink, collectionLink);
        } else {
            return new Resource<>(demande, selfLink);
        }
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
