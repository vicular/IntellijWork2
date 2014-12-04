package service;

import tool.sendG;
import tool.util;

import java.util.Map;

/**
 * Created by Administrator on 2014/12/2.
 */
public class qqOnline {
    private static final String check_online = "http://www.webxml.com.cn/webservices/qqOnlineWebService.asmx/qqCheckOnline?qqCode=";
    private static final String charset = "utf8";

    public static String checkQQOnline(final String QQ) {
        String back_xml = sendG.sendGet(check_online, QQ, charset);
        Map<String, Object> result = util.xmlR_dom4j(back_xml);
        String code = (String) result.get("string");
        System.out.println(code);
        String online = "";
        switch (code) {
            case "E":
                online = "QQ号码错误";
                break;
            case "Y":
                online = "在线";
                break;
            case "N":
                online = "离线";
                break;
            case "A":
                online = "商业用户验证失败";
                break;
            case "V":
                online = "免费用户超过数量";
                break;
            default:
                online = "内部错误";
        }
        return online;
    }

    public static void main(String[] args) {
        System.out.println(qqOnline.checkQQOnline("460703859"));
    }
}
