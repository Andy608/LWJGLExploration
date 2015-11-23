package com.bountive.window.input;

import java.util.LinkedHashSet;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import com.bountive.start.Exploration;

public final class KeyManager {

	public static boolean moveForward;
	public static boolean moveBackward;
	public static boolean moveLeft;
	public static boolean moveRight;
	
	public static boolean moveUp;
	public static boolean moveDown;
	
	public static boolean quit;
	private static boolean startQuit;
	
	public static boolean pause;
	public static boolean debug;
	
	private static LinkedHashSet<Integer> keys = new LinkedHashSet<>();
	
	public static void updateKeys(long windowID, int key, int action) {
		if (action == GLFW.GLFW_RELEASE) {
			keys.remove(key);
			
			switch (key) {
			case GLFW.GLFW_KEY_W: moveForward = false; break;
			case GLFW.GLFW_KEY_S: moveBackward = false; break;
			case GLFW.GLFW_KEY_A: moveLeft = false; break;
			case GLFW.GLFW_KEY_D: moveRight = false; break;
			
			case GLFW.GLFW_KEY_SPACE: moveUp = false; break;
			case GLFW.GLFW_KEY_LEFT_SHIFT: moveDown = false; break;
			case GLFW.GLFW_KEY_ESCAPE: pause = !pause; break;
			case GLFW.GLFW_KEY_F1: debug = !debug; break;
			}
			
			if (pause) {
				GLFW.glfwSetInputMode(windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
			}
			else {
				GLFW.glfwSetCursorPos(windowID, (Exploration.getExploration().getWindow().getWindowOptions().getWidth() / 2), (Exploration.getExploration().getWindow().getWindowOptions().getHeight() / 2));
				GLFW.glfwSetInputMode(windowID, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
			}
			
			if (debug) {
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			}
			else {
				GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			}
			
			if (startQuit && !(keys.contains(GLFW.GLFW_KEY_ESCAPE) && keys.contains(GLFW.GLFW_KEY_LEFT_SHIFT))) {
				startQuit = false;
				quit = true;
			}
		}
		else if (action == GLFW.GLFW_PRESS) {
			keys.add(key);
			switch (key) {
			case GLFW.GLFW_KEY_W: moveForward = true; break;
			case GLFW.GLFW_KEY_S: moveBackward = true; break;
			case GLFW.GLFW_KEY_A: moveLeft = true; break;
			case GLFW.GLFW_KEY_D: moveRight = true; break;
			
			case GLFW.GLFW_KEY_SPACE: moveUp = true; break;
			case GLFW.GLFW_KEY_LEFT_SHIFT: moveDown = true; break;
			}
			
			if (keys.contains(GLFW.GLFW_KEY_ESCAPE) && keys.contains(GLFW.GLFW_KEY_LEFT_SHIFT)) {
				startQuit = true;
			}
		}
	}
}
