package service;

import tool.sendG;
import tool.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2014/12/2.
 */
public class phoneArea {
    private final static String charset = "gb2312";
    private final static String query_url = "http://life.tenpay.com/cgi-bin/mobile/MobileQueryAttribution.cgi?chgmobile=";

    private static boolean checkPhoneNum(final String phoneNum) {
        final String checkRegex = "^\\d{11,11}$";
        Pattern pattern = Pattern.compile(checkRegex);
        Matcher matcher = pattern.matcher(phoneNum);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static String queryPhoneArea(final String phoneNum) {
        if (phoneArea.checkPhoneNum(phoneNum)) {
            String back_info = sendG.sendGet(query_url, phoneNum, charset);
            Map<String, Object> result = util.xmlR_dom4j(back_info);
            if ("OK".equals(result.get("retmsg.11"))) {
                return result.get("chgmobile.7") + "\n" + result.get("province.9") + "/" + result.get("city.8") + "(省/市)\n" + result.get("supplier.12");
            } else {
                return "Not Found";
            }
        } else {
            return "Error Phone Number";
        }
    }

    public static void main(String[] args) {
        System.out.println(phoneArea.queryPhoneArea("17606711067"));
    }
}
