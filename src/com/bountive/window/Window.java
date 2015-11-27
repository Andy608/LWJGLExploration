package com.bountive.window;

import java.nio.ByteBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import com.bountive.util.Disposable;
import com.bountive.util.ExplorationData;
import com.bountive.window.callback.CallbackHandler;

public class Window implements Disposable {

	private CallbackHandler callbackHandler;
	
	private String windowTitle;
	private long windowID;
	
	private int width;
	private int height;
	
	private WindowOptions windowSettings;
	
	private ByteBuffer displayMode;
	
	public Window() {
		windowTitle = ExplorationData.TITLE + " | " + ExplorationData.VERSION + " | " + ExplorationData.AUTHOR;
	}
	
	public void init() {
		try {
			initGLFW();
			createWindow();
			callbackHandler = new CallbackHandler(windowID);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	private void initGLFW() throws IllegalStateException {
		System.out.println("Initializing GLFW! ... ");
		
		if (GLFW.glfwInit() == GL11.GL_FALSE) {
			throw new IllegalStateException("Unable to initialize GLFW.");
		}
	}
	
	private void createWindow() throws RuntimeException {
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE);
		
		displayMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		width = GLFWvidmode.width(displayMode) / 2;
		height = GLFWvidmode.height(displayMode) / 2;
		
		windowID = GLFW.glfwCreateWindow(width, height, windowTitle, MemoryUtil.NULL, MemoryUtil.NULL);
		windowSettings = new WindowOptions(width, height);
		
		
		if (windowID == MemoryUtil.NULL) {
			throw new RuntimeException("Failed to create GLFW window.");
		}
		
		setPosition((GLFWvidmode.width(displayMode) - width) / 2, (GLFWvidmode.height(displayMode) - height) / 2);
		
		GLFW.glfwMakeContextCurrent(windowID);
		GLFW.glfwSwapInterval(0); //v-sync
		GLFW.glfwShowWindow(windowID);
	}
	
	public void setPosition(int x, int y) {
		GLFW.glfwSetWindowPos(windowID, x, y);
	}
	
	public long getID() {
		return windowID;
	}
	
	public WindowOptions getWindowOptions() {
		return windowSettings;
	}

	@Override
	public void dispose() {
		callbackHandler.dispose();
		System.out.println("Disposing GLFW...");
		GLFW.glfwDestroyWindow(windowID);
		GLFW.glfwTerminate();
	}
}
