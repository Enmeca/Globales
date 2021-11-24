-- UFRIENDS DB RECURSIVE SCRIPT 

--=================================================

-- TABLE DROPS

PROMPT ========================================
PROMPT DROP TABLES
PROMPT ========================================


drop table CHAT_MESSAGES cascade constraints;
drop table MATCHED_USERS cascade constraints;
drop table LIKED_USERS cascade constraints;
drop table CAREERS cascade constraints;
drop table UNIVERSITIES cascade constraints;
drop table TAGS cascade constraints;
drop table USER_TAGS cascade constraints;
drop table TUTOR_REVIEWS cascade constraints;
drop table FORUM_TOPICS cascade constraints;
drop table FORUM_COMMENTS cascade constraints;
drop table COMMENT_REPORTS cascade constraints;
drop table USER_PHOTOS cascade constraints;
drop table USERS cascade constraints;

--=================================================

-- SEQUENCE DROPS

PROMPT ========================================
PROMPT DROP SEQUENCES
PROMPT ========================================


drop sequence SEQ_ID_MATCHED_USERS;
drop sequence SEQ_ID_CHAT_MESSAGES;
drop sequence SEQ_ID_FORUM_TOPICS;  
drop sequence SEQ_ID_FORUM_COMMENTS;

--=================================================

-- CREATE TABLES

PROMPT ========================================
PROMPT CREATE TABLES
PROMPT ========================================


create table CAREERS(ID varchar2(10) not null, 
					NAME varchar2(50) not null) tablespace system;
					
					
create table UNIVERSITIES(ID varchar2(10) not null, 
					NAME varchar2(50) not null) tablespace system;
					
					
create table TAGS(NAME varchar2(50) not null) tablespace system;					
					                           
											   
create table USERS(ID varchar2(20) not null, 
				CAREER_ID varchar2(10) not null,
				UNIVERSITY_ID varchar2(10) not null, 
				EMAIL varchar2(45) not null,  
				PASSWORD varchar2(20) not null,
				NAME varchar2(45) not null, 
				LAST_NAME_1 varchar2(25) not null, 
				LAST_NAME_2 varchar2(25) not null, 
				DATE_OF_BIRTH date not null,
				DESCRIPTION varchar2(200),
				LAST_CONNECTED date,
				IS_ACTIVE number(1) not null,
				IS_TUTOR number(1) not null,
				IS_ADMIN number(1) not null) tablespace system;
				
				
create table USER_PHOTOS(USER_UID varchar2(20) not null, 
					PROFILE_PIC blob) tablespace system;
				
				
create table TUTOR_REVIEWS(USER_UID varchar2(20) not null,
						TUTOR_ID varchar2(20) not null,
						SCORE float(2) not null,
						COMMENTS varchar2(100) not null) tablespace system;
						
						
create table USER_TAGS(USER_UID varchar2(20) not null,
						TAG_ID varchar2(45) not null) tablespace system;
						
						
create table FORUM_TOPICS(ID number not null,
						AUTHOR_ID varchar2(20) not null,
						TITLE varchar2(100) not null,
						DESCRIPTION varchar2(500) not null,
						CREATION_DATE date,
						COMMENTS_QUANTITY number not null,
						IS_ANON number(1) not null) tablespace system;
						
						
create table FORUM_COMMENTS(ID number not null,
						FORUM_ID number not null,
						AUTHOR_ID varchar2(20) not null,
						DESCRIPTION varchar2(500) not null,
						CREATION_DATE date,
						IS_ANON number(1) not null) tablespace system;
						
						
create table COMMENT_REPORTS(COMMENT_ID number not null,
						USER_UID varchar2(20) not null,
						MOTIVE varchar2(500) not null,
						CREATION_DATE date) tablespace system;
						
						
create table LIKED_USERS(USER_UID varchar2(20) not null,
						LIKED_USER_UID varchar2(20) not null) tablespace system;
						
