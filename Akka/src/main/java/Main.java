import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by Hunky on 06.12.2015.
 */
public class Main {

    private static ActorSystem system;

    public static void main(String[] args) throws Exception{
        system = ActorSystem.create("ClientSystem");
        system.actorOf(Props.create(ClientActor.class));


    }
}