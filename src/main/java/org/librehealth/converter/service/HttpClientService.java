package org.librehealth.converter.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpClientService {

	private static HttpClient client = new DefaultHttpClient();

	public static String getResource(String url) {
		try {

			HttpGet request = new HttpGet(url);

			String encodedAuth = Base64.getEncoder().encodeToString(("admin:Admin123").getBytes());
			String authHeader = "Basic " + encodedAuth;

			request.addHeader(HttpHeaders.AUTHORIZATION, authHeader);
			request.addHeader(HttpHeaders.ACCEPT, "application/json");

			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

				return result.toString();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return null;
	}

}