-- This table is used to store the matched users and also as a "chat" table						
create table MATCHED_USERS(ID number not null,
						USER_UID varchar2(20) not null,
						MATCHED_USER_UID varchar2(20) not null,
						MATCHED_DATE date) tablespace system;
						
create table CHAT_MESSAGES(ID number not null,
						CHAT_UID number not null,
						USER_UID varchar2(20) not null,
						MESSAGE varchar2(200) not null,
						DATE_SENT date,
						WAS_READ number(1) not null) tablespace system;
						
						
--=================================================

-- CREATE SEQUENCES

PROMPT ========================================
PROMPT CREATE SEQUENCES
PROMPT ========================================


create sequence SEQ_ID_FORUM_TOPICS start with 1 increment by 1 cache 2;

create sequence SEQ_ID_FORUM_COMMENTS start with 1 increment by 1 cache 2;

create sequence SEQ_ID_MATCHED_USERS start with 1 increment by 1 cache 2;

create sequence SEQ_ID_CHAT_MESSAGES start with 1 increment by 1 cache 2;


--=================================================

-- ALTER TABLES - PRIMARY KEY

PROMPT ========================================
PROMPT ALTER TABLES - PRIMARY KEY
PROMPT ========================================


alter table CAREERS add constraint CAREERS_PK primary key(ID) using index tablespace system;

alter table UNIVERSITIES add constraint UNIVERSITIES_PK primary key(ID) using index tablespace system;

alter table TAGS add constraint TAGS_PK primary key(NAME) using index tablespace system;

alter table USERS add constraint USERS_PK primary key(ID) using index tablespace system;

alter table USER_PHOTOS add constraint USERS_PHOTOS_PK primary key(USER_UID) using index tablespace system;

alter table TUTOR_REVIEWS add constraint TUTOR_REVIEWS_PK primary key(USER_UID,TUTOR_ID) using index tablespace system;

alter table USER_TAGS add constraint USER_TAGS_PK primary key(USER_UID,TAG_ID) using index tablespace system;

alter table FORUM_TOPICS add constraint FORUM_TOPICS_PK primary key(ID) using index tablespace system;

alter table FORUM_COMMENTS add constraint FORUM_COMMENTS_PK primary key(ID) using index tablespace system;

alter table COMMENT_REPORTS add constraint COMMENT_REPORTS_PK primary key(COMMENT_ID,USER_UID) using index tablespace system;

alter table LIKED_USERS add constraint LIKED_USERS_PK primary key(USER_UID,LIKED_USER_UID) using index tablespace system;

alter table MATCHED_USERS add constraint MATCHED_USERS_PK primary key(ID) using index tablespace system;

alter table CHAT_MESSAGES add constraint CHAT_MESSAGES_PK primary key(ID) using index tablespace system;


--=================================================

-- ALTER TABLES - FOREIGN KEY

PROMPT ========================================
PROMPT ALTER TABLES - FOREIGN KEY
PROMPT ========================================


alter table USERS add constraint CAREER_FK foreign key (CAREER_ID) references CAREERS;
alter table USERS add constraint UNIVERSITY_FK foreign key (UNIVERSITY_ID) references UNIVERSITIES;

--alter table USER_PHOTOS add constraint USERS_PHOTOS_FK foreign key (USER_UID) references USERS;

alter table TUTOR_REVIEWS add constraint REVIEWS_USER_FK foreign key (USER_UID) references USERS;
alter table TUTOR_REVIEWS add constraint REVIEWS_TUTOR_FK foreign key (TUTOR_ID) references USERS;

alter table USER_TAGS add constraint USER_TAGS_USER_FK foreign key (USER_UID) references USERS;
alter table USER_TAGS add constraint USER_TAGS_TAG_FK foreign key (TAG_ID) references TAGS;

alter table FORUM_TOPICS add constraint TOPICS_AUTHOR_FK foreign key (AUTHOR_ID) references USERS;

