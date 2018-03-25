package com.mike.sql;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * POJO for joining 'config_mix_events', 'config_mixes' and 'PROGRAMS' tables
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigEventConfigMixProgramPojo extends EventPojo {
    
    /*
     * From 'config_mix_events' table
     */
    private Integer deviceDemandModelingGroupId;
    private Integer programId;
    private String name;

    /*
     * From 'config_mixes' table
     */
    private Integer storageId;
    private Integer colorId;
    private Integer carrierId;
    private Integer bundleId;
    private Boolean dtcp;
    private Integer assortmentId;
    private BigDecimal mixPercent;

    /*
     * From 'PROGRAMS' table
     */
    private String programName;
    private Integer deviceTypeId;

    public ConfigEventConfigMixProgramPojo(ConfigEventConfigMixProgramPojo source) {

        this.setStartDate(source.getStartDate());
        this.setDurationDays(source.getDurationDays());

        this.deviceDemandModelingGroupId = source.deviceDemandModelingGroupId;
        this.programId = source.programId;
        this.name = source.name;

        /*
         * From 'config_mixes' table
         */
        this.storageId = source.storageId;
        this.colorId = source.colorId;
        this.carrierId = source.carrierId;
        this.bundleId = source.bundleId;
        this.dtcp = source.dtcp;
        this.assortmentId = source.assortmentId;
        this.mixPercent = source.mixPercent;

        /*
         * From 'PROGRAMS' table
         */
        this.programName = source.programName;
        this.deviceTypeId = source.deviceTypeId;
    }
}
