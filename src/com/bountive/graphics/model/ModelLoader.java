package com.bountive.graphics.model;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.bountive.util.Disposable;

public final class ModelLoader implements Disposable {

	public static ModelLoader loader;
	
	private List<Integer> vaoTracker;
	private List<Integer> vboTracker;
	
	private ModelLoader() {
		vaoTracker = new ArrayList<>();
		vboTracker = new ArrayList<>();
	}
	
	public static void init() {
		if (loader == null) {
			loader = new ModelLoader();
		}
	}
	
	public ModelBase createModel(float[] positions, int dataSize, float[] colors, int dataSize1) {
		int vaoID = createVao();
		bindDataToVao(0, positions, dataSize);
		bindDataToVao(1, colors, dataSize1);
		unbindVao();
		return new ModelBase(vaoID, positions.length / 3);
	}
	
	public ModelBase createModel(float[] positions, int[] indices, float[] colors) {
		int vaoID = createVao();
		bindIndicesBuffer(indices);
		bindDataToVao(0, positions, 3);
		bindDataToVao(1, colors, 4);
		unbindVao();
		return new ModelBase(vaoID, indices.length);
	}
	
	private int createVao() {
		int vaoID = GL30.glGenVertexArrays();
		vaoTracker.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void bindIndicesBuffer(int[] indices) {
		int vboIndicesID = GL15.glGenBuffers();
		vboTracker.add(vboIndicesID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboIndicesID);
		IntBuffer buffer = toReadableIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	public void bindDataToVao(int index, float[] data, int dataLength) {
		int vboDataID = GL15.glGenBuffers();
		vboTracker.add(vboDataID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboDataID);
		FloatBuffer buffer = toReadableFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, dataLength, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL20.glEnableVertexAttribArray(index);
	}
	
	private IntBuffer toReadableIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data).flip();
		return buffer;
	}
	
	private FloatBuffer toReadableFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data).flip();
		return buffer;
	}
	
	private void unbindVao() {
		GL30.glBindVertexArray(0);
	}

	@Override
	public void dispose() {
		for (int vao : vaoTracker) {
			GL30.glDeleteVertexArrays(vao);
		}
		
		for (int vbo : vboTracker) {
			GL15.glDeleteBuffers(vbo);
		}
	}
}
