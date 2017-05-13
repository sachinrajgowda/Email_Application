
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;
/**
 *
 * @author Sachin
 */
import b_interface.mail_interface;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;
import java.io.Serializable;
import javax.naming.InitialContext;

@ManagedBean(name="mb")
@SessionScoped
public class mail_bean implements Serializable
{
    @EJB
    private mail_interface mailEJB;
    private String username="";
    private String password="";
    private String receiver="";
    private String subject="";
    private String message="";
    private String sender="";
    private msg_content msg;
    public int con=0;
    ArrayList<msg_content> array_msg= new ArrayList();
   
    public mail_bean()
    {
        try 
        {
            InitialContext init_context = new InitialContext();
            this.mailEJB = (mail_interface)init_context.lookup("java:global/op/mail_ejb");
        }
        catch (Exception e) 
        {
            System.out.println(e.toString());
        }
    }

    public mail_interface getMailEJB() {
        return mailEJB;
    }

    public void setMailEJB(mail_interface mailEJB) {
        this.mailEJB = mailEJB;
    }

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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    public msg_content getMsg() {
        return msg;
    }

    public void setMsg(msg_content msg) {
        this.msg = msg;
    }

    public ArrayList<msg_content> getArray_msg() {
        this.array_msg.clear();
        for (int i = 0; i < con; ++i) {
            if (this.mailEJB.getMessage(i) == null) 
                continue;
            this.array_msg.add(i, this.mailEJB.getMessage(i));
        }
        return this.array_msg;
    }

    public void setArray_msg(ArrayList<msg_content> array_msg) {
        this.array_msg = array_msg;
    }
  
    public String login()
    {
	if((username.matches("csuhduke") || username.matches("csuhduke[1-9]")) && password.matches("cs6522"))
	{
		con = this.mailEJB.connect(this.username, this.password);
		return "welcome.xhtml";
	}
	else
		return "";
    }
    
    public String sendMail()
    {
        //this.sender = this.username ;
	this.mailEJB.sendMessage(username + "@yyuj.sci.csueastbay.edu", password, receiver +"@yyuj.sci.csueastbay.edu", username, subject, message);
	return "welcome.xhtml";
    }
    
    public void display(msg_content mc)
    {
        this.msg = mc;
    }
    
    public void delete()
    {
        for (msg_content data : this.array_msg) {
            if (!data.flag) 
                continue;
            int i = this.array_msg.indexOf((Object)data);
            this.mailEJB.deleteMessage(i,username,password);
            break;
        }
    }
    
    public String logout()
    {
        this.username = null;
        this.password = null;
        this.receiver = null;
        this.subject = null;
        this.message = null;
        this.msg = null;
        this.mailEJB.disconnect();
	return "login.xhtml";
    }
}
