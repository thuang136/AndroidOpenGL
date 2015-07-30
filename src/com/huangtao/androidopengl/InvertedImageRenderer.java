package com.huangtao.androidopengl;

import javax.microedition.khronos.egl.EGLConfig; 
import javax.microedition.khronos.opengles.GL10; 
 





import com.huangtao.androidopengl.object.Image;
import com.huangtao.androidopengl.program.TextureShaderProgram;
import com.huangtao.androidopengl.util.MatrixHelper;
import com.huangtao.androidopengl.util.TextureHelper;

import static android.opengl.GLES20.*; 
import static android.opengl.Matrix.*; 
import android.content.Context; 
import android.opengl.GLSurfaceView.Renderer; 
 
 
 
 
public class InvertedImageRenderer implements Renderer{ 
 
    public InvertedImageRenderer(Context context) 
    { 
        this.context = context; 
    } 
    @Override 
    public void onSurfaceCreated(GL10 gl, EGLConfig config) { 
        // TODO Auto-generated method stub 
        glClearColor(0.0f, 0f, 0f, 1f); 
        m_textureShaderProgram = new TextureShaderProgram(context,R.raw.texture_vertex_shader,R.raw.texture_fragment_shader); 
        m_inverseTextureShaderProgram = new TextureShaderProgram(context,R.raw.texture_vertex_shader,R.raw.texture_inv_fragment_shader); 
        m_image = new Image(); 
        m_textureId = TextureHelper.loadTexture(context, R.drawable.icon); 
    } 
 
    @Override 
    public void onSurfaceChanged(GL10 gl, int width, int height) { 
         
        // TODO Auto-generated method stub 
        glViewport(0, 0, width, height); 
        float aspect = (float)width/height; 
         
        MatrixHelper.perspectiveM(projectionMatrix, 45, aspect, 1f, 10f); 
         
    } 
 
    @Override 
    public void onDrawFrame(GL10 gl) { 
        // TODO Auto-generated method stub 
        glClear(GL_COLOR_BUFFER_BIT); 
         
        setIdentityM(modelMatrix, 0); 
        scaleM(modelMatrix,0,0.6f,0.6f,1.0f); 
        translateM(modelMatrix, 0, 0, 0.5f, -2); 
        multiplyMM(modelViewProjectionMatrix, 0, projectionMatrix, 0, modelMatrix, 0); 
         
         
        m_textureShaderProgram.useProgram(); 
        m_textureShaderProgram.setUniforms(modelViewProjectionMatrix, m_textureId);
        m_image.bindData(m_textureShaderProgram); 
        m_image.draw(); 
         
         
         
        setIdentityM(modelMatrix, 0); 
        scaleM(modelMatrix,0,0.6f,-0.6f,1.0f); 
        translateM(modelMatrix, 0, 0, 0.5f, -2); 
        multiplyMM(modelViewProjectionMatrix, 0, projectionMatrix, 0, modelMatrix, 0); 
         
        m_inverseTextureShaderProgram.useProgram(); 
        m_inverseTextureShaderProgram.setUniforms(modelViewProjectionMatrix, m_textureId); 
        m_image.bindData(m_inverseTextureShaderProgram); 
        m_image.draw(); 
         
         
    } 
 
 
    private final float[] projectionMatrix = new float[16]; 
    private final float[] modelMatrix = new float[16]; 
     
    private final float[] modelViewProjectionMatrix = new float[16]; 
     
    private TextureShaderProgram m_textureShaderProgram; 
    private TextureShaderProgram m_inverseTextureShaderProgram; 
     
    private Context context; 
    private Image m_image; 
    private int m_textureId; 
} 
