package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {//implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Bean
	public CommandLineRunner demo(CustomerRepository repository) {
    	return (args) -> {
			repository.save(new Customer("A firstName", "A"));
			repository.save(new Customer("B firstName", "B"));
			for (Customer customer : repository.findAll()) {
				log.info(">ALL>>>>>> " + customer.toString());
			}			
			repository.findByLastName("A").forEach(customer -> {
				log.info(">A>>>>>"+customer.toString());
			});

    	};
    }
}

