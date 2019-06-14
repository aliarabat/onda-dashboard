/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onda.dashboard.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author AMINE
 */
public class NumberUtil {

	private static final String CHAINE_VIDE = "";
	private static final Double ZERO = 0.00;

	public static Double toDouble(String value) {
		if (value == null || value.isEmpty()) {
			return 0D;
		} else {
			return Double.parseDouble(value);
		}
	}

	public static String toString(Integer value) {
		if (value == null) {
			return CHAINE_VIDE;
		} else {
			return String.valueOf(value);
		}
	}

	public static Integer toInteger(String value) {
		if (value == null || value.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(value);
		}
	}

	public static int toInt(String value) {
		if (value == null || value.isEmpty()) {
			return 0;
		} else {
			return Integer.parseInt(value);
		}
	}

	public static Long toLong(String value) {
		if (value == null || value.isEmpty()) {
			return 0L;
		} else {
			return Long.parseLong(value);
		}
	}

	public static String fromIntToString(Integer value) {
		if (value == null || value.toString().equals("")) {
			return CHAINE_VIDE;
		} else {
			return String.valueOf(value);
		}
	}

	public static Double scaleDoubletoTwo(Double number) {
		if (number == null || String.valueOf(number).equals("")) {
			return ZERO;
		} else {
			return new BigDecimal(number).setScale(2, RoundingMode.DOWN).doubleValue();
		}
	}

}
