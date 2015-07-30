package com.huangtao.androidopengl.data;

import java.nio.ByteBuffer; 
import java.nio.ByteOrder; 
import java.nio.FloatBuffer; 
 
 
 



import com.huangtao.androidopengl.GLConstants;
import static android.opengl.GLES20.*; 
 
public class VertexArray { 
     
    private final FloatBuffer floatBuffer; 
 
     
    public VertexArray(float[] vertexData) 
    { 
        floatBuffer = ByteBuffer.allocateDirect(vertexData.length * GLConstants.BYTES_PER_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertexData); 
    } 
     
    public void setVertexAttribPointer(int dataOffset,int attributeLocation,int componentCount, int stride) 
    { 
        floatBuffer.position(dataOffset); 

        glVertexAttribPointer(attributeLocation,componentCount,GL_FLOAT,false,stride,floatBuffer); 
        glEnableVertexAttribArray(attributeLocation); 
        floatBuffer.position(0); 
    } 
 
} 