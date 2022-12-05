-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-12-2022 a las 13:28:53
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `parques`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `dni` varchar(255) NOT NULL,
  `edad` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `apellido`, `dni`, `edad`) VALUES
(1, 'Mario', 'Zaton', '72756384R', 21),
(3, 'Juan ', 'Da Silva', '7275634T', 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id` int(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `dni` varchar(255) NOT NULL,
  `edad` varchar(255) NOT NULL,
  `fecha_nac` varchar(255) NOT NULL,
  `fecha_contrato` varchar(255) NOT NULL,
  `nacionalidad` varchar(255) NOT NULL,
  `cargo` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id`, `nombre`, `apellido`, `dni`, `edad`, `fecha_nac`, `fecha_contrato`, `nacionalidad`, `cargo`) VALUES
(1, 'Mario', 'Zaton', '72756384R', '21', '2001-08-02', '2022-11-24', 'ES', 1),
(2, 'Jokin', 'Aspuru', '75848385U', '32', '1994-12-01', '2019-07-04', 'ES', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculos`
--

CREATE TABLE `espectaculos` (
  `id` int(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `aforo` int(255) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `lugar` varchar(255) NOT NULL,
  `coste` float NOT NULL,
  `empleado_cargo` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `espectaculos`
--

INSERT INTO `espectaculos` (`id`, `nombre`, `aforo`, `descripcion`, `lugar`, `coste`, `empleado_cargo`) VALUES
(1, 'Dragon Khan', 30, 'Montaña rusa', 'Seccion E', 0, 1),
(2, 'Shambala', 50, 'Montaña rusa', ' Seccion F', 10, 2),
(3, 'Pili kili', 10, 'Atraccion de miedo', 'Seccion G', 0, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculo_clientes`
--

CREATE TABLE `espectaculo_clientes` (
  `id` int(255) NOT NULL,
  `id_espectaculo` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `espectaculo_clientes`
--

INSERT INTO `espectaculo_clientes` (`id`, `id_espectaculo`, `id_cliente`) VALUES
(1, 1, 1),
(3, 2, 1),
(4, 3, 1),
(5, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parque`
--

CREATE TABLE `parque` (
  `id` int(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apertura` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `parque`
--

INSERT INTO `parque` (`id`, `nombre`, `apertura`, `direccion`) VALUES
(1, 'PortAventura', '2022-11-22', 'Calle PortAventura 27');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `espectaculos`
--
ALTER TABLE `espectaculos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `empleado_cargo` (`empleado_cargo`);

--
-- Indices de la tabla `espectaculo_clientes`
--
ALTER TABLE `espectaculo_clientes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_espectaculo` (`id_espectaculo`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `parque`
--
ALTER TABLE `parque`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `espectaculos`
--
ALTER TABLE `espectaculos`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `espectaculo_clientes`
--
ALTER TABLE `espectaculo_clientes`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `parque`
--
ALTER TABLE `parque`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `espectaculos`
--
ALTER TABLE `espectaculos`
  ADD CONSTRAINT `espectaculos_ibfk_1` FOREIGN KEY (`empleado_cargo`) REFERENCES `empleados` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `espectaculo_clientes`
--
ALTER TABLE `espectaculo_clientes`
  ADD CONSTRAINT `espectaculo_clientes_ibfk_1` FOREIGN KEY (`id_espectaculo`) REFERENCES `espectaculos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `espectaculo_clientes_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
