package com.example.medpal;

public class Record {
    String dob;
    String address;
    String postcode;
    String city;
    String phone;
    String disease;
    String allergies;

    public Record(String dob, String address, String postcode, String city, String phone, String disease, String allergies) {
        this.dob = dob;
        this.address = address;
        this.postcode = postcode;
        this.city = city;
        this.phone = phone;
        this.disease = disease;
        this.allergies = allergies;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() { return postcode; }

    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getAllergies() { return allergies; }

    public void setAllergies(String allergies) { this.allergies = allergies; }
}
