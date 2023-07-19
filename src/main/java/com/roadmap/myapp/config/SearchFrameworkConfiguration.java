package com.roadmap.myapp.config;

import com.owse.searchFramework.builder.ExcelBuilder;
import com.owse.searchFramework.rest.SearchController;
import com.owse.searchFramework.service.EmailService;
import com.owse.searchFramework.service.ExportFileService;
import com.owse.searchFramework.service.MessageService;
import com.roadmap.myapp.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class SearchFrameworkConfiguration {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private MessageSource messageSource;

    @Bean
    public SearchController searchController() {
        return new SearchController(beanFactory, exportFileService());
    }

    @Bean
    ExcelBuilder excelBuilder() {
        return new ExcelBuilder();
    }

    @Bean
    MessageService messageService() {
        return new MessageService(messageSource);
    }

    @Bean
    EmailService emailService() {
        return new EmailService(javaMailSender, templateEngine, messageService());
    }

    @Bean
    ExportFileService exportFileService() {
        return new ExportFileService(excelBuilder(), emailService());
    }

    @Bean("userClass")
    Class getUserClass() {
        return User.class;
    }
}
