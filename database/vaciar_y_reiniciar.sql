USE db_stock_control;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE movimientos;
TRUNCATE TABLE productos;
TRUNCATE TABLE categorias;
TRUNCATE TABLE usuarios;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO usuarios (username, password_hash, salt, nombre_completo, rol, activo) VALUES
    ('admin', '1ec3a9d454d6e261fb22cdc6c70229ebe5b453866f04b71870f65567c2bec1e1', 'a3f1c9e7b2d84f6013a9e5c7b1d2f4a8',
     'Administrador del Sistema', 'ADMIN', TRUE);