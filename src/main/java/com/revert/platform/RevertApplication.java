package com.revert.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.revert"})
//@MapperScan(basePackages = {"com.revert"})
@EnableScheduling
public class RevertApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RevertApplication.class,args);
    }

    //war包需实现方法
    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder application) {
        return application.sources(RevertApplication.class);
    }

//    //手动指定logback路径，修复war包不能找到logback的问题
//    @Override
//    public void onStartup(final ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//        configureLogging(servletContext);
//    }
//
//    private void configureLogging(final ServletContext servletContext) {
//        try {
//            String realPath = Thread.currentThread().getContextClassLoader().getResource("config/logback.xml").getPath();
//            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//            JoranConfigurator configurator = new JoranConfigurator();
//            configurator.setContext(context);
//            context.reset();
//            configurator.doConfigure(realPath);
//        } catch (JoranException je) {
//        }
//    }

}
