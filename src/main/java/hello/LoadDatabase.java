package hello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import lombok.extern.slf4j.Slf4j;
//@Slf4j
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);


    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			System.out.println("###>>>Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
			log.info("###>>>Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
		};
	}
}
