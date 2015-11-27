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
		rotation.x = (rotation.x + dx);
		rotation.y = (rotation.y + dy);
		rotation.z = (rotation.z + dz);
		
		if (rotation.y > 360) {
			rotation.y -= 360;
			prevRotation.y -= 360;
		}
		else if (rotation.y < -360) {
			rotation.y += 360;
			prevRotation.y += 360;
		}
		
		if (rotation.x > 360) {
			rotation.x -= 360;
			prevRotation.x -= 360;
		}
		else if (rotation.x < -360) {
			rotation.x += 360;
			prevRotation.x += 360;
		}
		
		if (rotation.z > 360) {
			rotation.z -= 360;
			prevRotation.z -= 360;
		}
		else if (rotation.z < -360) {
			rotation.z += 360;
			prevRotation.z += 360;
		}
	}
	
	public void updateLerp(float lerp) {
		if (!prevPosition.equals(position)) {
			prevPosition.set(position);
			lerpVec(prevPosition, position, lerp);
		}
		
		if (!prevRotation.equals(rotation)) {
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
	
	public void setPosition(float x, float y, float z) {
		this.position.set(x, y, z);
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		this.rotation.set(x, y, z);
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	public void setScale(float uniformScale) {
		this.scale.set(uniformScale, uniformScale, uniformScale);
	}
	
	public void scaleEntity(float uniformScale) {
		this.scale.set(scale.x + uniformScale, scale.y + uniformScale, scale.z + uniformScale);
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
