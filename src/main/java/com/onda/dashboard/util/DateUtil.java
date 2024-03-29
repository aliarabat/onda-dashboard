/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author AMINE
 */
public class DateUtil {

	public static String toString(Date dt) {
		if (dt == null) {
			return null;
		} else {
			return dt.toString();
		}
	}

	public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
		return LocalDateTime.of(fromDate(dateToConvert),
				LocalTime.of(dateToConvert.getHours(), dateToConvert.getMinutes(), dateToConvert.getSeconds()));
	}

	public static LocalTime fromString(String string) {
		if (string == null || string.equals("")) {
			return null;
		} else {
			LocalTime lt = LocalTime.parse(string);
			return lt;
		}
	}

	public static Date toDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		} else {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.of("GMT")));
			return Date.from(instant);
		}
	}

	public static LocalDate fromDate(java.util.Date date) {
		return asLocalDate(date, ZoneId.systemDefault());
	}

	public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
		if (date == null) {
			return null;
		}

		if (date instanceof java.sql.Date) {
			return ((java.sql.Date) date).toLocalDate();
		} else {
			return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
		}
	}

	public static String toString(Duration d) {
		if (d == null) {
			return null;
		} else {
			return d.toString();
		}

	}

	public static String toString(LocalDate ld) {
		if (ld == null) {
			return null;
		} else {
			return ld.toString();
		}

	}

	public static LocalDate fromStringToLocalDate(String date) {
		if (date == null || date.equals("")) {
			return null;
		} else {
			return LocalDate.parse(date);
		}
	}

	public static LocalDate getFirstMonday(DayOfWeek dayOfWeek) {
		LocalDate d = LocalDate.now();
		LocalDate d2 = d.with(TemporalAdjusters.dayOfWeekInMonth(1, dayOfWeek));
		return d2;
	}

	public static LocalDate getFirstDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		System.out.println(cal.getTime());
		return DateUtil.fromDate(cal.getTime());
	}

	public static Date getFirstDayOfMonth() {
		LocalDate localDate = LocalDate.of(getFirstDayOfWeek().getYear(), getFirstDayOfWeek().getMonth(), 1);
		return toDate(localDate);
	}

	public static Date getFirstDayOfMonthByYearAndMonth(int year, int month) {
		LocalDate localDate = LocalDate.of(year, month, 1);
		return toDate(localDate);
	}

	public static int lenghtOfMonth(LocalDate date) {
		return date.lengthOfMonth();
	}
}
