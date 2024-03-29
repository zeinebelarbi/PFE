package com.example.managingfoodreservation.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class
UserWrapper {

    private Integer id;
    private String username;
    private  String email;
    private String password;
    private String role;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserWrapper(Integer id, String username, String email, String password, String role, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}

