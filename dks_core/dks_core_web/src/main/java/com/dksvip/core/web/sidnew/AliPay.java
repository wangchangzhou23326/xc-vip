package com.dksvip.core.web.sidnew;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import okhttp3.OkHttpClient;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


/**
 *@ClassName AliPay
 *@Description 修改javascript运行引擎
 *@Author bbg
 *@Date 9:38 2024-09-02
 **/

@Log4j2
public class AliPay {


    /**
     * 加密算法公匙
     */
    private static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDENksAVqDoz5SMCZq0bsZwE+I3NjrANyTTwUVSf1+ec1PfPB4tiocEpYJFCYju9MIbawR8ivECbUWjpffZq5QllJg+19CB7V5rYGcEnb/M7CS3lFF2sNcRFJUtXUUAqyR3/l7PmpxTwObZ4DLG258dhE2vFlVGXjnuLs+FI2hg4QIDAQAB";

    /**
     * 加密方式
     */
    private static RSA rsa = SecureUtil.rsa(null, PUBLIC_KEY);

    /**
     * 加入的JavaScript引擎
     */
    private static String standard = "nashorn";
    private static ScriptEngine engine = getEngine(standard);

    // todo
    //  项目部署用private static String path = "./CryptoJS.js";


    /**
     * 根据运行环境的不同选择不同的路径
     */
    private static String path = getSystem();

    /**
     * 读取环境下的CryptoJS.js文件
     */
    private static FileReader file = getFile(path);

    /**
     * 引擎载入CryptoJS.js文本
     */
    static {
        EngineEval(engine,file);
    }


