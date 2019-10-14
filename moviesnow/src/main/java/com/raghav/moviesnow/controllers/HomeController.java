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

    @Autowired
    private Moviedao moviedao;

    @Autowired
    private MovieTagdao movietagdao;

    @Autowired
    private Feeddao feeddao;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        Object lflag=session.getAttribute("login_flag");
        if( lflag == null)  session.setAttribute("login_flag", 0);
        List<Movie> mvtmp = moviedao.getall();
        for(int i=0;i<mvtmp.size();i++){

            mvtmp.get(i).setTags(movietagdao.getall(mvtmp.get(i).getId()));
        }
        model.addAttribute("movies_all", mvtmp);
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
    @RequestParam("descr") String descr,@RequestParam("tags") String tags, @RequestParam("imdb_rating") Double imdb_rating
    ,@RequestParam("runtime") int runtime) {

        int priv=(int)session.getAttribute("priv_lvl");

        if( priv != 2 ){

            session.setAttribute("message", "You do not have access to this page");
            return "redirect:/";
        }

        String[] alltags = tags.split(" ", 0);
        
        Movie newmov = new Movie();
        newmov.setName(name);
        newmov.setDesc(descr);
        newmov.setImdb_rating(imdb_rating);
        newmov.setRuntime(runtime);

        moviedao.save(newmov);
        Movie curr = moviedao.getObj(name);

        for(String tagg:alltags){

            MovieTag newtag = new MovieTag();
            newtag.setTag_name(tagg);
            newtag.setMovie(curr);
            movietagdao.save(newtag);
        }

        return "redirect:/";
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

    @GetMapping("/admin/deletemov/{movie_id}")
    public String revokeaccess(HttpSession session, Model model, @PathVariable("movie_id") int id) {

        int lflag=(int)session.getAttribute("login_flag");
        if( lflag == 0 ){

            session.setAttribute("message", "You are not logged in");
            return "redirect:/";
        }
        int priv=(int)session.getAttribute("priv_lvl");

        if( priv != 2 ){

            session.setAttribute("message", "You do not have access to this page");
            return "redirect:/";
        }

        if (!moviedao.existsById(id)){

            session.setAttribute("message", "Unknown movie Specified");
            return "redirect:/";
        }

        moviedao.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/addfeedback/{movie_id}")
    public String addfeed(HttpSession session, Model model, @PathVariable("movie_id") int movie_id) {

        model.addAttribute("movie_id", movie_id);
        return "addfeed";
    }

    @PostMapping("/addfeedback")
    public String addfeedpost(HttpSession session, Model model, @RequestParam("stars") int stars,
    @RequestParam("descr") String descr, @RequestParam("movie_id") int movie_id) {

        Feedback newfeed = new Feedback();
        newfeed.setComments(descr);
        newfeed.setRating(stars);
        newfeed.setProfile(profiledao.getprofile((String)session.getAttribute("user_email")));
        newfeed.setMovie(moviedao.getObj(movie_id));

        feeddao.save(newfeed);
       
        return "redirect:/feeds/"+movie_id;
    }

    @GetMapping("/feeds/{movie_id}")
    public String showfeed(HttpSession session, Model model, @PathVariable("movie_id") int movie_id) {

        if(!moviedao.existsById(movie_id)){

            session.setAttribute("message","No such movie exists");
            return "redirect:/";
        }

        Movie currmv = moviedao.getObj(movie_id);
        model.addAttribute("movie_name", currmv.getName());
        List<Feedback> ls = feeddao.getreviews(movie_id);

        for(int i=0;i<ls.size();i++){

            ls.get(i).setProfile(profiledao.getbyid(ls.get(i).getProfile().getId()));
        }
        model.addAttribute("all_feed", ls);

        return "feed";
    }
}