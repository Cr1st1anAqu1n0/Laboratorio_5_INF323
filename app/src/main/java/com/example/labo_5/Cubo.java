package com.example.labo_5;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;

public class Cubo {
	private float vertices[] = new float[] {
		// Frente
		-1, -1,  1, // 4   0
		 1, -1,  1, // 5   1
		 1,  1,  1, // 6   2
		-1,  1,  1, // 7   3
		// Atr�s
		-1,  1, -1, // 3   4
		 1,  1, -1, // 2   5
		 1, -1, -1, // 1   6
		-1, -1, -1, // 0   7
		// Izquierda
		-1, -1, -1, // 0   8
		-1, -1,  1, // 4   9
		-1,  1,  1, // 7  10 
		-1,  1, -1, // 3  11
		// Derecha
		 1, -1,  1, // 5  12 
		 1, -1, -1, // 1  13
		 1,  1, -1, // 2  14
		 1,  1,  1, // 6  15
		 // Abajo
		-1, -1, -1, // 0  16
		 1, -1, -1, // 1  17
		 1, -1,  1, // 5  18
		-1, -1,  1, // 4  19
		 // Arriba
		-1,  1,  1, // 7  20
		 1,  1,  1, // 6  21
		 1,  1, -1, // 2  22
		-1,  1, -1  // 3  23
	};

	byte maxColor = (byte)255;
	
	private byte colores[] = new byte[] {
		// Frente - lila
            (byte) 2, (byte) 138, (byte) 191, maxColor, // 4 0
            (byte) 2, (byte) 138, (byte) 191, maxColor, // 4 0
            (byte) 2, (byte) 138, (byte) 191, maxColor, // 4 0
            (byte) 2, (byte) 138, (byte) 191, maxColor, // 4 0

		
		// Atr�s - amarillo
            (byte) 236, (byte) 243, (byte) 0, maxColor, // 4 0
            (byte) 236, (byte) 243, (byte) 0, maxColor, // 4 0
            (byte) 236, (byte) 243, (byte) 0, maxColor, // 4 0
            (byte) 236, (byte) 243, (byte) 0, maxColor, // 4 0

            // Izquierda - celeste
            (byte) 236, (byte) 243, (byte) 0, maxColor, // 4 0
            (byte) 236, (byte) 243, (byte) 0, maxColor, // 4 0
            (byte) 236, (byte) 243, (byte) 0, maxColor, // 4 0
            (byte) 236, (byte) 243, (byte) 0, maxColor, // 4 0
		// Derecha - rojo
		0, maxColor, maxColor, maxColor, // 0   8
		0, maxColor, maxColor, maxColor, // 4   9
		0, maxColor, maxColor, maxColor, // 7  10
		0, maxColor, maxColor, maxColor, // 3  11
		// Abajo - azul
		0, 0, maxColor, maxColor, // 0  16
		0, 0, maxColor, maxColor, // 1  17
		0, 0, maxColor, maxColor, // 5  18
		0, 0, maxColor, maxColor, // 4  19
		// Arriba - verde
		0, maxColor, 0, maxColor, // 7  20
		0, maxColor, 0, maxColor, // 6  21
		0, maxColor, 0, maxColor, // 2  22
		0, maxColor, 0, maxColor  // 3  23
	};
	
	private short indices[] = new short [] { 
		 0,  1,  2,  0,  2,  3, // Frente
		 4,  5,  6,  4,  6,  7, // Atr�s
		 8,  9, 10,  8, 10, 11, // Izquierda 
		12, 13, 14, 12, 14, 15, // Derecha
		16, 17, 18, 16, 18, 19, // Abajo
		20, 21, 22, 20, 22, 23  // Arriba
	};
	
	private FloatBuffer bufVertices;
	private ByteBuffer bufColores;
	private ShortBuffer bufIndices;
	
	public Cubo() {
		ByteBuffer bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufVertices = bufByte.asFloatBuffer(); // Convierte de byte a float
		bufVertices.put(vertices);
		bufVertices.rewind(); // puntero al principio del buffer

		bufColores = ByteBuffer.allocateDirect(colores.length);
		bufColores.put(colores);
		bufColores.position(0); // puntero al principio del buffer
		
		bufByte = ByteBuffer.allocateDirect(indices.length * 2);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufIndices = bufByte.asShortBuffer(); // Convierte de byte a short
		bufIndices.put(indices);
		bufIndices.rewind(); // puntero al principio del buffer
	}
	
	public void dibuja(GL10 gl) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bufVertices);

		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,GL10.GL_UNSIGNED_SHORT, bufIndices);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
