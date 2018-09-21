package com.mike.strings;

import org.apache.commons.lang3.StringUtils;

public class GetDdmgFromModelName {

    public static void main(String[] args) {
        final String modelNames = "JP_ONLINE_AUCC_MOBILE_20180417T0955,JP_OFFLINE_AUCC_MOBILE_20180412T1659,DE_OFFLINE_AUCC_MOBILE_20180410T1516,UK_OFFLINE_AUCC_MOBILE_20180410T1430,US_OFFLINE_AUCC_MOBILE_20180410T1439,US_OFFLINE_AUCC_MOBILE_BBY_20180410T1234,DE_ONLINE_AUCC_MOBILE_20180409T1657,UK_ONLINE_AUCC_MOBILE_20180409T1747,US_ONLINE_AUCC_MOBILE_04092018T1445,CA_OFFLINE_TABLET_20180416T1009,US_ONLINE_APO_SPENCER_MINI_20180409T1806,US_OFFLINE_SMP_20180423T1511,ES_OFFLINE_AUCC_20180214T1727,ES_ONLINE_AUCC_20180214T1632,IT_OFFLINE_AUCC_20180214T1632,IT_ONLINE_AUCC_20180214T1632,FR_OFFLINE_AUCC_20180214T1436,FR_ONLINE_AUCC_20180214T1436,FR_OFFLINE_EINK_BOULANGER_20180409T1815,AU_OFFLINE_TABLET_20180309T1308,AU_ONLINE_TABLET_20180309T1326,US_OFFLINE_AUCC_TARGET_20180410T1436,US_OFFLINE_AUCC_KIDS_BBY_20171120T1347,US_OFFLINE_AUCC_KIDS_20171120T1515,US_ONLINE_AUCC_KIDS_20171121T0927,US_OFFLINE_SMP_FRANK_20171030T2332,US_ONLINE_SMP_FRANK_20171030T2331,DE_ONLINE_AUCC_ROW_20171030T1643,UK_ONLINE_AUCC_ROW_20171030T1642,US_ONLINE_AUCC_ROW_20171102T1542,US_OFFLINE_ViCC_STAPLES_20180319T1501,US_OFFLINE_ViCC_BEDBATH_20180319T1500,US_OFFLINE_ViCC_BESTBUY_20180319T1505,US_OFFLINE_AUCC_WFM_20171009T1732,CA_OFFLINE_AUCC_20170929T1713,CA_ONLINE_AUCC_20171107T1121,UK_OFFLINE_AUCC_20180410T1404,US_OFFLINE_AUCC_20180410T1246,US_OFFLINE_AUCC_BBY_20180410T1234,UK_ONLINE_AUCC_20180423T0927,CA_ONLINE_TABLETS_20170928T1529,US_ONLINE_AUCC_20180204T1632,UK_OFFLINE_TABLET_KET_ARGOS_20180116T1408,US_ONLINE_TABLET_NON_KET_20180116T1110,US_ONLINE_TABLET_KET_20180307T1515,UK_ONLINE_TABLET_NON_KET_20180125T1530_Test,US_ONLINE_EINK_ROW_20180307T1330,UK_ONLINE_TABLET_KET_20180125T1530_Test,US_OFFLINE_ViCC_PIE_Other_20180319T1506,US_ONLINE_FSN_HENDRIX_20180226T1138,US_OFFLINE_SMP_20180202T1451_NEWDDMG_ATT,DE_EDUENT_TABLET_20170928T1501,UK_EDUENT_TABLET_20170928T1523,DE_BRDCST_TABLET_20170928T1500,UK_BRDCST_TABLET_20170928T1524,ROWEN_ONLINE_SMP_STICK_DOZER_20180302T1529,BR_ONLINE_SMP_STICK_DOZER_20180102T0734,IN_EDUENT_SMP_BOX_20160606T1441_FCT_2019,IN_EDUENT_SMP_STICK_20160606T1440_FCT_2019,IN_BRDCST_SMP_BOX_20160606T1440_FCT_2019,IN_BRDCST_SMP_STICK_20160606T1439_FCT_2019,JP_EDUENT_SMP_BOX_20160606T1435_FCT_2019,JP_EDUENT_SMP_STICK_20160606T1435_FCT_2019,JP_BRDCST_SMP_BOX_20160606T1434_FCT_2019,JP_BRDCST_SMP_STICK_20160606T1433_FCT_2019,DE_EDUENT_SMP_BOX_20160606T1429_FCT_2019,DE_EDUENT_SMP_STICK_20160606T1428_FCT_2019,DE_BRDCST_SMP_BOX_20160606T1427_FCT_2019,DE_BRDCST_SMP_STICK_20160606T1426_FCT_2019,UK_EDUENT_SMP_BOX_20160606T1416_FCT_2019,UK_EDUENT_SMP_STICK_20160606T1415_FCT_2019,UK_BRDCST_SMP_BOX_20160606T1410_FCT_2019,UK_BRDCST_SMP_STICK_20160606T1403_FCT_2019,ES_BRDCST_TABLET_20170928T1502,IT_BRDCST_TABLET_20170928T1505,FR_BRDCST_TABLET_20170928T1503,JP_BRDCST_TABLET_20170928T15006,CN_BRDCST_TABLET_20170928T1458,DE_EDUENT_AuCC_20160527T1308,UK_EDUENT_AuCC_20160527T1303,DE_BRDCST_AuCC_20160527T1417,IN_BRDCST_AuCC_20160527T1420,JP_BRDCST_AuCC_20160527T1419,UK_BRDCST_AuCC_20160527T1415,IN_OFFLINE_SMP_STICK_20180212T0924,US_EDUENT_SMP_BOX_20160606T1442_FCT_2019,US_BRDCST_SMP_BOX_20160606T1420_FCT_2019,US_ONLINE_SMP_BOX_20180423T1527,US_OFFLINE_AUCC_NON_ECHO_FOX_20170717T1617,US_ONLINE_AUCC_NON_ECHO_FOX_20170929T1748,US_EDUENT_TABLET_20170928T1522,US_EDUENT_SMP_STICK_20160606T1421_FCT_2019,US_EDUENT_AuCC_20160527T1259,US_BRDCST_TABLET_20170928T1521,US_BRDCST_SMP_STICK_20160606T1419_FCT_2019,US_BRDCST_AuCC_20160527T1412,BR_OFFLINE_EINK_20180305T1325,ES_OFFLINE_EINK_20170928T2019,ES_ONLINE_TABLET_20171004T1619,IT_ONLINE_TABLET_20171004T1617,IT_ONLINE_EINK_20180319T1410,FR_OFFLINE_TABLET_20170928T1504,FR_ONLINE_TABLET_20171008T2358,FR_ONLINE_EINK_20180406T1015,CN_OFFLINE_TABLET_20170928T1457,CN_ONLINE_TABLET_20170928T1459,JP_OFFLINE_SMP_STICK_20171028T2348,DE_OFFLINE_TABLET_20180226T1752,DE_ONLINE_EINK_20180406T0945,UK_OFFLINE_SMP_STICK_20180423T1556,UK_OFFLINE_EINK_20180206T1700,UK_ONLINE_EINK_20180319T1330,US_OFFLINE_EINK_20171013T1035,US_ONLINE_SMP_STICK_20180226T1157";

        String[] models = modelNames.split(",");
        StringBuilder bld = new StringBuilder();
        for (String model : models) {
            String modelTruncated = StringUtils.substringBeforeLast(model, "_");
            System.out.println("modelTruncated=" + modelTruncated);
            bld.append(modelTruncated).append(",");
        }
        System.out.println("total=" + bld.toString());
    }
}