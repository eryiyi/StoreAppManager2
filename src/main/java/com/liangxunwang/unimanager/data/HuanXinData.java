package com.liangxunwang.unimanager.data;

/**
 * Created by zhl on 2016/5/12.
 * {
 "action": "post",
 "application": "68bff810-1815-11e6-89d4-f58d9f195b37",
 "uri": "https://a1.easemob.com/826321978/lbinsyuejiuba",
 "entities": [],
 "data": {
 "groupid": "195327227699659184"
 },
 "timestamp": 1463042964146,
 "duration": 45,
 "organization": "826321978",
 "applicationName": "lbinsyuejiuba",
 "statusCode": 200
 }
 */
public class HuanXinData {
    private String action;
    private String application;
    private String uri;
    private String timestamp;
    private String duration;
    private String organization;
    private String applicationName;
    private String statusCode;
    private HuanxinGroupIdData data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public HuanxinGroupIdData getData() {
        return data;
    }

    public void setData(HuanxinGroupIdData data) {
        this.data = data;
    }
}
