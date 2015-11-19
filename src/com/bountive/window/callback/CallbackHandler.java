package com.bountive.window.callback;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;

import com.bountive.util.Disposable;

public class CallbackHandler implements Disposable {

	private GLFWErrorCallback errorCallback;
	private GLFWWindowCloseCallback windowCloseCallback;
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;
	
	public CallbackHandler(long windowID) {
		GLFW.glfwSetErrorCallback(errorCallback = Callbacks.errorCallbackPrint(System.err));
		GLFW.glfwSetWindowCloseCallback(windowID, windowCloseCallback = new WindowCloseCallbackListener());
		GLFW.glfwSetKeyCallback(windowID, keyCallback = new KeyCallbackListener());
		GLFW.glfwSetMouseButtonCallback(windowID, mouseButtonCallback = new MouseButtonCallbackListener());
	}

	@Override
	public void dispose() {
		errorCallback.release();
		windowCloseCallback.release();
		keyCallback.release();
		mouseButtonCallback.release();
	}
}
