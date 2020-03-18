package com.liwei.paytemplate.template;

import com.liwei.paytemplate.constants.PayConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付回调
 *
 * @author liwei
 * @create: 2020/3/18 9:01 下午
 */
@Component
@Slf4j
public class AliPayCallbackTemplate extends AbstractPayCallbackTemplate {
    @Override
    protected Map<String, String> verifySignature() {
        //>>>>支付宝回调报文伪代码>>>>
        log.info(">>>>>第一步 解析支付宝数据报文.....verifySignature()");
        Map<String, String> verifySignature = new HashMap<>();
        verifySignature.put("price", "10000");
        verifySignature.put("orderDes", "充值会员");
        // 支付状态为1表示为成功....
        verifySignature.put("aliPayMentStatus", "1");
        verifySignature.put("aliPayOrderNumber", "20190511");
        // 解析报文是否成功 200 为成功..
        verifySignature.put("analysisCode", String.valueOf(PayConstant.RESULT_PAY_CODE_200));
        return verifySignature;
    }

    @Override
    protected String asyncService(Map<String, String> verifySignatureMap) {
        log.info(">>>>>第三步asyncService()verifySignatureMap:{}", verifySignatureMap);
        String paymentStatus = verifySignatureMap.get("aliPayMentStatus");
        if (paymentStatus.equals(String.valueOf(PayConstant.PAY_STATUS_SUCCESS))) {
            String aliPayOrderNumber = verifySignatureMap.get("aliPayOrderNumber");
            log.info(">>>>orderNumber:{aliPayOrderNumber},已经支付成功 修改订单状态为已经支付...");
        }
        return resultSuccess();
    }

    @Override
    protected String resultSuccess() {
        return PayConstant.ALI_PAY_RESULT_SUCCESS;
    }

    @Override
    protected String resultFail() {
        return PayConstant.ALI_PAY_RESULT_FAIL;
    }
}
