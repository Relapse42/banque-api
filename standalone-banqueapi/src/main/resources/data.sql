INSERT INTO demande ( id, nom, prenom, adresse, dateNaissance, revenus, montantCreditDemande, dureeCredit, etatCourantDemande, token)
VALUES ('1', 'Dupont', 'Jean-Michel', 'Paris','10-09-1992', 25, 25, 6, 'Debut', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWaWN0b3IiLCJleHAiOjE1MjI1MjQyNTF9.cXZW8TJVGUQi3FWpxGjkjresT_whWZB5n3sGOUqTA5wU7NmUB0K02aJpgFE0Zk4CwAb_sR2Z9abpW3b8pXxzng');
INSERT INTO action ( id, numero, nom, personnecharge, etat, date, iddemande)  VALUES ('55', 2,  'Debut', 'Titi','En cours', '30-07-1985','1');
