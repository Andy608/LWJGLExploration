package com.bountive.window.callback;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseButtonCallback extends GLFWMouseButtonCallback {
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		
		if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && action == GLFW.GLFW_RELEASE) {
			System.out.println("Click!");
		}
	}
}
