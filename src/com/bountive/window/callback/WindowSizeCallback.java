package com.bountive.window.callback;

import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;

import com.bountive.start.Exploration;
import com.bountive.util.MatrixUtil;

public class WindowSizeCallback extends GLFWWindowSizeCallback {

	@Override
	public void invoke(long window, int width, int height) {
		System.out.println("Window width: " + width + " | height: " + height);
		
		Exploration.getExploration().getWindow().getWindowOptions().setViewport(width, height);
		GL11.glViewport(0, 0, width, height);
		
		MatrixUtil.instance.createProjectionMatrix();
	}
}
