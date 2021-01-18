package com.spring.esouk;

import com.spring.esouk.entity.Admin;
import com.spring.esouk.entity.Role;
import com.spring.esouk.entity.User;
import com.spring.esouk.entity.dao.AdminRepository;
import com.spring.esouk.entity.dao.CategorieRepository;
import com.spring.esouk.entity.dao.RoleRepository;
import com.spring.esouk.entity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecuirty1Application implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AccountService accountService;
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecuirty1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.save(new Role("USER","client"));
		roleRepository.save(new Role("AGRICULTEUR","agriculteur"));
		roleRepository.save(new Role("ADMIN","admin"));
		/*tegorieRepository.save(new Categorie(1,"Fruits","fruit","Tous les fruits se trouvent ici "));
		categorieRepository.save(new Categorie(2,"Legumes","legumes","Tous les legumes se trouvent ici "));
		categorieRepository.save(new Categorie(3,"Produit Laitiers","laitier","Tous les produits laitiers se trouvent ici "));
		categorieRepository.save(new Categorie(4,"Autres","Autres","Plusieurs autres choses "));
		categorieRepository.save(new Categorie(5,"Cereales","cereales","Tous les cereales trouvent ici "));*/
		User admin= accountService.saveAppUser(new User("admin","tiflet99",true));
		accountService.AddRoleToUse("admin","ADMIN");
		adminRepository.save(new Admin("missouri","aymane",admin));


	}
}
