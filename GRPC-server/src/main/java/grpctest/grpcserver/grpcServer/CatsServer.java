package grpctest.grpcserver.grpcServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import grpctest.grpcserver.service.CatServiceImpl;

import java.io.IOException;

public enum CatsServer {
    INSTANCE;

    private Logger LOGGER = LoggerFactory.getLogger(CatsServer.class.getName());
    private static final int PORT = 50051;

    private Server server;

    public void start() throws IOException{

        if(server == null) {
            server = ServerBuilder.forPort(PORT)
                    .addService(new CatServiceImpl())
                    .build()
                    .start();

            LOGGER.info("Server started, listening on " + PORT);
        }
        LOGGER.info("Server is already running on " + PORT);
    }

    public void blockUntilShutdown() throws InterruptedException {
        if(server != null){
            server.awaitTermination();
        }
    }
}
