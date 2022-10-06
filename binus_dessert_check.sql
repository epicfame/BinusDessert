-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2021 at 02:23 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `binus_dessert_check`
--

-- --------------------------------------------------------

--
-- Table structure for table `icecreams`
--

CREATE TABLE `icecreams` (
  `DessertId` int(11) NOT NULL,
  `DessertName` varchar(100) NOT NULL,
  `DessertType` varchar(100) NOT NULL,
  `DessertPrice` int(11) NOT NULL,
  `CreamName` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `icecreams`
--

INSERT INTO `icecreams` (`DessertId`, `DessertName`, `DessertType`, `DessertPrice`, `CreamName`) VALUES
(1, 'Vanilla De Madagascar', 'Ice Cream', 50000, 'Creame Max Creamer'),
(2, 'Belgian Chocolate', 'Ice Cream', 80000, 'Creame Ellenka Fiber'),
(3, 'Strawberry Green Tea', 'Ice Cream', 70000, 'Creame Ansell'),
(4, 'Choco Chips Creamer', 'Ice Cream', 50000, 'Creame Coffindo'),
(5, 'Rum Raisins', 'Ice Cream', 100000, 'Creame Javaland');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `TransactionId` varchar(10) NOT NULL,
  `UserId` int(11) NOT NULL,
  `DessertId` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserId` int(11) NOT NULL,
  `UserName` varchar(100) NOT NULL,
  `UserEmail` varchar(100) NOT NULL,
  `UserAge` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserId`, `UserName`, `UserEmail`, `UserAge`) VALUES
(11, 'vincent.benedict', 'vincent.benedict@gmail.com', 20),
(12, 'lionell', 'lionell@gmail.com', 20),
(16, 'johanes', 'johanes@gmail.com', 5);

-- --------------------------------------------------------

--
-- Table structure for table `yoghurts`
--

CREATE TABLE `yoghurts` (
  `DessertId` int(11) NOT NULL,
  `DessertName` varchar(100) NOT NULL,
  `DessertType` varchar(100) NOT NULL,
  `DessertPrice` int(11) NOT NULL,
  `ProbioticName` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `yoghurts`
--

INSERT INTO `yoghurts` (`DessertId`, `DessertName`, `DessertType`, `DessertPrice`, `ProbioticName`) VALUES
(6, 'Greek Yoghurt', 'Yoghurt', 120000, 'Lactobacillus Bacteria'),
(7, 'Elle & Virre', 'Yoghurt', 110000, 'Lactobac Protect Bacteria'),
(8, 'Kin-Kin Yoghurt', 'Yoghurt', 140000, 'Bifidobacterium Bacteria'),
(9, 'Heavenly Brush', 'Yoghurt', 150000, 'Bifidobacter Dom Bacteria'),
(10, 'Sour Sally Creamy', 'Yoghurt', 130000, 'Lactobacill Bacteria');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `icecreams`
--
ALTER TABLE `icecreams`
  ADD PRIMARY KEY (`DessertId`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`TransactionId`),
  ADD KEY `foreign_key_userid` (`UserId`),
  ADD KEY `foreign_key_dessertid_yoghurt` (`DessertId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserId`);

--
-- Indexes for table `yoghurts`
--
ALTER TABLE `yoghurts`
  ADD PRIMARY KEY (`DessertId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `foreign_key_userid` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
