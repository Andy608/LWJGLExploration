package com.bountive.graphics.model;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.bountive.util.Disposable;

public class TriangleModel implements Disposable {

	private int vaoID;
	private int vboID;
	private int vboColorID;
	private float[] vertices = new float[] {
		 0.0f,  0.8f,
		-0.8f, -0.8f,
		 0.8f, -0.8f
	};
	
	private float[] colors = new float[] {
	    1, 0, 0, 1,
	    0, 1, 0, 1,
	    0, 0, 1, 1
	};
	
	public TriangleModel() {
		vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);
		
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
		verticesBuffer.put(vertices).flip();
		
		vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
		
		// Point the buffer at location 0, the location we set
        // inside the vertex shader. You can use any location
        // but the locations should match
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 0, 0);
		
		//////////////////////////////
		
		FloatBuffer colorsBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorsBuffer.put(colors).flip();
		
		vboColorID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboColorID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colorsBuffer, GL15.GL_STATIC_DRAW);
		// Point the buffer at location 1, the location we set
        // inside the vertex shader. You can use any location
        // but the locations should match
        GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 0, 0);
        
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        
        GL30.glBindVertexArray(0);
	}
	
	public void render() {
		//Bind the vertex array and enable location 0 (position location set in vertex shader).
		GL30.glBindVertexArray(vaoID);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);
		GL30.glBindVertexArray(0);
	}

	@Override
	public void dispose() {
		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(vaoID);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vboColorID);
		GL15.glDeleteBuffers(vboID);
	}
}
