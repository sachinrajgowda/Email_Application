/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package op;
/**
 *
 * @author Sachin
 */ 
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class authenticate extends Authenticator
{
    public String uname;
    private String pass;

    authenticate(String uname, String pass) {
        this.uname = uname;
        this.pass = pass;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.uname, this.pass);
    }
}
