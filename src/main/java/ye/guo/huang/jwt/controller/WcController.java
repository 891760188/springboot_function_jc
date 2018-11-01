package ye.guo.huang.jwt.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ye.guo.huang.jwt.common.Page;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.common.util.*;
import ye.guo.huang.jwt.pojo.PaymentPo;
import ye.guo.huang.jwt.pojo.SysFiles;
import ye.guo.huang.jwt.service.SysFilesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wc")
public class WcController {

    private static final Logger LOGGER = LogManager.getLogger(WcController.class);

    @Value("${weixin.host}")
    private String wcHost ;

    /**
     * 随机字符串，长度要求在32位以内。推荐随机数生成算法
     */
//    @Value("${constant.nonceStr}")
//    private String nonceStr;
    /**
     * 小程序ID
     */
    @Value("${constant.appid}")
    private String appid;


    @RequestMapping("/test0001.file")
    public ResponseBean test0001(HttpServletResponse response, String str) throws IOException {

        String [] strs = {"aa","达到"};

        return ResponseBean.response(strs);
    }

    /**
     * 换取 用户唯一标识 OpenID 和 会话密钥 session_key   ->>>  oopenid 和 session_key 组成会话标识，发给客户端，客户端凭借这个标识进行通信
     * 会话密钥 session_key 是对用户数据进行 加密签名 的密钥。
     * 为了应用自身的数据安全，开发者服务器不应该把会话密钥下发到小程序，也不应该对外提供这个密钥。
     * 临时登录凭证 code 只能使用一次
     * @param request
     * @param requestEntity
     * @param code
     * @return
     */
    @PostMapping("/jscode2session.wc")
    public ResponseBean jscode2session(HttpServletRequest request, HttpEntity<String> requestEntity,String code) {

        HttpMethod requestMethod = HttpMethod.resolve(request.getMethod());
        String body = requestEntity.getBody();
        Map<String,String> mapParam = JacksonUtil.fromJson(body,Map.class);
        code = mapParam.get("code");
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        requestEntity = new HttpEntity<>(requestEntity.getBody(), headers);

        ResponseEntity<String> response = null;
        response = restTemplate.exchange(
                wcHost+"sns/jscode2session?appid=wxd0ad6e09b4f09a2a&secret=919f06b91b6adbe682c0bd9edc0a008f&js_code="+code+"&grant_type=authorization_code",
                requestMethod,
                requestEntity,
                String.class);

        String result =  response.getBody();
        LOGGER.info("result="+result);
        Map<String,String> mapResult = JacksonUtil.fromJson(result,Map.class);
        return ResponseBean.response(mapResult);

    }

    /**
     *  商户server调用支付统一下单
//     * @param openid 用户唯一标识
//     * @param mch_id 商品编号
//     * @param total_fee 商品价格
//     * @param body 商品描述
//     * @param detail 商品详情
//     * @param attach 附加数据
//     * @param time_start 交易开始时间
//     * @param time_expire 交易结束时间
     * @return
     */
    @PostMapping("/payOrderPublic.wc")
    public ResponseBean  payOrderPublic(HttpServletRequest request,HttpEntity<String> requestEntity,PaymentPo paymentPo) throws UnsupportedEncodingException, DocumentException {

        paymentPo = JacksonUtil.fromJson(requestEntity.getBody(),PaymentPo.class);
        paymentPo.setBody(new String(paymentPo.getBody().getBytes("UTF-8"),"ISO-8859-1")) ;
//        String appid = "替换为自己的小程序ID";//小程序ID
        //当前时间
        String today = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        //生成8位随机数
        String code = PayUtil.createCode(8);
        //商户订单号
        String out_trade_no = paymentPo.getMch_id()+today+code;//商户订单号
        String spbill_create_ip = HttpUtil.getIpAddress(request);//终端IP
        String notify_url = "http://www.weixin.qq.com/wxpay/pay.php";//通知地址
        String trade_type = "JSAPI";//交易类型

        paymentPo.setAppid(appid);
        //随机字符串 32位
        String str=UUIDHexGeneratorUtil.generate();
        paymentPo.setNonce_str(str);
        paymentPo.setOut_trade_no(out_trade_no);
        paymentPo.setSpbill_create_ip(spbill_create_ip);
        paymentPo.setNotify_url(notify_url);
        paymentPo.setTrade_type(trade_type);

        // 把请求参数放进hashmap
        Map<String,String> sParaTemp = new HashMap<String,String>();
        sParaTemp.put("appid", paymentPo.getAppid());
        sParaTemp.put("mch_id", paymentPo.getMch_id());
        sParaTemp.put("nonce_str", paymentPo.getNonce_str());
        sParaTemp.put("body",  paymentPo.getBody());
        sParaTemp.put("out_trade_no", paymentPo.getOut_trade_no());
        sParaTemp.put("total_fee",paymentPo.getTotal_fee());
        sParaTemp.put("spbill_create_ip", paymentPo.getSpbill_create_ip());
        sParaTemp.put("notify_url",paymentPo.getNotify_url());
        sParaTemp.put("trade_type", paymentPo.getTrade_type());
        sParaTemp.put("openid", paymentPo.getOpenid());

        // 除去map中的空值和签名参数
        Map<String,String> sPara = PayUtil.paraFilter(sParaTemp);
        // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String prestr = PayUtil.createLinkString(sPara);
        String key = "&key=替换为商户支付密钥"; // 商户支付密钥
        //MD5运算生成签名
        String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
        paymentPo.setSign(mysign);
        //打包要发送的xml
        String respXml = MessageUtil.messageToXML(paymentPo);
        // 打印respXml发现，得到的xml中有“__”不对，应该替换成“_”
        respXml = respXml.replace("__", "_");
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单API接口链接
        String param = respXml;
        String result =PayUtil.httpRequest(url, "POST", param);
        // 将解析结果存储在HashMap中
        Map<String,String> map = new HashMap<String,String>();
        InputStream in = new ByteArrayInputStream(result.getBytes());
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        for (Element element : elementList) {
            map.put(element.getName(), element.getText());
        }
        // 返回信息
        String return_code = map.get("return_code");//返回状态码
        String return_msg = map.get("return_msg");//返回信息
        System.out.println("return_msg"+return_msg);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        if(return_code=="SUCCESS"||return_code.equals(return_code)){
            // 业务结果
            String prepay_id = map.get("prepay_id");//返回的预付单信息
            String nonceStr=UUIDHexGeneratorUtil.generate();
            resultMap.put("nonceStr", nonceStr);
            resultMap.put("package", "prepay_id="+prepay_id);
            Long timeStamp= System.currentTimeMillis()/1000;
            resultMap.put("timeStamp", timeStamp+"");
            String stringSignTemp = "appId="+appid+"&nonceStr=" + nonceStr + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;
            //再次签名
            String paySign=PayUtil.sign(stringSignTemp, "&key=替换为自己的密钥", "utf-8").toUpperCase();
            resultMap.put("paySign", paySign);
        }
            return ResponseBean.response(resultMap) ;
    }


}
