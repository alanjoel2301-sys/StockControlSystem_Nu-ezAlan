DROP DATABASE IF EXISTS db_stock_control;

CREATE DATABASE db_stock_control
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE db_stock_control;

-- ------------------------------------------------------------
-- Tabla: usuarios
-- ------------------------------------------------------------
CREATE TABLE usuarios (
    id_usuario       INT AUTO_INCREMENT PRIMARY KEY,
    username         VARCHAR(50)  NOT NULL UNIQUE,
    password_hash    VARCHAR(64)  NOT NULL,
    salt             VARCHAR(32)  NOT NULL,
    nombre_completo  VARCHAR(100) NOT NULL,
    rol              ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER',
    activo           BOOLEAN NOT NULL DEFAULT TRUE
) ENGINE = InnoDB;

-- ------------------------------------------------------------
-- Tabla: categorias
-- ------------------------------------------------------------
CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(80)  NOT NULL UNIQUE,
    descripcion  VARCHAR(255)
) ENGINE = InnoDB;

-- ------------------------------------------------------------
-- Tabla: productos
-- ------------------------------------------------------------
CREATE TABLE productos (
    id_producto   INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(120)   NOT NULL UNIQUE,
    descripcion   VARCHAR(255),
    id_categoria  INT            NOT NULL,
    precio        DECIMAL(10,2)  NOT NULL DEFAULT 0,
    stock_actual  INT            NOT NULL DEFAULT 0,
    stock_minimo  INT            NOT NULL DEFAULT 0,
    fecha_alta    DATE           NOT NULL,
    CONSTRAINT fk_producto_categoria
        FOREIGN KEY (id_categoria) REFERENCES categorias (id_categoria)
        ON DELETE RESTRICT
    ,CONSTRAINT chk_producto_precio CHECK (precio > 0),
    CONSTRAINT chk_producto_stock_actual CHECK (stock_actual >= 0),
    CONSTRAINT chk_producto_stock_minimo CHECK (stock_minimo >= 0)
) ENGINE = InnoDB;

-- ------------------------------------------------------------
-- Tabla: movimientos
-- ------------------------------------------------------------
CREATE TABLE movimientos (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_producto   INT             NOT NULL,
    tipo          ENUM('ENTRADA','SALIDA') NOT NULL,
    cantidad      INT             NOT NULL,
    fecha         DATETIME        NOT NULL,
    motivo        VARCHAR(255),
    usuario       VARCHAR(100),
    CONSTRAINT fk_movimiento_producto
        FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
        ON DELETE RESTRICT
    ,CONSTRAINT chk_movimiento_cantidad CHECK (cantidad > 0)
) ENGINE = InnoDB;

CREATE INDEX idx_movimientos_fecha ON movimientos (fecha);
CREATE INDEX idx_movimientos_producto ON movimientos (id_producto);

INSERT INTO usuarios (username, password_hash, salt, nombre_completo, rol, activo) VALUES
    ('admin', '1ec3a9d454d6e261fb22cdc6c70229ebe5b453866f04b71870f65567c2bec1e1', 'a3f1c9e7b2d84f6013a9e5c7b1d2f4a8',
     'Administrador del Sistema', 'ADMIN', TRUE);