alter table FORUM_COMMENTS add constraint COMMENTS_FORUM_FK foreign key (FORUM_ID) references FORUM_TOPICS on delete cascade;
alter table FORUM_COMMENTS add constraint COMMENTS_AUTHOR_FK foreign key (AUTHOR_ID) references USERS;

alter table COMMENT_REPORTS add constraint REPORTS_COMMENT_FK foreign key (COMMENT_ID) references FORUM_COMMENTS;
alter table COMMENT_REPORTS add constraint REPORTS_USER_FK foreign key (USER_UID) references USERS;

alter table LIKED_USERS add constraint LIKED_USERS_USER_FK foreign key (USER_UID) references USERS;
alter table LIKED_USERS add constraint LIKED_USERS_L_USER_FK foreign key (LIKED_USER_UID) references USERS;

alter table MATCHED_USERS add constraint MATCHED_USERS_USER_FK foreign key (USER_UID) references USERS;
alter table MATCHED_USERS add constraint MATCHED_USERS_M_USER_FK foreign key (MATCHED_USER_UID) references USERS;

alter table CHAT_MESSAGES add constraint CHAT_MESSAGES_CHAT_FK foreign key (CHAT_UID) references MATCHED_USERS;
alter table CHAT_MESSAGES add constraint CHAT_MESSAGES_USER_FK foreign key (USER_UID) references USERS;


--=================================================

-- ALTER TABLES - UNIQUE KEY

PROMPT ========================================
PROMPT ALTER TABLES - UNIQUE KEY
PROMPT ========================================

ALTER TABLE MATCHED_USERS ADD CONSTRAINT MATCHED_USERS_UK UNIQUE (USER_UID, MATCHED_USER_UID);


--=================================================

-- TRIGGERS

PROMPT ========================================
PROMPT CREATE TRIGGERS
PROMPT ========================================


create or replace trigger FORUM_TOPICS_DATE_TRIGGER
	before insert 
	on FORUM_TOPICS
	for each row
 BEGIN 
	select SYSDATE
	into :new.CREATION_DATE
	from DUAL;
 END;
 /
 
create or replace trigger FORUM_COMMENTS_DATE_TRIGGER
	before insert 
	on FORUM_COMMENTS
	for each row
 BEGIN 
	select SYSDATE
	into :new.CREATION_DATE
	from DUAL;
 END;
 /

create or replace trigger COMMENT_REPORTS_DATE_TRIGGER
	before insert 
	on COMMENT_REPORTS
	for each row
 BEGIN 
	select SYSDATE
	into :new.CREATION_DATE
	from DUAL;
 END;
 /

create or replace trigger MATCHED_USERS_DATE_TRIGGER
	before insert 
	on MATCHED_USERS
	for each row
 BEGIN 
	select SYSDATE
	into :new.MATCHED_DATE
	from DUAL;
 END;
 / 
 
create or replace trigger CHAT_MESSAGES_DATE_TRIGGER
	before insert 
	on CHAT_MESSAGES
	for each row
 BEGIN 
	select SYSDATE
	into :new.DATE_SENT
	from DUAL;
 END;
 / 

--==============================================================================================================

-- INSERTS

PROMPT ========================================
PROMPT INSERTS INTO TABLES TO HAVE INITIAL DATA 
PROMPT ======================================== 

--CAREERS

insert into CAREERS values ('ADM', 'Administracion de Empresas');
insert into CAREERS values ('ARQ', 'Arquitectura');
insert into CAREERS values ('BIO', 'Biologia');
insert into CAREERS values ('CMOVH', 'C. Movimiento Humano');
insert into CAREERS values ('DER', 'Derecho');
insert into CAREERS values ('ECON', 'Economia');
insert into CAREERS values ('HIS', 'Historia');
insert into CAREERS values ('ING-IND', 'Ingenieria Industrial');
insert into CAREERS values ('ING-SIS', 'Ingenieria de Sistemas');
insert into CAREERS values ('MECTR', 'Mecatronica');
insert into CAREERS values ('MED', 'Medicina y Cirugia');
insert into CAREERS values ('RELINT', 'Relaciones Internacionales');
insert into CAREERS values ('VET', 'Veterinaria');

