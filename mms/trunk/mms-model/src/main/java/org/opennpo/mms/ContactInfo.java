package org.opennpo.mms;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nate Jones
 */
public class ContactInfo {
    private Map<String, String> phoneNumbers;
    private Map<String, String> emailAddresses;
    private Map<String, MailingAddress> mailingAddresses;
    
    public ContactInfo(){
        phoneNumbers = new HashMap<String, String>();
        emailAddresses = new HashMap<String, String>();
        mailingAddresses = new HashMap<String, MailingAddress>();
    }
    
    public ContactInfo(Map<String, String> phoneNumbers, 
            Map<String, String> emailAddresses, 
            Map<String, MailingAddress> mailingAddresses){
        this.phoneNumbers = phoneNumbers;
        this.emailAddresses = emailAddresses;
        this.mailingAddresses = mailingAddresses;
    }
    
    public Map<String, String> getPhoneNumbers(){
        return phoneNumbers;
    }
    
    public Map<String, String> getEmailAddresses(){
        return emailAddresses;
    }
    
    public Map<String, MailingAddress> getMailingAddresses(){
        return mailingAddresses;
    }
}
