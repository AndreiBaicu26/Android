package com.example.faza1_baicuandrei;

public class Review {
    float information;
    float design;
    float intuity;

    public Review(float information, float design, float intuity) {
        this.information = information;
        this.design = design;
        this.intuity = intuity;
    }
    public Review(){

    }

    public float getInformation() {
        return information;
    }

    public void setInformation(float information) {
        this.information = information;
    }

    public float getDesign() {
        return design;
    }

    public void setDesign(float design) {
        this.design = design;
    }

    public float getIntuity() {
        return intuity;
    }

    public void setIntuity(float intuity) {
        this.intuity = intuity;
    }
}