--==============================================================================================================

--UNIVERSITIES
insert into UNIVERSITIES values ('TEC', 'Instituto Tecnologico de Costa Rica');
insert into UNIVERSITIES values ('UACA', 'Universidad Autonoma de Centro America');
insert into UNIVERSITIES values ('UCR', 'Universidad de Costa Rica');
insert into UNIVERSITIES values ('ULATINA', 'Universidad Latina de Costa Rica');
insert into UNIVERSITIES values ('UNA', 'Universidad Nacional de Costa Rica');
insert into UNIVERSITIES values ('UNED', 'Universidad Estatal a Distancia');

--==============================================================================================================

--TAGS
insert into TAGS values ('Animales');
insert into TAGS values ('Artes Marciales');
insert into TAGS values ('Ajedrez');
insert into TAGS values ('Baile');
insert into TAGS values ('Basketbol');
insert into TAGS values ('Boxeo');
insert into TAGS values ('Canto');
insert into TAGS values ('Ciclismo');
insert into TAGS values ('Cine');
insert into TAGS values ('Cocina');
insert into TAGS values ('Comics');
insert into TAGS values ('Criptomonedas');
insert into TAGS values ('Deportes');
insert into TAGS values ('Dibujo');
insert into TAGS values ('Escritura');
insert into TAGS values ('Fotografia');
insert into TAGS values ('Futbol');
insert into TAGS values ('Historia');
insert into TAGS values ('Idiomas');
insert into TAGS values ('Jardineria');
insert into TAGS values ('Lectura');
insert into TAGS values ('Musica');
insert into TAGS values ('Natacion');
insert into TAGS values ('Naturaleza');
insert into TAGS values ('Patinar');
insert into TAGS values ('Ping-pong');
insert into TAGS values ('Series animadas');
insert into TAGS values ('Teatro');
insert into TAGS values ('Tecnologia');
insert into TAGS values ('Viajar');
insert into TAGS values ('Videojuegos');
insert into TAGS values ('Yoga');


--==============================================================================================================

--USERS


-- THIS NEXT ONE IS THE ADMIN
insert into USERS values ('11111111','ING-SIS','UNA','admin-hector-p@gmail.com','123','Hector','Perez','Arias', TO_DATE('1988/03/24', 'yyyy/mm/dd'),
				'Administrador Principal del Sistema UFriends', null, 1, 0, 1);



-- THE NEXT ONES ARE TUTORS			

insert into USERS values ('212283905','ADM','TEC','mirAS-tutor@gmail.com','123','Miranda','Azofeifa','Solano', TO_DATE('2001/10/04', 'yyyy/mm/dd'),
				'Estudiante de Administracion de Empresas en el TEC. Siempre feliz de ayudar', null, 1, 1, 0);
				
insert into USERS values ('613253965','MED','UCR','tutor2PabloFC@gmail.com','123','Pablo','Ferrer','Castro', TO_DATE('1999/02/14', 'yyyy/mm/dd'),
				'Fan de la NBA. Tutor de varios cursos de la carrera de medicina.', null, 1, 1, 0);
				
insert into USERS values ('542937563','ECON','UNA','tutGabriel99@gmail.com','123','Gabriel','Fernandez','Sanchez', TO_DATE('2002/04/25', 'yyyy/mm/dd'),
				'Inversionista en Criptomonedas. Tutor de varios cursos de Economia.', null, 1, 1, 0);



-- THE NEXT ONES ARE NORMAL USERS

				
insert into USERS values ('117780905','ING-SIS','UNA','alb3232@gmail.com','123','Alberto','Achio','Badilla', TO_DATE('2000/05/30', 'yyyy/mm/dd'),
				'Estudiante de Ingenieria de Sistemas en la UNA. Actualmente en el ultimo semestre', null, 1, 0, 0);
				
