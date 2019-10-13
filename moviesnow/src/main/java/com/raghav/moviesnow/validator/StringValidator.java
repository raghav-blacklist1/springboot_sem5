package com.raghav.moviesnow.validator;

import java.util.regex.Pattern; 
  
public class StringValidator
{ 
    public StringValidator(){
        
    }
    public boolean isValidemail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }

    public boolean isValidmob(String mob){

        String mobRegex = "[0-9]{10}"; 
        Pattern pat = Pattern.compile(mobRegex); 
        if (mob == null) 
            return false; 
        return pat.matcher(mob).matches(); 
    }

} 