package grpctest.grpcserver.grpcServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class ServerRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(ServerRunner.class.getName());

    @PostConstruct
    private void startServerAndAwaitTermination() throws IOException, InterruptedException {
        CatsServer.INSTANCE.start();
        CatsServer.INSTANCE.blockUntilShutdown();
    }
}
