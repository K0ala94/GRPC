package grpctest.grpcclient.services;

import catsGrpc.Cat;
import catsGrpc.ListCatsRp;
import catsGrpc.ListCatsRq;
import grpctest.grpcclient.config.Config;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static catsGrpc.CatsGrpc.*;

@Component
public class CatsServiceClient {

    private final Logger LOGGER = LoggerFactory.getLogger(CatsServiceClient.class.getName());

    @Autowired
    private Config config;

    public List<Cat> listCats() throws InterruptedException {
        System.out.println("CatsServer: " + config.getCatsServer());
        ManagedChannel channel = ManagedChannelBuilder.forTarget(config.getCatsServer()).usePlaintext().build();
        try {
            CatsBlockingStub blockingStub = newBlockingStub(channel);

            ListCatsRq rq = ListCatsRq.newBuilder().build();
            ListCatsRp rp;
            rp = blockingStub.listCats(rq);
            rp.getCatsList().stream().map(c -> c.toString()).forEach(LOGGER::info);

            return rp.getCatsList();
        } finally{
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

}
