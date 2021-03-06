package com.dhcc.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
@MapperScan("com.dhcc.template.mapper")
@EnableTransactionManagement  //开启事务支持
public class AddContractAndOptionApplication
{
    public static void main(String[] args){
        SpringApplication.run(AddContractAndOptionApplication.class, args);
    }
}
