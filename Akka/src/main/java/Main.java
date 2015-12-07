import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Created by Hunky on 06.12.2015.
 */
public class Main {

    private static ActorSystem system;

    public static void main(String[] args) throws Exception{
        system = ActorSystem.create("ClientSystem");
        system.actorOf(Props.create(ClientActor.class));
        try (PrintWriter out = new PrintWriter(new File("1000.txt"))) {
            Files.lines(Paths.get("c:/100000.txt"))
                    .map(s -> s.split(";"))
                    .collect(Collectors.groupingBy(a -> a[0],
                            Collectors.summingInt(a -> Integer.parseInt(a[1]))))
                    .forEach((k,v) -> out.println(k + ";" + v));
        }

    }
}