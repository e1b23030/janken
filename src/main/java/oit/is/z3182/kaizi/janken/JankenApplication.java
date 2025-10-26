package oit.is.z3182.kaizi.janken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("oit.is.z3182.kaizi.janken.model")
public class JankenApplication {

	public static void main(String[] args) {
		SpringApplication.run(JankenApplication.class, args);
	}

}
