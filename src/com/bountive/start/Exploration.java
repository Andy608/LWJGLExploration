package com.bountive.start;

import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.bountive.graphics.model.TriangleModel;
import com.bountive.graphics.shader.BasicShader;
import com.bountive.util.Disposable;
import com.bountive.window.Window;

public class Exploration implements Disposable {
	
	private static final int TICKS_PER_SECOND = 60;
	private static final double TIME_SLICE = 1 / (double)TICKS_PER_SECOND;
	private static final double LAG_CAP = 0.15d;
	
	private int tickCount;
	private int frameCount;
	
	private double lastTime;
	private double currentTime;
	private double deltaTime;
	private double elapsedTime;
	
	private static Exploration instance;
	private Window window;
	
	//TODO: TEMPORARY
	private BasicShader shader;
	private TriangleModel t;
	///////////////////
	
	private void run() {
		System.out.println("Hello LWJGL! This is Version: " + Sys.getVersion() + ".");
		
		try {
			window = new Window();
			window.init();
			GL.createCapabilities();
			loop();
			
		} finally {
			dispose();
		}
	}
	
	private void loop() {
		shader = new BasicShader();
		t = new TriangleModel();
		
		lastTime = GLFW.glfwGetTime();
		
		while (GLFW.glfwWindowShouldClose(window.getID()) == GL11.GL_FALSE) {
			currentTime = GLFW.glfwGetTime();
			deltaTime = currentTime - lastTime;
			lastTime = currentTime;
			elapsedTime += deltaTime;
			
			if (elapsedTime >= LAG_CAP) {
				elapsedTime = LAG_CAP;
			}
			
			while (elapsedTime >= TIME_SLICE) {
				elapsedTime -= TIME_SLICE;
				update();
			}
			render((float)(elapsedTime / TIME_SLICE));
		}
	}
	
	private void update() {
		GLFW.glfwPollEvents();
		tickCount++;
		
		if (tickCount % 60 == 0) {
			System.out.println("Ticks: " + tickCount + ", Frames: " + frameCount);
			tickCount = 0;
			frameCount = 0;
		}
	}
	
	private void render(float lerp) {
		frameCount++;
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		//TODO: TEMPORARY
		shader.bind();
		t.render();
		shader.unbind();
		/////////////////
		
		GLFW.glfwSwapBuffers(window.getID());
	}
	
	public static void main(String[] args) {
		instance = new Exploration();
		instance.run();
	}
	
	public static Exploration getExploration() {
		return instance;
	}
	
	public void dispose() {
		window.dispose();
		shader.dispose();
		t.dispose();
	}
}
