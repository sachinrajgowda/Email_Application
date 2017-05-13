
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
import b_interface.mail_interface;
import bean.msg_content;
import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.mail.Folder;
import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Sachin
 */

@Stateful
public class mail_ejb implements mail_interface
{
    @Resource(name="mail/JamesMailSession")
    private Session session; 
    private static final String mailer = "JavaMailer";
    private Store store=null;
    private Folder folder = null;
    public Message[] messages=null;
    Properties properties;
   
   public void sendMessage(final String username, final String password, String recipient, String sender, String subject, String data) 
    {
        try 
        {
            this.properties.put("mail.smtp.host", "134.154.10.165");
            this.properties.put("mail.smtp.auth", "true");
            this.session = Session.getInstance((Properties)this.properties, (Authenticator)new Authenticator()
            {

                protected PasswordAuthentication getPasswordAuthentication() 
                {
                    return new PasswordAuthentication(username, password);
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(sender,false));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipient, false));
            msg.setSubject(subject);
            Date timeStamp = new Date();
            msg.setText(data);
            msg.setHeader("X-Mailer", mailer);
            msg.setSentDate(timeStamp);
            Transport.send(msg);
        } 
        catch (Exception e) 
        { 
            System.out.println(e.toString());
            throw new EJBException(e.getMessage());
        }
    }
    
    public msg_content getMessage(int i)
    {
        msg_content msg_c = null;
        try
        {
            if(i<this.messages.length)
            {
                String email = ((InternetAddress)this.messages[i].getFrom()[0]).toString();
                msg_c = new msg_content(false,email,this.messages[i].getSubject(),this.messages[i].getSentDate().toString(),this.messages[i].getContent().toString(),"");
                return msg_c;
            }
        }
        catch (IOException | MessagingException e)
        {
            e.printStackTrace();
        }
        return msg_c;
    }
    
    public void deleteMessage(int i,String username, String password) 
    {
        try 
        {
            if (i < this.messages.length) 
            {
                this.messages[i].setFlag(Flags.Flag.DELETED, true);
                this.folder.close(true);
                int a=this.connect(username, password);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    public int connect(String login, String passwd) 
    {
        int number = 0;
        try 
        {
            this.properties = new Properties();
            this.session = Session.getInstance((Properties)new Properties());
            this.store = this.session.getStore("pop3");
            try 
            {
                this.store.connect("134.154.10.165", login, passwd);
            }
            catch(MessagingException e)
            {
                e.printStackTrace();
            }
            this.folder = this.store.getFolder("INBOX");
            this.folder.open(Folder.READ_WRITE);
            this.messages = this.folder.getMessages();
            this.messages[0].setFlag(Flags.Flag.DELETED, true);
            number = this.messages.length;
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return number;
    }
    
    public void disconnect() 
    {
        try 
        {
            this.folder.close(true);
            this.store.close();
        }
        catch (Exception e) 
        {
            System.out.println(e.toString());
        }
    }
}
