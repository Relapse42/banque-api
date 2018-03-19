package org.miage.m2.entity;

public enum statutDemande {
    Depot(1),
    Debut(2),
    Etude(3),
    Decision(4),
    Acceptation(5),
    Rejet(6),
    Fin(7);
    private Integer numero;
    statutDemande(Integer numero){
        this.numero = numero;
    }
    public Integer getNumero(){
        return this.numero;
    }
}