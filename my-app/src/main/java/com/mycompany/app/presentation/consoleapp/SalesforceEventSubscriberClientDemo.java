/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */
package com.mycompany.app.presentation.consoleapp;

import java.util.Map;
import java.util.function.Consumer;

import javax.inject.Inject;

import com.mycompany.app.services.ISalesforceEventSubscriberService;
import com.mycompany.app.infrastructure.io.IPropertyFileReader;

public class SalesforceEventSubscriberClientDemo
{
    IPropertyFileReader propertyFileReader = null;
    ISalesforceEventSubscriberService salesforceEventSubscriberService = null;
 
    @Inject
    public SalesforceEventSubscriberClientDemo(IPropertyFileReader paPropertyFileReader,
        ISalesforceEventSubscriberService paSalesforceEventSubscriberService) 
    {
        this.propertyFileReader = paPropertyFileReader;
        this.salesforceEventSubscriberService = paSalesforceEventSubscriberService;
    }

    public Boolean startToListenToEvent() 
    {
        Consumer<Map<String, Object>> callBackListenerToSubscribedEvent = 
            SalesforceEventSubscriberClientDemoFactory.createCallBackListenerToSubscribedEvent(
                this.propertyFileReader.getProperty(Const.NEW_EVENT_MESSAGE)
        );

        Boolean eventRegistrationWasConfirmed = this.salesforceEventSubscriberService.subscribeToEvent(
            callBackListenerToSubscribedEvent,
            this.propertyFileReader.getProperty(Const.USER_NAME),
            this.propertyFileReader.getProperty(Const.USER_PASSWORD_WITH_TOKEN),
            this.propertyFileReader.getProperty(Const.PUSHTOPIC_OR_EVENT_NAME),
            Long.parseLong(this.propertyFileReader.getProperty(Const.REPLAY_ID))
        );     

        showSubscribedResult(eventRegistrationWasConfirmed, 
            this.propertyFileReader.getProperty(Const.PUSHTOPIC_OR_EVENT_NAME),
            this.propertyFileReader.getProperty(Const.SUCCESS_EVENT_REGISTRATION_MESSAGE),
            this.propertyFileReader.getProperty(Const.FAILED_EVENT_REGISTRATION_MESSAGE)
        );
        
        return eventRegistrationWasConfirmed;
    } 
    
    private void showSubscribedResult(Boolean paEventRegistrationWasConfirmed, String paPushTopicOrEventName,
            String paSuccessEventRegistrationMessage, String paFailedEventRegistrationMessage) 
    {
        String eventSubscriptionResponseToBeDisplayed = ( paEventRegistrationWasConfirmed ? 
            paSuccessEventRegistrationMessage: paFailedEventRegistrationMessage
        );
        
        System.out.println(String.format("%s: %s", 
            eventSubscriptionResponseToBeDisplayed, 
            paPushTopicOrEventName)
        );
    }
} 
