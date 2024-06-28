-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 28, 2024 at 03:41 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

CREATE DATABASE IF NOT EXISTS `pds-atari`;

USE `pds-atari`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pds-atari`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `rating` float NOT NULL,
  `description` varchar(255) NOT NULL,
  `stock` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game`
--

INSERT INTO game (id, name, path, price, rating, description, stock) VALUES
(2, '32 in 1', 'images/gallery/32 In 1 (USA).png', 15.99, 4.5, 'Enjoy a collection of 32 classic games all in one cartridge, featuring diverse gameplay and endless fun.', 15),
(3, 'Ace of Aces', 'images/gallery/Ace of Aces (USA).png', 22.5, 4.2, 'Take to the skies in Ace of Aces, a thrilling aerial combat game where you pilot a World War II fighter plane.', 3),
(4, 'Alien Brigade', 'images/gallery/Alien Brigade (USA).png', 12.75, 4.0, 'Join the fight against extraterrestrial invaders in Alien Brigade, a fast-paced, action-packed shooter.', 17),
(5, 'Asteroids', 'images/gallery/Asteroids (USA).png', 18.25, 4.3, 'Defend your spaceship from waves of asteroids and enemy saucers in this classic arcade shooter.', 2),
(6, 'Ballblazer', 'images/gallery/Ballblazer (USA).png', 10.5, 3.8, 'Experience futuristic sports action in Ballblazer, where players compete in high-speed ball games.', 6),
(7, 'Barnyard Blaster', 'images/gallery/Barnyard Blaster (USA).png', 14.75, 4.1, 'Test your sharpshooting skills in Barnyard Blaster, a fun and challenging light gun game set on a farm.', 11),
(8, 'Basketbrawl', 'images/gallery/Basketbrawl (USA).png', 20.0, 4.4, 'Combine basketball with brawling in Basketbrawl, a unique and exciting sports game with intense action.', 19),
(9, 'Centipide', 'images/gallery/Centipede (USA).png', 9.99, 3.7, 'Battle the relentless centipedes and other creepy crawlies in this classic arcade shooter.', 4),
(10, 'Choplifter!', 'images/gallery/Choplifter! (USA).png', 25.5, 4.6, 'Rescue hostages and complete daring missions in Choplifter!, a thrilling helicopter action game.', 20),
(11, 'Commando', 'images/gallery/Commando (USA).png', 13.25, 4.0, 'Lead your soldier into battle against enemy forces in Commando, an intense and challenging action game.', 5),
(12, 'Crack''ed', 'images/gallery/Crack''ed (USA).png', 16.8, 4.2, 'Protect your nest of eggs from a variety of predators in Crack''ed, a fun and frantic shooter.', 14),
(13, 'Crossbow', 'images/gallery/Crossbow (USA).png', 11.99, 3.9, 'Guide your team safely through dangerous environments using your trusty crossbow in this adventure game.', 7),
(14, 'Dark Chambers', 'images/gallery/Dark Chambers (USA).png', 28.75, 4.8, 'Explore mysterious dungeons and battle fearsome monsters in Dark Chambers, a captivating action-adventure game.', 8),
(15, 'Desert Falcon', 'images/gallery/Desert Falcon (USA).png', 14.5, 4.1, 'Soar through the skies as a mighty falcon, collecting treasures and battling enemies in Desert Falcon.', 0),
(16, 'Dig Dug', 'images/gallery/Dig Dug (USA).png', 19.5, 4.3, 'Tunnel underground and inflate enemies until they pop in Dig Dug, a classic arcade puzzle game.', 12),
(17, 'Donkey Kong', 'images/gallery/Donkey Kong (USA).png', 27.0, 4.7, 'Climb the scaffolding, dodge barrels, and rescue the damsel in distress in the iconic Donkey Kong.', 10),
(18, 'Donkey Kong Junior', 'images/gallery/Donkey Kong Junior (USA).png', 23.8, 4.6, 'Help Donkey Kong Junior rescue his father from Mario''s clutches in this fun and challenging platformer.', 13),
(19, 'Double Dragon', 'images/gallery/Double Dragon (USA).png', 16.99, 4.2, 'Battle through the streets to rescue Marian in Double Dragon, a classic beat ''em up with intense action.', 1),
(20, 'F-18 Hornet', 'images/gallery/F-18 Hornet (USA).png', 26.5, 4.7, 'Pilot a powerful F-18 Hornet fighter jet and complete thrilling missions in this high-flying action game.', 9),
(21, 'Fatal Run', 'images/gallery/Fatal Run (USA).png', 14.25, 4.0, 'Race against the clock and enemy vehicles in Fatal Run, an adrenaline-pumping post-apocalyptic driving game.', 6),
(22, 'Fight Night', 'images/gallery/Fight Night (USA).png', 18.75, 4.3, 'Step into the ring and go head-to-head with fierce opponents in Fight Night, a boxing game with realistic action.', 3),
(23, 'Food Fight', 'images/gallery/Food Fight (USA).png', 13.99, 4.1, 'Throw food at your rivals and avoid obstacles in Food Fight, a quirky and entertaining action game.', 15),
(24, 'Galaga', 'images/gallery/Galaga (USA).png', 22.0, 4.5, 'Defend against waves of alien invaders in Galaga, a beloved and timeless space shooter.', 18),
(25, 'Hat Trick', 'images/gallery/Hat Trick (USA).png', 9.5, 3.6, 'Hit the ice for fast-paced hockey action in Hat Trick, a game that combines sports with arcade excitement.', 7),
(26, 'Ikari Warriors', 'images/gallery/Ikari Warriors (USA).png', 25.25, 4.6, 'Join the Ikari Warriors and fight through enemy territory in this action-packed, top-down shooter.', 4),
(27, 'Impossible Mission', 'images/gallery/Impossible Mission (USA).png', 17.5, 4.2, 'Infiltrate the enemy base, solve puzzles, and stop the mad scientist in Impossible Mission.', 19),
(28, 'Joust', 'images/gallery/Joust (USA).png', 12.8, 4.0, 'Fly on your ostrich and defeat enemy knights in Joust, a unique and engaging arcade game.', 16),
(29, 'Karateka', 'images/gallery/Karateka (USA).png', 19.75, 4.4, 'Master the martial arts and rescue the princess in Karateka, a classic side-scrolling fighting game.', 11),
(30, 'Kung-Fu Master', 'images/gallery/Kung-Fu Master (USA).png', 15.0, 4.0, 'Fight your way through waves of enemies and save your kidnapped girlfriend in Kung-Fu Master.', 5),
(31, 'Mario Bros.', 'images/gallery/Mario Bros. (USA).png', 26.99, 4.7, 'Join Mario and Luigi as they battle creatures emerging from the sewers in this iconic arcade game.', 14),
(32, 'Mat Mania Challenge', 'images/gallery/Mat Mania Challenge (USA).png', 13.5, 4.1, 'Enter the wrestling ring and take on tough opponents in Mat Mania Challenge, a classic wrestling game.', 8),
(33, 'Mean 18 Ultimate Golf', 'images/gallery/Mean 18 Ultimate Golf (USA).png', 24.25, 4.6, 'Experience realistic golf simulation in Mean 18 Ultimate Golf, a game that brings the sport to life.', 2),
(34, 'Meltdown', 'images/gallery/Meltdown (USA).png', 11.8, 3.9, 'Prevent a nuclear disaster by solving puzzles and navigating dangerous environments in Meltdown.', 12),
(35, 'Midnight Mutants', 'images/gallery/Midnight Mutants (USA).png', 18.0, 4.3, 'Battle supernatural foes and uncover dark secrets in Midnight Mutants, a spooky action-adventure game.', 3),
(36, 'Motor Psycho', 'images/gallery/Motor Psycho (USA).png', 14.9, 4.1, 'Race through challenging tracks and perform daring stunts in Motor Psycho, a high-speed racing game.', 9),
(37, 'Ms. Pac-Man', 'images/gallery/Ms. Pac-Man (USA).png', 23.5, 4.6, 'Navigate mazes, eat dots, and avoid ghosts in Ms. Pac-Man, a beloved and timeless arcade classic.', 6),
(38, 'Ninja Golf', 'images/gallery/Ninja Golf (USA).png', 16.75, 4.2, 'Combine golf with ninja combat in Ninja Golf, a unique and exciting sports-action game.', 0),
(39, 'One-on-One Basketball', 'images/gallery/One-on-One Basketball (USA).png', 9.75, 3.7, 'Hit the court for intense one-on-one basketball action in this classic sports game.', 15),
(40, 'Pete Rose Baseball', 'images/gallery/Pete Rose Baseball (USA).png', 12.5, 3.9, 'Step up to the plate and play America''s favorite pastime in Pete Rose Baseball.', 18),
(41, 'Planet Smashers', 'images/gallery/Planet Smashers (USA).png', 21.8, 4.5, 'Defend the galaxy from alien threats in Planet Smashers, a high-energy space shooter.', 20),
(42, 'Pole Position II', 'images/gallery/Pole Position II (USA).png', 25.0, 4.6, 'Take on the ultimate racing challenge in Pole Position II, a thrilling and realistic driving game.', 4),
(43, 'Rampage', 'images/gallery/Rampage (USA).png', 17.99, 4.3, 'Unleash chaos as giant monsters and destroy cities in Rampage, a fun and destructive action game.', 7),
(44, 'RealSports Baseball', 'images/gallery/RealSports Baseball (USA).png', 15.25, 4.1, 'Experience realistic baseball gameplay in RealSports Baseball, a game that captures the spirit of the sport.', 11),
(45, 'Robotron - 2084', 'images/gallery/Robotron - 2084 (USA).png', 19.9, 4.4, 'Protect the last human family from relentless robot enemies in Robotron - 2084, a frantic shooter.', 9),
(46, 'Scrapyard Dog', 'images/gallery/Scrapyard Dog (USA).png', 14.5, 4.1, 'Help Louie rescue his kidnapped dog from a scrapyard full of dangerous enemies in Scrapyard Dog.', 2),
(47, 'Sentinel', 'images/gallery/Sentinel (Europe).png', 20.5, 4.4, 'Defend against alien invaders and save humanity in Sentinel, an intense and strategic shooter.', 13),
(48, 'Summer Games', 'images/gallery/Summer Games (USA).png', 11.25, 3.8, 'Compete in various Olympic events and aim for the gold in Summer Games, a sports simulation.', 6),
(49, 'Super Huey UH-IX', 'images/gallery/Super Huey UH-IX (USA).png', 23.25, 4.5, 'Pilot the advanced Super Huey helicopter and complete challenging missions in this flight simulation.', 14),
(50, 'Super Skateboardin''', 'images/gallery/Super Skateboardin'' (USA).png', 16.5, 4.2, 'Perform tricks and navigate obstacles in Super Skateboardin'', an exciting skateboarding game.', 19),
(51, 'Tank Command', 'images/gallery/Tank Command (USA).png', 13.8, 4.0, 'Take control of a tank and lead your forces to victory in Tank Command, a strategic action game.', 5),
(52, 'Title Match Pro Wrestling', 'images/gallery/Title Match Pro Wrestling (USA).png', 18.99, 4.3, 'Step into the ring and battle for the championship in Title Match Pro Wrestling.', 16),
(53, 'Tomcat - The F-14 Fighter', 'images/gallery/Tomcat - The F-14 Fighter Simulator (USA).png', 24.75, 4.7, 'Fly the iconic F-14 Tomcat and complete high-stakes missions in this realistic flight simulator.', 8),
(54, 'Touchdown Football', 'images/gallery/Touchdown Football (USA).png', 12.4, 3.9, 'Hit the gridiron and lead your team to victory in Touchdown Football, a fast-paced sports game.', 3),
(55, 'Tower Toppler', 'images/gallery/Tower Toppler (USA).png', 21.0, 4.4, 'Navigate treacherous towers and overcome obstacles in Tower Toppler, a challenging platformer.', 11),
(56, 'Water Ski', 'images/gallery/Water Ski (USA).png', 10.75, 3.8, 'Experience the thrills of water skiing in this exciting sports game.', 12),
(57, 'Winter Games', 'images/gallery/Winter Games (USA).png', 12.43, 1.5, 'Compete in a variety of winter sports and aim for the top in Winter Games, a seasonal sports simulation.', 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
