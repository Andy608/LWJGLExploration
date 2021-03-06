package com.bountive.start;

import java.util.Random;
import math.Vector3f;

import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import com.bountive.graphics.entity.EntityBase;
import com.bountive.graphics.entity.EntityCamera;
import com.bountive.graphics.model.ModelLoader;
import com.bountive.graphics.model.ModelSquare;
import com.bountive.graphics.render.EntityRenderer;
import com.bountive.graphics.shader.BasicShader;
import com.bountive.util.Disposable;
import com.bountive.window.Window;
import com.bountive.window.input.KeyManager;

public class Exploration implements Disposable {
	
	private static final int TICKS_PER_SECOND = 60;
	private static final double TIME_SLICE = 1 / (double)TICKS_PER_SECOND;
	private static final float LAG_CAP = 0.15f;
	
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
	private ModelSquare s;
	private EntityBase[][][] entitySquare;
	
	private EntityRenderer renderer;
	private EntityCamera camera;
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
			System.exit(0);
		}
		
		System.gc();
	}
	
	private void loop() {
		
		//TODO: TEMP
		ModelLoader.init();
		
		camera = new EntityCamera();
		
		shader = new BasicShader();
		renderer = new EntityRenderer(shader);
		s = new ModelSquare();
		
		int width, height, length;
		width = length = 16;
		height = 64;
		
		entitySquare = new EntityBase[width][height][length];
//		entitySquare[0] = new EntityBase(s.getModel(), new Vector3f(-1, 0, -2), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
//		entitySquare[1] = new EntityBase(s.getModel(), new Vector3f(1, 0, -2), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
//		entitySquare[2] = new EntityBase(s.getModel(), new Vector3f(0, -2, 0), new Vector3f(0, 0, 0), new Vector3f(20, 0.2f, 20));
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					entitySquare[x][y][z] = new EntityBase(s.getModel(), new Vector3f(x, y, z), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
				}
			}
		}
		
		GLFW.glfwSetInputMode(window.getID(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
		///////////////
		
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
				GLFW.glfwPollEvents();
				
				if (!KeyManager.pause) {
					update(TIME_SLICE);
				}
			}
			render((float)(elapsedTime / TIME_SLICE));
		}
	}
	
//	private float superTime;
	
//	private static final Random rand = new Random();
	
	private void update(double deltaTime) {
		tickCount++;
		
		if (tickCount % TICKS_PER_SECOND == 0) {
			System.out.println("Ticks: " + tickCount + ", Frames: " + frameCount);
			tickCount = 0;
			frameCount = 0;
		}
		
//		superTime += deltaTime;
		
		camera.update((float)deltaTime);
//		entitySquare[0].moveEntity((float)(Math.sin(superTime) / 20d), -(float)(Math.sin(superTime) / 120d), (float)(Math.cos(superTime) / 50d));
//		entitySquare[0].moveEntity((float)(Math.cos(superTime) / 10d), 0f, (float)(Math.sin(superTime) / 10d));
		
//		entitySquare[0].moveEntity((float)(Math.cos(superTime) / 20d), 0f, 0f);
		
//		entitySquare[0].moveEntity((float)(Math.cos(superTime) / 20d), 0f, (float)(Math.sin(superTime) / 20d));
//		entitySquare[0].moveEntity(0f, (float)((4 * Math.sin(4 * superTime)) / 40d), 0f);
		
//		entitySquare[1].moveEntity((float)(-Math.sin(superTime) / 50d), (float)(2 * Math.sin(2 * superTime) / 120d), (float)(-Math.cos(superTime) / 50d));
//		entitySquare[1].rotateEntity(0, 0, (float)(360 * deltaTime));
		
//		entitySquare[2].rotateEntity(0,  (float)(20 * deltaTime), 0);
		
		
//		entitySquare[0][0][0].scaleEntity((float)((1 * deltaTime)));
		
		for (int x = 0; x < entitySquare.length; x++) {
			for (int y = 0; y < entitySquare[x].length; y++) {
				for (int z = 0; z < entitySquare[x][y].length; z++) {
//					entitySquare[rand.nextInt(entitySquare.length)][rand.nextInt(entitySquare[x].length)][rand.nextInt(entitySquare[x][y].length)].moveEntity((float)(Math.cos(superTime)), 0, (float)(Math.sin(superTime)));
//					entitySquare[x][y][z].moveEntity((float)(Math.cos(superTime) / 20f), 0, (float)(Math.sin(superTime) / 20f));
				}
			}
		}
		
		GLFW.glfwSetCursorPos(window.getID(), (window.getWindowOptions().getWidth() / 2), (window.getWindowOptions().getHeight() / 2));
	}
	
	private void render(float lerp) {
		if (!KeyManager.pause)
			frameCount++;
		
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		//TODO: TEMPORARY
		shader.bind();
		
		//This method would ideally get called from a world that is holding a list of all the entities.
		shader.loadViewMatrix(camera);
		
//		renderer.render(entitySquare[0], lerp, shader);
//		renderer.render(entitySquare[1], lerp, shader);
//		renderer.render(entitySquare[2], lerp, shader);
		
		for (int x = 0; x < entitySquare.length; x++) {
			for (int y = 0; y < entitySquare[x].length; y++) {
				for (int z = 0; z < entitySquare[x][y].length; z++) {
					renderer.render(entitySquare[x][y][z], lerp, shader);
				}
			}
		}
		
//		t.render();
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
	
	public Window getWindow() {
		return window;
	}
	
	public void dispose() {
		window.dispose();
		shader.dispose();
		ModelLoader.loader.dispose();
	}
}