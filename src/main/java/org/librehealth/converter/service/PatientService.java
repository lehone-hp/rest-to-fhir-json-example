package org.librehealth.converter.service;

public class PatientService {

	private final static String patientUrl = "https://radiology.librehealth.io/lh-toolkit/ws/rest/v1/patient";

	public static String getPatientByUUid(String uuid) {
		return HttpClientService.getResource(patientUrl+"/"+uuid+"?v=full");
	}
}
