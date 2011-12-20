/*
 * WebGL / Javascript tutorial.
 * Author: Hartmut Schirmacher, hschirmacher.beuth-hochschule.de
 * (C)opyright 2011 by Hartmut Schirmacher, all rights reserved. 
 *
 */


/* 

   Class: VertexBasedShape
   The shape holds an array of vertices, and knows how to 
   draw itself using WebGL.
    
    Parameters to the constructor:
    - program is the Program that these buffer objects shall be bound to  
    - primitiveType is the geometric primitive to be used for drawing,
      e.g. gl.TRIANGLES, gl.LINES, gl.POINTS, gl.TRIANGLE_STRIP, 
            gl.TRIANGLE_FAN
    - numVertices is the number of vertices this object consists of
*/ 


VertexBasedShape = function(gl, primitiveType, numVertices, transform) {

    // arrays in which to store vertex buffers and the respective
    this.vertexBuffers = new Array();
    
    // remember what goemtric primitive to use for drawing
    this.primitiveType = primitiveType;
    
    // remember how many vertices this shape has
    this.numVertices = numVertices;
    
    // add a vertex attribute to the shape
    this.addVertexAttribute = function(gl, attrType, dataType, 
                                        numElements,dataArray) {
        this.vertexBuffers[attrType] = new VertexAttributeBuffer(gl,
                                            attrType, dataType,
                                            numElements,dataArray);
        var n = this.vertexBuffers[attrType].numVertices;
        if(this.numVertices != n) {
            window.console.log("Warning: wrong number of vertices (" 
                                + n + " instead of " + this.numVertices 
                                + ") for attribute " + attrType);
        }
    }
    
    /* 
       Method: draw using a vertex buffer object
    */
    this.draw = function(program) {

        var ntLocation = gl.getUniformLocation(program.glProgram,
                                                "nodeTransform");
        if(ntLocation == null) {
            window.console.log("Warning: uniform nodeTransform not used in shader.");
        } else {
            gl.uniformMatrix4fv(ntLocation, false, transform);
        }
        // go through all types of vertex attributes
        // and enable them before drawing
        for(attribute in this.vertexBuffers) {
            //window.console.log("activating attribute: " + attribute);
            this.vertexBuffers[attribute].makeActive(program);
        }

        // perform the actual drawing of the primitive
        // using the vertex buffer object
        //window.console.log("drawing shape with " + 
        //                    this.numVertices + " vertices.");
        program.gl.drawArrays(primitiveType, 0, this.numVertices);

    }
}
             
/* 

   Class:  Triangle
   The triangle consists of three vertices. 
    
   Parameters to the constructor:
   - program is a Program object that knows which vertex attributes 
     are expected by its shaders
   
*/ 

Triangle = function(gl, transform) {

    // instantiate the shape as a member variable
    this.shape = new VertexBasedShape(gl, gl.TRIANGLES, 3, transform);

    var vposition = new Float32Array( [ 0,1,0,  -1,-1,0, 1,-1,0 ]);
    var vcolor    = new Float32Array( [ 1,0,0,  0,1,0,   0,0,1 ]);
    this.shape.addVertexAttribute(gl, "vertexPosition", gl.FLOAT, 3,
                                  vposition);
    this.shape.addVertexAttribute(gl, "vertexColor",    gl.FLOAT, 3, 
                                  vcolor);
    
}        
    

/* 

   Class:  TriangleFan
   A little fan around a center vertex. 
    
   Parameters to the constructor:
   - program is a Program object that knows which vertex attributes 
     are expected by its shaders
   
*/ 

TriangleFan = function(gl, transform) {

    // instantiate the shape as a member variable
    this.shape = new VertexBasedShape(gl, gl.TRIANGLE_FAN, 9, transform);

    var vposition = new Float32Array( [ 0,0,1,        0,1,0,       -0.7,0.7,0,
                                        -1,0,0,      -0.7,-0.7,0,  0,-1,0,
                                        0.7,-0.7,0,  1.0,0,0,      0.7,0.7,0]);
    var vcolor    = new Float32Array( [ 1,1,1,  1,0,0,  0,1,0,      
                                        0,0,1,  1,0,0,  0,1,0,  
                                        0,0,1,  1,0,0,  0,1,0,    ]);
    this.shape.addVertexAttribute(gl, "vertexPosition", gl.FLOAT, 3, vposition);
    this.shape.addVertexAttribute(gl, "vertexColor",    gl.FLOAT, 3, vcolor);
    
}        
    

