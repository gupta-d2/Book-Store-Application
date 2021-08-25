package com.api.bookstore.app.config;

import com.api.bookstore.app.interceptor.BookStoreLoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BookStoreInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    BookStoreLoggerInterceptor bookStoreLoggerInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bookStoreLoggerInterceptor);
    }

}
