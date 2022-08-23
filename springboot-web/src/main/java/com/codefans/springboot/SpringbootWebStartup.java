package com.codefans.springboot; /**
 * @Author: codefans
 * @Date: 2022-05-23 23:18
 */

import org.apache.catalina.connector.Connector;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

//@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass=true)
//@EnableTransactionManagement
//@ComponentScan(basePackageClasses = {ApolloCommonConfig.class})
@EnableDubbo
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.codefans"})
public class SpringbootWebStartup {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebStartup.class, args);
    }

    /**
     * http://localhost:8080/$%7B44664%2A40778%7D.action这么访问就会报Invalid character found in the request target异常。
     * 该方法就是为了解决 Invalid character found in the request target 异常的。
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedQueryChars", "|{}[]$&");
            }
        });
        return factory;
    }

}