insert into USERS values ('615283905','ADM','UACA','jovs64@gmail.com','123','Joselyn','Vargas','Salazar', TO_DATE('2002/06/08', 'yyyy/mm/dd'),
				'Deseando que se termine el semestre para tener vacaciones.', null, 1, 0, 0);
				
insert into USERS values ('145678910','ARQ','ULATINA','dan23@gmail.com','123','Daniela','Lobo','Lara', TO_DATE('2001/11/15', 'yyyy/mm/dd'),
				'Me gusta leer y escribir poesia, me encanta conocer gente nueva.', null, 1, 0, 0);
				

--==============================================================================================================
				
-- USER TAGS

-- TAGS FOR USER '11111111'	Hector Perez - Admin
insert into USER_TAGS values ('11111111','Deportes');
insert into USER_TAGS values ('11111111','Viajar');
insert into USER_TAGS values ('11111111','Cine');	


-- TAGS FOR USER '212283905' Miranda Azofeifa - Tutor
insert into USER_TAGS values ('212283905','Naturaleza');
insert into USER_TAGS values ('212283905','Teatro');
insert into USER_TAGS values ('212283905','Ping-pong');	
insert into USER_TAGS values ('212283905','Yoga');	
insert into USER_TAGS values ('212283905','Viajar');	
insert into USER_TAGS values ('212283905','Cine');	


-- TAGS FOR USER '613253965' Pablo Ferrer - Tutor
insert into USER_TAGS values ('613253965','Ciclismo');	
insert into USER_TAGS values ('613253965','Naturaleza');	
insert into USER_TAGS values ('613253965','Musica');	
insert into USER_TAGS values ('613253965','Boxeo');	
insert into USER_TAGS values ('613253965','Basketbol');	
insert into USER_TAGS values ('613253965','Cine');	
insert into USER_TAGS values ('613253965','Viajar');	


-- TAGS FOR USER '542937563' Gabriel Fernandez - Tutor
insert into USER_TAGS values ('542937563','Natacion');	
insert into USER_TAGS values ('542937563','Lectura');	
insert into USER_TAGS values ('542937563','Musica');	
insert into USER_TAGS values ('542937563','Criptomonedas');		
insert into USER_TAGS values ('542937563','Teatro');	
insert into USER_TAGS values ('542937563','Cine');		
				
							
-- TAGS FOR USER '117780905' Alberto Achio - Normal User
insert into USER_TAGS values ('117780905','Videojuegos');
insert into USER_TAGS values ('117780905','Idiomas');
insert into USER_TAGS values ('117780905','Dibujo');
insert into USER_TAGS values ('117780905','Cine');	
insert into USER_TAGS values ('117780905','Cocina');	
insert into USER_TAGS values ('117780905','Natacion');
insert into USER_TAGS values ('117780905','Series animadas');
insert into USER_TAGS values ('117780905','Ping-pong');	
insert into USER_TAGS values ('117780905','Viajar');	


-- TAGS FOR USER '615283905' Joselyn Vargas - Normal User
insert into USER_TAGS values ('615283905','Videojuegos');
insert into USER_TAGS values ('615283905','Idiomas');
insert into USER_TAGS values ('615283905','Cine');	
insert into USER_TAGS values ('615283905','Naturaleza');	
insert into USER_TAGS values ('615283905','Animales');	
insert into USER_TAGS values ('615283905','Cocina');
insert into USER_TAGS values ('615283905','Viajar');
insert into USER_TAGS values ('615283905','Fotografia');
insert into USER_TAGS values ('615283905','Canto');
insert into USER_TAGS values ('615283905','Jardineria');

-- TAGS FOR USER '145678910' Daniela Lobo - Tutor
insert into USER_TAGS values ('145678910','Escritura');	
insert into USER_TAGS values ('145678910','Naturaleza');	
insert into USER_TAGS values ('145678910','Lectura');	
insert into USER_TAGS values ('145678910','Historia');	
insert into USER_TAGS values ('145678910','Baile');	
insert into USER_TAGS values ('145678910','Cine');	
insert into USER_TAGS values ('145678910','Viajar');	

