package com.game.notification.mail;

import com.game.notification.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 *
 *
 * @Author Vishal Upadhyay
 * @Since 9/04/2014
 * @Company Target
 * @project Notification
 */

public class MailJobManager {

    private static Logger logger = LoggerFactory.getLogger(MailJobManager.class);
    private int threadCount = 10;
    private static MailJobManager instance;
    private ExecutorService executorService;

    public static MailJobManager getInstance() throws InterruptedException{
        synchronized (MailJobManager.class){
            if(null == instance){
                instance = new MailJobManager();
            }
        }
        return instance;
    }

    private MailJobManager(){
        executorService = Executors.newFixedThreadPool(threadCount);
    }


    private void shutdown() throws InterruptedException{
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }

    public void makeRequest(Message message){
        logger.info("Making mailer request");
        MailJob job = new MailJob(message);
        executorService.submit(job);
    }

}
