package org.librehealth.converter.service;

import java.io.IOException;

import org.json.simple.JSONObject;

public class PatientService {

	private final static String patientUrl = "https://radiology.librehealth.io/lh-toolkit/ws/rest/v1/patient";
	private final static String postPatientUrl
			= "https://fhirtest.uhn.ca/baseDstu3/Patient/$validate?_format=json&profile=http://hl7.org/fhir/StructureDefinition/Patient";

	/**
	 * Get Json string representation of Patient by uuid
	 * @param uuid of Patient
	 * @return json string representation of Patient
	 */
	public static String getPatientByUUid(String uuid) {
		return HttpClientService.getResource(patientUrl+"/"+uuid+"?v=full");
	}

	/**
	 * Post Patient json object to fhir server for validation
	 * @param resource, fhir patient json
	 * @return response status
	 */
	public static String postPatient(JSONObject resource) throws IOException{
		return HttpClientService.postResource(postPatientUrl, resource);
	}
}
