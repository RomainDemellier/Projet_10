INSERT INTO public.auteur VALUES (1, 'Verne', 'Jules');
INSERT INTO public.auteur VALUES (2, 'Dumas', 'Alexandre');
INSERT INTO public.auteur VALUES (3, 'Rousseau', 'Jean-Jacques');
INSERT INTO public.auteur VALUES (4, 'Gary', 'Romain');
INSERT INTO public.auteur VALUES (5, 'Zola', 'Emile');
INSERT INTO public.auteur VALUES (6, 'Hugo', 'Victor');

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
