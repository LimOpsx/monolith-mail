package cloud.dowhat.monolith.core.pool;

import cloud.dowhat.monolith.core.pool.object.MonoSocket;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author linen
 */
public class MonoSocketObjectPool extends GenericObjectPool<MonoSocket> {
    public MonoSocketObjectPool(PooledObjectFactory<MonoSocket> factory) {
        super(factory);
    }

    public MonoSocketObjectPool(PooledObjectFactory<MonoSocket> factory, GenericObjectPoolConfig<MonoSocket> config) {
        super(factory, config);
    }

    public MonoSocketObjectPool(PooledObjectFactory<MonoSocket> factory, GenericObjectPoolConfig<MonoSocket> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
