package org.librehealth.converter.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

import com.bazaarvoice.jolt.JsonUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpClientService {

	private static HttpClient client = new DefaultHttpClient();

	/**
	 * Retrive resource via REST in json format
	 * @param url of resource
	 * @return json string representation of resource
	 */
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

			System.out.println("Resource: \n"+
					JsonUtils.toPrettyJsonString(new JSONParser().parse(result.toString())));

			return result.toString();
		} catch (ParseException pe) {

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return null;
	}

	/**
	 * Posts resource to https://fhirtest.uhn.ca/ for validation
	 * @return Status Line of the response
	 */
	public static String validateFHIRResource(String url, JSONObject resource) throws IOException{

		HttpPost request = new HttpPost(url);

		StringEntity stringEntity = new StringEntity(JsonUtils.toJsonString(resource));
		request.setEntity(stringEntity);

		// add header
		request.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8");
		request.addHeader(HttpHeaders.ACCEPT, "application/fhir+json;q=1.0, application/json+fhir;q=0.9");
		request.addHeader(HttpHeaders.USER_AGENT, "HAPI-FHIR/3.1.0 (FHIR Client; FHIR 3.0.1/DSTU3; apache)");
		request.addHeader(HttpHeaders.CONTENT_TYPE, "application/fhir+json; charset=UTF-8");

		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println("\nResponse Body\n");
		try {
			// print the response body in pretty json
			System.out.println(JsonUtils.toPrettyJsonString(new JSONParser().parse(result.toString())));
		} catch (ParseException pe) {
			System.out.println(result.toString());
		}

		return response.getStatusLine().getReasonPhrase();
	}

	public static String createLHPatient(String url, JSONObject resource) throws IOException{

		HttpPost request = new HttpPost(url);

		StringEntity stringEntity = new StringEntity(JsonUtils.toJsonString(resource));
		request.setEntity(stringEntity);

		// add header
		request.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8");
		request.addHeader(HttpHeaders.ACCEPT, "application/json");
		request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		HttpResponse response = client.execute(request);

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println("\nResponse Body\n");
		try {
			// print the response body in pretty json
			System.out.println(JsonUtils.toPrettyJsonString(new JSONParser().parse(result.toString())));
		} catch (ParseException pe) {
			System.out.println(result.toString());
		}

		return response.getStatusLine().getReasonPhrase();
	}
}
