CREATE DATABASE seriprint;
USE seriprint;
-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generación: 24-08-2020 a las 07:41:48
-- Versión del servidor: 8.0.18
-- Versión de PHP: 7.3.12

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
-- Estructura de tabla para la tabla `agregar_pago`
--

DROP TABLE IF EXISTS `agregar_pago`;
CREATE TABLE IF NOT EXISTS `agregar_pago` (
  `idPedido` int(4) NOT NULL AUTO_INCREMENT,
  `idCotizacion` int(11) NOT NULL,
  `Abona` double DEFAULT NULL,
  `Saldo` double DEFAULT NULL,
  `Observacion` varchar(600) NOT NULL,
  `Fecha` date NOT NULL,
  `Soporte` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `idCotizacion` (`idCotizacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `Detalle` varchar(300) NOT NULL,
  `idUsuario` int(4) NOT NULL,
  `idProducto` int(4) DEFAULT NULL,
  `idServicio` int(4) DEFAULT NULL,
  `Cantidad` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  `Hora` time NOT NULL,
  `Estampado` int(11) DEFAULT NULL,
  `Estado` enum('Asignada','Cancelada','Entregado','Pendiente','Proceso de producción','Verificación de pago') NOT NULL,
  PRIMARY KEY (`idCotizacion`),
  KEY `idRol` (`idUsuario`,`idProducto`,`idServicio`),
  KEY `idProducto` (`idProducto`),
  KEY `idServicio` (`idServicio`),
  KEY `Estampado` (`Estampado`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cotizacion`
--

INSERT INTO `cotizacion` (`idCotizacion`, `Detalle`, `idUsuario`, `idProducto`, `idServicio`, `Cantidad`, `Fecha`, `Hora`, `Estampado`, `Estado`) VALUES
(1, 'eee', 4, 2, NULL, 0, '2020-08-24', '01:39:34', NULL, 'Pendiente'),
(2, 'eee', 4, 3, NULL, 0, '2020-08-24', '01:39:54', NULL, 'Pendiente');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos`
--

DROP TABLE IF EXISTS `insumos`;
CREATE TABLE IF NOT EXISTS `insumos` (
  `idInsumo` int(4) NOT NULL AUTO_INCREMENT,
  `idTipo_insumo` int(4) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Cantidad` int(7) NOT NULL,
  `Precio_compra` decimal(10,0) NOT NULL,
  `Fecha_vencimiento` date NOT NULL,
  PRIMARY KEY (`idInsumo`),
  KEY `idTipo_insumo` (`idTipo_insumo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `insumos`
--

INSERT INTO `insumos` (`idInsumo`, `idTipo_insumo`, `Nombre`, `Cantidad`, `Precio_compra`, `Fecha_vencimiento`) VALUES
(1, 2, 'Limpiador axion', 2, '1200', '2020-08-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

DROP TABLE IF EXISTS `inventario`;
CREATE TABLE IF NOT EXISTS `inventario` (
  `idInventario` int(4) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(4) NOT NULL,
  `idProducto` int(4) DEFAULT NULL,
  `idInsumo` int(4) DEFAULT NULL,
  `idBodega` int(4) NOT NULL,
  `Fecha_ingreso` date NOT NULL,
  `Fecha_salida` date DEFAULT NULL,
  `estado` enum('Stock','Sin stock') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idInventario`),
  KEY `idRol` (`idUsuario`),
  KEY `idProducto` (`idProducto`,`idInsumo`,`idBodega`),
  KEY `idBodega` (`idBodega`),
  KEY `idInsumo` (`idInsumo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`idInventario`, `idUsuario`, `idProducto`, `idInsumo`, `idBodega`, `Fecha_ingreso`, `Fecha_salida`, `estado`) VALUES
(2, 4, NULL, 1, 4, '2002-02-01', NULL, 'Stock'),
(3, 4, NULL, 1, 4, '2002-02-01', NULL, 'Stock'),
(4, 4, NULL, 1, 4, '2002-02-01', NULL, 'Stock'),
(5, 4, NULL, 1, 4, '2002-02-01', NULL, 'Stock'),
(7, 4, 1, NULL, 4, '2020-08-23', NULL, 'Stock'),
(8, 5, 1, 1, 3, '2020-08-23', NULL, 'Stock'),
(9, 5, 1, 1, 3, '2020-08-23', NULL, 'Stock'),
(10, 5, 1, 1, 3, '2020-08-23', NULL, 'Stock');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario-solicitud`
--

DROP TABLE IF EXISTS `inventario-solicitud`;
CREATE TABLE IF NOT EXISTS `inventario-solicitud` (
  `idInvenSoli` int(11) NOT NULL,
  `idInventario` int(11) NOT NULL,
  `idSolicitud` int(11) NOT NULL,
  PRIMARY KEY (`idInvenSoli`),
  KEY `idInventario` (`idInventario`),
  KEY `idSolicitud` (`idSolicitud`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  `Imagen` varchar(100) NOT NULL,
  `Precio_unidad` decimal(10,0) NOT NULL,
  `Precio_compra` decimal(10,0) DEFAULT NULL,
  `Precio_venta` decimal(10,0) DEFAULT NULL,
  `Cantidad` varchar(500) NOT NULL,
  `Descuento` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idProducto`),
  KEY `idTipo_producto` (`idTipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`idProducto`, `idTipo_producto`, `Nombre`, `Descripcion`, `Imagen`, `Precio_unidad`, `Precio_compra`, `Precio_venta`, `Cantidad`, `Descuento`) VALUES
(1, 2, 'Fall guys', 'Juego', '...', '35000', NULL, NULL, '12', NULL),
(2, 2, 'Mochila', 'Mochila descrip', '../img/mochila.PNG', '1000000', NULL, NULL, '15', NULL),
(3, 2, 'Mochila', 'Mochila descrip', '../img/mochila.PNG', '1000000', NULL, NULL, '15', NULL),
(4, 1, 'Marco ', 'Cuenta con cremallera...', 'img/mochila.PNG', '0', NULL, NULL, '15', NULL),
(5, 1, 'Marco ', 'Cuenta con cremallera...', 'img/mochila.PNG', '0', NULL, NULL, '15', NULL);

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
(1, 'Estapados', 'Sobre textiles como camisetas, toallas, overoles, Plásticos flexibles, plásticos rígidos, vidrio, metales, madera, acrilicos, poliestilenos, papel, caucho, corcho y demas solo preguntanos.', 20000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
CREATE TABLE IF NOT EXISTS `solicitud` (
  `idSolicitud` int(11) NOT NULL AUTO_INCREMENT,
  `idUsuario` int(11) NOT NULL,
  `idPedido` int(11) NOT NULL,
  `fechaLimite` date NOT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `horaEntrega` time DEFAULT NULL,
  PRIMARY KEY (`idSolicitud`),
  KEY `idUsuario` (`idUsuario`),
  KEY `fechaLimite` (`fechaLimite`),
  KEY `idPedido` (`idPedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
(1, 'Emulsiones', 'Es un producto que se aplica a una pantalla con una raedera de forma manual o gracias a maquinas de emulsionado para poder serigrafiar nuestro diseño en las prendas o sobre el soporte que necesitemos serigrafiar.'),
(2, 'Limpiadores', 'Es un disolvente de limpieza de serigrafia adecuado para limpiar plastisol en la pantalla y resto de tintas durante la impresión, la malla y los útiles de trabajo.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_de_producto`
--

DROP TABLE IF EXISTS `tipo_de_producto`;
CREATE TABLE IF NOT EXISTS `tipo_de_producto` (
  `idTipo_producto` int(4) NOT NULL AUTO_INCREMENT,
  `Nombres` set('Marcos/Bastidores - Sedas/Tejidos','Escobillines/Raseros','Tintas Litográficas - Tintas para Screen','Maquinaria') NOT NULL,
  `Descripcion` varchar(300) NOT NULL,
  PRIMARY KEY (`idTipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_de_producto`
--

INSERT INTO `tipo_de_producto` (`idTipo_producto`, `Nombres`, `Descripcion`) VALUES
(1, 'Marcos/Bastidores - Sedas/Tejidos', 'Productos de estructura para la creación de un estampado.'),
(2, 'Escobillines/Raseros', 'Herramientas empleadas para pasar la tinta por la pantalla y depositarla sobre la prenda o material textil.'),
(3, 'Tintas Litográficas - Tintas para Screen', 'Tintas de impreción.'),
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idUsuario`, `idRol`, `Ndocumento`, `Nombres`, `Apellidos`, `Direccion`, `Telefono`, `Correo_electronico`, `Contrasena`, `Fecha`, `Estado`) VALUES
(4, 1, 103847, 'dddd', 'dddd', 'dddd', 123123, 'sss', '123', '2020-01-21', 'Inactivo'),
(5, 3, 10093872, 'Luis', 'Huertas', 'Suba', 3336412, 'ldhuertas56@misena.edu.co', '123', '2020-01-22', 'Inactivo');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agregar_pago`
--
ALTER TABLE `agregar_pago`
  ADD CONSTRAINT `agregar_pago_ibfk_1` FOREIGN KEY (`idCotizacion`) REFERENCES `cotizacion` (`idCotizacion`) ON DELETE CASCADE ON UPDATE CASCADE;

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
  ADD CONSTRAINT `insumos_ibfk_1` FOREIGN KEY (`idTipo_insumo`) REFERENCES `tipo_de_insumo` (`idTipo_insumo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inventario_ibfk_2` FOREIGN KEY (`idBodega`) REFERENCES `bodega` (`idBodega`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inventario_ibfk_3` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inventario_ibfk_4` FOREIGN KEY (`idInsumo`) REFERENCES `insumos` (`idInsumo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `inventario-solicitud`
--
ALTER TABLE `inventario-solicitud`
  ADD CONSTRAINT `inventario-solicitud_ibfk_1` FOREIGN KEY (`idInventario`) REFERENCES `inventario` (`idInventario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inventario-solicitud_ibfk_2` FOREIGN KEY (`idSolicitud`) REFERENCES `solicitud` (`idSolicitud`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `permisos_ibfk_1` FOREIGN KEY (`Permiso_padre`) REFERENCES `permisos` (`idPermisos`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`idTipo_producto`) REFERENCES `tipo_de_producto` (`idTipo_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `roles_has_permisos`
--
ALTER TABLE `roles_has_permisos`
  ADD CONSTRAINT `roles_has_permisos_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `roles` (`idRol`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `roles_has_permisos_ibfk_2` FOREIGN KEY (`idPermisos`) REFERENCES `permisos` (`idPermisos`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `solicitud_ibfk_2` FOREIGN KEY (`idPedido`) REFERENCES `agregar_pago` (`idPedido`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `roles` (`idRol`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
