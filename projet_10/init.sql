--
-- PostgreSQL database dump
--

-- Dumped from database version 10.15 (Ubuntu 10.15-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.15 (Ubuntu 10.15-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: auteur; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.auteur (
    id bigint NOT NULL,
    nom character varying(255),
    prenom character varying(255)
);


ALTER TABLE public.auteur OWNER TO romain;

--
-- Name: auteur_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.auteur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auteur_id_seq OWNER TO romain;

--
-- Name: auteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.auteur_id_seq OWNED BY public.auteur.id;


--
-- Name: emprunt; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.emprunt (
    id bigint NOT NULL,
    actif boolean,
    date_emprunt date,
    date_retour date,
    prolonge boolean,
    exemplaire_id bigint NOT NULL,
    usager_id bigint NOT NULL
);


ALTER TABLE public.emprunt OWNER TO romain;

--
-- Name: emprunt_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.emprunt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.emprunt_id_seq OWNER TO romain;

--
-- Name: emprunt_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.emprunt_id_seq OWNED BY public.emprunt.id;


--
-- Name: exemplaire; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.exemplaire (
    id bigint NOT NULL,
    etat character varying(255),
    livre_id bigint NOT NULL
);


ALTER TABLE public.exemplaire OWNER TO romain;

--
-- Name: exemplaire_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.exemplaire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.exemplaire_id_seq OWNER TO romain;

--
-- Name: exemplaire_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.exemplaire_id_seq OWNED BY public.exemplaire.id;


--
-- Name: livre; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.livre (
    id bigint NOT NULL,
    genre character varying(255),
    nbre_exemplaires integer,
    nbre_total integer NOT NULL,
    titre character varying(255),
    auteur_id bigint NOT NULL,
    reservable boolean
);


ALTER TABLE public.livre OWNER TO romain;

--
-- Name: livre_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.livre_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.livre_id_seq OWNER TO romain;

--
-- Name: livre_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.livre_id_seq OWNED BY public.livre.id;


--
-- Name: reservation; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.reservation (
    id bigint NOT NULL,
    date timestamp(0) without time zone,
    date_limit timestamp(0) without time zone,
    livre_id bigint NOT NULL,
    usager_id bigint NOT NULL,
    actif boolean
);


ALTER TABLE public.reservation OWNER TO romain;

--
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_id_seq OWNER TO romain;

--
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;


--
-- Name: usager; Type: TABLE; Schema: public; Owner: romain
--

CREATE TABLE public.usager (
    id bigint NOT NULL,
    email character varying(255),
    nom character varying(20),
    password character varying(255),
    prenom character varying(20),
    role character varying(255)
);


ALTER TABLE public.usager OWNER TO romain;

--
-- Name: usager_id_seq; Type: SEQUENCE; Schema: public; Owner: romain
--

CREATE SEQUENCE public.usager_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usager_id_seq OWNER TO romain;

--
-- Name: usager_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: romain
--

ALTER SEQUENCE public.usager_id_seq OWNED BY public.usager.id;


--
-- Name: auteur id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.auteur ALTER COLUMN id SET DEFAULT nextval('public.auteur_id_seq'::regclass);


--
-- Name: emprunt id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt ALTER COLUMN id SET DEFAULT nextval('public.emprunt_id_seq'::regclass);


--
-- Name: exemplaire id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.exemplaire ALTER COLUMN id SET DEFAULT nextval('public.exemplaire_id_seq'::regclass);


--
-- Name: livre id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.livre ALTER COLUMN id SET DEFAULT nextval('public.livre_id_seq'::regclass);


--
-- Name: reservation id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- Name: usager id; Type: DEFAULT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.usager ALTER COLUMN id SET DEFAULT nextval('public.usager_id_seq'::regclass);


--
-- Data for Name: auteur; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.auteur (id, nom, prenom) FROM stdin;
1	Verne	Jules
2	Dumas	Alexandre
3	Rousseau	Jean-Jacques
4	Gary	Romain
5	Zola	Emile
6	Hugo	Victor
\.


--
-- Data for Name: emprunt; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.emprunt (id, actif, date_emprunt, date_retour, prolonge, exemplaire_id, usager_id) FROM stdin;
3	t	2020-11-08	2020-12-06	f	12	6
1	f	2020-11-08	2020-12-06	f	31	5
2	f	2020-11-08	2020-12-06	f	12	5
4	t	2020-11-22	2020-12-20	f	31	5
5	t	2020-11-28	2021-01-23	f	12	5
\.


--
-- Data for Name: exemplaire; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.exemplaire (id, etat, livre_id) FROM stdin;
12	neuf	1
13	neuf	1
17	neuf	3
18	moyen	3
21	vieux	4
22	moyen	4
23	moyen	5
24	neuf	5
26	vieux	6
27	moyen	6
28	moyen	7
29	neuf	7
30	neuf	7
31	vieux	8
32	vieux	9
33	moyen	9
34	moyen	10
35	moyen	11
36	neuf	11
38	moyen	12
39	vieux	12
40	vieux	13
41	neuf	13
1	neuf	4
2	neuf	10
3	vieux	10
4	neuf	12
\.


--
-- Data for Name: livre; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.livre (id, genre, nbre_exemplaires, nbre_total, titre, auteur_id, reservable) FROM stdin;
3	Science-fiction	2	2	Vingt milles lieues sous les mers	1	t
4	Science-fiction	3	3	Voyage au centre de la Terre	1	t
5	Aventure	2	2	La reine Margot	2	t
6	Aventure	2	2	Le comte de Monte Cristo	2	t
7	Aventure	3	3	Les trois mousquetaires	2	t
10	Roman	3	3	La vie devant soi	4	t
9	Autobiographie	2	2	Les confessions	3	t
11	Drame	2	2	Germinal	5	t
13	Roman	2	2	Notre Dame de Paris	6	t
12	Roman	3	3	Les Misérables	6	t
8	Aventure	1	1	Vingt ans après	2	t
1	Science-fiction	0	2	De la Terre à la Lune	1	t
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.reservation (id, date, date_limit, livre_id, usager_id, actif) FROM stdin;
7	2020-11-14 00:00:00	\N	1	6	f
14	2020-11-22 14:41:12	2020-11-30 11:11:19	8	7	f
15	2020-11-28 11:14:57	2020-11-30 12:16:18	1	7	f
16	2020-11-28 11:23:57	2020-11-30 12:00:04	1	7	f
17	2020-11-29 15:51:08	2020-11-29 15:51:35	1	6	t
\.


--
-- Data for Name: usager; Type: TABLE DATA; Schema: public; Owner: romain
--

COPY public.usager (id, email, nom, password, prenom, role) FROM stdin;
2	muriel.demellier@yahoo.fr	Demellier	$2a$10$FYtHS2axgvdhOo5uPFjAHevCJsP2udjdTyH0.VXFRAccOAMwbH.AK	Muriel	USER
3	daniel.demellier@yahoo.fr	Demellier	$2a$10$HCDV9MFIj3rdNBJxDqae2OVIVM3QoCEmKfwCUiXtYofknV29bztB6	Daniel	USER
4	antoine.demellier@yahoo.fr	Demellier	$2a$10$lPv5rPgLhS/38xgF66b9veSauqJQkjzb3q7pz6Gl1EG6n6Hglt59y	Antoine	USER
5	martin.dupont@yahoo.fr	Dupont	$2a$10$ZJnt1HwUPqZauSY6FXh5/OVTTqOnSeelR0U5mfuV97SXYzBN2yRq2	Martin	USER
6	robert.durand@yahoo.fr	Durand	$2a$10$CWwmr524dOEFcK9NBXMklukivLRKZXWx2y7ndvbGuj8N6UoIaJhoK	Robert	USER
7	marie.jeannot@yahoo.fr	Jeannot	$2a$10$RDazVQjaxf7nxd/dq9WCPu41gQw1gEPlxzmPPVVYQox4x.UYiqb9m	Marie	USER
8	gilbert.morel@yahoo.fr	Morel	$2a$10$AvMNDznmrpOzAyEZEP16c.T.hUuvg5JfJsXsnNRVu1b7Yx/wYzNE2	Gilbert 	USER
9	jacqueline.jeannot@yahoo.fr	Jeannot	$2a$10$eSPn6/SvCg6MSamVcD1.buTv/JsgXFHbSLCVWllTX0Ad6JB53Z2MG	Jacqueline	USER
10	veronique.biso@yahoo.fr	Biso	$2a$10$kwKdUfEb3PIKeqvwedExU.bW5Psx7qJ7gEdu9Ag.T715VzmdTOxdS	Véronique	USER
11	stephane.biso@yahoo.fr	Biso	$2a$10$8ShPU4DBhrALGkE3BCmitOd20xkFIDwWQbxYY/eIh/xL3Uq2iK/Re	Stéphane	USER
12	nino.biso@yahoo.fr	Biso	$2a$10$6XtPBJybcVOvrDehDo3QgOurKB7k8p1IUNO7SsXYQ3v5uDt.UxOiS	Nino	USER
13	clement.biso@yahoo.fr	Biso	$2a$10$q9wgfDvO9qX5y9vkOjdkR.zQGR0Dz1cNUSwzOtV/UYZr6kRbSHxaa	Clément	USER
14	xavier.dumazel@yahoo.fr	Dumazel	$2a$10$6skLbDyIdtYlxkdSp.7He.nswxdGJnHmiLxMTFKmiVazYgPKvd83e	Xavier	USER
15	alexandre.cadiere@yahoo.fr	Cadière	$2a$10$kOWIFGaPnvxspfNie6HiNujNglXDyhPsbJh9iJeBQCQq3oQUgg46G	Alexandre	USER
16	stan.barnett@yahoo.fr	Barnett	$2a$10$CKhBmIjpES6/e9yMPSrdAOvOgNfRVIErqdl/qZncUeGQRtzls13x6	Stan	USER
1	romaindemellier@yahoo.fr	Demellier	$2a$10$3tVfsbSexsNBiVJgz3u4H.3RcljrmKi8y1PJAQDRMb1SLXieOe5cO	Romain	ADMIN
17	batch@yahoo.fr	Batch	$2a$10$ivXuOn74w1X7sG5lSdhJreadxRpylp5FQJHJWmC8YwD41IJWsCVJu	Batch	USER
\.


--
-- Name: auteur_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.auteur_id_seq', 1, false);


--
-- Name: emprunt_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.emprunt_id_seq', 5, true);


--
-- Name: exemplaire_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.exemplaire_id_seq', 4, true);


--
-- Name: livre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.livre_id_seq', 1, false);


--
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.reservation_id_seq', 17, true);


--
-- Name: usager_id_seq; Type: SEQUENCE SET; Schema: public; Owner: romain
--

SELECT pg_catalog.setval('public.usager_id_seq', 17, true);


--
-- Name: auteur auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.auteur
    ADD CONSTRAINT auteur_pkey PRIMARY KEY (id);


--
-- Name: emprunt emprunt_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT emprunt_pkey PRIMARY KEY (id);


--
-- Name: exemplaire exemplaire_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT exemplaire_pkey PRIMARY KEY (id);


--
-- Name: livre livre_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT livre_pkey PRIMARY KEY (id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- Name: usager uk_dqf4vbwkwu9ky0mn0d29i0fmt; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.usager
    ADD CONSTRAINT uk_dqf4vbwkwu9ky0mn0d29i0fmt UNIQUE (email);


--
-- Name: usager usager_pkey; Type: CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.usager
    ADD CONSTRAINT usager_pkey PRIMARY KEY (id);


--
-- Name: emprunt fk6atl8ahwhjb2775lpoofi0mju; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT fk6atl8ahwhjb2775lpoofi0mju FOREIGN KEY (usager_id) REFERENCES public.usager(id);


--
-- Name: reservation fkbtwk47ayb3rwdyr6uqp7dsgr2; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fkbtwk47ayb3rwdyr6uqp7dsgr2 FOREIGN KEY (livre_id) REFERENCES public.livre(id);


--
-- Name: livre fkh0pb6pxv3ubtgo1s3ev4gebgj; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.livre
    ADD CONSTRAINT fkh0pb6pxv3ubtgo1s3ev4gebgj FOREIGN KEY (auteur_id) REFERENCES public.auteur(id);


--
-- Name: emprunt fkj7uf2osy43jrxo78f2l217ar4; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.emprunt
    ADD CONSTRAINT fkj7uf2osy43jrxo78f2l217ar4 FOREIGN KEY (exemplaire_id) REFERENCES public.exemplaire(id);


--
-- Name: exemplaire fkkcraqrinp6mtrkkg9rigi6len; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.exemplaire
    ADD CONSTRAINT fkkcraqrinp6mtrkkg9rigi6len FOREIGN KEY (livre_id) REFERENCES public.livre(id);


--
-- Name: reservation fknk305oqdydt777ngtpch6u0w4; Type: FK CONSTRAINT; Schema: public; Owner: romain
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT fknk305oqdydt777ngtpch6u0w4 FOREIGN KEY (usager_id) REFERENCES public.usager(id);


--
-- PostgreSQL database dump complete
--
