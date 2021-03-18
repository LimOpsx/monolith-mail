package cloud.dowhat.monolith.core.config;

import cloud.dowhat.monolith.core.pool.MonoSocketObjectPool;
import cloud.dowhat.monolith.core.pool.factory.MonoSocketObjectFactory;
import cloud.dowhat.monolith.core.pool.object.MonoSocket;
import cloud.dowhat.monolith.core.prop.PoolProperties;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * @author linen
 */
@Configuration
public class MonoSocketPoolAutoConfiguration {

    private final PoolProperties poolProperties;

    private MonoSocketObjectPool pool;

    public MonoSocketPoolAutoConfiguration(PoolProperties poolProperties) {
        this.poolProperties = poolProperties;
    }


    @ConditionalOnClass({MonoSocketObjectFactory.class})
    @Bean
    protected MonoSocketObjectPool monoSocketObjectPool(){
        MonoSocketObjectFactory objectFactory = new MonoSocketObjectFactory();
        //set pool params
        GenericObjectPoolConfig<MonoSocket> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(poolProperties.getMaxIdle());
        poolConfig.setMaxTotal(poolProperties.getMaxTotal());
        poolConfig.setMinIdle(poolProperties.getMinIdle());
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30);

        poolConfig.setJmxEnabled(false);
        //new pool
        pool = new MonoSocketObjectPool(objectFactory,poolConfig);
        initPool(poolProperties.getInitConn(),poolProperties.getMaxIdle());
        return pool;
    }

    /**
     * init obj to pool
     * @param initConn init count
     * @param maxIdle max idle count
     */
    private void initPool(Integer initConn, Integer maxIdle) {
        if (initConn <= 0) {
            return;
        }

        int size = Math.min(initConn, maxIdle);
        for (int i = 0; i < size; i++) {
            try {
                pool.addObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PreDestroy
    public void destroy(){
        if(pool!=null){
            pool.close();
        }
    }

}
