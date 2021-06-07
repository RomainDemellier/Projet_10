-- On vide toutes les tables
TRUNCATE TABLE usager,livre,auteur,reservation,emprunt,exemplaire RESTART IDENTITY CASCADE;

-- Insertion usager
INSERT INTO public.usager (email,nom,"password",prenom,"role") VALUES
('romain.demellier@yahoo.fr','Demellier','$2a$10$R/KszKV6CsnmFgHLLt82HuAm4D7oZvEM/TmHYrKaNB9TNfFI6oqdS','Romain','USER'),
('muriel.demellier@yahoo.fr','Demellier','$2a$10$/o7RpsyPzFFQR4Kg24F09uiPwxqLdJy8msr/2R848Asr7McsLRHje','Muriel','USER'),
('daniel.demellier@yahoo.fr','Demellier','$2a$10$WHHg9B79741bcy.czi0Kc.3CAFEI43qATNdA6BaEah45OIhTSs1tK','Daniel','USER'),
('antoine.demellier@yahoo.fr','Demellier','$2a$10$M6ZoDxEGpTofHEInrJ2b9u5u2hI.HHr8pkN8rJWqj.jyKfnMsWK9u','Antoine','USER'),
('jacqueline.demellier@yahoo.fr','Demellier','$2a$10$dLbNxe3wPc6f0cVzc/DDTuxL68eCBVlXdxe1Hk0ix6uex7mYPK.I2','Jacqueline ','USER'),
('veronique.demellier@yahoo.fr','Demellier','$2a$10$nNsCrb8Z/brqDWorSSnNp.rH3PWM8XsLzdzi8fc0qn4P1qu34vjU2','Véronique','USER'),
('admin@yahoo.fr','admin','$2a$10$AbhVNRMgrwKGITO1t/LdOeDpqBmZ5Qa2IxucBffugu1xV1iXIzUVS','admin','ADMIN'),
('jean.demellier@yahoo.fr','Demellier','$2a$10$DNOMhnQSaK2oR4RuY98x9e41K50NRQgJQnl7GJMyOxsHDsZBBAqEO','Jean','USER'),
('batch@yahoo.fr','batch','$2a$10$.NJRh8Oz/8oLqVHd6Q04Au1yp8pCW2aPshhvMLPB7bts3M3Hyxm52','batch','ADMIN'),
('michel.dupont@yahoo.fr','Dupont','$2a$10$axMq6q54XXDDkkt6xLGpP.BnTuwSEUpXmlBLN6yqdlhSNxt9BHUk2','Michel','USER'),
('robert.durand@yahoo.fr','Durand','$2a$10$dOk/tYG4vDT7rmWUbS995uoArj6H2YSfUp7yOeqYWW7ZZiRJCKbWy','Robert','USER');

-- Insertion auteur
INSERT INTO public.auteur (nom,prenom) VALUES
('Verne','Jules'),
('Dumas','Alexandre'),
('Rousseau','Jean-Jacques'),
('Gary','Romain'),
('Zola','Emile'),
('Hugo','Victor');

-- Insertion livre
INSERT INTO public.livre (genre,nbre_exemplaires,nbre_total,titre,auteur_id,nbre_reservations,reservable) VALUES
('Science-fiction',0,2,'Vingt milles lieues sous les mers',1,0,NULL),
('Science-fiction',2,3,'Voyage au centre de la Terre',1,0,NULL),
('Aventure',1,2,'La reine Margot',2,0,NULL),
('Aventure',2,2,'Le comte de Monte Cristo',2,0,NULL),
('Aventure',0,3,'Les trois mousquetaires',2,1,NULL),
('Roman',3,3,'La vie devant soi',4,0,NULL),
('Autobiographie',0,2,'Les confessions',3,2,NULL),
('Drame',2,2,'Germinal',5,0,NULL),
('Roman',1,2,'Notre Dame de Paris',6,0,NULL),
('Roman',2,3,'Les Misérables',6,0,NULL),
('Aventure',1,1,'Vingt ans après',2,0,NULL),
('Science-fiction',0,2,'De la Terre à la Lune',1,4,NULL);

-- Insertion exemplaire
INSERT INTO public.exemplaire (etat,livre_id) VALUES
('neuf',12),
('neuf',12),
('neuf',1),
('moyen',1),
('vieux',2),
('moyen',2),
('moyen',3),
('neuf',3),
('vieux',4),
('moyen',4);
INSERT INTO public.exemplaire (etat,livre_id) VALUES
('moyen',5),
('neuf',5),
('neuf',5),
('vieux',11),
('vieux',7),
('moyen',7),
('moyen',6),
('moyen',8),
('neuf',8),
('moyen',10);
INSERT INTO public.exemplaire (etat,livre_id) VALUES
('vieux',10),
('vieux',9),
('neuf',9),
('neuf',2),
('neuf',6),
('vieux',6),
('neuf',10);

-- Insertion emprunt
INSERT INTO public.emprunt (actif,date_emprunt,date_retour,prolonge,exemplaire_id,usager_id) VALUES
(true,'2021-05-23','2021-06-20',false,13,3),
(true,'2021-05-20','2021-06-17',false,16,2),
(true,'2021-05-13','2021-06-10',false,12,2),
(true,'2021-05-23','2021-06-20',false,5,4),
(true,'2021-05-23','2021-06-20',false,20,5),
(true,'2021-05-23','2021-06-20',false,7,6),
(true,'2021-05-23','2021-06-20',false,22,6),
(true,'2021-05-22','2021-06-19',false,2,2),
(true,'2021-05-23','2021-06-20',false,15,1),
(true,'2021-05-23','2021-06-20',false,11,1);
INSERT INTO public.emprunt (actif,date_emprunt,date_retour,prolonge,exemplaire_id,usager_id) VALUES
--(true,'2021-05-25','2021-06-22',false,3,1),
--(true,'2021-05-25','2021-06-22',false,4,2),
(true,'2021-04-19','2021-05-17',false,1,1);

-- Insertion réservation
INSERT INTO public.reservation (actif,"date",date_limit,livre_id,usager_id) VALUES
/*(true,'2021-05-20 17:12:20',NULL,12,6),
(true,'2021-05-23 17:11:44.128',NULL,12,4),
(true,'2021-05-23 17:12:03.206',NULL,12,5),
(true,'2021-05-23 17:11:29.517',NULL,12,3),
(true,'2021-05-19 17:15:41',NULL,7,8);*/
(true,'2021-05-23 17:11:44.128',NULL,12,4),
(true,'2021-05-23 17:12:03.206',NULL,12,5),
(true,'2021-05-23 17:11:29.517',NULL,12,3),
(true,'2021-05-19 17:15:41',NULL,7,8),
(true,'2021-05-20 17:12:20',NULL,12,6),
(true,'2021-06-03 14:10:58','2021-06-05 15:00:00',5,10),
(true,'2021-06-01 15:37:29','2021-06-04 15:00:00',7,11);
