package Main;

import service.mail;

public class Main {
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1.toUpperCase().equals(str2.toUpperCase());
    }

    public static void main(String[] args) throws Exception {
//        String str = "<?xml version=\"1.0\" encoding=\"gb2312\" ?><items><item name=\"resultno\" value=\"0007\" /><item name=\"retmsg\" value=\"没有采购此类商品\" /></items>";
        /*String regex = ".+resultno.{9}(\\d+)\".+retmsg.{9}(.+)\".+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            System.out.println(matcher.group(2));
        }else{
            System.out.println("error");
        }*/

        /*String regex = ".+resultno.{9}(.+)\".+retmsg.{9}(.+)\".+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()){
            if(equalsIgnoreCase(matcher.group(1),"0000")){
                System.out.println("订单提交成功");
            }
            else if(equalsIgnoreCase(matcher.group(1),"0001")){
                System.out.println("传入的参数不完整");
            }
            else if(equalsIgnoreCase(matcher.group(1),"0007")){
                System.out.println("没有采购此类商品");
            }else{
                System.out.println(matcher.group(2));
            }
        }else{
            System.out.println("错误！");
        }*/

        /*System.out.println(str.matches(".+resultno.{9}0007\".+"));
        String[] tmp = str.split("retmsg.{9}");
        System.out.println(tmp[1].substring(0,tmp[1].indexOf("\"")));*/

        /*try(InputStream is = new ByteArrayInputStream(str.getBytes())){
            System.out.println(is.available());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*String smtp = "smtp.exmail.qq.com";
        String from = "linningning@golandit.com";
        String to = "linningning@golandit.com,lanhoo123@163.com";
        String copyto = "linningning@golandit.com";
        String subject = "邮件主题";
        String content = "邮件内容";
        String username="linningning@golandit.com";
        String password="";
        String filename = "C:\\Users\\Administrator\\Downloads\\test.txt";
        mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password, filename);*/

        /*MailBuilderFactory mailBuilderFactory = new MailBuilderFactory();

        mailBuilderFactory.setSubject("邮件主题");
        TextTemplateContent textTemplateContent = new TextTemplateContent("余额通知");
        mailBuilderFactory.setContent(textTemplateContent);

        MailBuilder mail = (MailBuilder) mailBuilderFactory.getObject();
        mail.setAddress(MailAddressType.TO, "linningning@golandit.com");
        mail.setAddress(MailAddressType.FROM,"linningning@golandit.com");
        MailTransport mailTransport = mail.getMailService().getMailTransport();
        mailTransport.connect();
        mailTransport.send(mail);
        mailTransport.close();*/


    }
}