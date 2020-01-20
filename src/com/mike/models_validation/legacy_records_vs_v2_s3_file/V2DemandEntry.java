package com.mike.models_validation.legacy_records_vs_v2_s3_file;

import javax.annotation.concurrent.NotThreadSafe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NotThreadSafe
@Builder(toBuilder = true)
public class V2DemandEntry {

    private String forecastVersion;

    private String date;

    private String fromCountry;

    private String toCountry;

    private String channel;

    private String asin;

    private String retailerId;

    private String retailerName;

    private String deviceType;

    private String program;

    private String wireless;

    private String carrier;

    private String storage;

    private String dtcp;

    private String baselineUnits;

    private String promoDescription;

    private String approvedPromoUnits;

    private String unapprovedPromoUnits;

    private String vdpUnits;

    private String vdpPlusUnits;

    private String deviceColor;

    private String bundleName;

    private String bundleColor;

    private String manualVdp;

    private String demandSource;

    private String modelType;
}
