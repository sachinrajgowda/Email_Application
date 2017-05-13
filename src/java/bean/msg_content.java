/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Sachin
 */
@ManagedBean
@SessionScoped
public class msg_content 
{
        boolean flag;
	public String receiver="";
	public String subject="";
	public String sentdate="";
	public String message="";
        public String sender="";
        public msg_content()
        {   }
	public msg_content(boolean f, String recv, String sub, String date, String msg, String sen)
	{
		this.flag = f;
		this.receiver = recv;
		this.subject = sub;
		this.sentdate = date;
		this.message = msg;
                this.sender=sen;
	}

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
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

    public String getSentdate() {
        return sentdate;
    }

    public void setSentdate(String sentdate) {
        this.sentdate = sentdate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
	
}
