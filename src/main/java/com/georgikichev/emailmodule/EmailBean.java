/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.georgikichev.emailmodule;

import java.io.File;
import jodd.mail.Email;
import jodd.mail.EmailAddress;
import jodd.mail.EmailAttachment;
import jodd.mail.EmailFilter;
import jodd.mail.EmailMessage;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import jodd.mail.MailAddress;
import jodd.mail.ReceiveMailSession;
import jodd.mail.ReceivedEmail;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import jodd.mail.SmtpSslServer;
import jodd.mail.ImapSslServer;
import javax.mail.Flags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 1333539
 */
public class EmailBean extends Email {
    private final Logger log = LoggerFactory.getLogger(getClass().getName());
    
    private Date date = new Date();
    private Flags flags;
    private String EmailFolder = "";
    
    private final String smtpServerName = "smtp.gmail.com";
    private final String imapServerName = "imap.gmail.com";
    
    public EmailBean()
    {
        //super();
    }
    
    /**
     *
     * @return
     */
    @Override
    public ArrayList<EmailAttachment> getAttachments()
    {
        return attachments;        
    }

    /**
     *
     * @return
     */
    @Override
    public MailAddress getFrom() {
        return from;
    }

    /**
     *
     * @param from
     */
    @Override
    public void setFrom(MailAddress from) {
        this.from = from;
    }

    /**
     *
     * @return
     */
    @Override
    public MailAddress[] getTo() {
        return to;
    }

    /**
     *
     * @param to
     */
    @Override
    public void setTo(MailAddress[] to) {
        this.to = to;
    }

    /**
     *
     * @return
     */
    @Override
    public MailAddress[] getReplyTo() {
        return replyTo;
    }

    /**
     *
     * @param replyTo
     */
    @Override
    public void setReplyTo(MailAddress[] replyTo) {
        this.replyTo = replyTo;
    }

    /**
     *
     * @return
     */
    @Override
    public MailAddress[] getCc() {
        return cc;
    }

    /**
     *
     * @param cc
     */
    @Override
    public void setCc(MailAddress[] cc) {
        this.cc = cc;
    }

    /**
     *
     * @return
     */
    @Override
    public MailAddress[] getBcc() {
        return bcc;
    }

    /**
     *
     * @param bcc
     */
    @Override
    public void setBcc(MailAddress[] bcc) {
        this.bcc = bcc;
    }