Cube = function(gl, l, transform) {

    var l2 = l / 2;
    this.shape = new VertexBasedShape(gl, gl.TRIANGLES, 36, transform);

    var vposition   = new Float32Array([    -1,-1,-1,    1,-1,-1,    1, 1,-1,       // abc
                                            -1,-1,-1,    1, 1,-1,   -1, 1,-1,       // acd
                                             1,-1,-1,    1, 1,-1,    1,-1, 1,       // bcf
                                             1, 1,-1,    1,-1, 1,    1, 1, 1,       // cfg
                                             1,-1, 1,    1, 1, 1,   -1, 1, 1,       // fgh
                                            -1,-1, 1,    1,-1, 1,   -1, 1, 1,       // efh
                                            -1,-1, 1,   -1, 1,-1,   -1, 1, 1,       // edh
                                            -1,-1,-1,   -1, 1,-1,   -1,-1, 1,       // ade
                                            -1,-1,-1,    1,-1,-1,   -1,-1, 1,       // abe
                                             1,-1,-1,   -1,-1, 1,    1,-1, 1,       // bef
                                             1, 1,-1,   -1, 1,-1,    1, 1, 1,       // cdg
                                            -1, 1,-1,    1, 1, 1,   -1, 1, 1]);     // dgh

    for (var i=0; i<108; i++){
        vposition[i]=vposition[i] * l2;
    }

    var vcolor       = new Float32Array([    1, 0, 0,    1, 0, 0,    1, 0, 0,
                                             1, 0, 0,    1, 0, 0,    1, 0, 0,
                                             0, 1, 0,    0, 1, 0,    0, 1, 0,
                                             0, 1, 0,    0, 1, 0,    0, 1, 0,
                                             0, 0, 1,    0, 0, 1,    0, 0, 1,
                                             0, 0, 1,    0, 0, 1,    0, 0, 1,
                                             1, 1, 0,    1, 1, 0,    1, 1, 0,
                                             1, 1, 0,    1, 1, 0,    1, 1, 0,
                                             0, 1, 1,    0, 1, 1,    0, 1, 1,
                                             0, 1, 1,    0, 1, 1,    0, 1, 1,
                                             1, 0, 1,    1, 0, 1,    1, 0, 1,
                                             1, 0, 1,    1, 0, 1,    1, 0, 1]);

    this.shape.addVertexAttribute(gl, "vertexPosition", gl.FLOAT, 3, vposition);
    this.shape.addVertexAttribute(gl, "vertexColor",    gl.FLOAT, 3, vcolor);

}

Sphere = function (gl, radius, n, m, color1, color2, transform){

    var PI = Math.PI;
    this.radius = radius;

    this.x = function (u, v) {
        if(u < 0 || PI < u) return 0;
        if(v < 0 || 2*PI < v) return 0;
        return this.radius * Math.sin(u) * Math.cos(v);
    }

    this.y = function (u, v) {
        if(u < 0 || PI < u) return 0;
        if(v < 0 || 2*PI < v) return 0;
        return this.radius * Math.sin(u) * Math.sin(v);
    }

    this.z  = function (u) {
        if(u < 0 || PI < u) return 0;
        return this.radius * Math.cos(u);
    }

    this.shape = new VertexBasedShape(gl, gl.TRIANGLES, n*m*6, transform);

    var vposition = new Float32Array(n*m*6*3);
    var vcolor = new Float32Array(n*m*6*3);
    var index = 0;
    var c = color1
    for (var i = 1; i<=n; i++){
        for (var j = 1; j<=m; j++){

            c = ((i + j) % 2 == 0) ? color1 : color2

            var ui1 = PI / n * i;
            var ui0 = PI / n * (i-1);
            var vj1 = 2 * PI / m * j;
            var vj0 = 2 * PI / m * (j-1);

            vcolor[index] = c[0];
            vposition[index++] = this.x(ui0, vj0);
            vcolor[index] = c[1];
            vposition[index++] = this.y(ui0, vj0);
            vcolor[index] = c[2];
            vposition[index++] = this.z(ui0);

            vcolor[index] = c[0];
            vposition[index++] = this.x(ui1, vj0);
            vcolor[index] = c[1];
            vposition[index++] = this.y(ui1, vj0);
            vcolor[index] = c[2];
            vposition[index++] = this.z(ui1);

            vcolor[index] = c[0];
            vposition[index++] = this.x(ui1, vj1);
            vcolor[index] = c[1];
            vposition[index++] = this.y(ui1, vj1);
            vcolor[index] = c[2];
            vposition[index++] = this.z(ui1);


            vcolor[index] = c[0];
            vposition[index++] = this.x(ui0, vj0);
            vcolor[index] = c[1];
            vposition[index++] = this.y(ui0, vj0);
            vcolor[index] = c[2];
            vposition[index++] = this.z(ui0);

            vcolor[index] = c[0];
            vposition[index++] = this.x(ui0, vj1);
            vcolor[index] = c[1];
            vposition[index++] = this.y(ui0, vj1);
            vcolor[index] = c[2];
            vposition[index++] = this.z(ui0);

            vcolor[index] = c[0];
            vposition[index++] = this.x(ui1, vj1);
            vcolor[index] = c[1];
            vposition[index++] = this.y(ui1, vj1);
            vcolor[index] = c[2];
            vposition[index++] = this.z(ui1);

        }

        this.shape.addVertexAttribute(gl, "vertexPosition", gl.FLOAT, 3, vposition);
        this.shape.addVertexAttribute(gl, "vertexColor",    gl.FLOAT, 3, vcolor);
    }

}