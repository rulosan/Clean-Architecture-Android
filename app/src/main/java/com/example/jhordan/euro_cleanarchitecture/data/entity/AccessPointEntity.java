package com.example.jhordan.euro_cleanarchitecture.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rulosan on 8/13/17.
 */

public class AccessPointEntity {

    @SerializedName("id")
    private int id;
    @SerializedName("ssid")
    private String ssid;
    @SerializedName("macaddress")
    private String macaddress;
    @SerializedName("cypher_mode")
    private String cypher_mode;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("insert_timestamp")
    private String insert_timestamp;
    @SerializedName("update_timestamp")
    private String update_timestamp;

    public int getId() {
        return id;
    }

    public String getSsid() {
        return ssid;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public String getCypher_mode() {
        return cypher_mode;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getInsert_timestamp() {
        return insert_timestamp;
    }

    public String getUpdate_timestamp() {
        return update_timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public void setCypher_mode(String cypher_mode) {
        this.cypher_mode = cypher_mode;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setInsert_timestamp(String insert_timestamp) {
        this.insert_timestamp = insert_timestamp;
    }

    public void setUpdate_timestamp(String update_timestamp) {
        this.update_timestamp = update_timestamp;
    }
}
