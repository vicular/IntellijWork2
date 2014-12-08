package Main;

import tool.sendP;
import tool.util;

import java.util.Map;

/**
 * Created by Administrator on 2014/12/3.
 */
public class yikahui_main {
    final static String charset = "utf8";

    final static String user = "2014120300000169";
    final static String key = "A3E45DCFE3FBEE17DA1150AAD3F08B14";
    final static String sign = util.md5Enc(user + key, false);
    final static String yue_url = "http://api.ekhui.com/userBanlance";
    final static String yue_param = "userNo=" + user + "&sign=" + sign;

    final static String test_user = "2014120300000133";
    final static String test_key = "123456";
    final static String test_sign = util.md5Enc(test_user + test_key, false);
    final static String yue_test_url = "http://testapi.ekhui.com:5003/api_game/userBanlance";
    final static String yue_test_param = "userNo=" + test_user + "&sign=" + test_sign;

    public static void main(String[] args) {
        String back_xml = sendP.sendPost(yue_test_url, yue_test_param, charset);
        System.out.println(back_xml);
        Map<String, Object> dingd_result = util.xmlR_dom4j(back_xml);
        System.out.println("------------------------------以下为查询余额显示------------------------------");
        System.out.println(dingd_result.get("resMsg.1"));
        System.out.println(dingd_result.get("failedCode.2"));
        System.out.println(dingd_result.get("failedReason.3"));
        System.out.println(dingd_result.get("balance.4"));
    }
}
