package com.base.engine.components;

import com.base.engine.components.attachments.Renderable;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;

public class MeshRenderer extends GameComponent implements Renderable
{
	private Mesh mesh;
	private Material material;

	public MeshRenderer(Mesh mesh, Material material)
	{
		this.mesh = mesh;
		this.material = material;
	}

	@Override
	public int render(Shader shader, RenderingEngine renderingEngine)
	{
		shader.bind();
		shader.updateUniforms(getTransform(), material, renderingEngine);
		mesh.draw();
		return 1;
	}
}
