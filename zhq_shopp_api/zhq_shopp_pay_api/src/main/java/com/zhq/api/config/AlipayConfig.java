package com.zhq.api.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101300673361";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCW/85mV+e7ajoX2+cHGPGk+7RYEFg4VHpxxlC2/VTX2haoaDhwoEEzoaPD1/yT0eRglQR7G+TUj5Zx+brxjRelib2qYcl3oVy0XDnTXtDhUFtW8wcWTQUr/p181ZEbJ/GcWe2bpQNq2hf1aOFLwgn1lhRtP82C7Y3hyVNPU5MY3QHgD64jp7aZRv3V84F7mqv28P8h87VuLsP2JtpblL/iNbM/teO1cVfTXgycxEEUAXQO2adYc9upmf9T4/TLD9r7B91+rnnmKAZRApuW+Wo5IrVoV9HW0JgiLQ8q5CkGbffafj05W7VOqbhAOLfrsOuqBvnCMRE8B/m374lF7Ks3AgMBAAECggEAaloLtputdJwSBKH2q1IPSIOb9va0HCCsCRyD1w7AxjMuCDk3n2PP6Jytao/irv6DWt86Q7dWybvEW8xntiAJCqfkNXAgZSj60XGvrMc4WamuW6RaVoOy49S2eak2RakxjEzuhNSJfqXTgB75p9EoEJg/FN9+UPweFWwmSFX14ekVoiG4LxTMsNcCYSPDMEbvYhJdqbLUh3/Wj4s45jvWoadqB3zT7GgyOsTiEn2Y2qTd7WWUS+lNE+zcIi59X3ICAFeOnAD51chFjKlUV4VaMTzwZSdY8vHlGO85afkVc0PFFt1vRtH2y9V0nxU/CKH8wA5TE84cJIMAKP9XgP/B6QKBgQDtxRuazFEbJnVrGgjLlvVQ1pWTgxsEsqr5BokCNGznhH24rY8jdXlGuIbveOwqLYXt3B4XkOgww87RkWAxub9vCtLiOwrYKVWHVBWNnDXKXodZwYCBFdU9beyKJ3ffEm05nKEU3L1xxE86P52syRzHWJT8yI1TH6WOMduAF12AcwKBgQCik5Vi2bQrmQstwwrv0NtgWF43kgseitoM0LzFrp0fZap4arQAP/2TeG3g4rw/ExXbQDqbTSsxstesdYP1ZJBSGlZRrfs88IQwh0dC1CM7/RBQqhvvpX8bIXaH1dCqGPR6lmMSXWBu3gZ6Z9F9B4T3O+0aqZTVy2q61Q0bJ5vNLQKBgQDD4Xmfaql6y5E6PcV3LBNHmLC7SOwuT58o7uhah6Qci3sjdOCSXsodqBGKIb219bD7RzcHRRr0aavt3G06KgN60JjxRaDmeg77IRtsgX+TC5NIL3DVZ444QGH5YxCPFtgi+VV/IWd03TxECXq1iS2LUP/UzMgY7d5vKcXiXd1q3wKBgFf48mIeGc9prlHh894XL05wIvOBFIE/c4Rxw2RY+27M7bThUGu+NLIC/bouFt0ur6oHlG8LD++BrhEFOhWkiC/VTpY+RfAQ2gcEfLwd3L8OTjDIC/vemuwMbH5OZe5VOJ3JGO0MGC2F+Q1tdj9ovTuncsL1trMFsshMlx31jeo1AoGAG497/oZp+ZqzPx3l8WQ6wnDzShBRF8P8TQRcNFXe+/PHehF7Bn8m0C3okwmqZ1mzHR2HtiKYzD4YHEXDVDtntbut00QMVcpUXvfLbEJy2XAQxAy2IdTRamKptWIOP1Zu2PDodB7XxvtfcdTYj8A9O5IywzuuMK7xGCnGdQTMQVw=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgOWeSao5mnkH3uxkxN8erPGbFX0knkOvLH5ocXeOINkUvuSviw2PtePVspiU5JoN7dPxXxL7m/lsBH0CQcGpqI5tjlOEGt80pHBMZyojppeq2GhveN4hASLhsnjqyjvLH6bULkBVTIlYBbtWi47KiaSdR4VysL5PVb0CGdqfdVTgHCpYdpQL7qZYEsDiRXBgzDjV46jN1cTv+IPFtanZwnblwcZiyKgHG9Y5jJC2BzPvHsTqVivCTqFOhnuZFaR90mXZ6fS+SBlenm2TCKEROFrReeZ+wv/LUhXaGe/algHynrECBlnchBp9fCgLXw8qKB32NfbPTYS77saItclA0wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "https://5ff87332.ngrok.io/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "https://5ff87332.ngrok.io/return_url.jsp";

    // 签名方式
	public static String sign_type = "RSA2";

    // 字符编码格式
	public static String charset = "utf-8";

    // 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
	public static String log_path = "d:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

