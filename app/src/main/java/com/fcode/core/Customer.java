package com.fcode.core;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by fcorde on 25/09/16.
 */

public class Customer implements Serializable {

    private int id;
    private String name;
    private String last_name;
    private String email;
    private String phone_number;
    private String cellphone;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private int user_id;



    public Customer() {

        id = 0;
        name = "";
        last_name = "";
        email = "";
        phone_number = "";
        cellphone = "";
        updated_at = null;
        deleted_at = null;
        user_id = 0;


    }

    public Customer(int id, int user_id,String name, String last_name, String email,
                    String phone_number, String cellphone, Timestamp updated_at, Timestamp deleted_at) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.cellphone = cellphone;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.user_id = user_id;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /*public ArrayList<ServiceOrder> getService_orders() {
        return service_orders;
    }

    public void setService_orders(ArrayList<ServiceOrder> service_orders) {
        this.service_orders = service_orders;
    }

    public ServiceOrder getServiceOrder(int index){
        return service_orders.get(index);
    }

    public void setServiceOrder(ServiceOrder serviceOrder){
        this.service_orders.clear();
        this.service_orders.add(serviceOrder);

    }*/

}
