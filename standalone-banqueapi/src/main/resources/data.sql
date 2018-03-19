INSERT INTO demande ( id, nom, prenom, adresse, dateNaissance, revenus, montantCreditDemande, dureeCredit, etatCourantDemande)
VALUES ('1', 'Dupont', 'Jean-Michel', 'Paris','10-09-1992', 25, 25, 6, 'Debut');
INSERT INTO action ( id, numero, nom, personnecharge, etat, date, iddemande)  VALUES ('55', 2,  'Michel', 'Titi','En cours', '30-07-1985','1');
/*INSERT INTO action ( id, numero,nom, personnecharge, etat, date, iddemande)  VALUES ('56', 2,'Paul', 'Toto','Bon', '30-07-1985','1');*/
