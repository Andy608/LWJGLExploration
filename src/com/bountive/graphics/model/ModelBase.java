package com.bountive.graphics.model;

public class ModelBase {

	protected int vaoID;
	protected int vertexCount;
	
	public ModelBase(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
}
