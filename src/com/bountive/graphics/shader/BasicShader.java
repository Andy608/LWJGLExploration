package com.bountive.graphics.shader;

import math.Matrix4f;

import com.bountive.graphics.entity.EntityCamera;
import com.bountive.util.MatrixUtil;

public class BasicShader extends AbstractShader {

	private int transformationMatrixID;
	private int projectionMatrixID;
	private int viewMatrixID;
//	private int playerPositionID;
	
	public BasicShader() {
		super("com/bountive/graphics/shader/basic_shader.vs", "com/bountive/graphics/shader/basic_shader.fs");
	}

	@Override
	protected void bindUniformLocationValues() {
		transformationMatrixID = super.getUniformLocation("transformationMatrix");
		projectionMatrixID = super.getUniformLocation("projectionMatrix");
		viewMatrixID = super.getUniformLocation("viewMatrix");
		//playerPositionID = super.getUniformLocation("playerPosition");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(transformationMatrixID, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(projectionMatrixID, matrix);
	}
	
	public void loadViewMatrix(EntityCamera camera) {
		super.loadMatrix(viewMatrixID, MatrixUtil.createViewMatrix(camera));
	}
	
//	public void loadPlayerPosition(Vector3f position) {
//		super.loadVector(playerPositionID, position);
//	}
}
