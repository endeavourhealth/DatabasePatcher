package org.endeavourhealth.databasepatcher.core.database;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatchManager {



	public static Timestamp convertToDate(String date) {
		Timestamp timeStamp = null;

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date parsedDate = dateFormat.parse(date);
			timeStamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (Exception e) {

		}


		return timeStamp;

	}


}
