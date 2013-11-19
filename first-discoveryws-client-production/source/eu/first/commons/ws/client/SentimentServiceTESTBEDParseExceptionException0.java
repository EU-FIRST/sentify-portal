
/**
 * SentimentServiceTESTBEDParseExceptionException0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

package eu.first.commons.ws.client;

public class SentimentServiceTESTBEDParseExceptionException0 extends java.lang.Exception{
    
    private eu.first.commons.ws.client.SentimentServiceTESTBEDStub.SentimentServiceTESTBEDParseException faultMessage;
    
    public SentimentServiceTESTBEDParseExceptionException0() {
        super("SentimentServiceTESTBEDParseExceptionException0");
    }
           
    public SentimentServiceTESTBEDParseExceptionException0(java.lang.String s) {
       super(s);
    }
    
    public SentimentServiceTESTBEDParseExceptionException0(java.lang.String s, java.lang.Throwable ex) {
      super(s, ex);
    }
    
    public void setFaultMessage(eu.first.commons.ws.client.SentimentServiceTESTBEDStub.SentimentServiceTESTBEDParseException msg){
       faultMessage = msg;
    }
    
    public eu.first.commons.ws.client.SentimentServiceTESTBEDStub.SentimentServiceTESTBEDParseException getFaultMessage(){
       return faultMessage;
    }
}
    