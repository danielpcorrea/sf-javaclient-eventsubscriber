/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */
package com.mycompany.app.infrastructure.connectors.salesforce.eventsubscriber;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.enterprise.inject.Default;

import com.mycompany.app.infrastructure.connectors.IEventSubscriber;
import com.mycompany.app.infrastructure.connectors.salesforce.eventsubscriber.core.EmpConnector;

@Default
public class SalesforceEmpConnectorEventSubscriber implements IEventSubscriber
{

    @Override
    public Boolean subscribe(Consumer<Map<String, Object>> paConsumer,
        String paUserName,String paPassworld,String paEventOrTopicUrl,Long paReplayId ) 
    {
        Boolean connectionSucceed = null;
        try 
        {    
            EmpConnector empConnector = new EmpConnectorFactory()
                .createEmpConnector(paUserName,paPassworld);

            if(empConnector != null)
            { 
                empConnector.subscribe(
                    paEventOrTopicUrl,
                    paReplayId,
                    paConsumer
                ).get(5,TimeUnit.SECONDS);
                connectionSucceed = true;
            } 
        } 
        catch (Exception e) {
            connectionSucceed = false;
        }   
        
        return connectionSucceed; 
    }


}