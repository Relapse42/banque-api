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
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Action.class)
public class ActionController {
    private final ActionRessource ar;

    @Autowired
    public ActionController(ActionRessource ar) {
      this.ar = ar;
    }
    @GetMapping(value = "/actions")
    // GET all demandes
    public ResponseEntity<?> getAllActions() {
        Iterable<Action> allActions = ar.findAll();
        return new ResponseEntity<>(EntityToRessource.actionToResource(allActions), HttpStatus.OK);
    }
    //POST
    @PostMapping(value="/demandes/{demandeId}/actions")
    public ResponseEntity<?> saveActionOnDemande(@RequestBody Action action, @PathVariable("demandeId") String demandeId){
        Optional<Action> body = Optional.ofNullable(action);
        if (!body.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Action saved = ar.save(action);
        HttpHeaders responseHeaders= new HttpHeaders();
        responseHeaders.setLocation(linkTo(DemandeController.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
}