import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

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
        try (PrintWriter out = new PrintWriter(new File("1000.txt"))) {
            Files.lines(Paths.get("c:/100000.txt"))
                    .map(s -> s.split(";"))
                    .collect(Collectors.groupingBy(a -> a[0],
                            Collectors.summingInt(a -> Integer.parseInt(a[1]))))
                    .forEach((k,v) -> out.println(k + ";" + v));
        }
        br.close();
    }

    @Override
    public void onReceive(Object o) throws Exception {

    }
}