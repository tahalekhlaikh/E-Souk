package com.spring.esouk.controller;

import com.spring.esouk.entity.Agriculteur;
import com.spring.esouk.entity.Commande;
import com.spring.esouk.entity.Produitmettre;
import com.spring.esouk.entity.User;
import com.spring.esouk.entity.dao.*;
import com.spring.esouk.entity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private AgriculteurRepository agriculteurRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProduitmettreRepository produitmettreRepository;
    @GetMapping("/AjouterAgriculteur")
    private String showAgr(Model theModel)
    {

        theModel.addAttribute("agriculteur",new Agriculteur());
        return "AjouterAgriculteur";
    }
    @GetMapping("/admin")
    public String showHome(Model theModel)
    {
        theModel.addAttribute("nombreDeCommandes",commandeRepository.findAll().size());
        theModel.addAttribute("nombreDeClient",clientRepository.findAll().size());
        final int[] total = {0};
        produitmettreRepository.findAll().forEach(pm->{
            total[0] +=pm.getQte();
        });
        theModel.addAttribute("nombreDeProduitmettre",total[0]);
        theModel.addAttribute("nombreDeLigneCommande",ligneCommandeRepository.findAll().size());
        theModel.addAttribute("nombreAgriculteur",agriculteurRepository.findAll().size());
        theModel.addAttribute("agriculteurs",agriculteurRepository.findAll());
        Collection<Produitmettre> produitmettres=produitmettreRepository.findAll();

        HashMap<Integer,Integer> hashMap=new HashMap<Integer,Integer>();
        produitmettres.forEach(pm->{
                    hashMap.put(pm.getId(),pm.getNombreVendu());
        });
        LinkedHashMap<Integer, Integer> reverseSortedMap = new LinkedHashMap<>();
        System.out.println(hashMap);
//Use Comparator.reverseOrder() for reverse ordering
        hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        System.out.println(reverseSortedMap);
        List<Integer> list=new ArrayList<>();
        List<Produitmettre> produitmettres2=new ArrayList<>();
        Set set = reverseSortedMap.entrySet();
        Iterator it = set.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            list.add((Integer) entry.getKey());
        }

        list.forEach(lt->{
            produitmettres2.add(produitmettreRepository.findById(lt).get());
        });
        theModel.addAttribute("produitmettre",produitmettres2);
        return "admin";
    }
    @GetMapping("/addAgriculteur")
    public String showForm(Model theModel){
        theModel.addAttribute("agriculteur",new Agriculteur());
        return "AjouterAgriculteur";


    }
    @PostMapping("/addAgriculteur")
    private  String ajouterAgriculteur(@ModelAttribute("agricutleur") Agriculteur agr,Model theModel)
    {

        agr.setPhoto("moumou");
        String username=agr.getPrenom()+agr.getNom();
        User agriculteur=new User(username,agr.getCin(),true);
        accountService.saveAppUser(agriculteur);
        accountService.AddRoleToUse(username,"AGRICULTEUR");
        agr.setUser(agriculteur);
        agr.setPhoto("user");
        agriculteurRepository.save(agr);


        return "redirect:/admin";

    }
    @GetMapping("/profil/{id}")
    public String voirProfil(@PathVariable(name = "id") int id, Model theModel)
    {

        Agriculteur agr=agriculteurRepository.findById(id);
        theModel.addAttribute("agriculteur",agr);
        theModel.addAttribute("nombreProduit",produitmettreRepository.findByAgriculteur(agr));
        theModel.addAttribute("nombreCommande",commandeRepository.findByAgriculteur(agr));
        theModel.addAttribute("nombreLigne",ligneCommandeRepository.findByProduitmettre_Agriculteur(agr));
        theModel.addAttribute("total",calculerPrixTotalAgric(commandeRepository.findByAgriculteur(agr),agr));

        return "profilAgriculteur";
    }
    public int calculerPrixTotalAgric(Collection<Commande> commandes,Agriculteur agr)
    {
        final int[] total = {0};
        commandes.forEach(cm->
        {
            total[0] +=cm.total(cm.chercherAgr(agr));
        });
        return  total[0];
    }
    public  HashMap sort(HashMap map) {
        List linkedlist = new LinkedList(map.entrySet());
        Collections.sort(linkedlist, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = linkedlist.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
    @GetMapping("/bannir/{id}")
    public String bannir(@PathVariable(name="id") int id)
    {
        agriculteurRepository.delete(agriculteurRepository.findById(id));
        return "redirect:/admin";
    }
}

