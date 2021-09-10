-- UFRIENDS DB RECURSIVE SCRIPT 

--=================================================

-- TABLE DROPS

drop table CAREERS cascade constraints;
drop table UNIVERSITIES cascade constraints;
drop table TAGS cascade constraints;
drop table USER_TAGS cascade constraints;
drop table TUTOR_REVIEWS cascade constraints;
drop table FORUM_TOPICS cascade constraints;
drop table FORUM_COMMENTS cascade constraints;
drop table COMMENT_REPORTS cascade constraints;
drop table USERS cascade constraints;

--=================================================

-- SEQUENCE DROPS

drop sequence SEQ_ID_FORUM_TOPICS;  
drop sequence SEQ_ID_FORUM_COMMENTS;

--=================================================

-- CREATE TABLES

PROMPT ==========================
PROMPT CREATE TABLES
PROMPT ==========================

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
				PROFILE_PIC blob, 
				DESCRIPTION varchar2(200),
				IS_TUTOR number(1) not null,
				IS_ADMIN number(1) not null) tablespace system;
				
				
create table TUTOR_REVIEWS(USER_UID varchar2(20) not null,
						TUTOR_ID varchar2(20) not null,
						SCORE float(2) not null,
						COMMENTS varchar2(100) not null) tablespace system;
						
						
create table USER_TAGS(USER_UID varchar2(20) not null,
						TAG_ID varchar2(45) not null) tablespace system;
						
						
create table FORUM_TOPICS(ID number not null,
						AUTHOR_ID varchar2(20) not null,
						TITLE varchar2(50) not null,
						DESCRIPTION varchar2(500) not null,
						CREATION_DATE date not null,
						IS_ANON number(1) not null) tablespace system;
						
						
create table FORUM_COMMENTS(ID number not null,
						FORUM_ID number not null,
						AUTHOR_ID varchar2(20) not null,
						DESCRIPTION varchar2(500) not null,
						CREATION_DATE date not null,
						IS_ANON number(1) not null) tablespace system;
						
						
create table COMMENT_REPORTS(COMMENT_ID number not null,
						USER_UID varchar2(20) not null,
						MOTIVE varchar2(500) not null,
						CREATION_DATE date not null) tablespace system;
						
						
--=================================================

-- CREATE SEQUENCES

create sequence SEQ_ID_FORUM_TOPICS start with 1 increment by 1 cache 2;

create sequence SEQ_ID_FORUM_COMMENTS start with 1 increment by 1 cache 2;

--=================================================

-- ALTER TABLES - PRIMARY KEY

alter table CAREERS add constraint CAREERS_PK primary key(ID) using index tablespace system;

alter table UNIVERSITIES add constraint UNIVERSITIES_PK primary key(ID) using index tablespace system;

alter table TAGS add constraint TAGS_PK primary key(NAME) using index tablespace system;

alter table USERS add constraint USERS_PK primary key(ID) using index tablespace system;

alter table TUTOR_REVIEWS add constraint TUTOR_REVIEWS_PK primary key(USER_UID,TUTOR_ID) using index tablespace system;

alter table USER_TAGS add constraint USER_TAGS_PK primary key(USER_UID,TAG_ID) using index tablespace system;

alter table FORUM_TOPICS add constraint FORUM_TOPICS_PK primary key(ID) using index tablespace system;

alter table FORUM_COMMENTS add constraint FORUM_COMMENTS_PK primary key(ID) using index tablespace system;

alter table COMMENT_REPORTS add constraint COMMENT_REPORTS_PK primary key(COMMENT_ID,USER_UID) using index tablespace system;


--=================================================

-- ALTER TABLES - FOREIGN KEY

alter table USERS add constraint CAREER_FK foreign key (CAREER_ID) references CAREERS;
alter table USERS add constraint UNIVERSITY_FK foreign key (UNIVERSITY_ID) references UNIVERSITIES;

alter table TUTOR_REVIEWS add constraint REVIEWS_USER_FK foreign key (USER_UID) references USERS;
alter table TUTOR_REVIEWS add constraint REVIEWS_TUTOR_FK foreign key (TUTOR_ID) references USERS;

alter table USER_TAGS add constraint USER_TAGS_USER_FK foreign key (USER_UID) references USERS;
alter table USER_TAGS add constraint USER_TAGS_TAG_FK foreign key (TAG_ID) references TAGS;

alter table FORUM_TOPICS add constraint TOPICS_AUTHOR_FK foreign key (AUTHOR_ID) references USERS;

alter table FORUM_COMMENTS add constraint COMMENTS_FORUM_FK foreign key (FORUM_ID) references FORUM_TOPICS;
alter table FORUM_COMMENTS add constraint COMMENTS_AUTHOR_FK foreign key (AUTHOR_ID) references USERS;

alter table COMMENT_REPORTS add constraint REPORTS_COMMENT_FK foreign key (COMMENT_ID) references FORUM_COMMENTS;
alter table COMMENT_REPORTS add constraint REPORTS_USER_FK foreign key (USER_UID) references USERS;


--=================================================

-- INSERTS

PROMPT ==========================
PROMPT INSERTS 
PROMPT ==========================

insert into CAREERS values ('ING-SIS', 'Ingenieria de Sistemas');
insert into CAREERS values ('ADM', 'Administracion de Empresas');
insert into CAREERS values ('MECTR', 'Mecatronica');
insert into CAREERS values ('BIO', 'Biologia');
insert into CAREERS values ('TOP', 'Topografia');

insert into UNIVERSITIES values ('UCR', 'Universidad de Costa Rica');
insert into UNIVERSITIES values ('UNA', 'Universidad Nacional de Costa Rica');
insert into UNIVERSITIES values ('TEC', 'Instituto Tecnologico de Costa Rica');
insert into UNIVERSITIES values ('UNED', 'Universidad Estatal a Distancia');

insert into TAGS values ('Python');
insert into TAGS values ('Deportes');
insert into TAGS values ('Videojuegos');
insert into TAGS values ('Fotografia');
insert into TAGS values ('Cine');
insert into TAGS values ('Teatro');
insert into TAGS values ('Tecnologia');
insert into TAGS values ('Naturaleza');
insert into TAGS values ('Futbol');
insert into TAGS values ('Basketbol');
-- Hay que meter un montón de tags despues
				
insert into USERS values ('117780905','ING-SIS','UNA','java6464@gmail','123','Javier','Amador','Delgado', TO_DATE('2000/05/30', 'yyyy/mm/dd'),null,
				'Estudiante de Ingenieria de Sistemas en la UNA. Actualmente en el ultimo semestre', 0, 1);
				
insert into USERS values ('615283905','ADM','TEC','javo64@gmail','123','Alberto','Amador','Delgado', TO_DATE('2000/05/30', 'yyyy/mm/dd'),null,
				'Deprecado', 0, 0);
				

commit;