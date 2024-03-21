package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"mario\",\n" +
                "  \"emp_lastname\": \"germeen\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2004-08-05\",\n" +
                "  \"emp_status\": \"permanent\",\n" +
                "  \"emp_job_title\": \"QA Engineer\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeeJsonPayload(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","mario");
        obj.put("emp_lastname","germeen");
        obj.put("emp_middle_name","ms");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","2004-08-05");
        obj.put("emp_status","permanent");
        obj.put("emp_job_title","QA Engineer");
        return obj.toString();
    }

    public static String createEmployeeJsonPayloadDynamic
            (String emp_firstname, String emp_lastname,
             String emp_middle_name,String emp_gender,
             String emp_birthday, String emp_status, String emp_job_title){

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", emp_firstname);
        obj.put("emp_lastname", emp_lastname);
        obj.put("emp_middle_name",emp_middle_name);
        obj.put("emp_gender",emp_gender);
        obj.put("emp_birthday",emp_birthday);
        obj.put("emp_status",emp_status);
        obj.put("emp_job_title",emp_job_title);

        return obj.toString();
    }

}
