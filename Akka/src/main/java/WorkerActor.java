import akka.actor.UntypedActor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hunky on 06.12.2015.
 */
public class WorkerActor extends UntypedActor {

    Map sum = new HashMap();

    private String getId(String s) {
        return s.substring(0, s.indexOf(";"));
    }

    private String getAmount(String s) {
        return s.substring(s.lastIndexOf(";") + 1);
    }

    @Override
    public void onReceive(Object o) throws Exception {
        sum.put(getId((String) o), sum.get(getId((String) o) + getAmount(getAmount((String) o))));

//        clientActor.tell("", clientActor);
    }
}