package com.fcode.core;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by fcorde on 11/09/16.
 */
public class ServiceOrder implements Serializable {

    private String   description;
    private Date date_in;
    private Date     date_out;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private Integer  user_id;
    private String   color;
    private String service_request;
    private String   make;
    private String   model;
    private Integer  year;
    private Boolean  win_one;
    private Boolean  win_two;
    private Boolean  win_three;
    private Boolean  win_four;
    private Boolean  dl_one;
    private Boolean  dl_two;
    private Boolean  dl_three;
    private Boolean  dl_four;
    private Boolean  ft;
    private Boolean  ab;
    private Boolean  hag;
    private Boolean  li;
    private Boolean  avna;
    private Boolean  hn;
    private Boolean  sj;
    private Boolean  abs;
    private Boolean  sa;
    private Boolean  gmg;
    private Boolean  fe;
    private Boolean  air_bag;
    private Boolean  sayda;
    private Boolean  lff;
    private Boolean  casc;
    private Boolean  ses;
    private Boolean  sm;
    private Boolean  na;
    private Boolean  ac;
    private Boolean  dx;
    private Boolean  sc;
    private Boolean  bpa;
    private Boolean  af;
    private Boolean  cc;
    private Integer  customer_id;
    private String   plate;

    public ServiceOrder(String description, Date date_in, Date date_out, Timestamp created_at, Timestamp updated_at, Timestamp deleted_at, Integer user_id, String color, String service_request, String make, String model, Integer year, Boolean win_one, Boolean win_two, Boolean win_three, Boolean win_four, Boolean dl_one, Boolean dl_two, Boolean dl_three, Boolean dl_four, Boolean ft, Boolean ab, Boolean hag, Boolean li, Boolean avna, Boolean hn, Boolean sj, Boolean abs, Boolean sa, Boolean gmg, Boolean fe, Boolean air_bag, Boolean sayda, Boolean lff, Boolean casc, Boolean ses, Boolean sm, Boolean na, Boolean ac, Boolean dx, Boolean sc, Boolean bpa, Boolean af, Boolean cc, Integer customer_id, String plate) {
        this.description = description;
        this.date_in = date_in;
        this.date_out = date_out;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.user_id = user_id;
        this.color = color;
        this.service_request = service_request;
        this.make = make;
        this.model = model;
        this.year = year;
        this.win_one = win_one;
        this.win_two = win_two;
        this.win_three = win_three;
        this.win_four = win_four;
        this.dl_one = dl_one;
        this.dl_two = dl_two;
        this.dl_three = dl_three;
        this.dl_four = dl_four;
        this.ft = ft;
        this.ab = ab;
        this.hag = hag;
        this.li = li;
        this.avna = avna;
        this.hn = hn;
        this.sj = sj;
        this.abs = abs;
        this.sa = sa;
        this.gmg = gmg;
        this.fe = fe;
        this.air_bag = air_bag;
        this.sayda = sayda;
        this.lff = lff;
        this.casc = casc;
        this.ses = ses;
        this.sm = sm;
        this.na = na;
        this.ac = ac;
        this.dx = dx;
        this.sc = sc;
        this.bpa = bpa;
        this.af = af;
        this.cc = cc;
        this.customer_id = customer_id;
        this.plate = plate;
    }

    public ServiceOrder() {
        this.description = "";
        this.date_in = null;
        this.date_out = null;
        this.created_at = null;
        this.updated_at = null;
        this.deleted_at = null;
        this.user_id = 0;
        this.color = "";
        this.service_request = "";
        this.make = "";
        this.model = "";
        this.year = 0;
        this.win_one = false;
        this.win_two = false;
        this.win_three = false;
        this.win_four = false;
        this.dl_one = false;
        this.dl_two = false;
        this.dl_three = false;
        this.dl_four = false;
        this.ft = false;
        this.ab = false;
        this.hag = false;
        this.li = false;
        this.avna = false;
        this.hn = false;
        this.sj = false;
        this.abs = false;
        this.sa = false;
        this.gmg = false;
        this.fe = false;
        this.air_bag = false;
        this.sayda = false;
        this.lff = false;
        this.casc = false;
        this.ses = false;
        this.sm = false;
        this.na = false;
        this.ac = false;
        this.dx = false;
        this.sc = false;
        this.bpa = false;
        this.af = false;
        this.cc = false;
        this.customer_id = 0;
        this.plate = "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_in() {
        return date_in;
    }

    public void setDate_in(Date date_in) {
        this.date_in = date_in;
    }

