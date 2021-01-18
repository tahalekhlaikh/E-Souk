package com.spring.esouk.controller;

import com.spring.esouk.entity.Agriculteur;
import com.spring.esouk.entity.Client;
import com.spring.esouk.entity.Commentaire;
import com.spring.esouk.entity.Produitmettre;
import com.spring.esouk.entity.dao.AgriculteurRepository;
import com.spring.esouk.entity.dao.ClientRepository;
import com.spring.esouk.entity.dao.CommentaireRepository;
import com.spring.esouk.entity.dao.ProduitmettreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
//affichae du detail produit detail ( pas encore)
public class ProduitDetailController {
    @Autowired
    private ProduitmettreRepository produitmettreRepository;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ClientRepository clientDAO;
    @Autowired
    private HomeUserController homeUserController;
    @Autowired
    private AgriculteurRepository agriculteurRepository;
    @Autowired
    private CommentaireRepository commentaireRepository;

    @GetMapping("/produitDetail/{id}")
    public String showProduit(Model theModel, @PathVariable("id")int id)
    {
        int etoile=0;

        theModel.addAttribute("panier",homeUserController.getPanier());
        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username=securityContext.getAuthentication().getName();
        Client client=clientDAO.findByUserName(username);
        theModel.addAttribute("client",client);
        Produitmettre produitmettre=  produitmettreRepository.findById(id).get();
        System.out.println(id);
        if(produitmettre==null)
        {
            return "redirect : /userHome/home";
        }
        theModel.addAttribute("produitmettre",produitmettre);
        theModel.addAttribute("etoileF",etoile);
        theModel.addAttribute("produitMettre",produitmettreRepository.findById1(produitmettre.getAgriculteur().getId()));
        Commentaire cm=new Commentaire();
        theModel.addAttribute("commentair",cm);
        theModel.addAttribute("commentaires",commentaireRepository.findByProduitmettre(produitmettre));
        return "produitDetail";
    }
    @PostMapping("/produitDetail/{id}")
    public String evaluate(HttpServletRequest request,@PathVariable("id")int id)
    {
        Produitmettre produitmettre=  produitmettreRepository.findById(id).get();
        Agriculteur agr=produitmettre.getAgriculteur();
        System.out.println(agr.getEtoileF());
        agr.evaluer(Double.parseDouble(request.getParameter("etoileF")));
        System.out.println(agr.getEtoileF());
        System.out.println(Integer.parseInt(request.getParameter("etoileF")));
        agriculteurRepository.save(agr);

        return "redirect:/produitDetail/{id}";
    }
    @PostMapping("/commenter/{id}")
    public String commenter(@ModelAttribute("commentair") Commentaire commentaire,@PathVariable("id") int id)
    {
        commentaire.setDateDeCreation(new Date(System.currentTimeMillis()));
        SecurityContext securityContext= (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
        String username=securityContext.getAuthentication().getName();
        Client client=clientDAO.findByUserName(username);
        commentaire.setClient(client);
        Produitmettre pr=produitmettreRepository.findById(id).get();
        commentaire.setAgr(pr.getAgriculteur());
        commentaire.setProduitmettre(produitmettreRepository.findById(id).get());
        commentaireRepository.save(commentaire);
        return "redirect:/produitDetail/"+id;

    }
}
