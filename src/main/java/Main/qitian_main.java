package Main;

import tool.sendG;
import tool.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2014/11/28.
 */
public class qitian_main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        final String charset = "gb2312";

        final String merchantid = "105183";//商户ID
        final String productid = "qq0019";//商品类型ID
        final String buynumber = "1";//购买数量
        final String parvalue1 = "100";//商品面值
        final String tranid = "1417078478771";//商户系统流水号
        final String playerusername = "460703859";//充值账号
        final String RemoteIp = "127.0.0.1";//玩家充值IP
        final String sign = util.md5Enc("merchantid=" + merchantid + "&productid=" + productid + "&playerusername=" + playerusername + "&buynumber=" + buynumber + "&tranid=" + tranid +
                "&merchantkey=2bef1ed19c4d4180", true);//MD5签名

        String gamesrv = "";
        String gamearea = "";
        try {
            gamesrv = URLEncoder.encode("浙江电信一区", charset);
            gamearea = URLEncoder.encode("浙江电信", charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        String parvalue="按元充";
//        String chargetype="购买元宝";

        final String qitian_dingd = "http://202.75.218.82/rsct/XQT_OnlineOrder.asp", qitian_dingd_param = "?sign=" + sign + "&" + "parvalue1=" + parvalue1 + "&tranid=" + tranid + "&" +
                "productid=" + productid + "&buynumber=" + buynumber + "&RemoteIp=" + RemoteIp + "&playerusername=" + playerusername + "&merchantid=" + merchantid;
        System.out.println("------------------------------以下为提交订单显示------------------------------");
        final String dingd = sendG.sendGet(qitian_dingd, qitian_dingd_param, charset);
        Map<String, Object> dingd_result = util.xmlR_dom4j(dingd);
        System.out.println(dingd_result.get("item.1.value"));
        System.out.println(dingd_result.get("item.2.value"));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final String qitian_yue = "http://202.75.218.82/rsct/XQT_CustomerBalanceQuery.asp";
        final String querytime_ori = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        String querytime = URLEncoder.encode(querytime_ori, charset);
//        System.out.println(querytime_ori+"--->"+querytime);
        final String sign2 = util.md5Enc("merchantid=" + merchantid + "&querytime=" + querytime_ori + "&merchantkey=2bef1ed19c4d4180", false);
        final String qitian_yue_param = "?merchantid=" + merchantid + "&querytime=" + querytime + "&sign=" + sign2;
        final String dingd2 = sendG.sendGet(qitian_yue, qitian_yue_param, charset);
        Map<String, Object> yue_result = util.xmlR_dom4j(dingd2);
        System.out.println("------------------------------以下为查询余额显示------------------------------");
        System.out.println(yue_result.get("item.1.value"));
        System.out.println(yue_result.get("item.2.value"));
        System.out.println(yue_result.get("item.3.value"));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final String productid_qufu = "wm0001";
        final String gameid_qufu = "1006";
        final String gamever_qufu = "";
        final String qitian_qufu = "http://202.75.218.82/rsct/XQT_AreSerQuery.asp";
        final String sign3 = util.md5Enc("merchantid=" + merchantid + "&productid=" + productid_qufu + "&gameid=" + gameid_qufu + "&merchantkey=2bef1ed19c4d4180", true);
        final String qitian_qufu_param = "?merchantid=" + merchantid + "&productid=" + productid_qufu + "&Gamename=" + URLEncoder.encode("热舞派对", charset) + "&gameid=" + gameid_qufu + "&Gamever=" + gamever_qufu + "&sign=" + sign3;
        final String dingd3 = sendG.sendGet(qitian_qufu, qitian_qufu_param, charset);
        Map<String, Object> qufu_result = util.xmlR_dom4j(dingd3);
        System.out.println("------------------------------以下为查询区服显示------------------------------");
        System.out.println(dingd3);
        System.out.println(qufu_result.get("item.1.value"));
        System.out.println(qufu_result.get("item.2.value"));
    }
}
