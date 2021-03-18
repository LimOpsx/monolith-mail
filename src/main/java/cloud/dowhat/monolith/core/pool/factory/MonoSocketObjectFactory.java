package cloud.dowhat.monolith.core.pool.factory;

import cloud.dowhat.monolith.core.pool.object.MonoSocket;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * @author linen
 */
@Configurable
public class MonoSocketObjectFactory implements PooledObjectFactory<MonoSocket> {

    @Override
    public PooledObject<MonoSocket> makeObject() throws Exception {
        return new DefaultPooledObject<>(new MonoSocket());
    }

    @Override
    public void destroyObject(PooledObject<MonoSocket> pooledObject) throws Exception {
        pooledObject.getObject().destroy();
    }

    @Override
    public boolean validateObject(PooledObject<MonoSocket> pooledObject) {
        return pooledObject.getObject().isActive();
    }

    @Override
    public void activateObject(PooledObject<MonoSocket> pooledObject) throws Exception {
        pooledObject.getObject().setActive(true);
    }

    @Override
    public void passivateObject(PooledObject<MonoSocket> pooledObject) throws Exception {
        //todo
    }
}
