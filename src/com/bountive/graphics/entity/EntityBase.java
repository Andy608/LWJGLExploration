package com.bountive.graphics.entity;

import math.Vector3f;

import com.bountive.graphics.model.ModelBase;

//CLASS WILL BE ABSTRACT
public class EntityBase {

	private ModelBase model;
	private Vector3f position;
	private Vector3f rotation;
	private Vector3f scale;
	
	public EntityBase(ModelBase modelType, Vector3f pos, Vector3f rot, Vector3f scal) {
		model = modelType;
		position = pos;
		rotation = rot;
		scale = scal;
	}

	public void moveEntity(float dx, float dy, float dz) {
		position.x += dx;
		position.y += dy;
		position.z += dz;
	}
	
	public void rotateEntity(float dx, float dy, float dz) {
		rotation.x = (rotation.x + dx) % 360;
		rotation.y = (rotation.y + dy) % 360;
		rotation.z = (rotation.z + dz) % 360;
	}
	
	public ModelBase getModel() {
		return model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
}
