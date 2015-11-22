package com.bountive.graphics.entity;

import math.Vector3f;

import com.bountive.util.MathsUtil;
import com.bountive.window.input.KeyManager;
import com.bountive.window.input.MouseManager;

public class EntityCamera {

	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;
	
	private float moveSpeed = 4;
	
	public EntityCamera() {
		position = new Vector3f(0, 0, 0);
	}
	
	public void update(float deltaTime) {
		moveCamera(deltaTime);
	}
	
	private float superTime;
	public void moveCamera(float deltaTime) {
		superTime += deltaTime;
		
		if (!(KeyManager.moveForward || KeyManager.moveBackward || KeyManager.moveLeft || KeyManager.moveRight)) {
			superTime = 0;
		}
		
		if (KeyManager.moveForward) {
			position.x += (moveSpeed * deltaTime) * (float)(Math.sin(Math.toRadians(yaw))) + ((Math.sin(6 * superTime)) / 200f);
			position.z -= (moveSpeed * deltaTime) * (float)(Math.cos(Math.toRadians(yaw))) - ((Math.sin(6 * superTime)) / 200f);
			position.y += ((Math.sin(12 * superTime)) / 100f);
		}
		if (KeyManager.moveBackward) {
			position.x -= (moveSpeed * deltaTime) * (float)(Math.sin(Math.toRadians(yaw))) - ((Math.sin(6 * superTime)) / 200f);
			position.z += (moveSpeed * deltaTime) * (float)(Math.cos(Math.toRadians(yaw))) + ((Math.sin(6 * superTime)) / 200f);
			position.y += ((Math.cos(12 * superTime)) / 100f);
		}
		if (KeyManager.moveLeft) {
			position.x -= (moveSpeed * deltaTime) * (float)Math.sin(Math.toRadians(yaw + 90));
			position.z += (moveSpeed * deltaTime) * (float)Math.cos(Math.toRadians(yaw + 90));
			position.y += ((Math.sin(12 * superTime)) / 100f);
		}
		if (KeyManager.moveRight) {
			position.x -= (moveSpeed * deltaTime) * (float)Math.sin(Math.toRadians(yaw - 90));
			position.z += (moveSpeed * deltaTime) * (float)Math.cos(Math.toRadians(yaw - 90));
			position.y += ((Math.sin(12 * superTime)) / 100f);
		}
		if (KeyManager.moveUp) {
			position.y += (moveSpeed * deltaTime);
		}
		if (KeyManager.moveDown) {
			position.y -= (moveSpeed * deltaTime);
		}
		
		yaw += 5 * (float)Math.toRadians(MouseManager.getOffsetX());
		pitch = MathsUtil.clamp(pitch + 5 * (float)Math.toRadians(MouseManager.getOffsetY()), -90, 90);
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}
}
