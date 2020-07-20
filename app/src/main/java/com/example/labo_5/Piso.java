package com.example.labo_5;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Piso {

	private float vertices[] = new float[42 * 6];
	private byte colores[] = new byte[42 * 8];
	private FloatBuffer bufVertices;
	private ByteBuffer bufColores;
	public Piso() {

		int i = 0;
		int j = 0;
		for (float x = -10; x <= 10; x++){
			vertices[i  ] = x; vertices[i+1] = -1; vertices[i+2] =  10;
			vertices[i+3] = x; vertices[i+4] = -1; vertices[i+5] = -10;
			i = i + 6;
			colores[j  ] = 0; colores[j+1] = 0; colores[j+2] = 0; colores[j+3] = 0;
			colores[j+4] = 0; colores[j+5] = 0; colores[j+6] = 0; colores[j+7] = 0;
			j = j + 8;
		}
		for (float z = 10; z >= -10; z--){
			vertices[i  ] = -10; vertices[i+1] = -1; vertices[i+2] = z;
			vertices[i+3] =  10; vertices[i+4] = -1; vertices[i+5] = z;
			i = i + 6;
			colores[j  ] = 0; colores[j+1] = 0; colores[j+2] = 0; colores[j+3] = 0;
			colores[j+4] = 0; colores[j+5] = 0; colores[j+6] = 0; colores[j+7] = 0;
			j = j + 8;
		}
		
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
	}
	
	public void dibuja(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufVertices);
        gl.glColor4f(2/255f, 104/255f, 13/255f,1);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 84);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);


	}
}
