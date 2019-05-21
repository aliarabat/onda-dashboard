package com.onda.dashboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onda.dashboard.util.DateUtil;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) throws ParseException {
		String sDate6 = "31-12-1998 23:37:50";
		SimpleDateFormat formatter6 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date6 = formatter6.parse(sDate6);
		Date today = new Date();
		@SuppressWarnings("deprecation")
		LocalDateTime ldt = LocalDateTime.of(DateUtil.fromDate(date6),
				LocalTime.ofSecondOfDay(date6.getTime()));
		System.out.println("hhaa localTime ==> " + ldt);

		SpringApplication.run(DashboardApplication.class, args);
	}

}
