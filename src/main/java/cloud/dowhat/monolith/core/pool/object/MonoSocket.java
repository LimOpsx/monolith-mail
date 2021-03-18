package cloud.dowhat.monolith.core.pool.object;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author LimOps
 */
public class MonoSocket extends ServerSocket {

    @Getter
    @Setter
    private Boolean active;

    public MonoSocket(int port) throws IOException {
        super(port);
    }

    public MonoSocket() throws IOException {
        super();
    }


    public void destroy(){

    }

    public boolean isActive() {
        return false;
    }
}
