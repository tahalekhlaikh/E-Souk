package com.spring.esouk.controller;

import com.spring.esouk.entity.*;
import com.spring.esouk.entity.dao.*;
import com.spring.esouk.entity.service.ServiceInterfaceProduitHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class AgriculteurController {
    @Autowired
    private ServiceInterfaceProduitHome serviceProduit;
    @Autowired
    private AgriculteurRepository agrdao;
    @Autowired
    private ProductRepository produitDao;
    @Autowired
    private ProduitmettreRepository produitmettreDao;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private BoutiqueRepository boutiqueDAO;
    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ProduitmettreRepository mettreDao;
    @Autowired
    private AgriculteurRepository agriculteurRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @GetMapping("/agriculteur")
    public String showHome(Model theModel)
    {
        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username =securityContext.getAuthentication().getName();
        Agriculteur agr=agrdao.findByUserName(username);
        Boutique boutique=agr.getBoutique();
        System.out.println(agr.getId());
        theModel.addAttribute("produitmettre",mettreDao.findById1(agr.getId()));
        theModel.addAttribute("produits",serviceProduit.getProduits());
        theModel.addAttribute("produitmettre1",new Produitmettre());
        theModel.addAttribute("boutique",boutique);
        theModel.addAttribute("agriculteur",User());

        return "agrHome";
    }


    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("produitmettre1") Produitmettre produit, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "agrHome";
        }
        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username =securityContext.getAuthentication().getName();
        Agriculteur agr=agrdao.findByUserName(username);
        produit.setAgriculteur(agr);
        produitmettreDao.save(produit);
        return "redirect:/agriculteur";
    }
    @RequestMapping(value="/delete")
    public String delete(Integer id) {
        produitmettreDao.deleteById(id);
        return "redirect:/agriculteur";
    }
    public Agriculteur User()
    {
        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username=securityContext.getAuthentication().getName();
        Agriculteur agr=agriculteurRepository.findByUserName(username);
        return agr;
    }
    @RequestMapping(value="/editProduct",method= RequestMethod.GET)
    public String editProduct(Model theModel,Integer id)
    {
        Produitmettre pr=produitmettreDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        theModel.addAttribute("user",pr);
        theModel.addAttribute("produits",serviceProduit.getProduits());
        produitmettreDao.deleteById(id);
        return "editproduit";
    }
    @GetMapping("/commandes")
    public String voirCommandes(Model theModel)
    {
        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username =securityContext.getAuthentication().getName();
        Agriculteur agr=agrdao.findByUserName(username);
        theModel.addAttribute("commandes",commandeRepository.findByAgriculteur(agr));
        System.out.println(commandeRepository.findByAgriculteur(agr).size());
        theModel.addAttribute("agriculteur",User());

        return "commandesAgr";

    }
    @GetMapping("/approuver/{id}")
    public String approuver(Model theModel,@PathVariable(name = "id") int id)
    {
        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username =securityContext.getAuthentication().getName();
        Agriculteur agr=agrdao.findByUserName(username);
        Commande commande=commandeRepository.findById(id).get();
        Collection<LigneCommande> ligneCommandes=commande.chercherAgr(agr);
        ligneCommandes.forEach(lg->{
            lg.setApprouve(1);
            ligneCommandeRepository.save(lg);
        });
        return voirCommandes(theModel);

    }




}