    /**
     *
     * @return
     */
    @Override
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     */
    @Override
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<EmailMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<EmailMessage> messages) {
        this.messages = messages;
    }

    /**
     *
     * @return
     */
    @Override
    public Date getSentDate() {
        return sentDate;
    }

    //new email objects have to be casted to EmailBean

    /**
     *
     * @param sentDate
     */
    @Override
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    //EQUALS METHOD NOT COMPLETE
    public boolean equals(EmailBean email) {
        //get the To and From from both emails
        MailAddress [] to1 = email.getTo();
        MailAddress [] to2 = this.getTo();      
        
        //comapre lengths and if not same return false, they cannot be equal
        if(to1.length != to2.length)
            return false;
        
        String [] toList1 = new String [to1.length];
        String [] toList2 = new String [to2.length]; 
        
        //convert the To to strings
        for(int i = 0; i < toList1.length; i ++)
        {
            toList1 [i] = to1[i].toString();
            toList2 [2] = to2[2].toString();
        }
        Arrays.sort(toList1);
        Arrays.sort(toList2);
        
        //compare the 2 lists
        for(int i = 0; i < toList1.length; i++)
        {
            if(!toList1[i].equals(toList2[i]))
                return false;
        }
        //compare subject
        if(!this.getSubject().equals(email.getSubject()))
            return false;
        
        //get cc
        MailAddress [] cc1 = this.getCc();
        MailAddress [] cc2 = email.getCc();
        
        //compare length
        if(cc1.length != cc2.length)
            return false;
        
        String [] ccList1 = new String [cc1.length];
        String [] ccList2 = new String [cc2.length];
        
        //convert the cc's to strings
        for(int i = 0; i < toList1.length; i ++)
        {
            ccList1 [i] = cc1[i].toString();
            ccList2[2] = cc2[2].toString();
        }
        Arrays.sort(ccList1);
        Arrays.sort(ccList2);
        
        //compare each element
        for(int i = 0; i < cc1.length; i++)
        {
            if(!ccList1[i].equals(ccList2[i]))
                return false;
        }
        
        //get all messages for both emails
        List msg1 = this.getAllMessages();
        List msg2 = email.getAllMessages();
        
        //compare list lengths
        if(msg1.size() != msg2.size())
            return false;
                    
        EmailMessage em1;
        EmailMessage em2;
        
        String text1;
        String text2;
        
        for(int i = 0; i < msg1.size(); i++)
        {
            em1 = (EmailMessage) msg1.get(i);
            em2 = (EmailMessage) msg2.get(i);
            text1 = em1.toString();
            text2 = em2.toString();
            
            if(!text1.equals(text2))
                return false;
        }
       
       //compare attachments
       List attach1 = email.getAttachments();
       List attach2 = this.getAttachments();
        
       //compare attachment list length
       if(attach1.size() != attach2.size())
            return false;
       
       byte [] b1;
       byte [] b2;
       EmailAttachment e1;
       EmailAttachment e2;
       
       //start comparing byte arrays for attachments
       for(int i = 0; i < attach1.size(); i ++)
       {
           e1 = (EmailAttachment) attach1.get(i);
           e2 = (EmailAttachment) attach2.get(i);
           
           b1 = e1.toByteArray();
           b2 = e2.toByteArray();
           
           //if length not same, not same attachment
           if(b1.length != b2.length)
               return false;
           
           for(int j = 0; j < b1.length; j++)
           {
               //different byte, different attachment
               if(b1[i] != b2[i])
                   return false;
           }
       }
       return true;             
    }
    
    /**
     * Used to send an email to the receiver when both a receiver and 
     * sender are given.
     * @param sender
     * @param receiver
     * @param subj
     * @param message
     * @param attach
     * @return 
    */   
    public EmailBean send(String sender, String receiver,String subj, 
            String message, String attach)
    {
        ConfigBean b = new ConfigBean();        
        //create
        SmtpServer<SmtpSslServer> server = b.createSMTPServer(smtpServerName);
        
        //create email object and add the text to it
        EmailBean email = (EmailBean)Email.create();
        email.from(sender);
        email.subject(subj);
        email.addText(message);
        //check for attachment
        if(!attach.isEmpty())
            email.attach(EmailAttachment.attachment().file(attach));
        
        //create session and send email
        SendMailSession session = server.createSession();
        session.open();
        session.sendMail(email);
        session.close();
        
        return email;
    }
    /**
     * Method that when fed a receiver email, will import all new emails
     * that have been sent to this particular receiver. 
     * @param receiver
     * @return 
    */
    public List<EmailBean> receive(String receiver)
    {
        //create IMAP server
        
        ConfigBean b = new ConfigBean();
        ImapSslServer imapServer = b.createImap(imapServerName);
        
        //create session for server
        ReceiveMailSession session = imapServer.createSession();
        session.open();
        
        //create obj that communicates with server
        ReceivedEmail[] emails = session.receiveEmailAndMarkSeen(EmailFilter.
                filter().flag(Flags.Flag.SEEN, false));
        
        session.close();
        
        List<EmailBean> completeEmails = mergeReceives(emails);
        //loop through emails here
        if (completeEmails != null)
        {
            
            for (EmailBean email : completeEmails)
            {
                //log.info("\n\n===[" + email.getMessageNumber() + "]===");
                //log.info("FLAG: %1$h\n", email.getFlags());
                log.info("FROM:" + email.getFrom());
                log.info("TO:" + email.getTo()[0]);
                log.info("SUBJECT:" + email.getSubject());
                log.info("PRIORITY:" + email.getPriority());
                log.info("SENT DATE:" + email.getSentDate());
                //log.info("RECEIVED DATE: " + email.getReceiveDate());
                
                List<EmailMessage> msgs = email.getAllMessages();
                
                log.info("email content length = " + msgs.size());
                
                for (EmailMessage msg : msgs)
                {
                    log.info("------");
                    log.info(msg.getContent());
                }
                
                List<EmailAttachment> atts = email.getAttachments();
                if (attachments != null) {
                    log.info("+++++");
                    for (EmailAttachment att : atts) {
                        log.info("name: " + att.getName());
                        log.info("cid: " + att.getContentId());
                        log.info("size: " + att.getSize());

                        att.writeToFile(new File(b.getAttachmentFolder(),
                                att.getName()));
                    }
                }
            }
        }
        
        return completeEmails;
    }
    
    public List<EmailBean> mergeReceives(ReceivedEmail[] r)
    {
        //create list
        List<EmailBean> emails = new ArrayList<>();
        
        return emails;
    }
    
    /**
     * Used to send an email to the receivers when both a sender and 
     * an array of receivers are given, The receivers will be able to view
     * all of the other receivers.
     * @param sender
     * @param receivers
     * @param subj
     * @param text
     * @param attach
     * @return 
    */   
    public EmailBean sendWithCC(String sender, String [] receivers, String subj,
            String text, String attach)
    {
        ConfigBean b = new ConfigBean();        
        //create
        SmtpServer<SmtpSslServer> server = b.createSMTPServer(smtpServerName);
        
        //set up email with appropriate properties, including CC
        EmailBean email = (EmailBean)Email.create();
        email.subject(subj);
        email.addText(text);
        email.cc(receivers);
        //check for attachment
        if(!attach.isEmpty())
            email.attach(EmailAttachment.attachment().file(attach));
        
                
        //send email
        SendMailSession session = server.createSession();
        session.open();
        session.sendMail(email);
        session.close();
        
        return email;
    }  
    /**
     * Used to send an email to the receivers when both a sender and 
     * an array of receivers are given, The receivers will not be able to view
     * all of the other receivers.
     * @param sender
     * @param receivers
     * @param subj
     * @param text
     * @param attach
     * @return 
    */   
    public EmailBean sendWithBCC(String sender, String [] receivers, String subj,
            String text, String attach)
    {
        ConfigBean b = new ConfigBean();        
        //create
        SmtpServer<SmtpSslServer> server = b.createSMTPServer(smtpServerName);
        
        //setup email with appropriate properties, including BCC
        EmailBean email = (EmailBean)Email.create();
        email.subject(subj);
        email.addText(text);
        email.bcc(receivers);
        //check for attachment
        if(!attach.isEmpty())
            email.attach(EmailAttachment.attachment().file(attach));
                
        //send email
        SendMailSession session = server.createSession();
        session.open();
        session.sendMail(email);
        session.close();
        
        return email;
    }  
}