    public Date getDate_out() {
        return date_out;
    }

    public void setDate_out(Date date_out) {
        this.date_out = date_out;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getService_request() {
        return service_request;
    }

    public void setService_request(String service_request) {
        this.service_request = service_request;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getWin_one() {
        return win_one;
    }

    public void setWin_one(Boolean win_one) {
        this.win_one = win_one;
    }

    public Boolean getWin_two() {
        return win_two;
    }

    public void setWin_two(Boolean win_two) {
        this.win_two = win_two;
    }

    public Boolean getWin_three() {
        return win_three;
    }

    public void setWin_three(Boolean win_three) {
        this.win_three = win_three;
    }

    public Boolean getWin_four() {
        return win_four;
    }

    public void setWin_four(Boolean win_four) {
        this.win_four = win_four;
    }

    public Boolean getDl_one() {
        return dl_one;
    }

    public void setDl_one(Boolean dl_one) {
        this.dl_one = dl_one;
    }

    public Boolean getDl_two() {
        return dl_two;
    }

    public void setDl_two(Boolean dl_two) {
        this.dl_two = dl_two;
    }

    public Boolean getDl_three() {
        return dl_three;
    }

    public void setDl_three(Boolean dl_three) {
        this.dl_three = dl_three;
    }

    public Boolean getDl_four() {
        return dl_four;
    }

    public void setDl_four(Boolean dl_four) {
        this.dl_four = dl_four;
    }

    public Boolean getFt() {
        return ft;
    }

    public void setFt(Boolean ft) {
        this.ft = ft;
    }

    public Boolean getAb() {
        return ab;
    }

    public void setAb(Boolean ab) {
        this.ab = ab;
    }

    public Boolean getHag() {
        return hag;
    }

    public void setHag(Boolean hag) {
        this.hag = hag;
    }

    public Boolean getLi() {
        return li;
    }

    public void setLi(Boolean li) {
        this.li = li;
    }

    public Boolean getAvna() {
        return avna;
    }

    public void setAvna(Boolean avna) {
        this.avna = avna;
    }

    public Boolean getHn() {
        return hn;
    }

    public void setHn(Boolean hn) {
        this.hn = hn;
    }

    public Boolean getSj() {
        return sj;
    }

    public void setSj(Boolean sj) {
        this.sj = sj;
    }

    public Boolean getAbs() {
        return abs;
    }

    public void setAbs(Boolean abs) {
        this.abs = abs;
    }

    public Boolean getSa() {
        return sa;
    }

    public void setSa(Boolean sa) {
        this.sa = sa;
    }

    public Boolean getGmg() {
        return gmg;
    }

    public void setGmg(Boolean gmg) {
        this.gmg = gmg;
    }

    public Boolean getFe() {
        return fe;
    }

    public void setFe(Boolean fe) {
        this.fe = fe;
    }

    public Boolean getAir_bag() {
        return air_bag;
    }

    public void setAir_bag(Boolean air_bag) {
        this.air_bag = air_bag;
    }

    public Boolean getSayda() {
        return sayda;
    }

    public void setSayda(Boolean sayda) {
        this.sayda = sayda;
    }

    public Boolean getLff() {
        return lff;
    }

    public void setLff(Boolean lff) {
        this.lff = lff;
    }

    public Boolean getCasc() {
        return casc;
    }

    public void setCasc(Boolean casc) {
        this.casc = casc;
    }

    public Boolean getSes() {
        return ses;
    }

    public void setSes(Boolean ses) {
        this.ses = ses;
    }

    public Boolean getSm() {
        return sm;
    }

    public void setSm(Boolean sm) {
        this.sm = sm;
    }

    public Boolean getNa() {
        return na;
    }

    public void setNa(Boolean na) {
        this.na = na;
    }

    public Boolean getAc() {
        return ac;
    }

    public void setAc(Boolean ac) {
        this.ac = ac;
    }

    public Boolean getDx() {
        return dx;
    }

    public void setDx(Boolean dx) {
        this.dx = dx;
    }

    public Boolean getSc() {
        return sc;
    }

    public void setSc(Boolean sc) {
        this.sc = sc;
    }

    public Boolean getBpa() {
        return bpa;
    }

    public void setBpa(Boolean bpa) {
        this.bpa = bpa;
    }

    public Boolean getAf() {
        return af;
    }

    public void setAf(Boolean af) {
        this.af = af;
    }

    public Boolean getCc() {
        return cc;
    }

    public void setCc(Boolean cc) {
        this.cc = cc;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
