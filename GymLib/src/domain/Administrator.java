/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;

public class Administrator implements Serializable {
    private static final long serialVersionUID = 1L;

    private int administratorID;
    private String username;
    private String password;

    public Administrator() {}

    public Administrator(int administratorID, String username, String password) {
        this.administratorID = administratorID;
        this.username = username;
        this.password = password;
    }
    
    public int getAdministratorID() { return administratorID; }
    public void setAdministratorID(int administratorID) { this.administratorID = administratorID; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Administrator{id=" + administratorID + ", username=" + username + "}";
    }
}