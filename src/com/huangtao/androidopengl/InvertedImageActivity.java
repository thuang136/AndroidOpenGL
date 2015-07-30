package com.huangtao.androidopengl;

import android.app.Activity; 
import android.opengl.GLSurfaceView; 
import android.os.Build; 
import android.os.Bundle; 
 
 
public class InvertedImageActivity extends Activity { 
 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        glSurfaceView = new GLSurfaceView(this); 
         
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) 
        { 
            glSurfaceView.setEGLContextClientVersion(2); 
            glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 24, 8); 
            glSurfaceView.setRenderer(new InvertedImageRenderer(this)); 
            glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); 
            rendererSet = true; 
        } 
        setContentView(glSurfaceView); 
 
    } 
     
     
    public void onPause() 
    { 
        System.out.println("onPause"); 
        super.onPause(); 
        if(rendererSet) 
        { 
            glSurfaceView.onPause(); 
        } 
    } 
     
    public void onResume() 
    { 
        System.out.println("onResume"); 
        super.onResume(); 
        if(rendererSet) 
        { 
            glSurfaceView.onResume(); 
        } 
    } 
 
     
    private GLSurfaceView glSurfaceView; 
    private boolean rendererSet = false; 
} 