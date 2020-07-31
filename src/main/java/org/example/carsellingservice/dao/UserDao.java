package org.example.carsellingservice.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UserDao {
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String gender;
    private String locale;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;

    public UserDao(
            String id, String name, String email, String gender,
            String locale, LocalDateTime lastVisit, String userpic
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.locale = locale;
        this.lastVisit = lastVisit;
        this.userpic = userpic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }
}
