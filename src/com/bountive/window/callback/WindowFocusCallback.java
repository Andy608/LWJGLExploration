package com.bountive.window.callback;

import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.opengl.GL11;

import com.bountive.window.input.KeyManager;

public class WindowFocusCallback extends GLFWWindowFocusCallback {

	@Override
	public void invoke(long window, int focused) {

		if (focused == GL11.GL_FALSE) {
			KeyManager.pause = true;
		}
	}
}
