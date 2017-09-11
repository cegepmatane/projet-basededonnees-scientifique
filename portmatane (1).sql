-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 11, 2017 at 05:33 PM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `portmatane`
--

-- --------------------------------------------------------

--
-- Table structure for table `armateur`
--

CREATE TABLE `armateur` (
  `idArmateur` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `armateur`
--

INSERT INTO `armateur` (`idArmateur`, `nom`) VALUES
(1, 'Boluda France'),
(2, 'Bourbon'),
(3, 'Comex'),
(4, 'CMA CGM'),
(5, 'Compagnie du Ponant'),
(6, 'Genavir'),
(7, 'Les Abeilles'),
(8, 'Marfret'),
(9, 'Orange Marine'),
(10, 'Socatra');

-- --------------------------------------------------------

--
-- Table structure for table `bateau`
--

CREATE TABLE `bateau` (
  `idBateau` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `marque` varchar(50) NOT NULL,
  `modele` varchar(50) NOT NULL,
  `annee` date NOT NULL,
  `longueur` int(11) NOT NULL,
  `largeur` int(11) NOT NULL,
  `hauteur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `bouee`
--

CREATE TABLE `bouee` (
  `idBouee` int(11) NOT NULL,
  `latitude` int(11) NOT NULL,
  `longitude` int(11) NOT NULL,
  `temperatureEau` int(11) NOT NULL,
  `temperatureAir` int(11) NOT NULL,
  `salinite` float NOT NULL,
  `vitesseVent` float NOT NULL,
  `dimension` int(11) NOT NULL,
  `pressionAtmospherique` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bouee`
--

INSERT INTO `bouee` (`idBouee`, `latitude`, `longitude`, `temperatureEau`, `temperatureAir`, `salinite`, `vitesseVent`, `dimension`, `pressionAtmospherique`) VALUES
(1, 2, 3, 4, 5, 6, 7, 8, 9),
(2, 50, 420, 63, 453, 455, 4524, 4536, 5424),
(3, 1, 2, 3, 4, 5, 6, 7, 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `armateur`
--
ALTER TABLE `armateur`
  ADD PRIMARY KEY (`idArmateur`);

--
-- Indexes for table `bateau`
--
ALTER TABLE `bateau`
  ADD PRIMARY KEY (`idBateau`);

--
-- Indexes for table `bouee`
--
ALTER TABLE `bouee`
  ADD PRIMARY KEY (`idBouee`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `armateur`
--
ALTER TABLE `armateur`
  MODIFY `idArmateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `bateau`
--
ALTER TABLE `bateau`
  MODIFY `idBateau` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `bouee`
--
ALTER TABLE `bouee`
  MODIFY `idBouee` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
