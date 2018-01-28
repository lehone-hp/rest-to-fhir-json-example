package org.librehealth.converter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PatientUtil {

	/**
	 * Converts LH patient Json to FHIR Patient Json
	 * @param patientJson, string representation of Patient Json
	 * @return FHIR Json representation of transformed Patient
	 */
	public static JSONObject toFHIRPatient (String patientJson) throws Exception{

		List chainrSpecJSON = JsonUtils.filepathToList( "src/main/resources/patient_spec.json" );
		Chainr chainr = Chainr.fromSpec( chainrSpecJSON );

		Object input = new JSONParser().parse(patientJson);
		Object transformedOutput = chainr.transform( input );

		JSONObject jsonTransformedOutput =
				(JSONObject) new JSONParser().parse(JsonUtils.toPrettyJsonString( transformedOutput ));

		// Format the FHIR patient's birthDate to "yyyy-MM-dd"
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
		String birthDate = (String) jsonTransformedOutput.get("birthDate");

		Date date = dateFormat.parse(birthDate);
		jsonTransformedOutput.put("birthDate", dateFormat.format(date));

		return jsonTransformedOutput;

	}
}
