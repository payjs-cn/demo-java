package com.payjs.pay.pay.controller;

import com.alibaba.fastjson.JSON;
import com.payjs.pay.pay.entity.dto.NotifyDTO;
import com.payjs.pay.util.HttpsUtils;
import com.payjs.pay.util.PayjsConfig;
import com.payjs.pay.web.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/pay")
public class PayController {

    /**
     * native
     */
    @RequestMapping("/native")
    @ResponseBody
    public Object Native() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("total_fee", "100");
        payData.put("out_trade_no", "83432749"); // 订单号 随便输的，自己生成一下就好了
        payData.put("body","订单标题");
        payData.put("notify_url", "https://你的域名/api/pay/nitify");

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs获取二维码
        String result = HttpsUtils.sendPost(PayjsConfig.nativeUrl, JSON.toJSONString(payData),null);

        // 接口返回二维码数据
        return JSON.parseObject(result);
    }

    /**
     * jsapi
     */
    @RequestMapping("/jsapi")
    @ResponseBody
    public Object Jsapi() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("total_fee", "100");
        payData.put("out_trade_no", "83432749"); // 订单号 随便输的，自己生成一下就好了
        payData.put("openid", "xxxxxxxxxxxxxx"); // 根据openid接口获取到的openid
        payData.put("body","订单标题");
        payData.put("attach","自定义数据");
        payData.put("notify_url", "https://你的域名/api/pay/notify");

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.jsapiUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * mweb
     */
    @RequestMapping("/mweb")
    @ResponseBody
    public Object Jsapi() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("total_fee", "100");
        payData.put("out_trade_no", "83432749"); // 订单号 随便输的，自己生成一下就好了
        payData.put("body","订单标题");
        payData.put("attach","自定义数据");
        payData.put("notify_url", "https://你的域名/api/pay/notify");

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.mwebUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * check
     */
    @RequestMapping("/check")
    @ResponseBody
    public Object Check() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("payjs_order_id", "83432749"); // payjs订单号


        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.checkUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * close 关闭订单
     */
    @RequestMapping("/close")
    @ResponseBody
    public Object Close() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("payjs_order_id", "83432749"); // payjs订单号

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.closeUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * reverse 撤销订单
     */
    @RequestMapping("/reverse")
    @ResponseBody
    public Object Reverse() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("payjs_order_id", "83432749"); // payjs订单号

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.reverseUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * refund 订单退款
     */
    @RequestMapping("/refund")
    @ResponseBody
    public Object Refund() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("payjs_order_id", "83432749"); // payjs订单号

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.refundUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * info 商户资料
     */
    @RequestMapping("/info")
    @ResponseBody
    public Object Info() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.infoUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * complaint 投诉查询
     */
    @RequestMapping("/complaint")
    @ResponseBody
    public Object Complaint() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.complaintUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * bank 银行查询
     */
    @RequestMapping("/bank")
    @ResponseBody
    public Object Bank() throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("bank", "xxxxxx"); // 银行简称

        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.bankUrl, JSON.toJSONString(payData),null);

        // 接口返回数据
        return JSON.parseObject(result);
    }

    /**
     * 异步通知
     * @param notifyDTO
     * @return
     */
    @RequestMapping("/notify")
    @ResponseBody
    public Object payjsNotify(NotifyDTO notifyDTO){
        Map<String,String> notifyData = new HashMap<>();
        notifyData.put("return_code",notifyDTO.getReturn_code());
        notifyData.put("total_fee",notifyDTO.getTotal_fee());
        notifyData.put("out_trade_no",notifyDTO.getOut_trade_no());
        notifyData.put("payjs_order_id",notifyDTO.getPayjs_order_id());
        notifyData.put("transaction_id",notifyDTO.getTransaction_id());
        notifyData.put("time_end",notifyDTO.getTime_end());
        notifyData.put("openid",notifyDTO.getOpenid());
        notifyData.put("mchid",notifyDTO.getMchid());
        
        // options
        if (notifyDTO.getAttach() != null) {
            notifyData.put("attach",notifyDTO.getAttach());
        }
        if (notifyDTO.getType() != null) {
            notifyData.put("type", notifyDTO.getType());
        }


        String sign = sign(notifyData, PayjsConfig.key);
        if(sign.equals(notifyDTO.getSign())){
            // 验签通过，这里修改订单状态


            return "success";
        }

        return "failure";
    }


    //签名算法
    public String sign(Map<String, String> params, String secret){
        String sign="";
        StringBuilder sb = new StringBuilder();
        //step1：先对请求参数排序
        Set<String> keyset = params.keySet();
        TreeSet<String> sortSet = new TreeSet<String>();
        sortSet.addAll(keyset);
        Iterator<String> it = sortSet.iterator();
        //step2：把参数的key value链接起来 secretkey放在最后面，得到要加密的字符串
        while(it.hasNext())
        {
            String key = it.next();
            String value = params.get(key);
            sb.append(key).append("=").append(value).append("&");
        }
        sb.append("key=").append(secret);
        byte[] md5Digest;
        try {
            //得到Md5加密得到sign
            md5Digest = getMD5Digest(sb.toString());
            sign = byte2hex(md5Digest);
        } catch (IOException e) {
            System.out.println("生成签名错误" + e);
        }
        return sign;
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    private static byte[] getMD5Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }

}
