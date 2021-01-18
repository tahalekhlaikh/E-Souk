package com.spring.esouk.entity;

import com.spring.esouk.entity.dao.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Panier  {
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    private Map<Integer,LigneCommande> items=new HashMap<Integer, LigneCommande>();
    private double total;
    public void addItem(Produitmettre p,int quantite,int prix)
    {
        LigneCommande lc=items.get(p.getId());
        if(lc==null)
        {
            LigneCommande art=new LigneCommande();
            art.setProduitmettre(p);
            art.setQuantite(quantite);
            art.setPrix(prix);
            items.put(p.getId(),art);

        }
    }

    public Map<Integer,LigneCommande> getMap(){
        return items;
    }

    public Collection<LigneCommande> getItems()
    {
        return items.values();
    }
    public int getSize()
    {
        return items.size();
    }
    public double getTotal()
    {

        for(LigneCommande ls:items.values())
        {
            total+=ls.getPrix()*ls.getQuantite();
        }
        return total;
    }
    public void deleteItem(Long idproduit)
    {
        items.remove(idproduit);
        setItems(items);

    }
    public double getTotalSQ()
    {
        total=0;
        for(LigneCommande ls:items.values())
        {
            total+=ls.getPrix();
        }
        return total;
    }

    public void setItems(Map<Integer, LigneCommande> items) {
        this.items = items;
    }
}
