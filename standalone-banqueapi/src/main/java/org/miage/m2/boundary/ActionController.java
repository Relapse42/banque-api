package org.miage.m2.boundary;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.miage.m2.entity.*;
import org.miage.m2.ressource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(value="/actions", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Action.class)
public class ActionController {

    private final ActionRessource ar;

    @Autowired
    public ActionController(ActionRessource ar) {
      this.ar = ar;
    }

    /**
     * Toutes les actions - GET
     */
    public ResponseEntity<?> getAllActions() {
        Iterable<Action> allActions = ar.findAll();
        return new ResponseEntity<>(EntityToRessource.actionToResource(allActions), HttpStatus.OK);
    }

    /**
     * Une action - GET
     */
    @GetMapping(value = "/{actionId}")
    public ResponseEntity<?> getAction(@PathVariable("actionId") String idAction) {
        return Optional.ofNullable(ar.findOne(idAction))
                .map(u -> new ResponseEntity<>(EntityToRessource.actionToResource(u, true), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}