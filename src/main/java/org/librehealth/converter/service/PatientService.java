package org.librehealth.converter.service;

import java.io.IOException;

import org.json.simple.JSONObject;

public class PatientService {

	private final static String getPatientUrl = "https://radiology.librehealth.io/lh-toolkit/ws/rest/v1/patient";
	private final static String createPatientUrl = "https://radiology.librehealth.io/lh-toolkit/ws/rest/v1/patient";
	private final static String validatePatientUrl
			= "https://fhirtest.uhn.ca/baseDstu3/Patient/$validate?_format=json&profile=http://hl7.org/fhir/StructureDefinition/Patient";

	/**
	 * Get Json string representation of Patient by uuid
	 * @param uuid of Patient
	 * @return json string representation of Patient
	 */
	public static String getPatientByUUid(String uuid) {
		return HttpClientService.getResource(getPatientUrl+"/"+uuid+"?v=full");
	}

	/**
	 * Post Patient json object to fhir server for validation
	 * @param resource, fhir patient json
	 * @return response status
	 */
	public static String validateFHIRResource(JSONObject resource) throws IOException{
		return HttpClientService.validateFHIRResource(validatePatientUrl, resource);
	}

	/**
	 * Post converted Patient REST json for validation
	 * @param resource, LibreHealth patient json
	 * @return response status
	 */
	public static String postLHPatient(JSONObject resource) throws IOException {
		return HttpClientService.createLHPatient(createPatientUrl, resource);
	}
}
