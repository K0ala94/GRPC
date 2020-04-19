package grpctest.grpcserver.service;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static catsGrpc.CatsGrpc.CatsImplBase;
import static catsGrpc.CatsOuterClass.*;

public class CatServiceImpl extends CatsImplBase {

    private final Logger LOGGER = LoggerFactory.getLogger(CatServiceImpl.class.getName());
    private List<Cat> cats;

    @Override
    public void listCats(ListCatsRq request, StreamObserver<ListCatsRp> responseObserver) {
        ListCatsRp  rp = ListCatsRp.newBuilder().addCats(
                Cat.newBuilder().setAge(2)
                        .setName("Lady")
                        .setFur(Fur.newBuilder()
                                .setColor("Grey")
                                .setPattern("With pink patches")
                                .build())
                        .build())
                .build();
        responseObserver.onNext(rp);
        responseObserver.onCompleted();
        LOGGER.info("Sending ListCatsRp: {}", rp);
    }
}
