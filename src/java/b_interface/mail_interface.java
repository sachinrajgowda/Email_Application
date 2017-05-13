
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package b_interface;
/**
 *
 * @author Sachin
 */
import bean.msg_content;
import javax.ejb.Remote;

@Remote
public interface mail_interface 
{
    public void sendMessage(String username, String password, String recipient,String sender,String subject, String data);
    public int connect(String login, String passwd);
    public void disconnect();
    public msg_content getMessage(int i);
    public void deleteMessage(int i,String username,String password);
}
