package com.bountive.graphics.shader;

import java.nio.FloatBuffer;

import math.Matrix4f;
import math.Vector3f;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.bountive.util.Disposable;
import com.bountive.util.FileUtil;

public abstract class AbstractShader implements Disposable {

	private int vertexShaderID;
	private int fragmentShaderID;
	private int programID;
	
	private FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	public AbstractShader(String vertexFilePath, String fragmentFilePath) {
		programID = GL20.glCreateProgram();
		attachVertexShader(vertexFilePath);
		attachFragmentShader(fragmentFilePath);
		link();
		bindUniformLocationValues();
	}
	
	private void attachVertexShader(String vertexFilePath) {
		
		//Get the source from the vertex file.
		String vertexSource = FileUtil.readFile(vertexFilePath);
		
		//Create a vertex shader.
		vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		
		//Attach the source to the vertex shader.
		GL20.glShaderSource(vertexShaderID, vertexSource);
		
		//Check to see if the vertex shader compiled successfully, else throw exception.
		GL20.glCompileShader(vertexShaderID);
		if (GL20.glGetShaderi(vertexShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Error creating vertex shader\n" + GL20.glGetShaderi(vertexShaderID, GL20.glGetShaderi(vertexShaderID, GL20.GL_INFO_LOG_LENGTH)));
		}
		
		//Attach the vertex shader to the program.
		GL20.glAttachShader(programID, vertexShaderID);
	}
	
	private void attachFragmentShader(String fragmentFilePath) {
		
		//Get the source from the fragment file.
		String fragmentSource = FileUtil.readFile(fragmentFilePath);
		
		//Create a fragment shader.
		fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		
		//Attach the source to the fragment shader.
		GL20.glShaderSource(fragmentShaderID, fragmentSource);
		
		//Check to see if the fragment shader compiled successfully, else throw exception.
		GL20.glCompileShader(fragmentShaderID);
		if (GL20.glGetShaderi(fragmentShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Error creating fragment shader:\n" +
					GL20.glGetShaderi(fragmentShaderID, GL20.glGetShaderi(fragmentShaderID, GL20.GL_INFO_LOG_LENGTH)));
		}
		
		//Attach the fragment shader to the program.
		GL20.glAttachShader(programID, fragmentShaderID);
	}
	
	private void link() {
		//Link the program.
		GL20.glLinkProgram(programID);
		
		//Check for link errors.
		if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			System.err.println("Unable to link the shader program:\n" + 
					GL20.glGetProgramInfoLog(programID, GL20.glGetProgrami(programID, GL20.GL_INFO_LOG_LENGTH)));
		}
	}
	
	public void bind() {
		GL20.glUseProgram(programID);
	}
	
	public void unbind() {
		GL20.glIsProgram(0);
	}
	
	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	protected abstract void bindUniformLocationValues();
	
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	protected void loadVector(int location, Vector3f vector) {
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}
	
	protected void loadBoolean(int location, boolean value) {
		GL20.glUniform1f(location, value ? 1 : 0);
	}
	
	protected void loadMatrix(int location, Matrix4f matrix4f) {
		matrix4f.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4fv(location, false, matrixBuffer);
	}
	
	@Override
	public void dispose() {
		//Unbind the program.
		unbind();
		
		//Detach the shaders.
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		
		//Delete the shaders.
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		
		//Delete the program.
		GL20.glDeleteProgram(programID);
	}
}
