-- On vide toutes les tables
TRUNCATE TABLE usager,livre,auteur,reservation,emprunt,exemplaire RESTART IDENTITY CASCADE;

-- Insertion d'un usager profil ADMIN
INSERT INTO public.usager (email,nom,"password",prenom,"role") VALUES
('admin-test@admin.fr','admin-test','$2a$10$kxVjv/0QauGGisHbVDbJse.Gmlgwz.ehXfzXZmq2eo8FPzcnaLPSy','admin','ADMIN')
,('muriel.demellier@yahoo.fr','Demellier','$2a$10$FYtHS2axgvdhOo5uPFjAHevCJsP2udjdTyH0.VXFRAccOAMwbH.AK','Muriel','USER')
,('daniel.demellier@yahoo.fr','Demellier','$2a$10$HCDV9MFIj3rdNBJxDqae2OVIVM3QoCEmKfwCUiXtYofknV29bztB6','Daniel','USER')
,('antoine.demellier@yahoo.fr','Demellier','$2a$10$FYtHS2axgvdhOo5uPFjAHevCJsP2udjdTyH0.VXFRAccOAMwbH.AK','Antoine','USER')
,('romain.demellier@yahoo.fr','Demellier','$2a$10$FYtHS2axgvdhOo5uPFjAHevCJsP2udjdTyH0.VXFRAccOAMwbH.AK','Romain','USER')
,('jacqueline.demellier@yahoo.fr','Demellier','$2a$10$FYtHS2axgvdhOo5uPFjAHevCJsP2udjdTyH0.VXFRAccOAMwbH.AK','Jacqueline','USER')
;

-- Insertion de deux auteurs
INSERT INTO public.auteur (nom,prenom) VALUES
('Verne','Jules')
,('Dumas','Alexandre');

-- Insertion de quatre livres
INSERT INTO public.livre (genre,nbre_exemplaires,nbre_total,titre,auteur_id,reservable,nbre_reservations) VALUES
('Science-fiction',2,2,'Vingt milles lieues sous les mers',1,true,0)
,('Aventure',0,2,'Le comte de Monte Cristo',2,true,0)
,('Aventure',0,2,'La reine Margot',2,true,4);

-- Insertion de sept exemplaires
INSERT INTO public.exemplaire (etat,livre_id) VALUES
('neuf',1)
,('moyen',2);


-- Insertion de deux emprunts
INSERT INTO public.emprunt (actif,date_emprunt,date_retour,prolonge,exemplaire_id,usager_id) VALUES
(true,'2021-03-28','2021-04-25',false,1,2),
(false,'2021-03-28','2021-04-25',false,2,3);

-- Insertion de  réservations
INSERT INTO public.reservation (actif,"date",date_limit,livre_id,usager_id) VALUES
(true,'2021-03-28 16:58:53.82',NULL,1,2),
(true,'2021-03-28 17:07:25.545','2021-05-28 17:07:25.545',2,3),
(true,'2021-03-28 16:58:53.82',NULL,3,2),
(true,'2021-03-28 17:58:53.82',NULL,3,3),
(true,'2021-03-28 18:58:53.82',NULL,3,4),
(true,'2021-03-28 19:58:53.82','2021-05-28 17:07:25.545',3,5);

/*(true,'2021-03-28','2021-04-25',false,1,3),
(true,'2021-03-28','2021-04-25',false,2,4),
(true,'2021-03-28','2021-04-25',false,3,5),
(true,'2021-03-28','2021-04-25',false,4,2),
(true,'2021-03-28','2021-04-25',false,4,5)*/

-- Insertion de quatre usagers profil USER
/*INSERT INTO public.usager (email,nom,"password",prenom,"role") VALUES
('muriel.demellier@yahoo.fr','Demellier','$2a$10$FYtHS2axgvdhOo5uPFjAHevCJsP2udjdTyH0.VXFRAccOAMwbH.AK','Muriel','USER')
,('daniel.demellier@yahoo.fr','Demellier','$2a$10$HCDV9MFIj3rdNBJxDqae2OVIVM3QoCEmKfwCUiXtYofknV29bztB6','Daniel','USER')
,('antoine.demellier@yahoo.fr','Demellier','$2a$10$lPv5rPgLhS/38xgF66b9veSauqJQkjzb3q7pz6Gl1EG6n6Hglt59y','Antoine','USER')
,('romain.demellier@yahoo.fr','Dupont','$2a$10$ZJnt1HwUPqZauSY6FXh5/OVTTqOnSeelR0U5mfuV97SXYzBN2yRq2','Martin','USER');*/

-- Insertion de  réservations
/*INSERT INTO public.reservation (actif,"date",date_limit,livre_id,usager_id) VALUES
(true,'2021-03-28 16:58:53.82',NULL,3,2),
(true,'2021-03-28 17:07:25.545',NULL,2,5),
(true,'2021-03-28 17:07:57.633',NULL,3,2),
(true,'2021-03-28 17:08:22.259',NULL,4,3),
(true,'2021-03-28 17:09:12.615',NULL,1,5),
(true,'2021-03-28 17:12:01.485',NULL,4,3);*/

