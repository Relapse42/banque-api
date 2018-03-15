INSERT INTO demande ( id, nom, prenom, adresse, dateNaissance, revenus, montantCreditDemande, dureeCredit, etatCourantDemande)
VALUES ('1', 'Dupont', 'Jean-Michel', 'Paris','10-09-1992', 25, 25, 6, 'Debut');
INSERT INTO demande ( id, nom, prenom, adresse, dateNaissance, revenus, montantCreditDemande, dureeCredit, etatCourantDemande)  
VALUES ('2', 'Dupuis', 'Jean-Jacques', 'Paris','10-09-1992', 25, 25, 6, 'Fin');
INSERT INTO action ( id, nom, personnecharge, etat, date)  VALUES ('1', 'Michel', 'Titi','Bon', '30-07-1985');
INSERT INTO action ( id, nom, personnecharge, etat, date)  VALUES ('2', 'Paul', 'Toto','Bon', '30-07-1985');
INSERT INTO demande_actions_id VALUES ('1', '1');
INSERT INTO demande_actions_id VALUES ('1', '2');