--==============================================================================================================

-- FORUM TOPICS 
insert into FORUM_TOPICS values (SEQ_ID_FORUM_TOPICS.nextval,'117780905','UNA - Busco gente para proyecto 2 de Progra III','Buenas gente, ando buscando miembros de grupo para el proyecto 2 de Progra III. Favor los interesados dejar un comentario',null,0,0);
insert into FORUM_TOPICS values (SEQ_ID_FORUM_TOPICS.nextval,'542937563','Consideran que se deberia regresar a la presencialidad el siguiente semestre ?','En mi opinion creo que no, dado que aun siguen habiendo incrementos de los casos. Pero quisiera conocer mas opiniones',null,0,0);
insert into FORUM_TOPICS values (SEQ_ID_FORUM_TOPICS.nextval,'145678910','ULATINA - Noches de cuentos y poesia','Hola a todos, queria comentar por esta red que en la ULATINA se van a estar haciendo noches de cuento y poesia todos los jueves a las 6 pm en el aula 203 del Edificio 1. Se daran snacks a aquellos participantes frecuentes !',null,0,0);
				
				
--==============================================================================================================
				
-- FORUM COMMENTS

-- FORUM TOPIC 1 UNA - Busco gente ...
insert into FORUM_COMMENTS values (SEQ_ID_FORUM_COMMENTS.nextval,1,'11111111','Hola Alberto, yo estoy interesado. Ambos estamos en el mismo grupo con el profesor Enrique G.', null, 0);
insert into FORUM_COMMENTS values (SEQ_ID_FORUM_COMMENTS.nextval,1,'542937563','Yo no estoy interesado pero conozco gente que ocupa grupo. Aun queda campo ?', null, 0);
insert into FORUM_COMMENTS values (SEQ_ID_FORUM_COMMENTS.nextval,1,'117780905','Aun queda campo en el grupo, hacen falta 2 personas', null, 0);


-- FORUM TOPIC 2 Consideran que..
insert into FORUM_COMMENTS values (SEQ_ID_FORUM_COMMENTS.nextval,2,'613253965','Considero que si se deberia volver a la presencialidad, hay que empezar a volver a la normalidad en algun momento y mejor desde ya', null, 0);
insert into FORUM_COMMENTS values (SEQ_ID_FORUM_COMMENTS.nextval,2,'615283905','Es un tema delicado, yo en lo personal prefiero las clases remotas. Me ahorro tener que viajar y tengo mas tiempo.', null, 1);


-- FORUM TOPIC 3 ULATINA - Noches de ....
insert into FORUM_COMMENTS values (SEQ_ID_FORUM_COMMENTS.nextval,3,'212283905','Que bonito ! Pueden asistir personas que no sean de la universidad ?', null, 0);
insert into FORUM_COMMENTS values (SEQ_ID_FORUM_COMMENTS.nextval,3,'542937563','Interesado en ganar Bitcoins ? En la siguiente pagina puede aprender todos los secretos para ganar bitcoins: www.estafaObvia.com', null, 1);


--==============================================================================================================

-- TUTOR Reviews
insert into TUTOR_REVIEWS values ('615283905','212283905',4,'Muy buena tutora, siempre fue amable y me ayudo a entender la materia');
insert into TUTOR_REVIEWS values ('117780905','212283905',5,'Excelente tutora, los resumenes que hace son los mejores');


insert into TUTOR_REVIEWS values ('615283905','613253965',4,'Me ayudo mucho');
insert into TUTOR_REVIEWS values ('145678910','613253965',4,'Sabe bastante de los temas y hace practicas');


insert into TUTOR_REVIEWS values ('117780905','542937563',3,'Buena persona pero le suele costar explicar ideas complejas');
insert into TUTOR_REVIEWS values ('145678910','542937563',3,'Muchas veces no se econtraba disponible para ayudar');
								

commit;

-- THE TWO MOST COMPATIBLE USERS ARE '117780905' Alberto Achio and '615283905' Joselyn Vargas