package com.bountive.window;

public class WindowOptions {

	private int viewportWidth;
	private int viewportHeight;
	
	public WindowOptions(int width, int height) {
		setViewport(width, height);
	}
	
	public void setViewport(int width, int height) {
		viewportWidth = width;
		viewportHeight = height;
	}
	
	public int getWidth() {
		return viewportWidth;
	}
	
	public int getHeight() {
		return viewportHeight;
	}
}
