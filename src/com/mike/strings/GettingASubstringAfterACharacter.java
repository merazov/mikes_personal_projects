package com.mike.strings;

public class GettingASubstringAfterACharacter {

    public static void main(String[] args) {
        String s = "9629d924-3197-4b7a-8093-144bffbb92c2/US_ONLINE_TABLET_NON_KET_20180116T1110";
        System.out.println("subs=" + s.substring(s.lastIndexOf('/') + 1));
    }
}
