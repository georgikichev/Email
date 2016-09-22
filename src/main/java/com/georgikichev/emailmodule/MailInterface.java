/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.georgikichev.emailmodule;

import java.util.List;

/**
 * Interface for the EmailBean
 * @author Georgi Kichev
 */
public interface MailInterface {
{

    /**
     *
     * @return
     */
    public EmailBean send();
    
    public EmailBean sendWithCC();
    
    public EmailBean sendWithBCC();
    
    public List<EmailBean> receive();
    
    public EmailBean sendEmail(EmailBean email);

    public void setConfigBean(ConfigBean config);
    
    public ConfigBean getConfigBean();
    
    
}

