/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */

package com.mycompany.app.services;

import java.util.Map;
import java.util.function.Consumer;

import javax.inject.Inject;

import com.mycompany.app.infrastructure.connectors.IEventSubscriber;
 

public class SalesforceEventSubscriberService implements ISalesforceEventSubscriberService
{   
    IEventSubscriber eventSubscriber = null;

    @Inject
    public SalesforceEventSubscriberService(IEventSubscriber paEventSubscriber) {
        this.eventSubscriber = paEventSubscriber;
    }

	public Boolean subscribeToEvent(Consumer<Map<String, Object>> paCallBackListener, 
            String paUserName,String paPassworld, String paEventOrTopicUrl,Long paReplayId) 
    {
        return  eventSubscriber.subscribe(paCallBackListener, paUserName, 
            paPassworld,paEventOrTopicUrl, paReplayId
        );        
    }  
}