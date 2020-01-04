-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2019 at 08:35 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smkindonesia`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin'),
(2, 'bmti', 'bmti');

-- --------------------------------------------------------

--
-- Table structure for table `galeri`
--

CREATE TABLE `galeri` (
  `id` int(11) NOT NULL,
  `deskripsi` varchar(50) NOT NULL,
  `foto` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `galeri`
--

INSERT INTO `galeri` (`id`, `deskripsi`, `foto`) VALUES
(1, 'pramuka', '02-11-20161478079725'),
(2, '', '02-11-20161478079738'),
(3, 'vvbvvb', '02-11-20161478079779');

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `kdguru` varchar(10) NOT NULL,
  `nip` varchar(18) NOT NULL,
  `namaguru` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `jk` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`kdguru`, `nip`, `namaguru`, `alamat`, `jk`) VALUES
('G0005', '987', 'asifa', 'Bandung', 'perempuan'),
('G001', '123456', 'Joni', 'bandung', 'lelaki'),
('G002', '78976', 'Fenti', 'bandung', 'perempuan');

-- --------------------------------------------------------

--
-- Table structure for table `jurusan`
--

CREATE TABLE `jurusan` (
  `kdjurusan` varchar(5) NOT NULL,
  `namajurusan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jurusan`
--

INSERT INTO `jurusan` (`kdjurusan`, `namajurusan`) VALUES
('MM', 'Multimedia'),
('RPL', 'Rekayasa Perangkat Lunak'),
('TKJ', 'T Komputer jaringan'),
('TKR', 'Teknik Kendaraan Ringan');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `kdkelas` varchar(10) NOT NULL,
  `namakelas` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`kdkelas`, `namakelas`) VALUES
('XRPLA', 'Kelas X RPL A'),
('XRPLB', 'Kelas X RPL B'),
('XTKJA', 'Kelas X TKJ A'),
('XTKJB', 'Kelas X TKJ B');

-- --------------------------------------------------------

--
-- Table structure for table `mapel`
--

CREATE TABLE `mapel` (
  `kdmapel` varchar(10) NOT NULL,
  `namamapel` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mapel`
--

INSERT INTO `mapel` (`kdmapel`, `namamapel`) VALUES
('AS', 'Administrasi Server'),
('BD', 'Basis Data'),
('PK', 'Perakitan Komputer'),
('SO', 'Sistem Operasi');

-- --------------------------------------------------------

--
-- Table structure for table `mengajar`
--

CREATE TABLE `mengajar` (
  `kdmengajar` varchar(10) NOT NULL,
  `kdguru` varchar(10) NOT NULL,
  `kdjurusan` varchar(10) NOT NULL,
  `kdkelas` varchar(10) NOT NULL,
  `kdmapel` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mengajar`
--

INSERT INTO `mengajar` (`kdmengajar`, `kdguru`, `kdjurusan`, `kdkelas`, `kdmapel`) VALUES
('M001', 'G001', 'RPL', 'XRPLA', 'BD'),
('M002', 'G001', 'RPL', 'XRPLA', 'PK'),
('M003', 'G0005', 'RPL', 'XRPLB', 'SO'),
('M004', 'G001', 'RPL', 'XRPLB', 'BD'),
('M005', 'G001', 'TKJ', 'XTKJA', 'BD');

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE `nilai` (
  `kdnilai` varchar(10) NOT NULL,
  `nis` varchar(10) NOT NULL,
  `kdguru` varchar(10) NOT NULL,
  `kdkelas` varchar(10) NOT NULL,
  `kdjurusan` varchar(10) NOT NULL,
  `kdmapel` varchar(10) NOT NULL,
  `uh` double NOT NULL,
  `uts` double NOT NULL,
  `uas` double NOT NULL,
  `na` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nilai`
--

INSERT INTO `nilai` (`kdnilai`, `nis`, `kdguru`, `kdkelas`, `kdjurusan`, `kdmapel`, `uh`, `uts`, `uas`, `na`) VALUES
('N01', 'S001', 'G001', 'XRPLA', 'RPL', 'BD', 90, 90, 90, 90);

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nis` varchar(10) NOT NULL,
  `namasiswa` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `jk` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nis`, `namasiswa`, `alamat`, `jk`) VALUES
