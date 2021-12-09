package com.hr.hr_company;

import com.hr.hr_common.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.hr")
@EntityScan("com.hr")
public class HrCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrCompanyApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
