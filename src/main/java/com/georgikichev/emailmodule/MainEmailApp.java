/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.georgikichev.emailmodule;

import java.io.File;
import java.util.List;

import javax.mail.Flags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.mail.Email;
import jodd.mail.EmailAddress;
import jodd.mail.EmailAttachment;
import jodd.mail.EmailFilter;
import jodd.mail.EmailMessage;
import jodd.mail.ImapSslServer;
import jodd.mail.MailAddress;
import jodd.mail.ReceiveMailSession;
import jodd.mail.ReceivedEmail;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import jodd.mail.SmtpSslServer;

/**
 * Main application (business class) that will be used to send and receive
 * emails using gmail, the emails can include attachments.
 * @author Georgi Kichev
 */
public class MainEmailApp {
    
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    private final String smtpServerName = "smtp.gmail.com";
    private final String imapServerName = "imap.gmail.com";
    
    private final String attachmentDirectory = "";
        
    public void start()
    {
        send("georgiemail01@gmail.com", "georgiemail02@gmail.com", "Hi",
                "Work please");
    }
    private SmtpServer createSMTPServer(String serverName)
    {
        //create the server
        SmtpServer<SmtpSslServer> server = SmtpSslServer.create(serverName);      
        return server;
    }
    
    /**
     * Used to send an email to the receiver when both a receiver and 
     * sender are given.
    */   
    public void send(String sender, String receiver,String subj, String message)
    {
        //create
        SmtpServer<SmtpSslServer> server = createSMTPServer(smtpServerName);
        //authenticate with sender pswd
        server.authenticateWith(sender,"passhere");
        
        //debug on to see any errors
        server.debug(true);
        
        //create email object and add the text to it
        Email email = Email.create().from(sender).subject(subj).addText(message);
        
        //create session and send email
        SendMailSession session = server.createSession();
        session.open();
        session.sendMail(email);
        session.close();
    }
    /**
     * Method that when fed a receiver email, will import all new emails
     * that have been sent to this particular receiver. 
    */
    public void receive(String receiver)
    {
        //create IMAP server
        ImapSslServer imapServer = new ImapSslServer(imapServerName, receiver,
        "pass here");
        
        //debug to see any errors
        imapServer.setProperty("mail.debug", "true");
        
        //create session for server
        ReceiveMailSession session = imapServer.createSession();
        session.open();
        
        //create obj that communicates with server
        ReceivedEmail[] emails = session.receiveEmailAndMarkSeen(EmailFilter.
                filter().flag(Flags.Flag.SEEN, false));
        
        //loop through emails here
        //TODO
    }
    
    /**
     * Used to send an email to the receivers when both a sender and 
     * an array of receivers are given, The receivers will be able to view
     * all of the other receivers.
    */   
    public void sendWithCC(String sender, String [] receiver)
    {
        
    }  
    /**
     * Used to send an email to the receivers when both a sender and 
     * an array of receivers are given, The receivers will not be able to view
     * all of the other receivers.
    */   
    public void sendWithBCC(String sender, String [] receivers)
    {
        
    }
    /**
     * Used to send an email that contains an attachment, like a picture.
     * @param sender
     * @param receiver 
     */
    public void sendWithAttachment(String sender, String [] receivers)
    {
        
    }
}
