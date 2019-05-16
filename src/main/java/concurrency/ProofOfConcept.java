package concurrency;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class ProofOfConcept {

    @Inject FooProcessor fooProcessor;

    /**
     * Record hascodes of FooProcessors that were used
     */
    public static ConcurrentHashMap<Integer, Integer> hashCodes = new ConcurrentHashMap<>();

    @PostConstruct
    public void run() {
        for (int i = 0; i < 100; i++) {
            //this shall run concurrently, given the max pool size is set to 100
            fooProcessor.process();
        }
    }
}
