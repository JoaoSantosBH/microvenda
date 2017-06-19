/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory; 
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;
import util.AgenteVerificadorInatividade;

/**
 *
 * @author joaosantos
 */
public class InitJobVerificacaoInatividade {

    public static final String INATIVIDADE_JOB = "inativJob";

    public static void inicio() throws SchedulerException, InterruptedException {

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(AgenteVerificadorInatividade.class)
                .withIdentity(INATIVIDADE_JOB, "groupInativo")
                .build();

        //        #script de backup TODO DIA AS 3 DA MANHA
        //* 3 * * * root /root/backup.sh
        CronTrigger trigger = newTrigger()
                .withIdentity("triggerInativo", "groupInativo")
                .withSchedule(cronSchedule("0 50 4 * * ?"))
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);

        sched.start();
        Thread.sleep(10L * 1000L);
//        sched.shutdown(true);

            // Verificando se a tarefa encontra-se agendada,
        // para não registrá-la mais de uma vez.
//         


    }
}
