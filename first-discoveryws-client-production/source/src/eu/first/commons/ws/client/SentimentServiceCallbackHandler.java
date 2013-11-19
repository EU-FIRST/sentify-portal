
/**
 * SentimentServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4  Built on : Apr 26, 2008 (06:24:30 EDT)
 */

    package eu.first.commons.ws.client;

    /**
     *  SentimentServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SentimentServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SentimentServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SentimentServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getCorpus method
            * override this method for handling normal response from getCorpus operation
            */
           public void receiveResultgetCorpus(
                    eu.first.commons.ws.client.SentimentServiceStub.GetCorpusResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCorpus operation
           */
            public void receiveErrorgetCorpus(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIndicator method
            * override this method for handling normal response from getIndicator operation
            */
           public void receiveResultgetIndicator(
                    eu.first.commons.ws.client.SentimentServiceStub.GetIndicatorResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIndicator operation
           */
            public void receiveErrorgetIndicator(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentIndexScoreObjectLastNDaysFuzzy method
            * override this method for handling normal response from getSentimentIndexScoreObjectLastNDaysFuzzy operation
            */
           public void receiveResultgetSentimentIndexScoreObjectLastNDaysFuzzy(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectLastNDaysFuzzyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentIndexScoreObjectLastNDaysFuzzy operation
           */
            public void receiveErrorgetSentimentIndexScoreObjectLastNDaysFuzzy(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPhrases method
            * override this method for handling normal response from getPhrases operation
            */
           public void receiveResultgetPhrases(
                    eu.first.commons.ws.client.SentimentServiceStub.GetPhrasesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPhrases operation
           */
            public void receiveErrorgetPhrases(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTrendingTopics method
            * override this method for handling normal response from getTrendingTopics operation
            */
           public void receiveResultgetTrendingTopics(
                    eu.first.commons.ws.client.SentimentServiceStub.GetTrendingTopicsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTrendingTopics operation
           */
            public void receiveErrorgetTrendingTopics(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDocumentCount method
            * override this method for handling normal response from getDocumentCount operation
            */
           public void receiveResultgetDocumentCount(
                    eu.first.commons.ws.client.SentimentServiceStub.GetDocumentCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDocumentCount operation
           */
            public void receiveErrorgetDocumentCount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllWebsites method
            * override this method for handling normal response from getAllWebsites operation
            */
           public void receiveResultgetAllWebsites(
                    eu.first.commons.ws.client.SentimentServiceStub.GetAllWebsitesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllWebsites operation
           */
            public void receiveErrorgetAllWebsites(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDocumentVersions method
            * override this method for handling normal response from getDocumentVersions operation
            */
           public void receiveResultgetDocumentVersions(
                    eu.first.commons.ws.client.SentimentServiceStub.GetDocumentVersionsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDocumentVersions operation
           */
            public void receiveErrorgetDocumentVersions(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentDocumentObject method
            * override this method for handling normal response from getSentimentDocumentObject operation
            */
           public void receiveResultgetSentimentDocumentObject(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentDocumentObjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentDocumentObject operation
           */
            public void receiveErrorgetSentimentDocumentObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentIndexDOMObject method
            * override this method for handling normal response from getSentimentIndexDOMObject operation
            */
           public void receiveResultgetSentimentIndexDOMObject(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexDOMObjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentIndexDOMObject operation
           */
            public void receiveErrorgetSentimentIndexDOMObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDocumentMetadata method
            * override this method for handling normal response from getDocumentMetadata operation
            */
           public void receiveResultgetDocumentMetadata(
                    eu.first.commons.ws.client.SentimentServiceStub.GetDocumentMetadataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDocumentMetadata operation
           */
            public void receiveErrorgetDocumentMetadata(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentIndexScoreObjectPerDay method
            * override this method for handling normal response from getSentimentIndexScoreObjectPerDay operation
            */
           public void receiveResultgetSentimentIndexScoreObjectPerDay(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectPerDayResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentIndexScoreObjectPerDay operation
           */
            public void receiveErrorgetSentimentIndexScoreObjectPerDay(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentPeaks method
            * override this method for handling normal response from getSentimentPeaks operation
            */
           public void receiveResultgetSentimentPeaks(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentPeaksResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentPeaks operation
           */
            public void receiveErrorgetSentimentPeaks(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentIndexScoreObjectPerDayWithRandom method
            * override this method for handling normal response from getSentimentIndexScoreObjectPerDayWithRandom operation
            */
           public void receiveResultgetSentimentIndexScoreObjectPerDayWithRandom(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectPerDayWithRandomResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentIndexScoreObjectPerDayWithRandom operation
           */
            public void receiveErrorgetSentimentIndexScoreObjectPerDayWithRandom(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentPhrasebyDocument method
            * override this method for handling normal response from getSentimentPhrasebyDocument operation
            */
           public void receiveResultgetSentimentPhrasebyDocument(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentPhrasebyDocumentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentPhrasebyDocument operation
           */
            public void receiveErrorgetSentimentPhrasebyDocument(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentObjectsbyDocument method
            * override this method for handling normal response from getSentimentObjectsbyDocument operation
            */
           public void receiveResultgetSentimentObjectsbyDocument(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentObjectsbyDocumentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentObjectsbyDocument operation
           */
            public void receiveErrorgetSentimentObjectsbyDocument(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDocumentsbySentimentObjectCount method
            * override this method for handling normal response from getDocumentsbySentimentObjectCount operation
            */
           public void receiveResultgetDocumentsbySentimentObjectCount(
                    eu.first.commons.ws.client.SentimentServiceStub.GetDocumentsbySentimentObjectCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDocumentsbySentimentObjectCount operation
           */
            public void receiveErrorgetDocumentsbySentimentObjectCount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentDocument method
            * override this method for handling normal response from getSentimentDocument operation
            */
           public void receiveResultgetSentimentDocument(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentDocumentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentDocument operation
           */
            public void receiveErrorgetSentimentDocument(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentIndexScoreObjectLastNDays method
            * override this method for handling normal response from getSentimentIndexScoreObjectLastNDays operation
            */
           public void receiveResultgetSentimentIndexScoreObjectLastNDays(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectLastNDaysResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentIndexScoreObjectLastNDays operation
           */
            public void receiveErrorgetSentimentIndexScoreObjectLastNDays(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDocumentsbySentimentObject method
            * override this method for handling normal response from getDocumentsbySentimentObject operation
            */
           public void receiveResultgetDocumentsbySentimentObject(
                    eu.first.commons.ws.client.SentimentServiceStub.GetDocumentsbySentimentObjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDocumentsbySentimentObject operation
           */
            public void receiveErrorgetDocumentsbySentimentObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getDocumentsbySentimentObjectFuzzy method
            * override this method for handling normal response from getDocumentsbySentimentObjectFuzzy operation
            */
           public void receiveResultgetDocumentsbySentimentObjectFuzzy(
                    eu.first.commons.ws.client.SentimentServiceStub.GetDocumentsbySentimentObjectFuzzyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getDocumentsbySentimentObjectFuzzy operation
           */
            public void receiveErrorgetDocumentsbySentimentObjectFuzzy(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentObjectsbyDocumentFuzzy method
            * override this method for handling normal response from getSentimentObjectsbyDocumentFuzzy operation
            */
           public void receiveResultgetSentimentObjectsbyDocumentFuzzy(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentObjectsbyDocumentFuzzyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentObjectsbyDocumentFuzzy operation
           */
            public void receiveErrorgetSentimentObjectsbyDocumentFuzzy(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentPhrasebyDocumentALL method
            * override this method for handling normal response from getSentimentPhrasebyDocumentALL operation
            */
           public void receiveResultgetSentimentPhrasebyDocumentALL(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentPhrasebyDocumentALLResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentPhrasebyDocumentALL operation
           */
            public void receiveErrorgetSentimentPhrasebyDocumentALL(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentIndexScoreObject method
            * override this method for handling normal response from getSentimentIndexScoreObject operation
            */
           public void receiveResultgetSentimentIndexScoreObject(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentIndexScoreObject operation
           */
            public void receiveErrorgetSentimentIndexScoreObject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentIndexScoreAverageLastNDays method
            * override this method for handling normal response from getSentimentIndexScoreAverageLastNDays operation
            */
           public void receiveResultgetSentimentIndexScoreAverageLastNDays(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreAverageLastNDaysResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentIndexScoreAverageLastNDays operation
           */
            public void receiveErrorgetSentimentIndexScoreAverageLastNDays(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentIndexScoreObjectPerDayFuzzy method
            * override this method for handling normal response from getSentimentIndexScoreObjectPerDayFuzzy operation
            */
           public void receiveResultgetSentimentIndexScoreObjectPerDayFuzzy(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentIndexScoreObjectPerDayFuzzyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentIndexScoreObjectPerDayFuzzy operation
           */
            public void receiveErrorgetSentimentIndexScoreObjectPerDayFuzzy(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSentimentSentence method
            * override this method for handling normal response from getSentimentSentence operation
            */
           public void receiveResultgetSentimentSentence(
                    eu.first.commons.ws.client.SentimentServiceStub.GetSentimentSentenceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSentimentSentence operation
           */
            public void receiveErrorgetSentimentSentence(java.lang.Exception e) {
            }
                


    }
    