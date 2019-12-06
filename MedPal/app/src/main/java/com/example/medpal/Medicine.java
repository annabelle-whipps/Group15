package com.example.medpal;

public class Medicine {
    Integer Id;
    String name;
    String dose;
    String sideEffects;
    String prescriedBy;
    String extraInfo;

    public Medicine(Integer Id, String name, String dose, String sideEffects, String prescriedBy, String extraInfo) {
        this.Id = Id;
        this.name = name;
        this.dose = dose;
        this.sideEffects = sideEffects;
        this.prescriedBy = prescriedBy;
        this.extraInfo = extraInfo;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getPrescriedBy() {
        return prescriedBy;
    }

    public void setPrescriedBy(String prescriedBy) {
        this.prescriedBy = prescriedBy;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
