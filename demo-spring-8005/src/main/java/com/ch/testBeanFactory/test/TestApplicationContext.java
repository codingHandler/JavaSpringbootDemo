package com.ch.testBeanFactory.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ch
 * @date: 2022/8/14 14:54
 * @description: 常见ApplicationContext的实现
 */
@Slf4j
public class TestApplicationContext {
    public static void main(String[] args) {
        // testClassPathXmlApplicationContext();
        // testFileSystemXmlApplicationContext();
        // testAnnotationConfigApplicationContext();
         testAnnotationConfigServletWebServerAppliactionContext();

        // ClassPathXmlApplicationContext和 FileSystemXmlApplicationContext原理
        // principle();

    }

    /***
     * ClassPathXmlApplicationContext和 FileSystemXmlApplicationContext原理
     */
    static void principle() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        System.out.println("读取之前...");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("读取之后...");
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("bean.xml"));
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    private static void testAnnotationConfigServletWebServerAppliactionContext() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(WebConfig.class);
    }

    //@Configuration
    static class WebConfig {
        @Bean
        public ServletWebServerFactory servletWebServerFactory() {
            return new TomcatServletWebServerFactory();
        }

        @Bean
        public DispatcherServlet dispatcherServlet() {
            return new DispatcherServlet();
        }

        @Bean
        public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet) {
            return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        }

        @Bean("/hello")
        public Controller controller1() {
            return new Controller() {
                @Override
                public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
                    response.getWriter().print("Hello...");
                    return null;
                }
            };
        }
    }

    /***
     * 较为经典的容器,基于java配置类来创建
     */
    private static void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext beanFactory =
                new AnnotationConfigApplicationContext(Config.class);
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    /***
     * 基于磁盘路径下的xml格式的配置文件来创建
     */
    private static void testFileSystemXmlApplicationContext() {
        FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("src\\main\\resources\\bean.xml");
        for (String beanDefinitionName : fileSystemXmlApplicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    /***
     * 较为金典的容器,基于classpath下xml格式的配置文件来创建
     */
    private static void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 获取容器中所有的Bean定义名字
        for (String beanDefinitionName : classPathXmlApplicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }
    }

    static class Bean1 {
        @Autowired
        private Bean2 bean2;

        public Bean1() {
            log.info("构造 bean1");
        }


        public Bean2 getBean2() {
            return bean2;
        }
    }

    static class Bean2 {
        @Autowired
        private Bean1 bean1;

        public Bean2() {
            log.info("构造 bean2");
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }
}
