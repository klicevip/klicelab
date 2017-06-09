package klicelab.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by xieshengxin on 2017/6/9.
 */
@Component
public class SendRegisterEmailJob {
    Log log = LogFactory.getLog(SendRegisterEmailJob.class);
    TaskExecutor taskExecutor;

    public SendRegisterEmailJob(@Qualifier("sendRegisterEmailJobExecutor") TaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
    }

    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void work() throws InterruptedException {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("work invoked "+ Thread.currentThread().getId());
            }
        });
    }
}
