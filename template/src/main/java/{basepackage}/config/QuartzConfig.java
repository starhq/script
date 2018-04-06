package ${basepackage}.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务配置
 *
<%include("/java_description.include"){}%>
 */
@Configuration
@ComponentScan("${basepackage}.service.impl")
public class QuartzConfig {

    @Autowired
    private Environment environment;

    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
        MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
        obj.setTargetBeanName("toMakeCardService");
        obj.setTargetMethod("getYesterdayTomakeCard");
        return obj;
    }

//    @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
//        SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
//        stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
//        stFactory.setStartDelay(3000);
//        stFactory.setRepeatInterval(30000);
//        stFactory.setRepeatCount(3);
//        return stFactory;
//    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        stFactory.setCronExpression(environment.getProperty("cron"));
        return stFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(cronTriggerFactoryBean().getObject());
        return scheduler;
    }
}
