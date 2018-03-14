package org.miage.m2.entity;

import java.util.List;
import org.springframework.hateoas.core.Relation;

@Relation(collectionRelation="demandes")
public class Detail extends Demande {

    private final List<Action> actions;

    public Detail(Demande demande, List<Action> actions) {
        super(demande);
        this.actions = actions;
    }

    public List<Action> getActions() {
        return actions;
    }
}
