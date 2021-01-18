package com.spring.esouk.controller;


import com.spring.esouk.entity.Client;
import com.spring.esouk.entity.Register;
import com.spring.esouk.entity.User;
import com.spring.esouk.entity.dao.ClientRepository;
import com.spring.esouk.entity.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/register")
        public String showForm(Register userForm,Model theModel)
        {
            theModel.addAttribute("error","");
            theModel.addAttribute("userForm",userForm);
            return "register";
        }

    @PostMapping("/register")
    public  String saveUser(@Valid Register user, BindingResult bindingResult, String error, Model theModel)
    {
        if(!user.getPassword().equals(user.getRepassword())) {
            error="les mots de passes sont differents";
            theModel.addAttribute("error",error);
            theModel.addAttribute("userForm",new Register());
            return "/register";
        }
        User appUser1=accountService.findByUserName(user.getUsername());
        if(appUser1!=null)
        {
            error="cette personne deja existe ";
            theModel.addAttribute("error",error);
            theModel.addAttribute("userForm",new Register());
            return "/register";
        }
        if (bindingResult.hasErrors()) {

            return "/register";
        }

        User appUser=new User();
        appUser.setPassword(user.getPassword());
        appUser.setUsername(user.getUsername());
        accountService.saveAppUser(appUser);
        accountService.AddRoleToUse(user.getUsername(),"USER");
        clientRepository.save(new Client(user.getNom(),user.getPrenom(),"user",user.getAdresse(),null,user.getTelephone(),appUser));
        return "redirect:/login";
    }

}
