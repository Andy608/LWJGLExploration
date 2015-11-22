package com.bountive.window.callback;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import com.bountive.window.input.KeyManager;

public class KeyCallback extends GLFWKeyCallback {

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		KeyManager.updateKeys(window, key, action);
		
		if (KeyManager.quit) {
			GLFW.glfwSetWindowShouldClose(window, GL11.GL_TRUE);
		}
	}
}
