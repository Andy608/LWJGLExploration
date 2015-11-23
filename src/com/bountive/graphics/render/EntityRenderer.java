package com.bountive.graphics.render;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.bountive.graphics.entity.EntityBase;
import com.bountive.graphics.model.ModelBase;
import com.bountive.graphics.shader.BasicShader;
import com.bountive.util.MatrixUtil;

public final class EntityRenderer {
	
	public EntityRenderer(BasicShader shader) {
		MatrixUtil.init(shader);
	}
	
	public void render(EntityBase entity, BasicShader shader) {
		ModelBase model = entity.getModel();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		shader.loadTransformationMatrix(MatrixUtil.createTransformationMatrtix(entity.getPosition(), entity.getRotation(), entity.getScale()));
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}
