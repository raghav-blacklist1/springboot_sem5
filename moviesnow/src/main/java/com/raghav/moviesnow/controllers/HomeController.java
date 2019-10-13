package com.raghav.moviesnow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.raghav.moviesnow.validator.StringValidator;
import com.raghav.moviesnow.modeldao.*;
import com.raghav.moviesnow.models.*;
import com.raghav.moviesnow.hash.Hashor;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private Profiledao profiledao;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

       // System.out.println(flag);
        Object lflag=session.getAttribute("login_flag");
        if(lflag==null) session.setAttribute("login_flag",0);
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpSession session, Model model) {

        int lflag=(int)session.getAttribute("login_flag");

        if(lflag==1)   return "redirect:/";

        return "login";
    }

    @PostMapping("/login")
    public String loginpost(HttpSession session, @RequestParam("email") String email,
    @RequestParam("pass") String pass, Model model) {        
        
        if(profiledao.userexists(email)){

            String tmp = profiledao.getpass(email);

            Hashor htmp = new Hashor();
            if(tmp.compareTo(htmp.gen(pass))==0){

                session.setAttribute("login_flag", 1);
                session.setAttribute("user_name", profiledao.username(email));
                session.setAttribute("user_email", email);
                session.setAttribute("priv_lvl", profiledao.getpriv(email));
                return "redirect:/";
            }
        }

        session.setAttribute("message", "Invalid Username or Password.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutpost(HttpSession session, Model model) {        
        
        int lflag=(int)session.getAttribute("login_flag");
        if(lflag==0)    return "redirect:/";

        session.setAttribute("login_flag", 0);
        session.removeAttribute("user_name");
        session.removeAttribute("priv_lvl");
        session.removeAttribute("user_email");
        session.setAttribute("message", "You have been logged out successfully");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(HttpSession session, Model model) {

        return "register";
    }

    @PostMapping("/register")
    public String addUser(HttpSession session, Model model, @RequestParam("f_name") String f_name,
    @RequestParam("l_name") String l_name, @RequestParam("password") String pass, @RequestParam("passwordConfirm") 
    String pass_con, @RequestParam("email") String email, @RequestParam("mob") String mob) {

        StringValidator valt = new StringValidator();
        Hashor thash = new Hashor();
        if(!valt.isValidemail(email)){

            session.setAttribute("message", "Email address is not valid");
            return "redirect:/register";
        }

        if(!valt.isValidmob(mob)){

            session.setAttribute("message", "Mobile is not valid");
            return "redirect:/register";
        }

        if(pass.compareTo(pass_con)!=0){

            session.setAttribute("message", "Passwords do not match");
            return "redirect:/register";
        }

        if(profiledao.userexists(email)){

            session.setAttribute("message", "Email already exists");
            return "redirect:/register";
        }

        Profile profile = new Profile();
        profile.setF_name(f_name);
        profile.setL_name(l_name);
        profile.setEmail(email);
        profile.setMob(mob);
        profile.setPass(thash.gen(pass));
        profile.setPriv_lvl(1);
        profiledao.save(profile);

        session.setAttribute("message", "User: " + f_name + " has been registered successfully.");
        return "redirect:/";
    }

    @PostMapping("/addmovie")
    public String addmoviepost(HttpSession session, Model model, @RequestParam("name") String name,
    @RequestParam("descr") String descr,@RequestParam("tags") String tags) {

        int priv=(int)session.getAttribute("priv_lvl");

        if( priv != 2 ){

            session.setAttribute("message", "You do not have access to this page");
            return "redirect:/";
        }

        return "redirect:/addmovie";
    }

    @GetMapping("/addmovie")
    public String addmovie(HttpSession session, Model model) {

        int priv=(int)session.getAttribute("priv_lvl");

        if( priv != 2 ){

            session.setAttribute("message", "You do not have access to this page");
            return "redirect:/";
        }

        return "addmovie";
    }

    @GetMapping("/admins")
    public String admins(HttpSession session, Model model) {

        model.addAttribute("people", profiledao.getall());
        //System.out.println(profiledao.getall());
        return "admin";
    }

    @GetMapping("/admin/grant/{user}")
    public String grantaccess(HttpSession session, Model model, @PathVariable("user") String user) {

        int priv=(int)session.getAttribute("priv_lvl");

        if( priv != 2 ){

            session.setAttribute("message", "You do not have access to this page");
            return "redirect:/";
        }

        if (!profiledao.userexists(user)){

            session.setAttribute("message", "Unknown profile");
            return "redirect:/admins";
        }

        profiledao.elevate_priv(user);
        return "redirect:/admins";
    }

    @GetMapping("/admin/revoke/{user}")
    public String revokeaccess(HttpSession session, Model model, @PathVariable("user") String user) {

        int priv=(int)session.getAttribute("priv_lvl");

        if( priv != 2 ){

            session.setAttribute("message", "You do not have access to this page");
            return "redirect:/";
        }

        if (!profiledao.userexists(user)){

            session.setAttribute("message", "Unknown profile");
            return "redirect:/admins";
        }

        String curr = (String)session.getAttribute("user_email");

        if(curr.compareTo(user)==0){

            session.setAttribute("message", "Self integrity compromised");
            return "redirect:/admins";
        }

        profiledao.revoke_priv(user);
        return "redirect:/admins";
    }
}