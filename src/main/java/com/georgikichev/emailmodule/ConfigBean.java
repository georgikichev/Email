/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.georgikichev.emailmodule;

import jodd.mail.SmtpServer;
import jodd.mail.SmtpSslServer;
import jodd.mail.ImapSslServer;

/**
 *
 * @author 1333539
 */
public class ConfigBean {
    
    String emailAddressSender;
    String emailAddressReceiver;
    String emailPasswordSender;
    String emailPasswordReceiver;
    String attachmentFolder;
    
    public ConfigBean()
    {       
        emailAddressSender = "georgiemail1@gmain.com";
        emailPasswordSender = "pass";       
        emailAddressReceiver = "georgiemail2@gmain.com";
        emailPasswordReceiver = "pass";   
        //need to create this folder
        attachmentFolder = "C://Attachments";
    }
    
    public SmtpServer createSMTPServer(String serverName)
    {
        //create the server
        SmtpServer<SmtpSslServer> server = SmtpSslServer.create(serverName); 
                
        server.authenticateWith(emailAddressSender, emailPasswordSender);
        server.debug(true);
        return server;
    }
    
    public ImapSslServer createImap(String serverName)
    {
        ImapSslServer imapSslServer = new ImapSslServer(serverName,
                 emailAddressReceiver, emailPasswordReceiver);
        
        imapSslServer.setProperty("mail.debug", "true");
        return imapSslServer;
    }

    public String getEmailAddressSender() {
        return emailAddressSender;
    }

    public void setEmailAddressSender(String emailAddressSender) {
        this.emailAddressSender = emailAddressSender;
    }

    public String getEmailAddressReceiver() {
        return emailAddressReceiver;
    }

    public void setEmailAddressReceiver(String emailAddressReceiver) {
        this.emailAddressReceiver = emailAddressReceiver;
    }

    public String getEmailPasswordSender() {
        return emailPasswordSender;
    }

    public void setEmailPasswordSender(String emailPasswordSender) {
        this.emailPasswordSender = emailPasswordSender;
    }

    public String getEmailPasswordReceiver() {
        return emailPasswordReceiver;
    }

    public void setEmailPasswordReceiver(String emailPasswordReceiver) {
        this.emailPasswordReceiver = emailPasswordReceiver;
    }

    public String getAttachmentFolder() {
        return attachmentFolder;
    }

    public void setAttachmentFolder(String attachmentFolder) {
        this.attachmentFolder = attachmentFolder;
    }
    
}
