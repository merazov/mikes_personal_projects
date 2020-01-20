package com.mike.models_validation.legacy_vs_v2_all_steps;

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
public class LegacyDemandEntry {

    private String id;
    
    private String fct_version;
    
    private String year;
    
    private String month;
    
    private String week_ending;
    
    private String quarter;
    
    private String date;

    private String from_country;

    private String to_country;

    private String channel;

    private String asin;

    private String retailer_id;

    private String retailer_name;

    private String device_type;

    private String program;

    private String wireless;

    private String carrier;

    private String storage;

    private String dtcp;

    private String baseline_units;

    private String promoDescription;

    private String promo_units_app;

    private String promo_units_unapp;

    private String vdp_units;
    
    private String vdp_type;
    
    private String processed_file_id;
    
    private String created_at;
    
    private String updated_at;
    
    private String ngf_source;

    private String vdp_plus_units;

    private String device_color;
    
    //private String deviceColor;

    private String bundle_name;

    private String bundleColor;

    private String manualVdp;

    private String demand_source;

    private String modelType;
}
