
package com.radtech;

/**
 *
 * @author Lucas
 */
public class Login extends Model{
    private String username, password;
    
    public Login(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
