//package com.nic.master.config;
//
//import org.hibernate.validator.HibernateValidator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//@Configuration
//public class ValidationConfig {
//
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
//        validatorFactoryBean.setProviderClass(HibernateValidator.class);
//        validatorFactoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
//        return validatorFactoryBean;
//    }
//}