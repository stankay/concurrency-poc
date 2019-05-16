package concurrency;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
@Asynchronous
public class FooProcessor {

    public void process() {
        ProofOfConcept.hashCodes.put(this.hashCode(), this.hashCode());

        try {
            //sleep to simulate long running task in order to utilize multiple EJBs from pool
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("There are " + ProofOfConcept.hashCodes.size() + " unique FooProcessors in total: " + ProofOfConcept.hashCodes.values());
    }
}
