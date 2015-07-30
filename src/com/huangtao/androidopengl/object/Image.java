package com.huangtao.androidopengl.object;

import static android.opengl.GLES20.GL_TRIANGLES; 
import static android.opengl.GLES20.glDrawArrays; 

import com.huangtao.androidopengl.GLConstants;
import com.huangtao.androidopengl.data.VertexArray;
import com.huangtao.androidopengl.program.TextureShaderProgram;
 

public class Image { 
     
    private static final int POSITION_COMPONENT_COUNT = 2; 
     
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2; 
     
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT) * GLConstants.BYTES_PER_FLOAT; 
 
    private static final float[] VERTEX_DATA =  
    { 
            -0.5f, 0.5f, 0.0f,0.0f, 
            -0.5f, -0.5f, 0f,1f, 
            0.5f,-0.5f,1f,1f, 
            -0.5f, 0.5f, 0f,0f, 
            0.5f,-0.5f,1f,1f, 
            0.5f,0.5f,1f,0f 
           
    }; 
     
    private final VertexArray vertexArray; 
     
    public Image() 
    { 
        vertexArray = new VertexArray(VERTEX_DATA); 
    } 
     
    public void bindData(TextureShaderProgram textureProgram) 
    { 
        vertexArray.setVertexAttribPointer(0, textureProgram.getPositionAttributeLocation(), POSITION_COMPONENT_COUNT, STRIDE); 
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT, textureProgram.getTextureCoordinatesAttributeLocation(), TEXTURE_COORDINATES_COMPONENT_COUNT, STRIDE); 
    } 
     
    public void draw() 
    { 
        glDrawArrays(GL_TRIANGLES,0,6); 
    } 
} 
