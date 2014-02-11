package com.mutti.google;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.calendar.Calendar;

public class GoogleCalendarClient {

	private HttpTransport httpTransport = new NetHttpTransport();
	private JsonFactory jsonFactory = new JacksonFactory();
	private Calendar calendarClient;

	public GoogleCalendarClient(String accountId, File privateKey) {
		if ((StringUtils.isBlank(accountId)) || (privateKey == null)) {
			throw new IllegalArgumentException(
					"Google accoundId and the private key file must be set!");
		}
		try {
			initClient(accountId, privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Calendar getCalendar() {
		return calendarClient;
	}

	private void initClient(String accountId, File privateKey)
			throws IOException, GeneralSecurityException {
		GoogleCredential credentials = new GoogleCredential.Builder()
				.setTransport(httpTransport)
				.setJsonFactory(jsonFactory)
				.setServiceAccountId(accountId)
				.setServiceAccountScopes(
						Arrays.asList("https://www.googleapis.com/auth/calendar.readonly"))
				.setServiceAccountPrivateKeyFromP12File(privateKey).build();
		calendarClient = new Calendar.Builder(
				GoogleNetHttpTransport.newTrustedTransport(), jsonFactory,
				credentials).setApplicationName("MUTTI").build();
	}

}
