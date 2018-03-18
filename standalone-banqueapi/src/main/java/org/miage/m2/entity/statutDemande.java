package org.miage.m2.entity;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.miage.m2.boundary.DemandeController;
public enum statutDemande {
    Rien{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("")
            .withRel("Prochaine action à mener => Aucune c'est fini");
            return linkNextAction;
        }
    },
    Debut{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("accepter")
            .withRel("Prochaine action à mener => Commencement");
            return linkNextAction;
        }
    },
    Etude{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("attribuer")
            .withRel("Prochaine action à mener => Attribution");
            return linkNextAction;
        }
    },
    Decision_Acceptation{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("decider")
            .withRel("Prochaine action à mener => Validation");
            return linkNextAction;
        }
    },
    Decision_Rejet{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("decider")
            .withRel("Prochaine action à mener => Refus");
            return linkNextAction;
        }
    },
    Fin{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("sqd")
            .withRel("Prochaine action à mener => Aucune c'est fini");
            return linkNextAction;
        }
    };
    public abstract Link construireLien(Demande demande);
}