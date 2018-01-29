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
			String responseStatus = PatientService.postPatient(transformedPatientJson);
			System.out.println("Posted transformed resource to https://fhirtest.uhn.ca/ \n"
					+ "Status: " +responseStatus);
		}

	}

}
