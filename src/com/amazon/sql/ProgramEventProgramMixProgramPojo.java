package com.amazon.sql;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * POJO for joining 'program_mix_events' and 'program_mixes' tables, and 'PROGRAMS' tables
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramEventProgramMixProgramPojo extends EventPojo {
    /*
     * From 'program_mix_events' table
     */
    private Integer deviceDemandModelingGroupId;
    private BigDecimal demandFactor;

    /*
     * From 'program_mixes' table
     */
    private Integer programMixEventId;
    private Integer programId;
    private BigDecimal mixPercent;

    /*
     * From 'PROGRAMS' table
     */
    private String programName;
    private Integer deviceTypeId;

    public ProgramEventProgramMixProgramPojo(ProgramEventProgramMixProgramPojo source) {

        this.setStartDate(source.getStartDate());
        this.setDurationDays(source.getDurationDays());

        this.deviceDemandModelingGroupId = source.deviceDemandModelingGroupId;
        this.demandFactor = source.demandFactor;

        /*
         * From 'program_mixes' table
         */
        this.programMixEventId = source.programMixEventId;
        this.programId = source.programId;
        this.mixPercent = source.mixPercent;

        /*
         * From 'PROGRAMS' table
         */
        this.programName = source.programName;
        this.deviceTypeId = source.deviceTypeId;
    }

    public boolean isWithinDuration(LocalDate date) {
        return !getStartDate().isAfter(date) && getEndDate().isAfter(date);
    }
}
