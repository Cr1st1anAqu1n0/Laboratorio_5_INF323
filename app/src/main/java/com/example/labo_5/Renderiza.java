package com.example.labo_5;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.Matrix;
import android.opengl.GLSurfaceView.Renderer;
import android.view.MotionEvent;

public class Renderiza extends GLSurfaceView implements Renderer {

	private Cubo cubo;
	private Cubo1 cubo1;
	private Piso piso;
	private Piramide piramide;
	private Piramide1 piramide1;
	private Piramide2 piramide2;
	private Esfera esfera;
	private Rectangulo rectangulo;

	img texturaBotonArr;
	img texturaBotonAba;

	private final float[] vectorEntrada = { 0, 0, -1, 1 };
	private static float posicion[] = { 0, 1, 3 };
	private final float[] direccion = new float[4];
	private float[] MatrizRotacion = new float[16];

	final float[] matriz = new float[16];

	private float rotX, rotY;
	private float antX, antY;

	private int alto;
	private int ancho;

	private float tx, ty;
	private float inc = 1f;

	Context contexto;

	public Renderiza(Context contexto) {
		super(contexto);
		this.contexto = contexto;
		this.setRenderer(this);
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {

		cubo = new Cubo();
		cubo1 = new Cubo1();
		piso = new Piso();
		piramide = new Piramide();
		piramide1 = new Piramide1();
		piramide2 = new Piramide2();

		esfera = new Esfera(1, 48, 48);

		texturaBotonArr = new img(gl, contexto, "2.png");
		texturaBotonArr.setVertices(-0.5f, -4f, 1, 1);

		texturaBotonAba = new img(gl, contexto, "1.png");
		texturaBotonAba.setVertices(-0.5f, -5.5f, 1, 1);

		antX = antY = -1;

		gl.glClearColor(176 / 255f, 196 / 255f, 222 / 255f, 0);

	}

	@Override
	public void onDrawFrame(GL10 gl) {

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL10.GL_DEPTH_TEST);

		/* Matriz de Proyecci�n */
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 67, ancho / (float) alto, 1f, 50f);

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glRotatef(-rotX, 1, 0, 0);
		gl.glRotatef(-rotY, 0, 1, 0);
		gl.glTranslatef(-posicion[0], -posicion[1], -posicion[2]);

		// Piso
		gl.glPushMatrix();
		gl.glTranslatef(0, 0.3f, -6);
		piso.dibuja(gl);
		gl.glPopMatrix();

		// casa1
		gl.glPushMatrix();
		gl.glTranslatef(5, 1.4f, -10);
		gl.glScalef(0.7f, 0.7f, 0.7f);
		gl.glColor4f(156/255f,156/255f,156/255f,1);
		gl.glRotatef(tx, 0, 3, 0);
		piramide.dibuja(gl);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(5, 0, -10);
		gl.glScalef(0.9f, 0.9f, 0.9f);
		gl.glColor4f(49/255f,87/255f,167/255f,1);
		gl.glRotatef(tx, 0, 1, 0);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		
	//centroderecho
		
		gl.glPushMatrix();
		gl.glTranslatef(5, 1.4f, -5);
		gl.glScalef(0.7f, 0.7f, 0.7f);
		gl.glColor4f(244/255f,206/255f,113/255f,1);
		gl.glRotatef(tx, 0, 3, 0);
		piramide.dibuja(gl);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(5, 0, -5);
		gl.glScalef(0.9f, 0.9f, 0.9f);
		gl.glColor4f(1,1,0/255f,1);
		gl.glRotatef(tx, 0, 1, 0);
		cubo.dibuja(gl);
		gl.glPopMatrix();


		gl.glPushMatrix();
		gl.glTranslatef(0, 1.4f, -6);
		gl.glScalef(0.7f, 0.7f, 0.7f);
		gl.glColor4f(147/255f,115/255f,101/255f,1);
		gl.glRotatef(tx, 0, 3, 0);
		piramide.dibuja(gl);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(0, 0, -6);
		gl.glScalef(0.9f, 0.9f, 0.9f);
		gl.glColor4f(219/255f,62/255f,77/255f,1);
		gl.glRotatef(tx, 0, 1, 0);
		cubo.dibuja(gl);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(1, 1.4f, 1);
		gl.glScalef(0.7f, 0.7f, 0.7f);
		gl.glColor4f(130/255f,37/255f,9/255f,1);
		gl.glRotatef(tx, 0, 3, 0);
		piramide.dibuja(gl);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(1, 0, 1);
		gl.glScalef(00.9f, 0.9f, 0.9f);
		gl.glColor4f(54/255f,166f,146/255f,1);
		gl.glRotatef(tx, 0, 1, 0);
		cubo.dibuja(gl);
		gl.glPopMatrix();

