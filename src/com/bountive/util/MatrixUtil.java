package com.bountive.util;

import math.Matrix4f;
import math.Vector3f;

import com.bountive.graphics.entity.EntityCamera;
import com.bountive.graphics.shader.BasicShader;
import com.bountive.start.Exploration;
import com.bountive.window.WindowOptions;

public final class MatrixUtil {

	public static MatrixUtil instance;
	
	private Matrix4f projectionMatrix;
	
	//THESE WILL NOT BE HERE EVENTUALLY
	private float FOV;
	private float NEAR_PLANE = 0.1f;
	private float FAR_PLANE  = 100f;
	
	private BasicShader shader;
	////////////////
	
	private static final Vector3f X_AXIS = new Vector3f(1, 0, 0);
	private static final Vector3f Y_AXIS = new Vector3f(0, 1, 0);
	private static final Vector3f Z_AXIS = new Vector3f(0, 0, 1);
	
	private MatrixUtil(BasicShader s) {
		FOV = 85;
		shader = s;
		createProjectionMatrix();
	}
	
	public static void init(BasicShader s) {
		if (instance == null) {
			instance = new MatrixUtil(s);
		}
	}
	
	public static Matrix4f createTransformationMatrtix(Vector3f translation, Vector3f rotation, Vector3f scale) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rotation.x), X_AXIS, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rotation.y), Y_AXIS, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(rotation.z), Z_AXIS, matrix, matrix);
		Matrix4f.scale(scale, matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(EntityCamera camera) {
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.rotate((float)Math.toRadians(camera.getPitch()), X_AXIS, matrix, matrix);
		Matrix4f.rotate((float)Math.toRadians(camera.getYaw()), Y_AXIS, matrix, matrix);
		Vector3f cameraPosition = camera.getPosition();
		Vector3f oppositePosition = new Vector3f(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z);
		Matrix4f.translate(oppositePosition, matrix, matrix);
		return matrix;
	}
	
	public void createProjectionMatrix() {
		
		WindowOptions windowOptions = Exploration.getExploration().getWindow().getWindowOptions();
		float aspectRatio = (float)windowOptions.getWidth() / (float)windowOptions.getHeight();
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = 1f / (float)(Math.tan(Math.toRadians(FOV) / 2f));
		projectionMatrix.m11 = aspectRatio / (float)(Math.tan(Math.toRadians(FOV) / 2f));
		projectionMatrix.m22 = -((NEAR_PLANE + FAR_PLANE) / FAR_PLANE - NEAR_PLANE);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / FAR_PLANE - NEAR_PLANE);
		projectionMatrix.m33 = 0;
		
		shader.bind();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.unbind();
	}
	
	public void setFOV(float value) {
		FOV = value;
		createProjectionMatrix();
	}
	
	public float getFOV() {
		return FOV;
	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
}
