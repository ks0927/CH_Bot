package CyberHanryang;

import CyberHanryang.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChBotApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ChBotApplication.class, args);

		BotConfig botConfig = run.getBean(BotConfig.class);

		try {
			botConfig.setup();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
