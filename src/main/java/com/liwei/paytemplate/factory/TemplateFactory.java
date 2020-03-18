package com.liwei.paytemplate.factory;

import com.liwei.paytemplate.template.AbstractPayCallbackTemplate;
import com.liwei.paytemplate.utils.SpringUtils;

/**
 * 模板工厂
 *
 * @author liwei
 * @create: 2020/3/18 8:58 下午
 */
public class TemplateFactory {

    public static AbstractPayCallbackTemplate getPayCallbackTemplate(String templateId) {
        return (AbstractPayCallbackTemplate) SpringUtils.getBean(templateId);
    }

}
