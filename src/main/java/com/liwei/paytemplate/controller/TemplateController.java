package com.liwei.paytemplate.controller;

import com.liwei.paytemplate.factory.TemplateFactory;
import com.liwei.paytemplate.template.AbstractPayCallbackTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liwei
 * @create: 2020/3/18 8:51 下午
 */
@RestController
public class TemplateController {
    @RequestMapping("/asyncCallBack")
    public String asyncCallBack(String templateId) {
        AbstractPayCallbackTemplate payCallbackTemplate = TemplateFactory.getPayCallbackTemplate(templateId);
        return payCallbackTemplate.asyncCallBack();
    }
}