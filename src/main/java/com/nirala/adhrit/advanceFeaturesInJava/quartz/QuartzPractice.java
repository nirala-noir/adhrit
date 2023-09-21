package com.meta.verse.advanceFeaturesInJava.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class QuartzPractice {
    @Autowired
    private static SchedulerFactoryBean schedulerFactoryBean;
    public static void main(String[] args) {
        try {
            StdSchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();

            JobDetail jobDetail = JobBuilder
                    .newJob()
                    .ofType(HelloQuartz.class)
                    .withIdentity("Hello-job-name")
                    .build();

            SimpleTrigger simpleTrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("Hello-trigger-name")
                    .forJob(jobDetail)
                    .withSchedule(SimpleScheduleBuilder
                            .simpleSchedule()
                            .withIntervalInSeconds(1)
                            .withRepeatCount(5))
                    .build();
            schedulerFactoryBean.getScheduler().scheduleJob(jobDetail,simpleTrigger);
//            scheduler.scheduleJob(jobDetail, simpleTrigger);
//            scheduler.start();

            //run cron api using linux


        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
