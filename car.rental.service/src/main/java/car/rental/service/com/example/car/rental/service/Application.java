package car.rental.service.com.example.car.rental.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        String url = "http://localhost:8080/h2-console/login.jsp";
        String url2 = "http://localhost:8080/swagger-ui.html";
        openBrowser(url);
        openBrowser(url2);
    }

    private static void openBrowser(String url) {
        try {
            Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", url});
        } catch (Exception e) {
            System.out.println("Can not access browser.");
            e.printStackTrace();
        }
    }
}
