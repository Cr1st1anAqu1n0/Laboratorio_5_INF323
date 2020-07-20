package com.example.labo_5;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

public class img {
	
	private Context contexto;
	
	private String nombreDeArchivo;
	private int ancho;
	private int alto;
	
	int codigo[] = new int[1];
	
	private float vertices[] = new float [8];
	
	private float textura[] = new float [8];
	
	private short indices[] = new short [] { 
		 0,  1,  2,  0,  2,  3, 
 	};
	
	private FloatBuffer bufVertices;
	private FloatBuffer bufTextura;
	private ShortBuffer bufIndices;
	private ByteBuffer bufByte;
	
	public img(GL10 gl, Context contexto, String nombreDeArchivo) {
		
		this.contexto = contexto;
		this.nombreDeArchivo = nombreDeArchivo;
		leeTextura(gl);		
		setCoord_Textura(0, 0, 1, 1);
		bufByte = ByteBuffer.allocateDirect(indices.length * 2);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufIndices = bufByte.asShortBuffer(); // Convierte de byte a short
		bufIndices.put(indices).rewind(); // puntero al principio del buffer
		
	}
	
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	public int getCodigoTextura() {
		return codigo[0];
	}
	
	public void setVertices(float x1, float y1, float ancho, float alto) {
		
		float x2 = x1 + ancho;
		float y2 = y1 + alto;
		vertices[0] = x1; vertices[1] = y1;
		vertices[2] = x2; vertices[3] = y1;
		vertices[4] = x2; vertices[5] = y2;
		vertices[6] = x1; vertices[7] = y2;
        
		bufByte = ByteBuffer.allocateDirect(vertices.length * 4);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufVertices = bufByte.asFloatBuffer(); // Convierte de byte a float
		bufVertices.put(vertices).rewind(); // puntero al principio del buffer	
		
	}
	
	public void setCoord_Textura(float u1, float v1, float u2, float v2) {
        
		textura[0] = u1; textura[1] = v2;
		textura[2] = u2; textura[3] = v2;
		textura[4] = u2; textura[5] = v1;
		textura[6] = u1; textura[7] = v1;
        
		bufByte = ByteBuffer.allocateDirect(textura.length * 4);
		bufByte.order(ByteOrder.nativeOrder()); // Utiliza el orden de byte nativo
		bufTextura = bufByte.asFloatBuffer(); // Convierte de byte a float
		bufTextura.put(textura).position(0); // puntero al principio del buffer
		
	}	
	
	public void muestra(GL10 gl) {
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, codigo[0]);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, bufVertices);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, bufTextura);
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
				GL10.GL_UNSIGNED_SHORT, bufIndices);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
	}

	public void leeTextura(GL10 gl) {

		try {
			InputStream is = contexto.getAssets().open(nombreDeArchivo);

			Bitmap textura = BitmapFactory.decodeStream(is);
			
			ancho = textura.getWidth();
			alto = textura.getHeight();
			gl.glGenTextures(1, codigo, 0);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, codigo[0]);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
					GL10.GL_NEAREST);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
					GL10.GL_NEAREST);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
					GL10.GL_REPEAT);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
					GL10.GL_REPEAT);
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, textura, 0);

			gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);

			textura.recycle();
			is.close();

			is = null;

		} catch (IOException e) {
			Log.d("La textura", "No puede cargar " + nombreDeArchivo);
			throw new RuntimeException("No puede cargar " + nombreDeArchivo);
		}

	}
}
