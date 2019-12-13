/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */
package com.mycompany.app.presentation.consoleapp; 

public class SalesforceEventSubscriberClient 
{   
    public static void main(String[] argv) throws Exception 
    {   
        SalesforceEventSubscriberClientDemoFactory
            .createSalesforceEventSubscriberClientDemo()
            .startToListenToEvent();
    } 
    
}