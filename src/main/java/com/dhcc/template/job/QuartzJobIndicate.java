package com.dhcc.template.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * 定时任务job案例
 * @Author: 罗帅
 * @Date: 2020/11/12
 */
@Slf4j
@Service
public class QuartzJobIndicate extends QuartzJobBean {

    /**
     * 定时任务 样例
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("--------定时任务job执行----------");
    }
}
