package com.raghav.moviesnow.models;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Profile{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String f_name;
    private String l_name;
    private String pass;
    private int priv_lvl;
    private String mob;
    private String email;

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getF_name() {
        return this.f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return this.l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public int getPriv_lvl() {
        return this.priv_lvl;
    }

    public void setPriv_lvl(int priv_lvl) {
        this.priv_lvl = priv_lvl;
    }

    public String getMob() {
        return this.mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}