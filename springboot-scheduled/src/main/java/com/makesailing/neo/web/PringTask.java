package com.makesailing.neo.web;

import java.util.Date;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/9/8 15:07
 */
@Component
public class PringTask {

	/**
	 * 每5秒执行一次
	 * 输出如下 :
	 * 执行测试cron时间Sat Sep 08 16:49:25 CST 2018
	   执行测试cron时间Sat Sep 08 16:49:30 CST 2018
	   执行测试cron时间Sat Sep 08 16:49:35 CST 2018
	   执行测试cron时间Sat Sep 08 16:49:40 CST 2018
	   执行测试cron时间Sat Sep 08 16:49:45 CST 2018
	 */
	//@Scheduled(cron = "0/5 * * * * *")
	public void cron() {
		System.out.println("执行测试cron时间" + new Date(System.currentTimeMillis()));
	}

	/**
	 * 是上一次调用开始再次调用的延时(不用等待上次的调用完成)
	 * 输出如下:
	 * 执行测试fixedRate时间Sat Sep 08 16:51:42 CST 2018
	   执行测试fixedRate时间Sat Sep 08 16:51:44 CST 2018
	   执行测试fixedRate时间Sat Sep 08 16:51:46 CST 2018
	   执行测试fixedRate时间Sat Sep 08 16:51:48 CST 2018
	   执行测试fixedRate时间Sat Sep 08 16:51:50 CST 2018
	 * @throws InterruptedException
	 */
	//@Scheduled(fixedRate = 1000 * 1)
	public void fixedRate() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("执行测试fixedRate时间" + new Date(System.currentTimeMillis()));
	}

	/**
	 * 是上一次调用完成后再次调用的延时
	 * @throws InterruptedException
	 */
	//@Scheduled(fixedDelay = 1000 * 1)
	public void fixedDelay() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("执行测试fixedDelay时间" + new Date(System.currentTimeMillis()));
	}

	/**
	 * 第一次执行延迟时间
	 * @throws InterruptedException
	 */
	@Scheduled(initialDelay = 1000 * 10,fixedDelay = 1000* 2)
	public void initialDelay() throws InterruptedException {
		Thread.sleep(3000);
		System.out.println("执行测试initialDelay时间" + new Date(System.currentTimeMillis()));
	}

}


