package com.baeun;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@MapperScan(value = {"com.baeun"})
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] arrResource = new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*Mapper.xml");
        sqlSessionFactoryBean.setMapperLocations(arrResource);
        Objects.requireNonNull(sqlSessionFactoryBean.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://localhost:5501").allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] EXCLUDE_PATHS = {
                "/about",
                "/info",
                "/signin",
                "/signin-idchk"
        };
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns(EXCLUDE_PATHS);
    }
}
