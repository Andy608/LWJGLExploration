package com.bountive.util;

public class MathsUtil {

	public static float clamp(float input, float min, float max) {
		return Math.max(min, Math.min(input, max));
	}
}
