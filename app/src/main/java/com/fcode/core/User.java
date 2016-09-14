package com.fcode.core;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fcorde on 02/09/16.
 */
public class User implements Serializable{


    //private static final long serialVersionUID = -7495897652017488896L;
    //private String objectId;
    private Integer id;
    private String email;
    private int role_id;
    private boolean admin;
    private boolean superadmin;
    private Date deleted_at;
    private String name;
    private String job;
    private String phone;
    private String provider;

    private String token;
    private String uid;
    private String client;
    private String tokenType;
    private String expiry;









    public User() {
            this.email = "";
            this.name = "";
            this.job = "";
            this.role_id = 0;
            this.phone = "";
            this.id = 0;
            this.token = "";
            this.uid = "";
            this.provider = "";
            this.admin = false;
            this.superadmin = false;
            this.deleted_at = new Date();
            this.client = "";
            this.tokenType = "";
            this.expiry = "";
            /*this.gravatarId = "";
            this.avatarUrl = "";
            this.username = "";
            this.objectId = "";*/



        }


        public int getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public int getRole_id() {
            return role_id;
        }

        public void setRole_id(int role_id) {
            this.role_id = role_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public boolean isSuperadmin() {
            return superadmin;
        }

        public void setSuperadmin(boolean superadmin) {
            this.superadmin = superadmin;
        }

        public Date getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(Date deleted_at) {
            this.deleted_at = deleted_at;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public String getExpiry() {
            return expiry;
        }

        public void setExpiry(String expiry) {
            this.expiry = expiry;
        }

    /*public String getGravatarId() {
        return gravatarId;
    }

    public String getAvatarUrl() {
        if (TextUtils.isEmpty(avatarUrl)) {
            String gravatarId = getGravatarId();
            if (TextUtils.isEmpty(gravatarId))
                gravatarId = GravatarUtils.getHash(getUsername());
            avatarUrl = getAvatarUrl(gravatarId);
        }
        return avatarUrl;
    }

    private String getAvatarUrl(String id) {
        if (!TextUtils.isEmpty(id))
            return "https://secure.gravatar.com/avatar/" + id + "?d=404";
        else
            return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }*/
}
