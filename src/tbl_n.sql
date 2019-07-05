



CREATE TABLE `enseignant` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `deuxiemeMatiere` varchar(255) DEFAULT NULL,
  `nombreMatieres` int(11) NOT NULL,
  `premiereMatiere` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8



CREATE TABLE `recrutement` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `fraisParHeure` decimal(19,2) DEFAULT NULL,
  `image` longblob,
  `matricule` varchar(255) NOT NULL,
  `nombreHeure` int(11) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `salaire` decimal(19,2) DEFAULT NULL,
  `enseignant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4gj9bgh1qk4okiiwn0m2tapdh` (`enseignant_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8
