package klicelab.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by xieshengxin on 2017/6/9.
 */
@Component
public class ExperimentJob {
    Log log = LogFactory.getLog(ExperimentJob.class);
    TaskExecutor taskExecutor;
    ExecutorService executorService;

    public ExperimentJob(@Qualifier("experimentJobExecutor") TaskExecutor taskExecutor, ExecutorService executorService) {
        this.taskExecutor = taskExecutor;
        this.executorService = executorService;
    }

    @Scheduled(initialDelay = 100L, fixedRate = 10000L)
    public void CaculateRank() throws InterruptedException {
        log.info("call CaculateRank " + Thread.currentThread().getId());
        Future<?> task = executorService.submit(new Runnable() {
            @Override
            public void run() {
                log.info("CaculateRank invoked " + Thread.currentThread().getId());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            task.get();
            log.info("CaculateRank done " + Thread.currentThread().getId());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    //    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void GenerateDailyReport() throws InterruptedException {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("GenerateDailyReport invoked " + Thread.currentThread().getId());
            }
        });
    }

    //    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void GenerateWeeklyReport() throws InterruptedException {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("GenerateWeeklyReport invoked " + Thread.currentThread().getId());
            }
        });
    }

    //    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void GenerateMonthlyReport() throws InterruptedException {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("GenerateMonthlyReport invoked " + Thread.currentThread().getId());
            }
        });
    }
}