		// cuborotando
		tx = tx + inc;
		if (tx > 360) {
			tx = 0;
		}
		gl.glPushMatrix();
		gl.glTranslatef(-5, 0, -7);
		gl.glRotatef(tx, 0, 0, -5);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		cubo.dibuja(gl);
		gl.glPopMatrix();
		
		// arboles
		gl.glScalef(0.5f, 0.5f, 0.5f);
		dibujaA(gl, -8, 4, -25);
		dibujaA(gl, -15, 4, -25);
		dibujaA(gl, -15, 2, 5);

		dibujaA(gl, 8, 4, -25);
		dibujaA(gl, 8, 2, 5);
		dibujaA(gl, 15, 4, -25);
		dibujaA(gl, 15, 2, 5);

		dibujaA(gl, -15, 4, -8);
		dibujaA(gl, -15, 2, -3);

		dibujaA(gl, 15, 4, -8);
		dibujaA(gl, 15, 2, -3);

		dibujaA(gl, -3, 3, -15);
		dibujaA(gl, -3, 2, -3);
		dibujaA(gl, -3, 4, -25);

		dibujaA(gl, 3, 3, -15);
		dibujaA(gl, 3, 4, -25);
		gl.glFlush();

		// Botones de las opciones
		gl.glDisable(GL10.GL_DEPTH_TEST);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(-4, 4, -6, 6, 1, -1);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

		texturaBotonArr.muestra(gl);

		texturaBotonAba.muestra(gl);

		gl.glDisable(GL10.GL_BLEND);
		gl.glDisable(GL10.GL_TEXTURE_2D);

		gl.glFlush();

	}

	private void dibujaA(GL10 gl, int corx, int cory, int corz) {
		for (int i = -1; i <= cory; i++) {
			gl.glPushMatrix();
			gl.glTranslatef(corx, i, corz);
			cubo1.dibuja(gl);
			gl.glPopMatrix();
		}
		for (int i = corx - 1; i < corx + 3; i++) {
			for (int j = cory + 1; j < (cory + 1) + 2; j++) {
				for (int u = corz - 1; u < corz + 2; u++) {
					gl.glPushMatrix();
					gl.glTranslatef(i, j, u);
					gl.glColor4f(82/255f,121/255f,85/255f,1);
					esfera.dibuja(gl);
					gl.glPopMatrix();
				}
			}

		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int w, int h) {
		ancho = w;
		alto = h;
		gl.glViewport(0, 0, w, h);
		GLU.gluLookAt(gl, 0, 0, 0, 0, 0, -1, 0, 1, 0);
	}


	@Override
	public boolean onTouchEvent(MotionEvent e) {

		/* Obtiene la coordenada de la pantalla */
		float x = e.getX();
		float y = e.getY();

		/* Se considera cuando se levanta el dedo de la pantalla. */
		if (e.getAction() == MotionEvent.ACTION_DOWN
				|| e.getAction() == MotionEvent.ACTION_MOVE) {

			/* En coordenadas del OpenGL */
			float posx = ((x / (float) ancho) * 8) - 4;
			float posy = ((1 - y / (float) alto) * 12) - 6;

			/* Verifica �rea elegida */
			if (puntoEstaDentroDelRectangulo(posx, posy, -0.5f, -4f, 1, 1)) { // Arr
				Matrix.setIdentityM(matriz, 0);
				Matrix.rotateM(matriz, 0, rotY, 0, 1, 0);
				Matrix.rotateM(matriz, 0, rotX, 1, 0, 0);
				Matrix.multiplyMV(direccion, 0, matriz, 0, vectorEntrada, 0);

				posicion[0] = posicion[0] + direccion[0] * 0.1f;
				posicion[1] = posicion[1] + direccion[1] * 0.1f;
				posicion[2] = posicion[2] + direccion[2] * 0.1f;

			} else if (puntoEstaDentroDelRectangulo(posx, posy, -0.5f, -5.5f,
					1, 1)) { // Abj
				Matrix.setIdentityM(matriz, 0);
				Matrix.rotateM(matriz, 0, rotY, 0, 1, 0);
				Matrix.multiplyMV(direccion, 0, matriz, 0, vectorEntrada, 0);

				posicion[0] = posicion[0] - direccion[0] * 0.1f;
				posicion[1] = posicion[1] - direccion[1] * 0.1f;
				posicion[2] = posicion[2] - direccion[2] * 0.1f;
			} else {
				if (antX == -1) {
					antX = x;
					antY = y;
				} else {
					rotX = rotX + (y - antY) / 10;
					rotY = rotY + (x - antX) / 10;
					antX = x;
					antY = y;
				}
			}

		} else {
			antX = -1;
			antY = -1;
		}
		return true;
	}

	private boolean puntoEstaDentroDelRectangulo(float posx, float posy,
			float x, float y, float ancho, float alto) {
		return (x < posx && posx < x + ancho && y < posy && posy < y + alto);
	}

}
