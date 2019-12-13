/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */
package com.mycompany.app.services;

import java.util.Map;
import java.util.function.Consumer;
 
public interface ISalesforceEventSubscriberService 
{   
    Boolean subscribeToEvent(Consumer<Map<String, Object>> paCallBackListener, 
            String paUserName,String paPassworld, String paEventOrTopicUrl, 
            Long paReplayId); 
}