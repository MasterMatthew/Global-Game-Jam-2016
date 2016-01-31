package com.base.game;

import game.AccessItems;
import game.Battery;
import game.Listener;
import game.Map;
import game.NewBattery;
import game.Player;
import game.PickUpItem;
import game.PowerUp;
import game.Room;
import game.flashLight;

import com.base.engine.components.*;
import com.base.engine.core.*;
import com.base.engine.core.math.Quaternion;
import com.base.engine.core.math.Vector2f;
import com.base.engine.core.math.Vector3f;
import com.base.engine.physics.RigidBody.RigidBody;
import com.base.engine.physics.collision.Box;
import com.base.engine.physics.collision.Plane;
import com.base.engine.rendering.*;

public class TestGame extends Game
{
	public void init()
	{
		world = World.world;
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

		int indices[] = { 0, 1, 2,
				2, 1, 3};

		Vertex[] vertices2 = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth/ 10, 0.0f, -fieldDepth/ 10), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth/ 10, 0.0f, fieldDepth/ 10 * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth/ 10 * 3, 0.0f, -fieldDepth/ 10), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth/ 10 * 3, 0.0f, fieldDepth/ 10 * 3), new Vector2f(1.0f, 1.0f))};

		int indices2[] = { 0, 1, 2,
				2, 1, 3};
		

		
		Mesh mesh2 = new Mesh(vertices2, indices2, true);

		Mesh mesh = new Mesh(vertices, indices, true);
		Material material = new Material();//new Texture("test.png"), new Vector3f(1,1,1), 1, 8);
		material.addTexture("diffuse", new Texture("test.png"));
		material.addFloat("specularIntensity", 1);
		material.addFloat("specularPower", 8);
		

		Mesh tempMesh = new Mesh("monkey3.obj");
		
		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

		GameObject planeObject = new GameObject();
		planeObject.addComponent(meshRenderer);
		planeObject.getTransform().getPos().set(0, -1, 5);

		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(150f/255f,75f/255f,20f/255f), 0.4f);
