CREATE TABLE usuario (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL UNIQUE,
    password VARCHAR2(255) NOT NULL
);
CREATE TABLE producto (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    descripcion VARCHAR2(255) NOT NULL,
    precio NUMBER(10,2) NOT NULL,
    categoria VARCHAR2(50) NOT NULL
);
CREATE TABLE inventario (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    producto_id NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    CONSTRAINT fk_inventario_producto
        FOREIGN KEY (producto_id)
        REFERENCES producto(id)
);
CREATE TABLE carrito (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    usuario_id NUMBER NOT NULL,
    CONSTRAINT fk_carrito_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario(id)
);
CREATE TABLE item_carrito (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    carrito_id NUMBER NOT NULL,
    producto_id NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    CONSTRAINT fk_item_carrito_carrito
        FOREIGN KEY (carrito_id)
        REFERENCES carrito(id),
    CONSTRAINT fk_item_carrito_producto
        FOREIGN KEY (producto_id)
        REFERENCES producto(id)
);