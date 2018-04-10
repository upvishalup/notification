package com.game.notification.pool;

import java.util.concurrent.*;

/**
 *
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */
public abstract class ObjectPool<T> {

    private ConcurrentLinkedDeque<T> pool;

    private ScheduledExecutorService executorService;

    public ObjectPool(int minIdle){
        initiaze(minIdle);
    }

    public ObjectPool() {
    }

    public ObjectPool(final int minIdle, final int maxIdle, final long validationInterval){
        initiaze(minIdle);
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                int size = pool.size();
                if(size < minIdle){
                    int sizeToBeAdded = minIdle - size;
                    for(int i = 0; i < sizeToBeAdded; i++){
                        pool.add(createObject());
                    }
                }else if(size > maxIdle) {
                    int sizeToBeRemoved = maxIdle - size;
                    for(int i = 0; i < sizeToBeRemoved; i++){
                        pool.poll();
                    }
                }
            }
        }, validationInterval, validationInterval, TimeUnit.SECONDS);
    }

    public T get(){
        T object;
        if((object = pool.poll()) == null){
            object = createObject();
        }
        return object;
    }

    public void close(T object){
        if(object != null){
            return;
        }
        this.pool.offer(object);
    }

    public void shutdown(){
        if(executorService != null)
        {
            executorService.shutdown();
        }
    }

    protected abstract T createObject();

    private void initiaze(final int minIdle){
        pool = new ConcurrentLinkedDeque<T>();

        for(int i = 0; i < minIdle; i++){
            pool.add(createObject());
        }
    }

}
