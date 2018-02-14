package org.librehealth.converter;

import com.bazaarvoice.jolt.JsonUtils;
import org.json.simple.JSONObject;
import org.librehealth.converter.service.PatientService;
import org.librehealth.converter.util.PatientUtil;

public class Main {

	public static void main(String[] args) throws Exception {

		String patientUuid = "f19c6540-0058-4bb2-9019-d61dd6afd0ce";
		String patientResource = PatientService.getPatientByUUid(patientUuid);
		JSONObject transformedPatientJson = PatientUtil.toFHIRPatient(patientResource);

		System.out.println(JsonUtils.toPrettyJsonString(transformedPatientJson));

		if (transformedPatientJson != null) {
			String responseStatus = PatientService.validateFHIRResource(transformedPatientJson);
			System.out.println("Posted transformed resource to https://fhirtest.uhn.ca/ \n"
					+ "Status: " +responseStatus);
		}

		//TODO convert patient back to rest and post in radiology.librehealth.io
		JSONObject lhPatientJson = PatientUtil.toLHPatient(JsonUtils.toPrettyJsonString(transformedPatientJson));
		System.out.println("LibreHealth Patient\n" +JsonUtils.toPrettyJsonString(lhPatientJson));		

		System.out.println(PatientService.postLHPatient(lhPatientJson));
	}

}
