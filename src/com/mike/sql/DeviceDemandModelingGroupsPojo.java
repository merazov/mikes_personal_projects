package com.mike.sql;

/*
 * This file is generated by jOOQ.
*/

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;

/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DeviceDemandModelingGroupsPojo implements Serializable {

    private static final long serialVersionUID = 959219647;

    private Integer       id;
    private String        name;
    private Integer       countryId;
    private Integer       channelId;
    private Integer       deviceTypeId;
    private Integer       programId;
    private Integer       storageId;
    private Integer       carrierId;
    private Integer       colorId;
    private Integer       bundleId;
    private Byte          dtcp;
    private Integer       retailerId;

    public DeviceDemandModelingGroupsPojo() {}

    public DeviceDemandModelingGroupsPojo(DeviceDemandModelingGroupsPojo value) {
        this.id = value.id;
        this.name = value.name;
        this.countryId = value.countryId;
        this.channelId = value.channelId;
        this.deviceTypeId = value.deviceTypeId;
        this.programId = value.programId;
        this.storageId = value.storageId;
        this.carrierId = value.carrierId;
        this.colorId = value.colorId;
        this.bundleId = value.bundleId;
        this.dtcp = value.dtcp;
        this.retailerId = value.retailerId;
    }

    public DeviceDemandModelingGroupsPojo(
        Integer       id,
        String        name,
        Integer       countryId,
        Integer       channelId,
        Integer       deviceTypeId,
        Integer       programId,
        Integer       storageId,
        Integer       carrierId,
        Integer       colorId,
        Integer       bundleId,
        Byte          dtcp,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer       retailerId,
        Byte          allowMultipleProdModels
    ) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.channelId = channelId;
        this.deviceTypeId = deviceTypeId;
        this.programId = programId;
        this.storageId = storageId;
        this.carrierId = carrierId;
        this.colorId = colorId;
        this.bundleId = bundleId;
        this.dtcp = dtcp;
        this.retailerId = retailerId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public Integer getProgramId() {
        return this.programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Integer getStorageId() {
        return this.storageId;
    }

    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    public Integer getCarrierId() {
        return this.carrierId;
    }

    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }

    public Integer getColorId() {
        return this.colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getBundleId() {
        return this.bundleId;
    }

    public void setBundleId(Integer bundleId) {
        this.bundleId = bundleId;
    }

    public Byte getDtcp() {
        return this.dtcp;
    }

    public void setDtcp(Byte dtcp) {
        this.dtcp = dtcp;
    }

    public Integer getRetailerId() {
        return this.retailerId;
    }

    public void setRetailerId(Integer retailerId) {
        this.retailerId = retailerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DeviceDemandModelingGroupsPojo (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(countryId);
        sb.append(", ").append(channelId);
        sb.append(", ").append(deviceTypeId);
        sb.append(", ").append(programId);
        sb.append(", ").append(storageId);
        sb.append(", ").append(carrierId);
        sb.append(", ").append(colorId);
        sb.append(", ").append(bundleId);
        sb.append(", ").append(dtcp);
        sb.append(", ").append(retailerId);
        
        sb.append(")");
        return sb.toString();
    }
}
