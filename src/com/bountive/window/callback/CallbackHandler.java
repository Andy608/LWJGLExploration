package com.bountive.window.callback;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.glfw.GLFWWindowFocusCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

import com.bountive.util.Disposable;

public class CallbackHandler implements Disposable {

	private GLFWErrorCallback errorCallback;
	private GLFWWindowSizeCallback windowSizeCallback;
	private GLFWWindowCloseCallback windowCloseCallback;
	private GLFWWindowFocusCallback windowFocusCallback;
	private GLFWKeyCallback keyCallback;
	private GLFWMouseButtonCallback mouseButtonCallback;
	private GLFWCursorPosCallback cursorPositionCallback;
	
	public CallbackHandler(long windowID) {
		GLFW.glfwSetErrorCallback(errorCallback = Callbacks.errorCallbackPrint(System.err));
		GLFW.glfwSetWindowSizeCallback(windowID, windowSizeCallback = new WindowSizeCallback());
		GLFW.glfwSetWindowCloseCallback(windowID, windowCloseCallback = new WindowCloseCallback());
		GLFW.glfwSetWindowFocusCallback(windowID, windowFocusCallback = new WindowFocusCallback());
		GLFW.glfwSetKeyCallback(windowID, keyCallback = new KeyCallback());
		GLFW.glfwSetMouseButtonCallback(windowID, mouseButtonCallback = new MouseButtonCallback());
		GLFW.glfwSetCursorPosCallback(windowID, cursorPositionCallback = new CursorPositionCallback());
	}

	@Override
	public void dispose() {
		errorCallback.release();
		windowSizeCallback.release();
		windowCloseCallback.release();
		keyCallback.release();
		mouseButtonCallback.release();
		cursorPositionCallback.release();
	}
}
