package banquemisr.challenge05.task_mangment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "banquemisr.challenge05.task_mangment.Repo")
public class BanqueMisrTaskManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueMisrTaskManagementApplication.class, args);
	}
	

	 @Bean
	    public CommandLineRunner customBanner() {
	        return args -> {
	            System.out.println("******************************************");
	            System.out.println("*            Banque Misr Task            *");
	            System.out.println("******************************************");
	        };
	    }

}
