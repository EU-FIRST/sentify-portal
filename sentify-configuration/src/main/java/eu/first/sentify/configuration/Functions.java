package eu.first.sentify.configuration;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Common methods
 */

public class Functions {
	private long thirtyDaysInMills = 2592000000L; // 30 days in millisecs
	private long sevenDaysInMills = 604800000L; // 7 days in millisecs
	private long fourteenDaysInMills = 1209600000L; // 7 days in millisecs
	private int DATE_LENGTH = 10;
	private int MAX_NEWS = 10;
	private String wsdl = "http://first-vm1.ijs.si:8080/axis2/services/SentimentService?wsdl";
	//private String wsdl = "http://first-vm3.ijs.si:8080/axis2/services/SentimentService-TESTBED1?wsdl";
	private static final int SLEEP_TIME_IN_MILLIS = 1000 * 60 * 2; // 2 minutes
    private String soNameUrl = "http://localhost:8080/sentify-company-page/search?uri=";
	

	/**
	 * Gets todays date in XMLGregorianCalendar format
	 */
	public XMLGregorianCalendar getXMLGregorianCalendarNow()
			throws DatatypeConfigurationException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
		XMLGregorianCalendar now = datatypeFactory
				.newXMLGregorianCalendar(gregorianCalendar);
		return now;
	}

	/**
	 * Converts a given time in milliseconds into a {@link XMLGregorianCalendar}
	 * object.
	 */
	public XMLGregorianCalendar toXmlGregorianCalendar(final long date) {
		try {
			final GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTimeInMillis(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(
					calendar);
		} catch (final DatatypeConfigurationException ex) {
			System.out
					.println("Unable to convert date '%s' to an XMLGregorianCalendar object");
		}
		return null;
	}

	public long get30Days() {
		return this.thirtyDaysInMills;
	}

	public long get7Days() {
		return this.sevenDaysInMills;
	}

	public long get14Days() {
		return this.fourteenDaysInMills;
	}
	
	public int getLength() {
		return this.DATE_LENGTH;
	}

	public int getMaxNews() {
		return this.MAX_NEWS;
	}

	public String getWSDL() {
		return this.wsdl;
	}

	public int getSleep() {
		return this.SLEEP_TIME_IN_MILLIS;
	}
	
    public String getsoNameURL(){
    	return this.soNameUrl;
    }
}
