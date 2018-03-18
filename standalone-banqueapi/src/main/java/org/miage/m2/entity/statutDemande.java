package org.miage.m2.entity;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
import org.miage.m2.boundary.DemandeController;
import java.util.UUID;
public enum statutDemande {
    Depot{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("accepter")
            .withRel("Prochaine action à mener => Commencement");
            return linkNextAction;
        }
		public Action creerAction(Demande demande) {
            demande.setEtatcourantdemande(Debut);
			return new Action(UUID.randomUUID().toString(), 1, "A valider", "PERRIN", "En attente d'attribution", "18-03-2018");
		}
    },
    Debut{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("attribuer")
            .withRel("Prochaine action à mener => Attribution");
            return linkNextAction;
        }
        public Action creerAction(Demande demande) {
            demande.setEtatcourantdemande(Etude);
			return new Action(UUID.randomUUID().toString(),2, "Vérification informations", "PERRIN", "Revue en cours", "18-03-2018");
		}
    },
    Etude{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("decider")
            .withRel("Prochaine action à mener => Décision d'acceptation");
            return linkNextAction;
        }
        public Action creerAction(Demande demande) {
            demande.setEtatcourantdemande(Decision);
			return new Action(UUID.randomUUID().toString(),3, "Vérification informations", "PERRIN", "Décision en attente de validation", "18-03-2018");
		}
    },
    Decision{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("")
            .withRel("Decision");
            return linkNextAction;
        }
        public Action creerAction(Demande demande) {
            demande.setEtatcourantdemande(Fin);
			return new Action(UUID.randomUUID().toString(),4, "Notification", "PERRIN", "Demande acceptée", "18-03-2018");
		}
    },
    Acceptation {
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("decider")
            .slash("acceptation")
            .withRel("Acceptation");
            return linkNextAction;
        }
        public Action creerAction(Demande demande) {
            demande.setEtatcourantdemande(Fin);
			return new Action(UUID.randomUUID().toString(),5, "Notification", "PERRIN", "Demande acceptée", "18-03-2018");
		}
    },
    Rejet {
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("decider")
            .slash("refus")
            .withRel("Refus");
            return linkNextAction;
        }
        public Action creerAction(Demande demande) {
            demande.setEtatcourantdemande(Fin);
			return new Action(UUID.randomUUID().toString(),6,"Notification", "PERRIN", "Demande refusée", "18-03-2018");
		}
    },
    Fin{
        public Link construireLien(Demande demande) {
            Link linkNextAction=linkTo(methodOn(DemandeController.class).getDemande(demande.getId()))
            .slash("finir")
            .withRel("Prochaine action à mener => Aucune c'est fini");
            return linkNextAction;
        }
        public Action creerAction(Demande demande) {
            demande.setEtatcourantdemande(Fin);
			return new Action(UUID.randomUUID().toString(),7, "Notification", "PERRIN", "Erreur", "18-03-2018");
		}
    };
    public abstract Link construireLien(Demande demande);
    public abstract Action creerAction(Demande demande);
}