/*INSERT INTO public.auteur (id,nom,prenom) VALUES
(1,'Verne','Jules')
,(2,'Dumas','Alexandre')
,(3,'Rousseau','Jean-Jacques')
,(4,'Gary','Romain')
,(5,'Zola','Emile')
,(6,'Hugo','Victor')
;*/
/*INSERT INTO public.livre (id,genre,nbre_exemplaires,nbre_total,titre,auteur_id,reservable) VALUES
(1,'Science-fiction',2,2,'Vingt milles lieues sous les mers',1,true)
,(2,'Science-fiction',3,3,'Voyage au centre de la Terre',1,true)
,(3,'Aventure',2,2,'La reine Margot',2,true)
,(4,'Aventure',2,2,'Le comte de Monte Cristo',2,true)
,(5,'Aventure',3,3,'Les trois mousquetaires',2,true)
,(6,'Roman',3,3,'La vie devant soi',4,true)
,(7,'Autobiographie',2,2,'Les confessions',3,true)
,(8,'Drame',2,2,'Germinal',5,true)
,(9,'Roman',2,2,'Notre Dame de Paris',6,true)
,(10,'Roman',3,3,'Les Misérables',6,true)
,(11,'Aventure',1,1,'Vingt ans après',2,true)
,(12,'Science-fiction',0,2,'De la Terre à la Lune',1,true)
;

INSERT INTO public.exemplaire (id,etat,livre_id) VALUES
(12,'neuf',12)
                                                      ,(13,'neuf',12)
                                                      ,(17,'neuf',1)
                                                      ,(18,'moyen',1)
                                                      ,(21,'vieux',2)
                                                      ,(22,'moyen',2)
                                                      ,(23,'moyen',3)
                                                      ,(24,'neuf',3)
                                                      ,(26,'vieux',4)
                                                      ,(27,'moyen',4)
                                                      ,(28,'moyen',5)
                                                      ,(29,'neuf',5)
                                                      ,(30,'neuf',5)
                                                      ,(31,'vieux',11)
                                                      ,(32,'vieux',7)
                                                      ,(33,'moyen',7)
                                                      ,(34,'moyen',6)
                                                      ,(35,'moyen',8)
                                                      ,(36,'neuf',8)
                                                      ,(38,'moyen',10)
                                                      ,(39,'vieux',10)
                                                      ,(40,'vieux',9)
                                                      ,(41,'neuf',9)
                                                      ,(1,'neuf',2)
                                                      ,(2,'neuf',6)
                                                      ,(3,'vieux',6)
                                                      ,(4,'neuf',10)
;

INSERT INTO public.usager (id,email,nom,"password",prenom,"role") VALUES
(1,'muriel.demellier@yahoo.fr','Demellier','$2a$10$FYtHS2axgvdhOo5uPFjAHevCJsP2udjdTyH0.VXFRAccOAMwbH.AK','Muriel','USER')
                                                                       ,(2,'daniel.demellier@yahoo.fr','Demellier','$2a$10$HCDV9MFIj3rdNBJxDqae2OVIVM3QoCEmKfwCUiXtYofknV29bztB6','Daniel','USER')
                                                                       ,(3,'antoine.demellier@yahoo.fr','Demellier','$2a$10$lPv5rPgLhS/38xgF66b9veSauqJQkjzb3q7pz6Gl1EG6n6Hglt59y','Antoine','USER')
                                                                       ,(4,'romain.demellier@yahoo.fr','Dupont','$2a$10$ZJnt1HwUPqZauSY6FXh5/OVTTqOnSeelR0U5mfuV97SXYzBN2yRq2','Martin','USER')
                                                                       ,(5,'robert.durand@yahoo.fr','Durand','$2a$10$CWwmr524dOEFcK9NBXMklukivLRKZXWx2y7ndvbGuj8N6UoIaJhoK','Robert','USER')
                                                                       ,(6,'marie.jeannot@yahoo.fr','Jeannot','$2a$10$RDazVQjaxf7nxd/dq9WCPu41gQw1gEPlxzmPPVVYQox4x.UYiqb9m','Marie','USER')
                                                                       ,(7,'gilbert.morel@yahoo.fr','Morel','$2a$10$AvMNDznmrpOzAyEZEP16c.T.hUuvg5JfJsXsnNRVu1b7Yx/wYzNE2','Gilbert ','USER')
                                                                       ,(8,'jacqueline.jeannot@yahoo.fr','Jeannot','$2a$10$eSPn6/SvCg6MSamVcD1.buTv/JsgXFHbSLCVWllTX0Ad6JB53Z2MG','Jacqueline','USER')
                                                                       ,(9,'veronique.biso@yahoo.fr','Biso','$2a$10$kwKdUfEb3PIKeqvwedExU.bW5Psx7qJ7gEdu9Ag.T715VzmdTOxdS','Véronique','USER')
                                                                       ,(10,'stephane.biso@yahoo.fr','Biso','$2a$10$8ShPU4DBhrALGkE3BCmitOd20xkFIDwWQbxYY/eIh/xL3Uq2iK/Re','Stéphane','USER')
;
INSERT INTO public.emprunt (actif,date_emprunt,date_retour,prolonge,exemplaire_id,usager_id) VALUES
(true,'2021-03-28','2021-04-25',false,28,5),
(true,'2021-03-28','2021-04-25',false,29,6),
(true,'2021-03-28','2021-04-25',false,30,7);
INSERT INTO public.reservation (actif,"date",date_limit,livre_id,usager_id,place_dans_liste_reservations) VALUES
(true,'2021-03-28 16:58:53.82',NULL,5,1,0),
(true,'2021-03-28 17:07:25.545',NULL,5,2,1),
(true,'2021-03-28 17:07:57.633',NULL,5,4,2),
(true,'2021-03-28 17:08:22.259',NULL,5,3,3),
(true,'2021-03-28 17:09:12.615',NULL,5,8,4),
(true,'2021-03-28 17:12:01.485',NULL,5,9,5);*/



