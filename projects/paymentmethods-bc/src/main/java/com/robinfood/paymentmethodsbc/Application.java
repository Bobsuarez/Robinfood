package com.robinfood.paymentmethodsbc;

import com.robinfood.paymentmethodsbc.security.CipherUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        if (args.length > 0 && "generate-keys".equals(args[0])) {
            try {
                CipherUtility.printRSAKeyPair(2048);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
        SpringApplication.run(Application.class);
    }
}
