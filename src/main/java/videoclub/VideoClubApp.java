package videoclub;

import org.apache.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class VideoClubApp extends SpringBootServletInitializer {

	// Cargador que carga un logger en una clase -> Usado para los lectores de Logs
	public static final Logger logger = Logger.getLogger(VideoClubApp.class);

	public static void main(String[] args) {
		logger.info("Arrancamos");
		SpringApplication.run(VideoClubApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<VideoClubApp> applicationClass = VideoClubApp.class;
}
