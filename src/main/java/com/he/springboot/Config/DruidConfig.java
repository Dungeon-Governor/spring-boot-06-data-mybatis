package com.he.springboot.Config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.ResourceServlet;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

//    1.配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
//        配置参数
        Map<String,String> map = new HashMap<>();
//        登陆用户名
        map.put("loginUsername","admin");
//        登陆密码
        map.put("loginPassword","123456");
//        允许登陆用户，不填就是默认允许所有人登陆
        map.put("allow","");
//        拒绝登陆的用户
        map.put("deny","");
        bean.setInitParameters(map);
        return bean;
    }

//    2.配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
//        设置参数
        Map<String,String> map = new HashMap<>();
//        设置不拦截哪些请求
        map.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(map);
//        设置拦截参数
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
