INSERT INTO public.auteur (id,nom,prenom) VALUES
(1,'Verne','Jules')
,(2,'Dumas','Alexandre')
,(3,'Rousseau','Jean-Jacques')
,(4,'Gary','Romain')
,(5,'Zola','Emile')
,(6,'Hugo','Victor')
;

INSERT INTO public.livre (id,genre,nbre_exemplaires,nbre_total,titre,auteur_id,reservable) VALUES
(3,'Science-fiction',2,2,'Vingt milles lieues sous les mers',1,true)
,(4,'Science-fiction',3,3,'Voyage au centre de la Terre',1,true)
,(5,'Aventure',2,2,'La reine Margot',2,true)
,(6,'Aventure',2,2,'Le comte de Monte Cristo',2,true)
,(7,'Aventure',3,3,'Les trois mousquetaires',2,true)
,(10,'Roman',3,3,'La vie devant soi',4,true)
,(9,'Autobiographie',2,2,'Les confessions',3,true)
,(11,'Drame',2,2,'Germinal',5,true)
,(13,'Roman',2,2,'Notre Dame de Paris',6,true)
,(12,'Roman',3,3,'Les Misérables',6,true)
;
INSERT INTO public.livre (id,genre,nbre_exemplaires,nbre_total,titre,auteur_id,reservable) VALUES
(8,'Aventure',1,1,'Vingt ans après',2,true)
,(1,'Science-fiction',0,2,'De la Terre à la Lune',1,true)
;

INSERT INTO public.exemplaire (id,etat,livre_id) VALUES
(12,'neuf',1)
,(13,'neuf',1)
,(17,'neuf',3)
,(18,'moyen',3)
,(21,'vieux',4)
,(22,'moyen',4)
,(23,'moyen',5)
,(24,'neuf',5)
,(26,'vieux',6)
,(27,'moyen',6)
;
INSERT INTO public.exemplaire (id,etat,livre_id) VALUES
(28,'moyen',7)
,(29,'neuf',7)
,(30,'neuf',7)
,(31,'vieux',8)
,(32,'vieux',9)
,(33,'moyen',9)
,(34,'moyen',10)
,(35,'moyen',11)
,(36,'neuf',11)
,(38,'moyen',12)
;
INSERT INTO public.exemplaire (id,etat,livre_id) VALUES
(39,'vieux',12)
,(40,'vieux',13)
,(41,'neuf',13)
,(1,'neuf',4)
,(2,'neuf',10)
,(3,'vieux',10)
,(4,'neuf',12)
;

INSERT INTO public.usager (id,email,nom,"password",prenom,"role") VALUES
(2,'muriel.demellier@yahoo.fr','Demellier','$2a$10$FYtHS2axgvdhOo5uPFjAHevCJsP2udjdTyH0.VXFRAccOAMwbH.AK','Muriel','USER')
,(3,'daniel.demellier@yahoo.fr','Demellier','$2a$10$HCDV9MFIj3rdNBJxDqae2OVIVM3QoCEmKfwCUiXtYofknV29bztB6','Daniel','USER')
,(4,'antoine.demellier@yahoo.fr','Demellier','$2a$10$lPv5rPgLhS/38xgF66b9veSauqJQkjzb3q7pz6Gl1EG6n6Hglt59y','Antoine','USER')
,(5,'martin.dupont@yahoo.fr','Dupont','$2a$10$ZJnt1HwUPqZauSY6FXh5/OVTTqOnSeelR0U5mfuV97SXYzBN2yRq2','Martin','USER')
,(6,'robert.durand@yahoo.fr','Durand','$2a$10$CWwmr524dOEFcK9NBXMklukivLRKZXWx2y7ndvbGuj8N6UoIaJhoK','Robert','USER')
,(7,'marie.jeannot@yahoo.fr','Jeannot','$2a$10$RDazVQjaxf7nxd/dq9WCPu41gQw1gEPlxzmPPVVYQox4x.UYiqb9m','Marie','USER')
,(8,'gilbert.morel@yahoo.fr','Morel','$2a$10$AvMNDznmrpOzAyEZEP16c.T.hUuvg5JfJsXsnNRVu1b7Yx/wYzNE2','Gilbert ','USER')
,(9,'jacqueline.jeannot@yahoo.fr','Jeannot','$2a$10$eSPn6/SvCg6MSamVcD1.buTv/JsgXFHbSLCVWllTX0Ad6JB53Z2MG','Jacqueline','USER')
,(10,'veronique.biso@yahoo.fr','Biso','$2a$10$kwKdUfEb3PIKeqvwedExU.bW5Psx7qJ7gEdu9Ag.T715VzmdTOxdS','Véronique','USER')
,(11,'stephane.biso@yahoo.fr','Biso','$2a$10$8ShPU4DBhrALGkE3BCmitOd20xkFIDwWQbxYY/eIh/xL3Uq2iK/Re','Stéphane','USER')
;
INSERT INTO public.usager (id,email,nom,"password",prenom,"role") VALUES
(12,'nino.biso@yahoo.fr','Biso','$2a$10$6XtPBJybcVOvrDehDo3QgOurKB7k8p1IUNO7SsXYQ3v5uDt.UxOiS','Nino','USER')
,(13,'clement.biso@yahoo.fr','Biso','$2a$10$q9wgfDvO9qX5y9vkOjdkR.zQGR0Dz1cNUSwzOtV/UYZr6kRbSHxaa','Clément','USER')
,(14,'xavier.dumazel@yahoo.fr','Dumazel','$2a$10$6skLbDyIdtYlxkdSp.7He.nswxdGJnHmiLxMTFKmiVazYgPKvd83e','Xavier','USER')
,(15,'alexandre.cadiere@yahoo.fr','Cadière','$2a$10$kOWIFGaPnvxspfNie6HiNujNglXDyhPsbJh9iJeBQCQq3oQUgg46G','Alexandre','USER')
,(16,'stan.barnett@yahoo.fr','Barnett','$2a$10$CKhBmIjpES6/e9yMPSrdAOvOgNfRVIErqdl/qZncUeGQRtzls13x6','Stan','USER')
,(1,'romaindemellier@yahoo.fr','Demellier','$2a$10$3tVfsbSexsNBiVJgz3u4H.3RcljrmKi8y1PJAQDRMb1SLXieOe5cO','Romain','ADMIN')
,(17,'batch@yahoo.fr','Batch','$2a$10$ivXuOn74w1X7sG5lSdhJreadxRpylp5FQJHJWmC8YwD41IJWsCVJu','Batch','USER')
;