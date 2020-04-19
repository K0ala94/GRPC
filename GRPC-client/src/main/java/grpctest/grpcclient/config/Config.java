package grpctest.grpcclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "grpc")
public class Config {

    private String catsserver;

    public String getCatsServer() {
        return catsserver;
    }

    public void setCatsServer(String catsServer) {
        System.out.println(catsServer);
        this.catsserver = catsServer;
    }
}
