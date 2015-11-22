package com.bountive.window.callback;

import org.lwjgl.glfw.GLFWWindowCloseCallback;

public class WindowCloseCallback extends GLFWWindowCloseCallback {

	@Override
	public void invoke(long window) {
		//Save things on shutdown!!
	}
}