('S001', 'Rudi', 'Cimahi', 'lelaki'),
('S002', 'Fina', 'bandung', 'perempuan');

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_siswa`
-- (See below for the actual view)
--
CREATE TABLE `view_siswa` (
`nis` varchar(10)
,`namasiswa` varchar(50)
,`kdguru` varchar(10)
,`namaguru` varchar(50)
,`namajurusan` varchar(30)
,`namakelas` varchar(30)
,`namamapel` varchar(30)
,`uts` double
,`uas` double
,`uh` double
,`na` double
);

-- --------------------------------------------------------

--
-- Structure for view `view_siswa`
--
DROP TABLE IF EXISTS `view_siswa`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_siswa`  AS  select `siswa`.`nis` AS `nis`,`siswa`.`namasiswa` AS `namasiswa`,`guru`.`kdguru` AS `kdguru`,`guru`.`namaguru` AS `namaguru`,`jurusan`.`namajurusan` AS `namajurusan`,`kelas`.`namakelas` AS `namakelas`,`mapel`.`namamapel` AS `namamapel`,`nilai`.`uts` AS `uts`,`nilai`.`uas` AS `uas`,`nilai`.`uh` AS `uh`,`nilai`.`na` AS `na` from (((((`siswa` join `guru`) join `jurusan`) join `kelas`) join `mapel`) join `nilai`) where ((`siswa`.`nis` = `nilai`.`nis`) and (`guru`.`kdguru` = `nilai`.`kdguru`) and (`jurusan`.`kdjurusan` = `nilai`.`kdjurusan`) and (`kelas`.`kdkelas` = `nilai`.`kdkelas`) and (`mapel`.`kdmapel` = `nilai`.`kdmapel`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `galeri`
--
ALTER TABLE `galeri`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`kdguru`);

--
-- Indexes for table `jurusan`
--
ALTER TABLE `jurusan`
  ADD PRIMARY KEY (`kdjurusan`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`kdkelas`);

--
-- Indexes for table `mapel`
--
ALTER TABLE `mapel`
  ADD PRIMARY KEY (`kdmapel`);

--
-- Indexes for table `mengajar`
--
ALTER TABLE `mengajar`
  ADD PRIMARY KEY (`kdmengajar`),
  ADD UNIQUE KEY `kdguru` (`kdguru`,`kdkelas`,`kdmapel`),
  ADD UNIQUE KEY `kodepk` (`kdkelas`,`kdmapel`),
  ADD UNIQUE KEY `kdkelas` (`kdkelas`,`kdmapel`),
  ADD UNIQUE KEY `kdmapel` (`kdmapel`,`kdkelas`),
  ADD UNIQUE KEY `kdjurusan` (`kdjurusan`,`kdkelas`,`kdmapel`) USING BTREE;

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`kdnilai`),
  ADD UNIQUE KEY `nis` (`nis`,`kdguru`,`kdmapel`),
  ADD UNIQUE KEY `kdkelas` (`kdkelas`,`nis`,`kdmapel`),
  ADD UNIQUE KEY `kdmapel` (`kdmapel`,`kdkelas`,`nis`),
  ADD UNIQUE KEY `kdguru` (`kdguru`,`nis`,`kdmapel`),
  ADD UNIQUE KEY `kdjurusan` (`kdjurusan`,`kdmapel`,`nis`) USING BTREE;

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `galeri`
--
ALTER TABLE `galeri`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mengajar`
--
ALTER TABLE `mengajar`
  ADD CONSTRAINT `mengajar_ibfk_1` FOREIGN KEY (`kdguru`) REFERENCES `guru` (`kdguru`),
  ADD CONSTRAINT `mengajar_ibfk_3` FOREIGN KEY (`kdkelas`) REFERENCES `kelas` (`kdkelas`),
  ADD CONSTRAINT `mengajar_ibfk_4` FOREIGN KEY (`kdmapel`) REFERENCES `mapel` (`kdmapel`),
  ADD CONSTRAINT `mengajar_ibfk_5` FOREIGN KEY (`kdjurusan`) REFERENCES `jurusan` (`kdjurusan`);

--
-- Constraints for table `nilai`
--
ALTER TABLE `nilai`
  ADD CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`nis`) REFERENCES `siswa` (`nis`),
  ADD CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`kdguru`) REFERENCES `guru` (`kdguru`),
  ADD CONSTRAINT `nilai_ibfk_4` FOREIGN KEY (`kdkelas`) REFERENCES `kelas` (`kdkelas`),
  ADD CONSTRAINT `nilai_ibfk_5` FOREIGN KEY (`kdmapel`) REFERENCES `mapel` (`kdmapel`),
  ADD CONSTRAINT `nilai_ibfk_6` FOREIGN KEY (`kdjurusan`) REFERENCES `jurusan` (`kdjurusan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
