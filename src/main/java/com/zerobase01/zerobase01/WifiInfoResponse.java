package com.zerobase01.zerobase01;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WifiInfoResponse {
    @SerializedName("TbPublicWifiInfo")
    private WifiInfo wifiInfo;

    public WifiInfo getWifiInfo() {
        return wifiInfo;
    }

    public void setWifiInfo(WifiInfo wifiInfo) {
        this.wifiInfo = wifiInfo;
    }
}


class WifiInfo {
    @SerializedName("list_total_count")
    private int totalCount;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setRecords(List<WifiRecord> records) {
        this.records = records;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @SerializedName("RESULT")
    private Result result;

    @SerializedName("row")
    private List<WifiRecord> records;

    public int getTotalCount() {
        return totalCount;
    }

    public Result getResult() {
        return result;
    }

    public List<WifiRecord> getRecords() {
        return records;
    }
}

class Result {
    @SerializedName("CODE")
    private String code;

    @SerializedName("MESSAGE")
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

 class WifiRecord {
    @SerializedName("X_SWIFI_MGR_NO")
    private String controlNumber;

    @SerializedName("X_SWIFI_WRDOFC")
    private String borough;

    @SerializedName("X_SWIFI_MAIN_NM")
    private String wifiName;

    @SerializedName("X_SWIFI_ADRES1")
    private String roadNameAddress;

    @SerializedName("X_SWIFI_ADRES2")
    private String detailedAddress;

    @SerializedName("X_SWIFI_INSTL_FLOOR")
    private String installationFloor;

    @SerializedName("X_SWIFI_INSTL_TY")
    private String installationType;

    @SerializedName("X_SWIFI_INSTL_MBY")
    private String installer;

    @SerializedName("X_SWIFI_SVC_SE")
    private String serviceClassification;

    @SerializedName("X_SWIFI_CMCWR")
    private String networkType;

    @SerializedName("X_SWIFI_CNSTC_YEAR")
    private String installationYear;

    @SerializedName("X_SWIFI_INOUT_DOOR")
    private String indoorOutdoorClassification;

    @SerializedName("X_SWIFI_REMARS3")
    private String wifiConnectionEnvironment;

    @SerializedName("LAT")
    private double latitude;

    @SerializedName("LNT")
    private double longitude;

    @SerializedName("WORK_DTTM")
    private String workDate;

     @SerializedName("distance")
     private double distance;

     private Date searchDate;

     public String getSearchDate() {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
         return format.format(this.searchDate);
     }

     public void setSearchDate(String dateString) throws ParseException {
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
         this.searchDate = formatter.parse(dateString);
     }


     public double getDistance() {
         return distance;
     }

     public void setDistance(double distance) {
         this.distance = distance;
     }
    public String getControlNumber() {
        return controlNumber;
    }

    public String getBorough() {
        return borough;
    }

    public String getWifiName() {
        return wifiName;
    }

    public String getRoadNameAddress() {
        return roadNameAddress;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public String getInstallationFloor() {
        return installationFloor;
    }

    public String getInstallationType() {
        return installationType;
    }

    public String getInstaller() {
        return installer;
    }

    public String getServiceClassification() {
        return serviceClassification;
    }

    public String getNetworkType() {
        return networkType;
    }

    public String getInstallationYear() {
        return installationYear;
    }

    public String getIndoorOutdoorClassification() {
        return indoorOutdoorClassification;
    }

    public String getWifiConnectionEnvironment() {
        return wifiConnectionEnvironment;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

     public void setControlNumber(String controlNumber) {
         this.controlNumber = controlNumber;
     }

     public void setBorough(String borough) {
         this.borough = borough;
     }

     public void setWifiName(String wifiName) {
         this.wifiName = wifiName;
     }

     public void setRoadNameAddress(String roadNameAddress) {
         this.roadNameAddress = roadNameAddress;
     }

     public void setDetailedAddress(String detailedAddress) {
         this.detailedAddress = detailedAddress;
     }

     public void setInstallationFloor(String installationFloor) {
         this.installationFloor = installationFloor;
     }

     public void setInstallationType(String installationType) {
         this.installationType = installationType;
     }

     public void setInstaller(String installer) {
         this.installer = installer;
     }

     public void setServiceClassification(String serviceClassification) {
         this.serviceClassification = serviceClassification;
     }

     public void setNetworkType(String networkType) {
         this.networkType = networkType;
     }

     public void setInstallationYear(String installationYear) {
         this.installationYear = installationYear;
     }

     public void setIndoorOutdoorClassification(String indoorOutdoorClassification) {
         this.indoorOutdoorClassification = indoorOutdoorClassification;
     }

     public void setWifiConnectionEnvironment(String wifiConnectionEnvironment) {
         this.wifiConnectionEnvironment = wifiConnectionEnvironment;
     }

     public void setLatitude(double latitude) {
         this.latitude = latitude;
     }

     public void setLongitude(double longitude) {
         this.longitude = longitude;
     }

     public void setWorkDate(String workDate) {
         this.workDate = workDate;
     }

     public String getWorkDate() {
        return workDate;
    }
}
