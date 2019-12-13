/**
 * @author Daniel Correa (danielpcorrea@gmail.com)
 */
package com.mycompany.app;
  
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.mycompany.app.services.*;
import com.mycompany.app.presentation.consoleapp.*; 
import com.mycompany.app.infrastructure.io.IPropertyFileReader;
import com.mycompany.app.infrastructure.connectors.IEventSubscriber;
 
public class AppTest 
{ 
    @Test
    public void salesforceEventSubscriberClientTest()
    {
        //arrange 
        IPropertyFileReader propertyFileReaderMock = mock(IPropertyFileReader.class);
        ISalesforceEventSubscriberService salesforceEventSubscriberService = 
            mock(ISalesforceEventSubscriberService.class);

        String replayId = "-2";
        Long replayIdAsLong = Long.parseLong(replayId);
        String anyText = "teste";
        when(propertyFileReaderMock.getProperty(Const.USER_NAME)).thenReturn(anyText);
        when(propertyFileReaderMock.getProperty(Const.USER_PASSWORD_WITH_TOKEN)).thenReturn(anyText);
        when(propertyFileReaderMock.getProperty(Const.PUSHTOPIC_OR_EVENT_NAME)).thenReturn(anyText);
        when(propertyFileReaderMock.getProperty(Const.NEW_EVENT_MESSAGE)).thenReturn("");
        when(propertyFileReaderMock.getProperty(Const.SUCCESS_EVENT_REGISTRATION_MESSAGE)).thenReturn(anyText);
        when(propertyFileReaderMock.getProperty(Const.FAILED_EVENT_REGISTRATION_MESSAGE)).thenReturn(anyText);
        when(propertyFileReaderMock.getProperty(Const.REPLAY_ID)).thenReturn(replayId);        
        
        when(salesforceEventSubscriberService.subscribeToEvent(
            null, anyText, anyText,anyText,replayIdAsLong)
        ).thenReturn(true);
        
        //act 
        Boolean eventRegistrationWasConfirmed = new SalesforceEventSubscriberClientDemo(
            propertyFileReaderMock,salesforceEventSubscriberService
        ).startToListenToEvent(); 

        //assert
        assertEquals(eventRegistrationWasConfirmed, true); 
    }

    @Test
    public void salesforceEventSubscriberServiceTest()
    {
        //arrange  
        IEventSubscriber eventSubscriber = mock(IEventSubscriber.class);
        ISalesforceEventSubscriberService salesforceEventSubscriberService =
            new SalesforceEventSubscriberService(eventSubscriber);

        String replayId = "-2";
        Long replayIdAsLong = Long.parseLong(replayId);
        String anyText = "teste";

        when(eventSubscriber.subscribe(
            null, anyText, anyText, anyText,replayIdAsLong)
        ).thenReturn(true);
        
        //act 
        Boolean eventRegistrationWasConfirmed = salesforceEventSubscriberService.subscribeToEvent(
            null, anyText, anyText, anyText,replayIdAsLong
        );

        //assert
        assertEquals(eventRegistrationWasConfirmed, true); 
    }
     
}
