package tool;

import org.apache.commons.collections.CollectionUtils;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2014/11/27.
 */
public class util {
    public static String md5Enc(String str, boolean toUpperCase) {
        /*try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            char[] charArray = str.toCharArray();
            byte[] byteArray = new byte[charArray.length];

            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte)charArray[i];
                byte[] md5Bytes = md5.digest(byteArray);

                for (int j = 0; j < md5Bytes.length; j++){
                    int val = ((int) md5Bytes[j]) & 0xff;
                    if (val < 16)
                        hexValue.append("0");
                    hexValue.append(Integer.toHexString(val));
                }
            }
            return hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";*/

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char _str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                _str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                _str[k++] = hexDigits[byte0 & 0xf];
            }
            return toUpperCase ? new String(_str).toUpperCase() : new String(_str);
        } catch (Exception e) {
            return "";
        }
    }

    /*public static String xmlR(String xml,String charset,String... name){
        StringBuffer result = new StringBuffer();
        DocumentBuilderFactory dom = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dom.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(xml.getBytes(charset)));
            NodeList items = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                NamedNodeMap nodeMap = items.item(i).getAttributes();
                Node name_node = nodeMap.getNamedItem("name");
                Node value_node = nodeMap.getNamedItem("value");
                for (int j = 0; j < name.length; j++) {
                    if(name[j].equals(name_node.getNodeValue())){
                        result.append(name_node.getNodeValue()+" = "+value_node.getNodeValue()+"\n");
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }*/

    public static Map<String, Object> xmlR_dom4j(String xml) {
        Map<String, Object> responeMap = new HashMap<String, Object>();
        org.dom4j.Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        parseElement(document.getRootElement(), responeMap);
        return responeMap;
    }

    private static void parseElement(Element element, Map<String, Object> map) {
        Iterator<Element> i = element.elementIterator();
        if (!i.hasNext()) {
            map.put(element.getName(), element.getStringValue());
            return;
        }
        int j = 0;
        for (; i.hasNext(); ) {
            Element e = i.next();
            String eleName = e.getName();
            ++j;

            if (e.attributeCount() > 0) {
                for (Iterator<Attribute> attrIt = e.attributeIterator(); attrIt.hasNext(); ) {
                    Attribute attr = attrIt.next();
                    String attrName = attr.getName();
                    map.put(String.format("%s." + j + ".%s", eleName, attrName),
                            attr.getStringValue());
                }
            }
            if (CollectionUtils.isNotEmpty(e.elements())) {
                Map<String, Object> responeMap = new HashMap<String, Object>();
                parseElement(e, responeMap);
                map.put(eleName + "." + j, responeMap);
            } else {
                map.put(eleName + "." + j, e.getStringValue());
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(util.md5Enc("", true));

        Map<String, Object> xml = util.xmlR_dom4j("<?xml version=\"1.0\" encoding=\"gb2312\" ?><items><item name=\"resultno\" value=\"0007\" /><item name=\"retmsg\" value=\"没有采购此类商品\" /></items>");
        System.out.println(xml.get("item.1.value"));
        System.out.println(xml.get("item.2.value"));

        System.out.println(URLEncoder.encode("浙江电信一区", "utf8"));
        System.out.println(URLDecoder.decode("%E6%B5%99%E6%B1%9F%E7%94%B5%E4%BF%A1%E4%B8%80%E5%8C%BA", "utf8"));
    }
}
