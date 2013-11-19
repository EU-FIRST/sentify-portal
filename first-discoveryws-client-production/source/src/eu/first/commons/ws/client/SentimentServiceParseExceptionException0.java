
/**
 * SentimentServiceParseExceptionException0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

package eu.first.commons.ws.client;

public class SentimentServiceParseExceptionException0 extends java.lang.Exception{
    
    private eu.first.commons.ws.client.SentimentServiceStub.SentimentServiceParseException faultMessage;
    
    public SentimentServiceParseExceptionException0() {
        super("SentimentServiceParseExceptionException0");
    }
           
    public SentimentServiceParseExceptionException0(java.lang.String s) {
       super(s);
    }
    
    public SentimentServiceParseExceptionException0(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(eu.first.commons.ws.client.SentimentServiceStub.SentimentServiceParseException msg){
       faultMessage = msg;
    }
    
    public eu.first.commons.ws.client.SentimentServiceStub.SentimentServiceParseException getFaultMessage(){
       return faultMessage;
    }
}
    