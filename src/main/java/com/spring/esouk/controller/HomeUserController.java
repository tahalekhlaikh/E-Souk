package com.spring.esouk.controller;


import com.spring.esouk.entity.*;
import com.spring.esouk.entity.dao.*;
import com.spring.esouk.entity.service.ServiceInterfaceProduitHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/userHome")

//affichage de l'interface Client
public class HomeUserController {
    @Autowired
    private ServiceInterfaceProduitHome serviceProduit;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ClientRepository clientDAO;
    @Autowired
    private ProduitmettreRepository produitmettreRepository;
    private Panier panier;

    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private AgriculteurRepository agriculteurRepository;
    @Autowired
    private ClientRepository clientRepository;
    private String erreurPanier="";
    @GetMapping("/user")
    public String showHomeUser(Model theModel)
    {

        theModel.addAttribute("client",User());
        theModel.addAttribute("fruits",serviceProduit.getProduits(1));//Affichage des fruits qui se touvent dans la barre categorie
        theModel.addAttribute("legumes",serviceProduit.getProduits(2));
        //Affichage des Legumes qui se touvent dans la barre categorie
        theModel.addAttribute("laitier",serviceProduit.getProduits(3));
        theModel.addAttribute("autres",serviceProduit.getProduits(4));
        theModel.addAttribute("cereales",serviceProduit.getProduits(5));
        theModel.addAttribute("produitMettre",serviceProduit.getProduitmettre());//Affichage les produits des agriculteurs
        if(panier==null)
        {
            panier=new Panier();
            theModel.addAttribute("panier",panier);
            return "home";
        }

        theModel.addAttribute("panier",panier);
        theModel.addAttribute("erreurPanier",erreurPanier);
        theModel.addAttribute("recherche", "");

        return "home";
    }
    @PostMapping("/chercher")
    public String chercher(@RequestParam String recherche,Model theModel){
        Agriculteur agr=agriculteurRepository.findByNomContains(recherche);

        theModel.addAttribute("client",User());
        theModel.addAttribute("fruits",serviceProduit.getProduits(1));//Affichage des fruits qui se touvent dans la barre categorie
        theModel.addAttribute("legumes",serviceProduit.getProduits(2));//Affichage des Legumes qui se touvent dans la barre categorie
        theModel.addAttribute("produitMettre",produitmettreRepository.findByAgriculteur(agr));//Affichage les produits des agriculteurs
        theModel.addAttribute("panier",panier);
        theModel.addAttribute("erreurPanier",erreurPanier);
        theModel.addAttribute("recherche", "");

        return "home";
    }
    @GetMapping("/{produit}")
    //Affichage les produits des agriculteurs mais qui vend juste le {produit} (ex:pomme)
    public String showProduit(@PathVariable("produit") String produitNom,Model theModel)
    {
        System.out.println(produitNom);
        theModel.addAttribute("panier",panier);
        System.out.println(serviceProduit.getProduitmettre1(produitNom));
        theModel.addAttribute("client",User());
        theModel.addAttribute("fruits",serviceProduit.getProduits(1));
        theModel.addAttribute("legumes",serviceProduit.getProduits(2));


        theModel.addAttribute("produitMettre",serviceProduit.getProduitmettre1(produitNom));
        return "home";
    }
    @GetMapping("/ajouterPanier/{id}")
    public String ajouterPanier(@PathVariable(name = "id") int id,Model theModel)
    {
        if(panier==null)
        {
            panier=new Panier();
        }
        Produitmettre pr=produitmettreRepository.findById(id).get();
        System.out.println(pr);
        if(pr.getQte()!=0)
        {
        panier.addItem(pr,1,pr.getPrix());

        }
        else{
            erreurPanier="Il ne reste rien du produit";
        }
        return "redirect:/userHome/user";
    }
    @GetMapping("/commander")
    public String commanderHome(Model theModel)
    {
        Panier panier1=this.panier;
        theModel.addAttribute("client",User());
        theModel.addAttribute("panier",panier);
        theModel.addAttribute("panier1",panier1);
        return "commander";
    }
    @GetMapping("/supprimer/{id}")
    public String supprimerCommande(@PathVariable(name = "id")Long id){

        panier.deleteItem(id);



        return "redirect:/userHome/user";

    }
    @GetMapping("/validerCommande")
    public String validerCommande(Model theModel){
        final int[] i = {0};
        Commande commande=new Commande();
        commande.setClient(User());
        commande.setDateCreation(new Date(System.currentTimeMillis()));

        Collection<LigneCommande> ligneCommandes=panier.getItems();
        ligneCommandes.forEach(cm->{
            System.out.println(cm);
            ligneCommandeRepository.save(cm);
            Produitmettre produitMettre=cm.getProduitmettre();
            produitMettre.setQte(produitMettre.getQte()-1);
            produitMettre.setNombreVendu(produitMettre.getNombreVendu()+1);
            produitmettreRepository.save(produitMettre);


        });
        commande.setLigneCommandes(ligneCommandes);

        final Agriculteur[] agr = {new Agriculteur()};
        ligneCommandes.forEach(cm->{
            if(agr[0].getId() != cm.getProduitmettre().getAgriculteur().getId())
            {
                agr[0]=cm.getProduitmettre().getAgriculteur();
                commande.getAgriculteur().add(agr[0]);

            }

        });
        Commande commandes=commandeRepository.save(commande);
        theModel.addAttribute("client",User());
        System.out.println(commandeRepository.findByClient(User()));
        theModel.addAttribute("commandes",commandes);
        return "voirCommandes";

    }
    @GetMapping("/recommander")
    public String recommander()
    {
        panier=null;
        return "redirect:/userHome/user";
    }
    public Client User()
    {
        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username=securityContext.getAuthentication().getName();
        Client client=clientDAO.findByUserName(username);
        return client;
    }
    @GetMapping("/commandes")
    public String voirCommandes(Model theModel)
    {
        Collection<Commande> commandes=commandeRepository.findAll();
        commandes.forEach(cm->
        {
            cm.approuveCmd();
            commandeRepository.save(cm);
        });
        theModel.addAttribute("client",User());
        theModel.addAttribute("panier",panier);
        theModel.addAttribute("commandes",commandes);

        return "commandeApprouve";
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }
}