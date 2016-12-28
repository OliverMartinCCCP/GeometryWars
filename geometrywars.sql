-- phpMyAdmin SQL Dump
-- version 4.6.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Gegenereerd op: 23 dec 2016 om 14:28
-- Serverversie: 5.6.17
-- PHP-versie: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `geometrywars`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `abilities`
--

CREATE TABLE `abilities` (
  `abilityId` int(32) NOT NULL,
  `abilityName` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `avatars`
--

CREATE TABLE `avatars` (
  `avatarId` int(32) NOT NULL,
  `avatarName` varchar(64) NOT NULL,
  `image` varchar(64) NOT NULL,
  `backstory` varchar(124) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `clans`
--

CREATE TABLE `clans` (
  `clanId` int(32) NOT NULL,
  `clanName` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `drones`
--

CREATE TABLE `drones` (
  `droneId` int(32) NOT NULL,
  `droneName` varchar(64) NOT NULL,
  `abilityId` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `drops`
--

CREATE TABLE `drops` (
  `dropId` int(32) NOT NULL,
  `dropName` varchar(64) NOT NULL,
  `abilityId` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `enemies`
--

CREATE TABLE `enemies` (
  `enemyId` int(32) NOT NULL,
  `enemyName` varchar(64) NOT NULL,
  `enemyImage` varchar(64) NOT NULL,
  `enemyScore` int(32) NOT NULL,
  `xp` int(32) NOT NULL,
  `speed` int(32) NOT NULL,
  `hp` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `users`
--

CREATE TABLE `users` (
  `playerId` int(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `highscore` int(32) DEFAULT NULL,
  `current_level` int(32) NOT NULL DEFAULT '1',
  `password` varchar(32) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `playerLevel` int(32) DEFAULT NULL,
  `droneId` int(32) DEFAULT '1',
  `AvatarId` int(32) DEFAULT '1',
  `GameCredits` int(32) DEFAULT NULL,
  `premiumCredits` int(32) DEFAULT NULL,
  `ClanId` int(32) DEFAULT NULL,
  `multiplayerHighscore` int(32) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `users`
--

INSERT INTO `users` (`playerId`, `username`, `highscore`, `current_level`, `password`, `email`, `playerLevel`, `droneId`, `AvatarId`, `GameCredits`, `premiumCredits`, `ClanId`, `multiplayerHighscore`) VALUES
(1, 'Derango', 14280, 2, '', '', 0, 0, 0, 0, 0, 0, 0),
(2, 'Clydez', 1675, 2, '', '', 0, 0, 0, 0, 0, 0, 0);

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `abilities`
--
ALTER TABLE `abilities`
  ADD PRIMARY KEY (`abilityId`);

--
-- Indexen voor tabel `avatars`
--
ALTER TABLE `avatars`
  ADD PRIMARY KEY (`avatarId`);

--
-- Indexen voor tabel `clans`
--
ALTER TABLE `clans`
  ADD PRIMARY KEY (`clanId`);

--
-- Indexen voor tabel `drones`
--
ALTER TABLE `drones`
  ADD PRIMARY KEY (`droneId`);

--
-- Indexen voor tabel `drops`
--
ALTER TABLE `drops`
  ADD PRIMARY KEY (`dropId`);

--
-- Indexen voor tabel `enemies`
--
ALTER TABLE `enemies`
  ADD PRIMARY KEY (`enemyId`);

--
-- Indexen voor tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`playerId`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `abilities`
--
ALTER TABLE `abilities`
  MODIFY `abilityId` int(32) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `avatars`
--
ALTER TABLE `avatars`
  MODIFY `avatarId` int(32) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `clans`
--
ALTER TABLE `clans`
  MODIFY `clanId` int(32) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `drones`
--
ALTER TABLE `drones`
  MODIFY `droneId` int(32) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `drops`
--
ALTER TABLE `drops`
  MODIFY `dropId` int(32) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `enemies`
--
ALTER TABLE `enemies`
  MODIFY `enemyId` int(32) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT voor een tabel `users`
--
ALTER TABLE `users`
  MODIFY `playerId` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
