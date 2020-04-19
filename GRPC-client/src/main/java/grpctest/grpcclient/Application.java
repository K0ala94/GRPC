package grpctest.grpcclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import grpctest.grpcclient.services.CatsServiceClient;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Application {

    @Autowired
    private CatsServiceClient catsServiceClient;

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

    @EventListener
    private void callCats(ContextRefreshedEvent ctxStarted) throws InterruptedException {
        System.out.println("calling ListCats");
        catsServiceClient.listCats();
    }
}
