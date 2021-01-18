package com.spring.esouk.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCommande;
    private Date dateCommande;
    private Date dateCreation;

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }



    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="idClient" )
    private Client client;
    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})

    private Collection<LigneCommande> ligneCommandes=new ArrayList<>();
    @ManyToMany
    private Collection<Agriculteur> agriculteur=new ArrayList<>();

    public void setAgriculteur(Collection<Agriculteur> agriculteur) {
        this.agriculteur = agriculteur;
    }

    public Collection<Agriculteur> getAgriculteur() {
        return agriculteur;
    }

    private int approuve;

    public int getApprouve() {
        return approuve;
    }

    public void setApprouve(int approuve) {
        this.approuve = approuve;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<LigneCommande> getLigneCommandes() {
        return ligneCommandes;
    }

    public void setLigneCommandes(Collection<LigneCommande> ligneCommandes) {
        this.ligneCommandes = ligneCommandes;
    }

    public Commande() {
    }

    public Commande(Date dateCommande, Client client, Collection<LigneCommande> ligneCommandes) {
        this.dateCommande = dateCommande;
        this.client = client;
        this.ligneCommandes = ligneCommandes;
    }
    public double total(Collection<LigneCommande> ligneCommandes)
    {
        final double[] totale = {0};
        ligneCommandes.forEach(lg->{
            totale[0] = totale[0] +lg.getPrix();
        });
        return  totale[0];
    }
    public Collection<LigneCommande> chercherAgr(Agriculteur agr)
    {
        Collection <LigneCommande> ligneCommandes1=new ArrayList<>();
        this.ligneCommandes.forEach(ct->
        {
            if(ct.getProduitmettre().getAgriculteur()==agr)
            {
                ligneCommandes1.add(ct);
            }
        });
        return  ligneCommandes1;
    }
    public void approuveCmd()
    {
        int size=ligneCommandes.size();
        final int[] i = {0};
        this.ligneCommandes.forEach(lg->
        {
            if(lg.getApprouve()==1)
            {
                i[0]++;
            }
        });
        if(size==i[0])
        {
            this.approuve=1;
        }
    }
    public int verifierApprouve(Collection<LigneCommande> ligneCommandes){
        int size=ligneCommandes.size();
        final int[] i = {0};
        ligneCommandes.forEach(lg->
        {
            if(lg.getApprouve()==1)
            {
                i[0]++;
            }
        });
        if (i[0]==size)
        {
            return 1;
        }
        return 0;

    }
}
