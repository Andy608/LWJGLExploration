package com.bountive.graphics.entity;

import math.Vector3f;

import com.bountive.graphics.model.ModelBase;

//CLASS WILL BE ABSTRACT
public class EntityBase {

	private ModelBase model;
	private Vector3f position;
	private Vector3f rotation;
	
	private Vector3f scale;
	
	private Vector3f prevPosition;
	private Vector3f prevRotation;
	private Vector3f prevScale;
	
//	private Vector3f lerpRotationNormal;
	
	public EntityBase(ModelBase modelType, Vector3f pos, Vector3f rot, Vector3f scal) {
		model = modelType;
		
		prevPosition = new Vector3f(pos);
		prevRotation = new Vector3f(rot);
		prevScale = new Vector3f(scal);
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
	
	public void updateLerp(float lerp) {
		if (!prevPosition.equals(position)) {
			lerpVec(prevPosition, position, lerp);
		}
		
		if (!prevRotation.equals(rotation)) {
//			Vector3f.sub(prevRotation, rotation, lerpRotationNormal);
//			lerpRotationNormal.normalise();
			
			
			lerpVec(prevRotation, rotation, lerp);
		}
		
		if (!prevScale.equals(scale)) {
			lerpVec(prevScale, scale, lerp);
		}
	}
	
	private void lerpVec(Vector3f prev, Vector3f now, float ratio) {
		prev.x = (now.x * ratio) + (prev.x * (1 - ratio));
		prev.y = (now.y * ratio) + (prev.y * (1 - ratio));
		prev.z = (now.z * ratio) + (prev.z * (1 - ratio));
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
	
	public Vector3f getLerpPosition() {
		return prevPosition;
	}
	
	public Vector3f getLerpRotation() {
		return prevRotation;
	}
	
	public Vector3f getLerpScale() {
		return prevScale;
	}
}
