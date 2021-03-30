package com.dhcc.template;

import com.dhcc.template.webservice.WebServiceContract;
import com.dhcc.template.webservice.WebServiceOption;
import com.gitee.starblues.realize.BasePlugin;
import com.gitee.starblues.realize.OneselfListener;
import com.gitee.starblues.utils.OrderPriority;
import lombok.extern.slf4j.Slf4j;
import org.minbox.framework.api.boot.plugin.quartz.ApiBootQuartzService;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * @author wangxiao
 */

@Slf4j
public class AddContractAndOptionPluginListener implements OneselfListener {

    @Autowired
    private ApiBootQuartzService quartzService;

    @Autowired
    com.dhcc.webservicepublish.endpoint.WebServicePublisher webServicePublisher;
    @Autowired

    private WebServiceContract webServiceContract;
    @Autowired
    private WebServiceOption webServiceOption;


    @Override
    public void startEvent(final BasePlugin basePlugin) {
        //发布webservice
        webServicePublisher.publish(webServiceContract, "/webServiceContract");
        log.info("发布webservice成功", webServiceContract == null ? "not fount" : webServiceContract);
        webServicePublisher.publish(webServiceOption, "/webServiceOption");
        log.info("发布webservice成功", webServiceOption == null ? "not fount" : webServiceOption);

        //每5秒执行一次定时任务
//        quartzService.newJob(ApiBootCronJobWrapper.Context().jobClass(QuartzJobIndicate.class).cron("0/5 * * * * ? ")
//                .param(ApiBootJobParamWrapper.wrapper().
//                        put("插件模板", "QuartzJobIndicate")).wrapper());

    }

    @Override
    public OrderPriority order() {
        return null;
    }

    @Override
    public void stopEvent(final BasePlugin basePlugin) {

    }
}
