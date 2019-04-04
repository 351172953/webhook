package com.xesapp.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@EnableAsync
@SpringBootApplication
public class WebhookApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebhookApplication.class, args);
	}

}