//		RenderingEngine.dlight = directionalLight;

		directionalLightObject.addComponent(directionalLight);

		GameObject pointLightObject = new GameObject();
		pointLightObject.addComponent(new PointLight(new Vector3f(0,1,0), 0.4f, new Attenuation(0,0,1)));

		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,
				new Attenuation(0,0,0.1f), 0.7f);

		GameObject spotLightObject = new GameObject();
		spotLightObject.addComponent(spotLight);

		spotLightObject.getTransform().getPos().set(5, 0, 5);
		spotLightObject.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(90.0f)));

		//addObject(planeObject);
		addObject(directionalLightObject);
		//addObject(pointLightObject);
		//addObject(spotLightObject);

		//getRootObject().addChild(new GameObject().addComponent(new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f)));

		GameObject testMesh1 = new GameObject().addComponent(new MeshRenderer(mesh2, material));
		GameObject testMesh2 = new GameObject().addComponent(new MeshRenderer(mesh2, material));
		GameObject testMesh3 = new GameObject().addComponent(new FreeLook(0.5f)).addComponent(new FreeMove(10)).addComponent(new LookAtComponent()).addComponent(new MeshRenderer(tempMesh, material));

		testMesh1.getTransform().getPos().set(0, 2, 0);
		testMesh1.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), 0.4f));

		testMesh2.getTransform().getPos().set(0, 0, 5);

		testMesh1.addChild(testMesh2);
		//getRootObject()
		//GameObject cameraObject = new GameObject();
		//Camera camera = new Camera((float)Math.toRadians(70.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000.0f);
		//GameComponent freelook = new FreeLook(0.5f);
		//GameComponent freeMove = new FreeMove(10);
		
		
		//world.add(cameraObject);
		
		GameComponent newBat = new NewBattery();
		//cameraObject.addComponent(newBat);
		
		//RenderingEngine.mainCamera = camera;
		
		//cameraObject.addComponent(freelook).addComponent(freeMove).addComponent(camera);
		//world.focus = cameraObject;
		//world.add(cameraObject);
		//testMesh2.addChild(cameraObject);

		//addObject(testMesh2);
		
		//addObject(testMesh1);
		//addObject(testMesh3);

		testMesh3.getTransform().getPos().set(5, 5, 5);
		testMesh3.getTransform().setRot(new Quaternion(new Vector3f(0,1,0), (float)Math.toRadians(-70.0f)));
		
		//addObject(new GameObject().addComponent(new MeshRenderer(new Mesh("monkey3.obj"), material)));
		
		directionalLight.getTransform().setRot(new Quaternion(new Vector3f(1,0,0), (float)Math.toRadians(-45)));
		
		
		GameObject physics1 = new GameObject();
		GameObject physics2 = new GameObject();
		GameObject physics3 = new GameObject();
		
		Box box = new Box();
		Box box2 = new Box();
		Plane plane = new Plane();
		
		RigidBody body = new RigidBody(5, .2f, .2f);
		RigidBody body2 = new RigidBody(5, .5f, .5f);
		
		box.attach(body);
		box2.attach(body2);
		
		physics1.addComponent(body);
		physics3.addComponent(body2);
		
		physics1.addComponent(box);
		physics2.addComponent(plane);
		physics3.addComponent(box2);
		
		body.attach(physics1);
		body2.attach(physics3);
		
		physics1.getTransform().setPos(new Vector3f(-20,21,1));
		physics2.getTransform().setPos(new Vector3f(0,0,0));
		physics3.getTransform().setPos(new Vector3f(-20, 20, 0));
		
		plane.offset = 0;
		plane.direction = new Vector3f(0,1,0);
		
		float halfSize = 1f;
		box.halfSize = new Vector3f(halfSize, halfSize, halfSize);
		box2.halfSize = new Vector3f(halfSize, halfSize, halfSize);
		
		Vertex[] bv = new Vertex[]{ 
				new Vertex( new Vector3f(halfSize, halfSize, halfSize), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(halfSize, halfSize, -halfSize), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(halfSize, -halfSize, halfSize), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(halfSize, -halfSize, -halfSize), new Vector2f(1.0f, 1.0f)),
				new Vertex( new Vector3f(-halfSize, halfSize, halfSize), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-halfSize, halfSize, -halfSize), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(-halfSize, -halfSize, halfSize), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(-halfSize, -halfSize, -halfSize), new Vector2f(1.0f, 1.0f))
		};
		
		int bi[] = { 0, 1, 2,
					2, 1, 3,
					
					//Bottom
					1, 3, 5,
					5, 1, 7,
					
					//Top
					0, 2, 4,
					4, 2, 6,
					
					2, 3, 4,
					4, 3, 5,
					
					4, 5, 6,
					6, 5, 7,
					
					6, 7, 0,
					0, 7, 1
		};
		Mesh bm = new Mesh(bv, bi);
		MeshRenderer boxMesh = new MeshRenderer(bm, material);
		MeshRenderer boxMesh2 = new MeshRenderer(bm, material);
		//MeshRenderer planeMesh = new MeshRenderer(mesh2, material);
		
		physics3.addComponent(boxMesh2);
		//physics2.addComponent(planeMesh);
		physics1.addComponent(boxMesh);
		
		box2.calculateInternals();
		box.calculateInternals();
		plane.calculateInternals();
		
		//world.add(physics3);
		world.add(physics2);
		//world.add(physics1);
		
		GameObject object = new GameObject("Last Object");
		flashLight fl = new flashLight();
		object.addComponent(fl);
		world.add(object);
		
		GameComponent accessItems = new AccessItems();
		object.addComponent(accessItems);
		
		GameComponent pickUp = new PickUpItem();
		object.addComponent(pickUp);
		
		
		Map map = new Map(5,5);
		map.showMap();
		
		Listener l = new Listener();
		//cameraObject.addComponent(l);
		//Room room = new Room("Our House");
		//room.addComponent(room.calculate());
		//room.getTransform().setPos(new Vector3f(0,10,0));	
		
		//world.add(room);
		
		Player player = new Player();
		player.getTransform().setPos(new Vector3f(0,3,0));
		world.addToBucket(player);
		
		//addObject(new TestObject(0,0,0).convert());
	}
}
