CREATE DATABASE seriprint;
USE seriprint;
-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generaciÃ³n: 27-08-2020 a las 16:29:41
-- VersiÃ³n del servidor: 8.0.18
-- VersiÃ³n de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `seriprint`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anuncios`
--

DROP TABLE IF EXISTS `anuncios`;
CREATE TABLE IF NOT EXISTS `anuncios` (
  `idAnuncio` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `descripcion` varchar(500) NOT NULL,
  `img` varchar(100) NOT NULL,
  PRIMARY KEY (`idAnuncio`) USING BTREE
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bodega`
--

DROP TABLE IF EXISTS `bodega`;
CREATE TABLE IF NOT EXISTS `bodega` (
  `idBodega` int(4) NOT NULL AUTO_INCREMENT,
  `Ubicacion` varchar(100) NOT NULL,
  `Capacidad_bodega` varchar(50) NOT NULL,
  PRIMARY KEY (`idBodega`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `bodega`
--

INSERT INTO `bodega` (`idBodega`, `Ubicacion`, `Capacidad_bodega`) VALUES
(3, 'B-123', '10'),
(4, 'Estanteria 2', '10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
CREATE TABLE IF NOT EXISTS `cotizacion` (
  `idCotizacion` int(4) NOT NULL AUTO_INCREMENT,
  `num_factura` varchar(20) NOT NULL,
  `idUsuario` int(4) NOT NULL,
  `idProducto` int(4) DEFAULT NULL,
  `idServicio` int(4) DEFAULT NULL,
  `Detalle` varchar(300) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `precio_compra` double NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Estampado` int(11) DEFAULT NULL,
  `Estado` enum('Asignada','Cancelado','Entregado','Pendiente','Proceso de producciÃ³n','VerificaciÃ³n de pago') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idCotizacion`),
  UNIQUE KEY `num_factura` (`num_factura`),
  KEY `idRol` (`idUsuario`,`idProducto`,`idServicio`),
  KEY `idProducto` (`idProducto`),
  KEY `idServicio` (`idServicio`),
  KEY `Estampado` (`Estampado`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cotizacion`
--

INSERT INTO `cotizacion` (`idCotizacion`, `num_factura`, `idUsuario`, `idProducto`, `idServicio`, `Detalle`, `Cantidad`, `precio_compra`, `Fecha`, `Hora`, `Estampado`, `Estado`) VALUES
(52, 'UW3155TU', 11, 19, NULL, 'detalle', 33, 660000, '2020-08-27', '01:32:48', NULL, 'Entregado'),
(53, 'BD6260AB', 11, 19, NULL, 'asdasd', 8, 160000, '2020-08-27', '13:18:13', NULL, 'VerificaciÃ³n de pago'),
(54, 'CE4383BC', 11, 19, NULL, 'adasdfas', 15, 300000, '2020-08-27', '13:19:50', NULL, 'Pendiente'),
(55, 'LN4471KL', 11, 19, NULL, 'asdasd', 16, 320000, '2020-08-27', '13:21:42', NULL, 'Pendiente'),
(56, 'GI4908FG', 11, 19, NULL, 'Este sirve', 8, 160000, '2020-08-27', '13:32:28', NULL, 'Entregado'),
(57, 'FH10124EF', 12, 22, NULL, 'asdasdasdadasda', 65, 1950000, '2020-08-27', '14:39:56', NULL, 'Entregado'),
(58, 'VX1743UV', 13, 22, NULL, 'asdadasd', 14, 420000, '2020-08-27', '15:52:14', NULL, 'Entregado'),
(59, 'DF6022CD', 13, 24, NULL, 'sdfsf', 16, 1777760, '2020-08-27', '16:03:18', NULL, 'Pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estampado`
--

DROP TABLE IF EXISTS `estampado`;
CREATE TABLE IF NOT EXISTS `estampado` (
  `idEstampado` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(70) NOT NULL,
  `img` varchar(100) NOT NULL,
  `precio` double DEFAULT NULL,
  PRIMARY KEY (`idEstampado`)
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos`
--

DROP TABLE IF EXISTS `insumos`;
CREATE TABLE IF NOT EXISTS `insumos` (
  `idInsumo` int(4) NOT NULL AUTO_INCREMENT,
  `idTipo_insumo` int(4) NOT NULL,
  `idInventario` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Cantidad` int(7) NOT NULL,
  `Precio_compra` double NOT NULL,
  `Fecha_vencimiento` date NOT NULL,
  PRIMARY KEY (`idInsumo`),
  KEY `idTipo_insumo` (`idTipo_insumo`),
  KEY `idInventario` (`idInventario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `insumos`
--

INSERT INTO `insumos` (`idInsumo`, `idTipo_insumo`, `idInventario`, `Nombre`, `Cantidad`, `Precio_compra`, `Fecha_vencimiento`) VALUES
(2, 2, 27, 'Marco ', 56, 100000, '2020-08-26');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

DROP TABLE IF EXISTS `inventario`;
CREATE TABLE IF NOT EXISTS `inventario` (
  `idInventario` int(4) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(4) NOT NULL,
  `idBodega` int(4) NOT NULL,
  `Fecha_ingreso` date NOT NULL,
  `estado` enum('Stock','Sin stock') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idInventario`),
  KEY `idRol` (`idUsuario`),
  KEY `idBodega` (`idBodega`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`idInventario`, `idUsuario`, `idBodega`, `Fecha_ingreso`, `estado`) VALUES
(18, 11, 3, '2020-08-26', 'Stock'),
(19, 11, 3, '2020-08-26', 'Stock'),
(20, 11, 3, '2020-08-27', 'Stock'),
(21, 11, 4, '2020-08-27', 'Stock'),
(22, 11, 3, '2020-08-27', 'Stock'),
(23, 11, 3, '2020-08-27', 'Stock'),
(24, 11, 3, '2020-08-27', 'Stock'),
(25, 11, 3, '2020-08-27', 'Stock'),
(26, 11, 3, '2020-08-27', 'Stock'),
(27, 7, 3, '2020-08-27', 'Stock'),
(28, 11, 3, '2020-08-27', 'Sin stock'),
(29, 12, 3, '2020-08-27', 'Stock'),
(30, 12, 3, '2020-08-27', 'Stock'),
(31, 12, 3, '2020-08-27', 'Sin stock'),
(32, 13, 3, '2020-08-27', 'Stock'),
(33, 13, 3, '2020-08-27', 'Stock');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden_compra`
--

DROP TABLE IF EXISTS `orden_compra`;
CREATE TABLE IF NOT EXISTS `orden_compra` (
  `idPedido` int(4) NOT NULL AUTO_INCREMENT,
  `idCotizacion` int(11) NOT NULL,
  `idOperario` int(11) DEFAULT NULL,
  `Abona` double DEFAULT NULL,
  `Saldo` double DEFAULT NULL,
  `Observacion` varchar(600) NOT NULL,
  `FechaCompra` date NOT NULL,
  `horaCompra` time NOT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `horaEntrega` time DEFAULT NULL,
  `Soporte` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `idCotizacion` (`idCotizacion`),
  KEY `idOperario` (`idOperario`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `orden_compra`
--

INSERT INTO `orden_compra` (`idPedido`, `idCotizacion`, `idOperario`, `Abona`, `Saldo`, `Observacion`, `FechaCompra`, `horaCompra`, `fechaEntrega`, `horaEntrega`, `Soporte`) VALUES
(13, 52, 10, NULL, NULL, 'asdasdasda', '2020-08-27', '01:33:27', '2020-08-27', '14:24:03', '../docs/eje-factura.pdf'),
(14, 53, NULL, NULL, NULL, 'asdasdasda', '2020-08-27', '13:18:41', NULL, NULL, '../docs/eje-factura.pdf'),
(15, 56, 8, NULL, NULL, 'asdasdasda', '2020-08-27', '13:32:42', '2020-08-27', '16:01:45', '../docs/eje-factura.pdf'),
(16, 57, 10, NULL, NULL, 'asdasdasda', '2020-08-27', '14:43:02', '2020-08-27', '14:46:53', '../docs/eje-factura.pdf'),
(17, 58, 8, NULL, NULL, 'sadad', '2020-08-27', '15:52:31', '2020-08-27', '16:01:33', '../docs/eje-factura.pdf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

DROP TABLE IF EXISTS `permisos`;
CREATE TABLE IF NOT EXISTS `permisos` (
  `idPermisos` int(4) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `name` varchar(30) NOT NULL,
  `Url` text NOT NULL,
  `Icon` varchar(50) NOT NULL,
  `Permiso_padre` int(4) NOT NULL,
  PRIMARY KEY (`idPermisos`),
  KEY `Permiso_padre` (`Permiso_padre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `idProducto` int(4) NOT NULL AUTO_INCREMENT,
  `idTipo_producto` int(4) NOT NULL,
  `idInventario` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  `Imagen` varchar(100) NOT NULL,
  `Precio_unidad` double NOT NULL,
  `Precio_venta` double DEFAULT NULL,
  `Cantidad` int(11) NOT NULL,
  `Descuento` double DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `idTipo_producto` (`idTipo_producto`),
  KEY `idInventario` (`idInventario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `idTipo_producto`, `idInventario`, `Nombre`, `Descripcion`, `Imagen`, `Precio_unidad`, `Precio_venta`, `Cantidad`, `Descuento`) VALUES
(19, 1, 28, 'Camiseta estampada', 'Descripcion', '../img/camiseta.PNG', 20000, 16600, -27, 17),
(20, 1, 29, 'Camiseta estampada', 'Descripcion', '../img/camiseta.PNG', 20000, 18000, 30, 10),
(21, 1, 30, 'Camiseta estampada', 'Descripcion', '../img/camiseta.PNG', 20000, 18000, 30, 10),
(22, 1, 31, 'Mochila', 'Descripcion', '../img/mochila.PNG', 30000, 21000, -129, 30),
(23, 1, 32, 'Camiseta estampada sin descuento', 'Descripcion', '../img/camiseta.PNG', 0, 0, 30, 10),
(24, 1, 33, 'asdasdasdasd', 'asdasdasd', 'asdasdada', 111110, 111110, 50, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `idRol` int(4) NOT NULL AUTO_INCREMENT,
  `Nombre_rol` varchar(50) NOT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`idRol`, `Nombre_rol`) VALUES
(1, 'Administrador'),
(2, 'Operario'),
(3, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_has_permisos`
--

DROP TABLE IF EXISTS `roles_has_permisos`;
CREATE TABLE IF NOT EXISTS `roles_has_permisos` (
  `idRoles_has_permisos` int(4) NOT NULL AUTO_INCREMENT,
  `idRol` int(4) NOT NULL,
  `idPermisos` int(4) NOT NULL,
  PRIMARY KEY (`idRoles_has_permisos`),
  KEY `idRol` (`idRol`,`idPermisos`),
  KEY `idPermisos` (`idPermisos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

DROP TABLE IF EXISTS `servicios`;
CREATE TABLE IF NOT EXISTS `servicios` (
  `idServicio` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  `Precio` double NOT NULL,
  PRIMARY KEY (`idServicio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`idServicio`, `Nombre`, `Descripcion`, `Precio`) VALUES
(1, 'Estapados', 'Sobre textiles como camisetas, toallas, overoles, PlÃ¡sticos flexibles, plÃ¡sticos rÃ­gidos, vidrio, metales, madera, acrilicos, poliestilenos, papel, caucho, corcho y demas solo preguntanos.', 20000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_de_insumo`
--

DROP TABLE IF EXISTS `tipo_de_insumo`;
CREATE TABLE IF NOT EXISTS `tipo_de_insumo` (
  `idTipo_insumo` int(4) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  PRIMARY KEY (`idTipo_insumo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_de_insumo`
--

INSERT INTO `tipo_de_insumo` (`idTipo_insumo`, `Nombre`, `Descripcion`) VALUES
(1, 'Emulsiones', 'Es un producto que se aplica a una pantalla con una raedera de forma manual o gracias a maquinas de emulsionado para poder serigrafiar nuestro diseÃ±o en las prendas o sobre el soporte que necesitemos serigrafiar.'),
(2, 'Limpiadores', 'Es un disolvente de limpieza de serigrafia adecuado para limpiar plastisol en la pantalla y resto de tintas durante la impresiÃ³n, la malla y los Ãºtiles de trabajo.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_de_producto`
--

DROP TABLE IF EXISTS `tipo_de_producto`;
CREATE TABLE IF NOT EXISTS `tipo_de_producto` (
  `idTipo_producto` int(4) NOT NULL AUTO_INCREMENT,
  `Nombres` set('Marcos/Bastidores - Sedas/Tejidos','Escobillines/Raseros','Tintas LitogrÃ¡ficas - Tintas para Screen','Maquinaria') NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  PRIMARY KEY (`idTipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_de_producto`
--

INSERT INTO `tipo_de_producto` (`idTipo_producto`, `Nombres`, `Descripcion`) VALUES
(1, 'Marcos/Bastidores - Sedas/Tejidos', 'Productos de estructura para la creaciÃ³n de un estampado.'),
(2, 'Escobillines/Raseros', 'Herramientas empleadas para pasar la tinta por la pantalla y depositarla sobre la prenda o material textil.'),
(3, 'Tintas LitogrÃ¡ficas - Tintas para Screen', 'Tintas de impreciÃ³n.'),
(4, 'Maquinaria', 'Maquinarias empleadas en cada uno de los procesos neserarios de los servicios solicitados por los clientes ya sea Estampado, Emulsionado, Recuperado de malla, Tenzado u Artes Finales.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `idUsuario` int(4) NOT NULL AUTO_INCREMENT,
  `idRol` int(4) NOT NULL,
  `Ndocumento` int(4) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `Direccion` varchar(50) NOT NULL,
  `Telefono` int(4) NOT NULL,
  `Correo_electronico` varchar(50) NOT NULL,
  `Contrasena` varchar(12) NOT NULL,
  `Fecha` date NOT NULL,
  `Estado` enum('Activo','Inactivo') NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idRol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `idRol`, `Ndocumento`, `Nombres`, `Apellidos`, `Direccion`, `Telefono`, `Correo_electronico`, `Contrasena`, `Fecha`, `Estado`) VALUES
(7, 1, 1009098234, 'David', 'Mendoza', 'Suba', 32991832, 'David@gmail.com', '123', '2020-01-23', 'Activo'),
(8, 2, 100393292, 'Jorge', 'Aguirre', 'La Castellana', 82528, 'luisdahr2002@gmail.com', '123', '2020-01-23', 'Activo'),
(9, 3, 10735232, 'Maria', 'Ezequiel', 'La loma', 12335, 'mrpte@gmial.com', '123', '2020-01-26', 'Activo'),
(10, 2, 1089253, 'Lara', 'Lara', 'Patio bonito', 21311, 'lara@mga.com', '123', '2020-01-23', 'Activo'),
(11, 3, 1009095732, 'Jhon', 'Medina', 'La 8ta', 312345321, 'jhonmedina345@gmail.com', '123', '2020-08-26', 'Activo'),
(12, 3, 10094738, 'Wilmer', 'Bohorquez', 'DSasdads', 32134234, 'wdbohorquez77@misena.edu.co', '123', '2020-01-26', 'Activo'),
(13, 3, 10094838, 'Gozalo', 'Ortiz', 'sdadsd', 123123, 'wadsdsa', '123', '2020-01-26', 'Activo');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cotizacion`
--
ALTER TABLE `cotizacion`
  ADD CONSTRAINT `cotizacion_ibfk_1` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cotizacion_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cotizacion_ibfk_4` FOREIGN KEY (`idServicio`) REFERENCES `servicios` (`idServicio`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cotizacion_ibfk_7` FOREIGN KEY (`Estampado`) REFERENCES `estampado` (`idEstampado`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `insumos`
--
ALTER TABLE `insumos`
  ADD CONSTRAINT `insumos_ibfk_1` FOREIGN KEY (`idTipo_insumo`) REFERENCES `tipo_de_insumo` (`idTipo_insumo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `insumos_ibfk_2` FOREIGN KEY (`idInventario`) REFERENCES `inventario` (`idInventario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inventario_ibfk_2` FOREIGN KEY (`idBodega`) REFERENCES `bodega` (`idBodega`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `orden_compra`
--
ALTER TABLE `orden_compra`
  ADD CONSTRAINT `orden_compra_ibfk_1` FOREIGN KEY (`idCotizacion`) REFERENCES `cotizacion` (`idCotizacion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orden_compra_ibfk_2` FOREIGN KEY (`idOperario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `permisos_ibfk_1` FOREIGN KEY (`Permiso_padre`) REFERENCES `permisos` (`idPermisos`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`idTipo_producto`) REFERENCES `tipo_de_producto` (`idTipo_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`idInventario`) REFERENCES `inventario` (`idInventario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `roles_has_permisos`
--
ALTER TABLE `roles_has_permisos`
  ADD CONSTRAINT `roles_has_permisos_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `roles` (`idRol`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `roles_has_permisos_ibfk_2` FOREIGN KEY (`idPermisos`) REFERENCES `permisos` (`idPermisos`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `roles` (`idRol`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
