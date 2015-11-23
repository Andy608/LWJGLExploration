#version 330 core

layout(location = 0) in vec3 position;
layout(location = 1) in vec4 color;

//uniform vec3 playerPosition;
uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

out vec4 vColor;

void main() {
	vColor = color;
	//vColor = vec4(clamp(1 / playerPosition.xyz, 0.0, 1.0), 1.0);
	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
}