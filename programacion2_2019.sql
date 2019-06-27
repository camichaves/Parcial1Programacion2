-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 27-06-2019 a las 22:40:54
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `programacion2_2019`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(4) NOT NULL,
  `Nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `Apellido` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `documento` int(15) NOT NULL,
  `activo` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `Nombre`, `Apellido`, `documento`, `activo`) VALUES
(2, 'Guillermo', 'Casasola', 34465463, NULL),
(3, 'Rocio', 'Chaves', 45665543, 1),
(4, 'Diego', 'Chaves', 457654324, 0),
(5, 'Carlos', 'Chaves', 54367664, 0),
(6, 'Rodrigo', 'Noya', 54634575, 1),
(7, 'Tanya', 'Budler', 546464, 0),
(16, 'Coki', 'Chaves', 54657463, 1),
(17, 'Zaira', 'Casasola', 6456456, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(4) NOT NULL,
  `Nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `Descripcion` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `Precio` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `Nombre`, `Descripcion`, `Precio`) VALUES
(3, 'Cama', 'cama matrimonial', 1000.23),
(4, 'Mesa', 'mesa comedor chica', 300.75),
(6, 'Peine', 'rosita', 20),
(7, 'Mate', 'lunas', 20),
(8, 'Cepillo', 'rosa', 16.22),
(14, 'Collar', 'antipulgas', 100),
(15, 'Alimento', 'premmium', 400),
(16, 'Cartuchera', 'grande', 70);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relventaproducto`
--

CREATE TABLE `relventaproducto` (
  `id` int(4) NOT NULL,
  `idventa` int(4) NOT NULL,
  `idproducto` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` int(4) NOT NULL,
  `idpersona` int(4) NOT NULL,
  `fecha` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `monto` float NOT NULL,
  `descripcion` varchar(256) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `relventaproducto`
--
ALTER TABLE `relventaproducto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
