package com.proteam.bai_13_2_json_gson.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright
 * Created by CuongNV31.
 */
public class PowerSearch {
    @SerializedName("commitClinics")
    List<PowerClinic> commitClinics = new ArrayList<>();
    @SerializedName("powerClinics")
    List<PowerClinic> powerClinics = new ArrayList<>();

    public List<PowerClinic> getCommitClinics() {
        return commitClinics;
    }

    public List<PowerClinic> getPowerClinics() {
        return powerClinics;
    }

    public void setCommitClinics(List<PowerClinic> commitClinics) {
        this.commitClinics = commitClinics;
    }

    public void setPowerClinics(List<PowerClinic> powerClinics) {
        this.powerClinics = powerClinics;
    }
}
