package com.huangtao.androidopengl.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import static android.opengl.GLES20.*;
import static android.opengl.GLUtils.*;
 
public class TextureHelper {
     
    public static int loadTexture(Context context, int resourceId)
    {
        final int[] textureObjectIds = new int[1];
         
        glGenTextures(1,textureObjectIds,0);
         
        if(textureObjectIds[0] == 0)
        {
            if(LoggerConfig.ON)
            {
                Log.w(TAG,"could not generate a new OpenGL texture object");
            }
        }
         
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
         
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
         
        if(bitmap == null)
        {
            if(LoggerConfig.ON)
            {
                Log.w(TAG,"Resouce ID " + resourceId + "could not be decoded");
            }
             
            glDeleteTextures(1,textureObjectIds,0);
             
            return 0;
        }
         
        /*
         * once texture object IDs have been generated with glGenTextures, the application must bind the texture object to operate on it. once
 
           texture object are bound, subsequent operation such as glTexImage2D and glTexParameter affect the bound texture object.
 
         */
        glBindTexture(GL_TEXTURE_2D,textureObjectIds[0]);
         
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_LINEAR);
         
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
         
        int internalFormat = getInternalFormat(bitmap);
         
        System.out.println("internalFormat :" + internalFormat);
         
        texImage2D(GL_TEXTURE_2D,0,bitmap,0);
         
        bitmap.recycle();
         
        glGenerateMipmap(GL_TEXTURE_2D);
         
        glBindTexture(GL_TEXTURE_2D,0);
         
         
        return textureObjectIds[0];
         
         
    }
     
    private static final String TAG = "TextureHelper";
 
}
