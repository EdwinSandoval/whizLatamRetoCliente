CREATE TABLE cliente (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nombres VARCHAR(100) NOT NULL,
                         apellido_paterno VARCHAR(100) NOT NULL,
                         apellido_materno VARCHAR(100) NOT NULL,
                         fecha_nacimiento DATE NOT NULL,
                         sexo VARCHAR(10) NOT NULL,
                         correo VARCHAR(100) NOT NULL UNIQUE,
                         telefono VARCHAR(15)
);