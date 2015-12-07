import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.io.*;


/**
 * Created by Hunky on 06.12.2015.
 */
public class ClientActor extends UntypedActor {


    ActorRef worker = getContext().actorOf(Props.create(WorkerActor.class));
    @Override
    public void preStart() throws Exception{
        FileInputStream fis = new FileInputStream(new File("c:/100000.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        while ((line = br.readLine()) != null) {
            worker.tell(line, getSelf());
        }
        br.close();
    }

    @Override
    public void onReceive(Object o) throws Exception {

    }
}