/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */

package com.mycompany.app.infrastructure.connectors.salesforce.eventsubscriber;

import java.util.concurrent.TimeUnit;
import com.mycompany.app.infrastructure.connectors.salesforce.eventsubscriber.core.*;

class EmpConnectorFactory
{ 
    public EmpConnector createEmpConnector(String paUserName, String paPassworld) 
    {
        EmpConnector empConnector = null;

        BearerTokenProvider bearerTokenProvider = createBearerTokenProvider(paUserName,paPassworld);
        if(bearerTokenProvider != null)
        { 
            BayeuxParameters bayeuxParameters = createBayeuxParameters(bearerTokenProvider);
            empConnector = new EmpConnector(bayeuxParameters);
            try {
                empConnector.setBearerTokenProvider(bearerTokenProvider);
                empConnector.start().get(5, TimeUnit.SECONDS);
            } catch (Exception e) {
                empConnector = null;
            }
        } 
        return empConnector;
    }    
    private BearerTokenProvider createBearerTokenProvider(String paUserName, String paPassworld) {
		return new BearerTokenProvider(() -> {
		    try {
		        return LoginHelper.login(paUserName, paPassworld);
		    } catch (Exception e) {		        
                return null;
            }
        }); 
    } 
    private BayeuxParameters createBayeuxParameters(BearerTokenProvider paBearerTokenProvider)
    {
        BayeuxParameters params;
        try {
            params = paBearerTokenProvider.login();
        } catch (Exception e) {
            params = null;
        }
        return params;
    }

}