package com.bountive.graphics.model;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class ModelSquare {

	private ModelBase model;
	
//	private float[] vertices = new float[] {
//			-0.5f,  0.5f, 0f,	
//			-0.5f, -0.5f, 0f,	
//			 0.5f,  0.5f, 0f,	
//			 0.5f, -0.5f, 0f,
//	};
//	
//	private int[] indices = new int[] {
//			0, 1, 2,
//			2, 1, 3
//	};
//	
//	private float[] colors = new float[] {
//			1, 0, 0, 1,
//			0, 1, 0, 1,
//			0, 0, 1, 1,
//			1, 1, 0, 1,
//	};
	
	private float[] vertices = new float[] {
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,
			0.5f,0.5f,-0.5f,		
			
			-0.5f,0.5f,0.5f,	
			-0.5f,-0.5f,0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			0.5f,0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			-0.5f,-0.5f,0.5f,	
			-0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,
			
			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f
	};
	
	private int[] indices = new int[] {
			0,1,3,	
			3,1,2,	
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,	
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22
	};
	
	private float[] colors = new float[] {
			1, 0, 0, 1,
			0, 1, 0, 1,
			0, 0, 1, 1,
			1, 1, 0, 1,
			
			1, 0, 0, 1,
			0, 1, 0, 1,
			0, 0, 1, 1,
			1, 1, 0, 1,
			
			1, 0, 0, 1,
			0, 1, 0, 1,
			0, 0, 1, 1,
			1, 1, 0, 1,
			
			1, 0, 0, 1,
			0, 1, 0, 1,
			0, 0, 1, 1,
			1, 1, 0, 1,
			
			1, 0, 0, 1,
			0, 1, 0, 1,
			0, 0, 1, 1,
			1, 1, 0, 1,
			
			1, 0, 0, 1,
			0, 1, 0, 1,
			0, 0, 1, 1,
			1, 1, 0, 1,
	};
	
	public ModelSquare() {
		model = ModelLoader.loader.createModel(vertices, indices, colors);
	}
	
	//This isnt being called anywhere.
	public void render() {
		GL30.glBindVertexArray(model.getVaoID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		GL30.glBindVertexArray(0);
	}
	
	public ModelBase getModel() {
		return model;
	}
	
	public float[] getVertices() {
		return vertices;
	}
	
	public float[] getColors() {
		return colors;
	}
}
