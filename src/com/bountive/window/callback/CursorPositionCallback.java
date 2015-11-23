package com.bountive.window.callback;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import com.bountive.window.input.MouseManager;

public class CursorPositionCallback extends GLFWCursorPosCallback {

	@Override
	public void invoke(long window, double xpos, double ypos) {
		MouseManager.updateMousePosition(window, xpos, ypos);
		
//		if (KeyManager.debug) {
//			System.out.println("XOffset: " + MouseManager.getOffsetX() + " | " + "YOffset: " + MouseManager.getOffsetX());
//			System.out.println("XPos:    " + MouseManager.getPosX() + " | " + "YPos:    " + MouseManager.getPosY());
//		}
	}
}
