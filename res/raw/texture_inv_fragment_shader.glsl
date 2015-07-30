precision mediump float;

uniform sampler2D u_TextureUnit;
varying vec2 v_TextureCoordinates;

void main()
{
    vec4 baseColor = texture2D(u_TextureUnit,v_TextureCoordinates);
    baseColor = baseColor * v_TextureCoordinates.y;
    gl_FragColor = baseColor;
}