package com.example.labo_5;


import java.nio.ByteBuffer; 
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cubo1 {
	private float vertices[] = new float[] {
		      // Frente
            -0.5f, 0.0f, 0.5f, // 4 0
            0.5f, 0.0f, 0.5f, // 5 1
            0.5f, 1.0f, 0.5f, // 6 2
            -0.5f, 1.0f, 0.5f, // 7 3
            // Atras
            -0.5f, 1.0f, -0.5f, // 3 4
            0.5f, 1.0f, -0.5f, // 2 5
            0.5f, 0.0f, -0.5f, // 1 6
            -0.5f, 0.0f, -0.5f, // 0 7
            // Izquierda
            -0.5f, 0.0f, -0.5f, // 0 8
            -0.5f, 0.0f, 0.5f, // 4 9
            -0.5f, 1.0f, 0.5f, // 7 10
            -0.5f, 1.0f, -0.5f, // 3 11
            // Derecha
            0.5f, 0.0f, 0.5f, // 5 12
            0.5f, 0.0f, -0.5f, // 1 13
            0.5f, 1.0f, -0.5f, // 2 14
            0.5f, 1.0f, 0.5f, // 6 15
            // Abajo
            -0.5f, 0.0f, -0.5f, // 0 16
            0.5f, 0.0f, -0.5f, // 1 17
            0.5f, 0.0f, 0.5f, // 5 18
            -0.5f, 0.0f, 0.5f, // 4 19
            // Arriba
            -0.5f, 1.0f, 0.5f, // 7 20
            0.5f, 1.0f, 0.5f, // 6 21
            0.5f, 1.0f, -0.5f, // 2 22
            -0.5f, 1.0f, -0.5f // 3 23
	};				

	byte maxColor = (byte)255;

	private byte colores[] = new byte[] { 
			
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0
            (byte) 150, (byte) 75, (byte) 0, maxColor, // 4 0

	};								
	private short indices[] = new short [] {	
			0,	1,	2,	0,	2,	3, // Frente	
			4,	5,	6,	4,	6,	7, // Atr�s		
			8,	9, 10,	8, 10, 11, // Izquierda	
			12, 13, 14, 12,	14, 15, // Derecha	
			16, 17, 18, 16,	18, 19,	// Abajo		
			20, 21, 22, 20,	22, 23	// Arriba	
	};								

	private FloatBuffer bufVertices;
	private ByteBuffer bufColores; 
	private ShortBuffer bufIndices;

	public Cubo1() {

		/* Lee los v�rtices */

		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufVertices = bufByte.asFloatBuffer(); // Convierte de byte a float 
		bufVertices.put(vertices);

		bufVertices.rewind(); // puntero al principio del buffer

		/* Lee los colores */

		bufColores = ByteBuffer.allocateDirect(colores.length); 
		bufColores.put(colores);
		bufColores.position(0); // puntero al principio del buffer

		/* Lee los indices */

		bufByte = ByteBuffer.allocateDirect(indices.length * 2);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufIndices = bufByte.asShortBuffer(); // Convierte de byte a short 
		bufIndices.put(indices);
        bufIndices.rewind(); // puntero al principio del buffer

	}

	public void dibuja(GL10 gl) {

		/* Se activa el arreglo de v�rtices */
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		/* Se activa el arreglo de colores */ 
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

		/* Se especifica los datos del arreglo de v�rtices */
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufVertices);

		/* Se especifica los datos del arreglo de colores */ 
		gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, bufColores);

		/* Se dibuja el cubo */ 
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,

				GL10.GL_UNSIGNED_SHORT, bufIndices);

		/* Se desactiva el arreglo de v�rtices */
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

		/* Se desactiva el arreglo de colores */ 
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);

	}

}



/*
// Cubos
/*for (int z = 0; z >= -8; z = z - 2) {
	for (int x = -4; x <= 4; x = x + 2) {
		gl.glPushMatrix();
		gl.glTranslatef(x, 0, z);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		cubo.dibuja(gl);
		gl.glPopMatrix();
	}
}


//figura 1
gl.glPushMatrix();
gl.glTranslatef(-8, 0, -13);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();


gl.glPushMatrix();
gl.glTranslatef(-8, 1, -13);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

//figura 2
gl.glPushMatrix();
gl.glTranslatef(8, 0, -13);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(8, 1, -13);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide.dibuja(gl);
gl.glPopMatrix();

//figura 3
gl.glPushMatrix();
gl.glTranslatef(8, 0, 3);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(8, 1, 3);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);
gl.glPopMatrix();


//figura 4
gl.glPushMatrix();
gl.glTranslatef(-8, 1, 3);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(-8, 0, 3);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);
gl.glPopMatrix();

//figura 5
gl.glPushMatrix();
gl.glTranslatef(0, 1, -13);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(0, 0, -13);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);
gl.glPopMatrix();

//figura 6
gl.glPushMatrix();
gl.glTranslatef(-8, 0, -5);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(-8, 1, -5);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);
gl.glPopMatrix();

//figura 7
gl.glPushMatrix();
gl.glTranslatef(8, 0, -5);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(8, 1, -5);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(8, 2, -5);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);
gl.glPopMatrix();


//figura 8
gl.glPushMatrix();
gl.glTranslatef(0, 2, 3);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(0, 1, 3);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(0, 0, 3);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);
gl.glPopMatrix();

//figura 9
tx=tx+inc;
if (tx>359) {
	tx=0;

}
gl.glPushMatrix();
gl.glTranslatef(0, 0, -5);
gl.glRotatef(tx, 0, 0,-5);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();


//figura 10
gl.glPushMatrix();
gl.glTranslatef(-4, 1.3f, -9);
gl.glRotatef(45, -4, 1.3f,-9);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(-4, 0, -9);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);
gl.glPopMatrix();

//figura 11
gl.glPushMatrix();
gl.glTranslatef(4, 1.3f, -9);
gl.glRotatef(45, 4, 1.3f,-9);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(4,0, -9);
gl.glRotatef(45, 4, 0f,-9);
gl.glScalef(0.5f, 0.5f, 0.5f);
cubo.dibuja(gl);
gl.glPopMatrix();

//figura 12

gl.glPushMatrix();
gl.glTranslatef(0, 1, -9);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(0, 0, -9);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);

gl.glPopMatrix();
gl.glPushMatrix();
gl.glTranslatef(0, 3, -9);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide.dibuja(gl);
gl.glPopMatrix();

gl.glPushMatrix();
gl.glTranslatef(0, 2, -9);
gl.glScalef(0.5f, 0.5f, 0.5f);
piramide2.dibuja(gl);
gl.glPopMatrix();
*/




