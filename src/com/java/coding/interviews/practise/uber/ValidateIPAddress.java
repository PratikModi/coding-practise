package com.java.coding.interviews.practise.uber;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateIPAddress {
    public static void main(String[] args) {
        String IPV4 = "192.168.1.1";
        System.out.println(validateIPAddress(IPV4));
        String IPV6 = "2001:db8:85a3:0:0:8A2E:0370:7334";
        System.out.println(validateIPAddress(IPV6));
    }

    public static String validateIPAddress(String ipAddress){
        String IPV4Pattern = "((([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5]))";
        Pattern ipv4 = Pattern.compile(IPV4Pattern);
        Matcher matcher = ipv4.matcher(ipAddress);
        boolean isIPV4 = matcher.matches();
        if(isIPV4)
            return "IPV4";
        String IPV6Pattern = "((([0-9a-fA-F]{1,4})\\:){7}([0-9a-fA-f]{1,4}))";
        Pattern ipv6 = Pattern.compile(IPV6Pattern);
        Matcher ipv6Matcher = ipv6.matcher(ipAddress);
        boolean isIPV6 = ipv6Matcher.matches();
        new ArrayList<String>().stream().map(i->i.toString()).toArray();
        if(isIPV6)
            return "IPV6";
        return "";
    }



}
