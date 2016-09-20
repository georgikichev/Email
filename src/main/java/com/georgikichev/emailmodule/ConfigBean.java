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
    
    String emailAddress;
    String emailPassword;
    String serverName;
    
    public ConfigBean(boolean senderOrReceiver, String emailAddress,
            String emailPassword, String serverName)
    {       
        emailAddress = this.emailAddress;
        emailPassword = this.emailPassword;
        serverName = this.serverName;        
    }
    
    public SmtpServer createSMTPServer()
    {
        //create the server
        SmtpServer<SmtpSslServer> server = SmtpSslServer.create(serverName); 
                
        server.authenticateWith(emailAddress, emailPassword);
        server.debug(true);
        return server;
    }
    
    public ImapSslServer createImap()
    {
        ImapSslServer imapSslServer = new ImapSslServer(serverName,
                 emailAddress, emailPassword);
        
        imapSslServer.setProperty("mail.debug", "true");
        return imapSslServer;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
    
}
