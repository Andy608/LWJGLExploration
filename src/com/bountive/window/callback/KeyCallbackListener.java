package com.bountive.window.callback;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

public class KeyCallbackListener extends GLFWKeyCallback {

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
			GLFW.glfwSetWindowShouldClose(window, GL11.GL_TRUE);
		}
	}
}
