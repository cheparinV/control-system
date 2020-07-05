package org.demo.project.controlsystem;

import org.demo.project.controlsystem.config.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ServiceConfiguration.class)
public class ControlSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlSystemApplication.class, args);
	}

}
