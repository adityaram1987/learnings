package com.config.sample.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReadingPropertiesSpringApplication {

//	@Autowired
//	ReadPropertiesFile readPropertiesFile;
	
	@Bean
	MyBean myBean() {
        return new MyBean();
    }
	
	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(ReadingPropertiesSpringApplication.class, args);
//		ReadPropertiesFile readPropertiesFile1 = new ReadPropertiesFile();
//		readPropertiesFile1.testing();
//		MyBean myBean = context.getBean(MyBean.class);
//        myBean.startApplication();
	}
	
	private class MyBean {

        @Value("${app.name}")
        private String appTitle;

        public void startApplication() {
            System.out.printf("-- running application: %s --%n", appTitle);

        }
    }
//	public void run(String... args) {
//		
//		System.out.println("lksdlk");
//		readPropertiesFile.testing();
//
//	}

}
