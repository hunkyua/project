package sqlcmd;

import java.sql.Connection;

/**
 * Created by oktopus on 10.10.2015.
 */
public interface Connector {
    Connection get();
    boolean connect();
}
