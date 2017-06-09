package klicelab.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by xieshengxin on 2017/6/9.
 */
@Component
public class ExperimentJob {
    Log log = LogFactory.getLog(ExperimentJob.class);
    TaskExecutor taskExecutor;

    public ExperimentJob(@Qualifier("experimentJobExecutor") TaskExecutor taskExecutor){
        this.taskExecutor = taskExecutor;
    }

    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void CaculateRank() throws InterruptedException {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("CaculateRank invoked "+ Thread.currentThread().getId());
            }
        });
    }

    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void GenerateDailyReport() throws InterruptedException {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("GenerateDailyReport invoked "+ Thread.currentThread().getId());
            }
        });
    }

    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void GenerateWeeklyReport() throws InterruptedException {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("GenerateWeeklyReport invoked "+ Thread.currentThread().getId());
            }
        });
    }

    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void GenerateMonthlyReport() throws InterruptedException {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("GenerateMonthlyReport invoked "+ Thread.currentThread().getId());
            }
        });
    }
}
