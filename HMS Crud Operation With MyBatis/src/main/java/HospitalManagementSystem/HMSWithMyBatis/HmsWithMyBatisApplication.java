package HospitalManagementSystem.HMSWithMyBatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("HospitalManagementSystem.HMSWithMyBatis.Mapper")

public class HmsWithMyBatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmsWithMyBatisApplication.class, args);
	}

}
