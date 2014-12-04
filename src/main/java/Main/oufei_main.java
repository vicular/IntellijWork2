package Main;

import tool.sendG;
import tool.util;

import java.util.Map;

/**
 * Created by Administrator on 2014/11/28.
 */
public class oufei_main {
    public static void main(String[] args) {
        String charset = "gb2312";
        String oufei_yue = "http://api2.ofpay.com/queryuserinfo.do", oufei_yue_param = "?userpws=fde2fec0d98017615f41542b992f71a0&Sign=&userid=A1002762&version=6.0";
        String balance = sendG.sendGet(oufei_yue, oufei_yue_param, charset);
        Map<String, Object> dingd_result = util.xmlR_dom4j(balance);
        System.out.println("------------------------------以下为查询余额显示------------------------------");
        System.out.println(dingd_result.get("err_msg.1"));
        System.out.println(dingd_result.get("retcode.2"));
    }
}
