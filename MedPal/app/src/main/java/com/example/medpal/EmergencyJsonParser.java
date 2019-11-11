package com.example.medpal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class EmergencyJsonParser {

    private String ISOCountryCode;

    public EmergencyJsonParser(String ISOCountryCode){
        //The JSON file has only upper case code so to avoid any mistake we force the upper case on the ISO code of the sim network.
        this.ISOCountryCode = ISOCountryCode.toUpperCase();
    }


    /**
     * Returns a String from the JSON encoded string describing emergency numbers per country provided by the Emergency Numbers API.
     *
     * @param json The emergency number JSON encoded string returned by the Emergency Numbers API.
     * @return The ambulance emergency number from a specific country as a String.
     */
    public String parseEmergencyJson(String json) {
        String number = "";
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray dataObj = jsonObj.getJSONArray("data");
            Locale current =  Locale.getDefault();
            for(int i = 0; i<=248;i++){
                JSONObject countryArray = dataObj.getJSONObject(i);
                JSONObject country = countryArray.getJSONObject("Country");
                String ISO = country.getString("ISOCode");
                if(ISO.equals(this.ISOCountryCode)){
                    JSONObject ambulance = countryArray.getJSONObject("Ambulance"); //We are only interested in Ambulance services
                    JSONArray All = ambulance.getJSONArray("All");
                    number = All.getString(0);
                    if(number == "null"){ //If for any reason the number is not filled
                        if (countryArray.getBoolean("Member_112")){ //If the country is using the 112 number it will return 112
                            number = "112";
                        } else {
                            JSONObject dispatch = countryArray.getJSONObject("Dispatch"); //We are only interested in Ambulance services
                            JSONArray All_bis = ambulance.getJSONArray("All");
                            if(All_bis.getString(0).equals("null")){
                                number = "911"; //If not the default 911 is sent
                            } else {
                                number = All_bis.getString(0); //Sometimes countries have the same number for every service so we have tor retrieve it
                            }
                        }
                    }
                    return number;
                }
            }
        } catch (JSONException e) {
            return "ERR";
        }
        return number;
    }
}