    /**
     * @param appPay 可以拉起的支付宝支付字符串，进行加解密处理。
     * 请求支付宝处理成加密字符串，然后调用CryptoJS.js函数进行解密处理，
     * 运算成html链接进行支付。
     * @return 返回h5支付链接
     */
    public static String alipayToH5 (String appPay) {
        if( Validator.isEmpty(appPay)){
            appPay =  "app_id=2021001153670340&biz_content=%7B%22body%22%3A%22%E9%BB%98%E8%AE%A4%E8%AE%A2%E5%8D%95%E6%8F%8F%E8%BF%B0%22%2C%22goods_type%22%3A%221%22%2C%22out_trade_no%22%3A%2212741707686477041664%22%2C%22passback_params%22%3A%22%7B%5C%22paySource%5C%22%3A%5C%2203%5C%22%7D%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E5%82%A8%E5%80%BC%E5%8D%A1%E5%85%85%E5%80%BC-%E4%B8%AA%E4%BA%BA%22%2C%22timeout_express%22%3A%2259m%22%2C%22total_amount%22%3A%2210.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fpayment.mcd.com.cn%2Fonline%2Fv1%2Fcallback%2FALI%2FaliNotify%2Fade631f5aae746f2a3ce9d4874d5e93f%2F03%2Fnull%2F01&sign=CJTrUXzC7qe7I5iR1TrB9EJJnMcmA%2BPVbw8CihXHzQCIav55bQ%2BijlehMmebHlCpL7L7CfG1TN5F6TqtBI03ABANayer91yFljLqePxJa88tEMtasiPpfOlFieNuXzNh0XVdz%2BsVb7GckANh08VRgPTtASlWhHhMGmZwH1S62PqAUh%2BXaG%2FeFH1VhyqJaXP6cb0wrSNGKpQ6s6Wpnkr%2BDZu5ovbUmsvEzgRzCWXvVhAPrlVk%2B9e4%2BRIHC8%2FVNN4zIG1TKO9aM6OLhgVk2RF7RZZGqgJUj%2BIL6cvQDvYKnkLLZuOuhYeCq7ion3%2Fw9wPJ%2Bg36OUHvNbN6BwyKWXEuqg%3D%3D&sign_type=RSA2&timestamp=2024-08-08+17%3A21%3A32&version=1.0";
        }
        String appKey = "2021002145675770";
        String key = RandomUtil.randomStringUpper(24);
        JSONObject action = new JSONObject()
                .set("type", "cashier")
                .set("method", "main");
        JSONObject content = new JSONObject()
                .set("tid", SecureUtil.sha256(key))
                .set("user_agent", String.format("Msp\\/9.1.5 (Android 9;Linux 4.4.146;zh_CN;http;540*960;21.0;WIFI;87699552;32617;1;000000000000000;000000000000000;8efce46e85;HUAWEI;HPB-AN00;false;00:00:00:00:00:00;-1.0;-1.0;sdk-and-lite;%s;%s;<unknown ssid>;02:00:00:00:00:00)",
                        RandomUtil.randomStringUpper(15), RandomUtil.randomStringUpper(15)))
                .set("has_alipay", false)
                .set("has_msp_app", false)
                .set("external_info", appPay)
                .set("app_key", appKey)
                .set("utdid", RandomUtil.randomStringUpper(25))
                .set("new_client_key", "8efcf8b134")
                .set("action", action)
                .set("gzip", true);


        String KeyCode = "key = CryptoJS.enc.Utf8.parse(\"{0}\");".replace("{0}",key);

        String TripleDES_Encrypt = KeyCode + "TripleDES_Encrypt({0})".replace("{0}",JSONUtil.toJsonStr(content));

        String keyEncode = getJSEvalStr(engine,TripleDES_Encrypt);


        String encode = rsa.encryptBase64(key, KeyType.PublicKey);
        String a2 = toHex(encode.length(), 8);
        String a3 = toHex(keyEncode.length(), 8);
        JSONObject data = new JSONObject()
                .set("data", new JSONObject()
                        .set("device", "HPB-AN00")
                        .set("namespace", "com.alipay.mobilecashier")
                        .set("api_name", "com.alipay.mcpay")
                        .set("api_version", "4.0.2")
                        .set("params", new JSONObject()
                                .set("req_data",  a2+encode+a3+keyEncode )
                        ));
        String url = "http://mcgw.alipay.com/gateway.do";
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(120L, TimeUnit.SECONDS)
                .readTimeout(120L, TimeUnit.SECONDS)
                .writeTimeout(120L, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                .build();
       /* HttpClient httpClient = new HttpClient(client);
        HashMap<String , String> head = new HashMap<>();
        head.put("Accept-Charset", "UTF-8");
        head.put("Connection", "Keep-Alive");
        head.put("Content-Length", "2243");
        head.put("Content-Type", "application/octet-stream;binary/octet-stream");
        head.put("Cookie",  "zone=RZ43A");
        head.put("Cookie2",  "$Version=1");
        head.put("Host",  "mcgw.alipay.com");
        head.put("Keep-Alive",  "timeout=180, max=100");
        head.put("User-Agent",  "msp");
        JSONObject resp = httpClient.httpPostNoCheck(url, head, JSONUtil.toJsonStr(data));
        String resData = resp.getJSONObject("data")
                .getJSONObject("params")
                .getStr("res_data");

        String TripleDES_Decrypt = KeyCode + "var resDate = \"{0}\";".replace("{0}",resData) +"TripleDES_Decrypt(resDate);";

        String resStr = getJSEvalStr(engine,TripleDES_Decrypt);

        return ReUtil.getGroup1("js:\\/\\/wappay\\('(.*)'\\)", resStr);*/
        return "";
    }


    /**
     * 格式化字符串
     * @param serialNum  序号
     * @param length  长度
     * @return
     */
    public static String toHex(int serialNum, int length) {
        return String.format("%0" + length + "x", serialNum);
    }


    /**
     * 创建引擎
     * @param standard 引擎库
     * @return
     */
    private static ScriptEngine getEngine(String standard){
        return new ScriptEngineManager().getEngineByName(standard);
    }


    /**
     * 调用js函数
     * @param engine
     * @param JSCode
     * @return
     */
    public static String getJSEvalStr(ScriptEngine engine ,String JSCode){
        try {
            return engine.eval(JSCode).toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 引擎执行javascript文件
     * @param engine
     * @param reader
     */
    public static void EngineEval(ScriptEngine engine,FileReader reader){
        try {
            engine.eval(reader);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 读取文件
     * @param path 文件路径
     * @return
     */
    public static FileReader getFile(String path){
        try {
            return new FileReader(path);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 得到运行环境
     * @return
     */
    public static String getSystem(){
        Boolean isWindows = System.getProperty("os.name").toUpperCase().indexOf("WINDOWS")>=0?true:false;
        if(isWindows){
            return "./rx_core/rx_core_web/src/main/resources/CryptoJS.js";
        }else{
            return "./CryptoJS.js";
        }
    }
}
