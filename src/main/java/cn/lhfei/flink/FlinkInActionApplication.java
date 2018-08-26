package cn.lhfei.flink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FlinkInActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlinkInActionApplication.class, args);
	}
}
