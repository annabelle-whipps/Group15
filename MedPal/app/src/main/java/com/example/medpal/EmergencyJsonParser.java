package com.example.medpal;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Member;
import java.util.Locale;

public class EmergencyJsonParser {

    private String ISOCountryCode;

    public EmergencyJsonParser(String ISOCountryCode){
        this.ISOCountryCode = ISOCountryCode.toUpperCase();
    }


    /**
     * Converts the JSON encoded string describing locations provided by the MetOffice DataPoint API to Location objects.
     *
     * @param json The locations JSON encoded string returned by the MetOffice DataPoint API
     * @return Locations extracted from the json string.
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
                    JSONObject ambulance = countryArray.getJSONObject("Ambulance");
                    JSONArray All = ambulance.getJSONArray("All");
                    number = All.getString(0);
                    if(number == "null"){
                        if (countryArray.getBoolean("Member_112")){
                            number = "112";
                        } else {
                            number = "911";
                        }
                    }
                    return number;
                }
            }
            // add this new Location to the list of them
        } catch (JSONException e) {
            // should do some more appropriate error handling here
            e.printStackTrace();
        }
        return number;
    }
}
