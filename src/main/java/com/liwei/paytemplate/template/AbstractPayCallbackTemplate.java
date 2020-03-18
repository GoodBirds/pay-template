package com.liwei.paytemplate.template;

import com.liwei.paytemplate.constants.PayConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 抽象支付回调模板类
 *
 * @author liwei
 * @create: 2020/3/18 8:51 下午
 */
@Component
@Slf4j
public abstract class AbstractPayCallbackTemplate {

    /**
     * 异步回调业务
     */
    public String asyncCallBack() {
        // 1. 支付回调验证参数
        Map<String, String> verifySignatureMap = verifySignature();
        // 2. 参数验证成功，写入日志中
        payLog(verifySignatureMap);
        String analysisCode = verifySignatureMap.get("analysisCode");
        if (!analysisCode.equals(String.valueOf(PayConstant.RESULT_PAY_CODE_200))) {
            return resultFail();
        }
        // 3. 执行回调异步相关逻辑
        return asyncService(verifySignatureMap);
    }

    /**
     * 使用多线程异步写入日志
     */
    @Async
    protected void payLog(Map<String, String> verifySignatureMap) {
        log.info(">>>>>>>>>>第二步 写入payLog........{}", verifySignatureMap);
    }


    /**
     * 实现业务解析操作
     *
     * @param verifySignatureMap
     * @return
     */
    protected abstract String asyncService(Map<String, String> verifySignatureMap);

    /**
     * 异步返回成功结果
     *
     * @return
     */
    protected abstract String resultSuccess();


    /**
     * 异步返回失败结果
     *
     * @return
     */
    protected abstract String resultFail();

    /**
     * 支付回调验证参数
     *
     * @return
     */
    protected abstract Map<String, String> verifySignature();
}
