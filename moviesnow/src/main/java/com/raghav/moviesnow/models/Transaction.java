package com.raghav.moviesnow.models;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Transaction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trans_id;
    private Date date;
    private String pay_mode;
    private int amt;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPay_mode() {
        return this.pay_mode;
    }

    public void setPay_mode(String pay_mode) {
        this.pay_mode = pay_mode;
    }

    public int getAmt() {
        return this.amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }


}