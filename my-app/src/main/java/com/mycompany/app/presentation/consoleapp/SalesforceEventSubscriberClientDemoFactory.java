/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */
package com.mycompany.app.presentation.consoleapp;

import java.util.Map;
import java.util.function.Consumer;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
  
public class SalesforceEventSubscriberClientDemoFactory
{
    public static Consumer<Map<String, Object>> createCallBackListenerToSubscribedEvent(
        String paNewEventMessage) 
    {
        Consumer<Map<String, Object>> callBackListener = null;

        Boolean theNewEventMessageHasContent = paNewEventMessage != null && 
                !paNewEventMessage.trim().isEmpty();

        if(theNewEventMessageHasContent)
        {
            callBackListener = event -> 
                System.out.println(String.format(paNewEventMessage + ":\n%s", event));                
        }
        return callBackListener;
    }

    public static SalesforceEventSubscriberClientDemo createSalesforceEventSubscriberClientDemo() 
    {
        SalesforceEventSubscriberClientDemo salesforceEventSubscriberClientDemo = null;
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        salesforceEventSubscriberClientDemo = container.instance().select(
            SalesforceEventSubscriberClientDemo.class
        ).get();
        
        weld.shutdown();

        return salesforceEventSubscriberClientDemo;
    }

} 
