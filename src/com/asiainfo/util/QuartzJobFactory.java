package com.asiainfo.util;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("����ɹ�����");
        ScheduleJob scheduleJob =(ScheduleJob)context.getMergedJobDataMap().get("scheduleJob");
        System.out.println("�������� = [" + scheduleJob.getJobName() + "]");
	}

}