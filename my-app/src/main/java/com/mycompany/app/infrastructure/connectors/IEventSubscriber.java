/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */
package com.mycompany.app.infrastructure.connectors;

import java.util.Map;
import java.util.function.Consumer;

public interface IEventSubscriber
{
    Boolean subscribe(Consumer<Map<String, Object>> paConsumer,String paUserName, 
        String paPassworld,String paEventOrTopicUrl,Long paReplayId);
}