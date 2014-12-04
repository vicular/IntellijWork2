package service;

import tool.sendG;
import tool.util;

import java.util.Map;

/**
 * Created by Administrator on 2014/12/3.
 */
public class IPArea {
    private final static String charset = "utf8";
    private final static String query_url = "http://www.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx/getCountryCityByIp?theIpAddress=";

    public static String queryIPArea(final String IPAddr) {
        String back_info = sendG.sendGet(query_url, IPAddr, charset);
        Map<String, Object> result = util.xmlR_dom4j(back_info);
        return (String) result.get("string.2");
    }

    public static void main(String[] args) {
        System.out.println(IPArea.queryIPArea("124.90.51.18"));
    }
}
