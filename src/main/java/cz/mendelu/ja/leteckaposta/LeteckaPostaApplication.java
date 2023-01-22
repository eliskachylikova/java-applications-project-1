package cz.mendelu.ja.leteckaposta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
public class LeteckaPostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeteckaPostaApplication.class, args);
	}

	@Bean
	CommandLineRunner jdbcTemplateTest(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		return args -> {
			int countries = jdbcTemplate.queryForObject("SELECT count(*) from countries", Integer.class);
			log.info("Countires count {}", countries);
		};
	}

}
