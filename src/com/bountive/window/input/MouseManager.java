package com.bountive.window.input;

import com.bountive.start.Exploration;
import com.bountive.window.WindowOptions;

public class MouseManager {

	private static double mouseX;
	private static double mouseY;
	private static double mouseCenterOffsetX;
	private static double mouseCenterOffsetY;
	
	public static void updateMousePosition(long windowID, double x, double y) {
		WindowOptions options = Exploration.getExploration().getWindow().getWindowOptions();
		mouseX = x;
		mouseY = y;
		
		mouseCenterOffsetX = (x - (options.getWidth() / 2));
		mouseCenterOffsetY = (y - (options.getHeight() / 2));
	}
	
	public static double getOffsetX() {
		return mouseCenterOffsetX;
	}
	
	public static double getOffsetY() {
		return mouseCenterOffsetY;
	}
	
	public static double getPosX() {
		return mouseX;
	}
	
	public static double getPosY() {
		return mouseY;
	}
}
