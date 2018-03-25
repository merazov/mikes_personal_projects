package com.mike.serialization;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Class that models a particular setup
 */
@Data
@RequiredArgsConstructor
public class Spec {
    private final int countryId;
    private final int channelId;
    private final int deviceTypeId;
  //  private final int retailerId; //< TODO
    private final int programId;
    private final boolean dtcp;

    /*public Spec(ConfigEventConfigMixProgramPojo currentConfigEvent, DeviceDemandModelingGroupsPojo ddmg) {
        countryId = ddmg.getCountryId();
        channelId = ddmg.getChannelId();
        deviceTypeId = ddmg.getDeviceTypeId();
     //   retailerId = ddmg.getRetailerId(); //<TODO
        programId = ddmg.getProgramId();
        dtcp = currentConfigEvent.getDtcp(); //<TODO when 'FeatureStatus.feature_enabled?(FeatureStatus::C2M_INTEGRATION)' then 'dtcp = config_mix.assortment.dtcp'
        //TODO enable:
        /*
         * if FeatureStatus.feature_enabled?(FeatureStatus::C2M_INTEGRATION)
            if config_mix.assortment.storage_id.present?
              spec[:storage_id] = config_mix.assortment.storage_id
            end
            if config_mix.assortment.carrier_id.present?
              spec[:carrier_id] = config_mix.assortment.carrier_id
            end
      
            if config_mix.assortment.bundle_id.present?
              spec[:bundle_id] = config_mix.assortment.bundle_id
            end
      
            if config_mix.assortment.color_id.present?
              spec[:color_id] = config_mix.assortment.color_id
            end
          else
            if config_mix.storage_id.present?
              spec[:storage_id] = config_mix.storage_id
            end
            if config_mix.carrier_id.present?
              spec[:carrier_id] = config_mix.carrier_id
            end
      
            if config_mix.bundle_id.present?
              spec[:bundle_id] = config_mix.bundle_id
            end
      
            if config_mix.color_id.present?
              spec[:color_id] = config_mix.color_id
            end
          end
         
    } */
}
