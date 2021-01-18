package com.spring.esouk.controller;

import com.spring.esouk.entity.Produit;
import com.spring.esouk.entity.Souk;
import com.spring.esouk.entity.dao.CategorieRepository;
import com.spring.esouk.entity.service.ServiceInterfaceProduitHome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	// le service qui gere les requetes sql du Produit
	@Autowired
	private ServiceInterfaceProduitHome serviceProduit;
	@Autowired
	private CategorieRepository categorieDAO;

	
	@SuppressWarnings("null")
	@GetMapping("/home")
	// Affichage de la page principale
	public String showHome(Model theModel)
	{
		Souk souk=serviceProduit.getSoukInTime();
		//Afichage des produits
		theModel.addAttribute("produits",serviceProduit.getProduits());
		theModel.addAttribute("categories",categorieDAO.findAll());

		return "eSouk";
	}
	
	@PostMapping("/searchProduit")
	public String searchProduit(Model theModel,@ModelAttribute("produit") Produit produit)
	
	{
		String nom=produit.getDesignation();
		Produit pr=serviceProduit.searchProduit(nom);
		theModel.addAttribute("produits",pr);
		
		
	return showHome(theModel);

}


}
	
