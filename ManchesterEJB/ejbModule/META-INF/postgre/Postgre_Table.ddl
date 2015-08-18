

DROP TABLE public.access1 CASCADE;
DROP TABLE public.difficulty CASCADE;
DROP TABLE public.locale CASCADE;
DROP TABLE public.priority CASCADE;
DROP TABLE public.project CASCADE;
DROP TABLE public.task CASCADE;
DROP TABLE public.projectaccess CASCADE;
DROP TABLE public.taskstatus CASCADE;
DROP TABLE public.tasktype CASCADE;
DROP TABLE public.user1 CASCADE;
DROP TABLE public.usertype CASCADE;


CREATE TABLE public.access1 (
  id int4 NOT NULL,
  desc1 text,
  CONSTRAINT pk_access1 PRIMARY KEY (id)
) WITH OIDS;


CREATE TABLE public.difficulty (
  id int4 NOT NULL,
  desc1 text,
  CONSTRAINT pk_difficulty PRIMARY KEY (id)
) WITH OIDS;

CREATE TABLE public.locale (
  id int4 NOT NULL,
  desc1 text,
  loc text,
  CONSTRAINT pk_locale PRIMARY KEY (id)
) WITH OIDS;

CREATE TABLE public.usertype (
  id int4 NOT NULL,
  desc1 text,
  CONSTRAINT pk_usertype PRIMARY KEY (id)
) WITH OIDS;

CREATE TABLE public.priority (
  id int4 NOT NULL,
  desc1 text,
  CONSTRAINT pk_priority PRIMARY KEY (id)
) WITH OIDS;

CREATE TABLE public.project (
  id int4 NOT NULL,
  desc1 text,
  CONSTRAINT pk_project PRIMARY KEY (id)
) WITH OIDS;

CREATE TABLE public.taskstatus (
  id int4 NOT NULL,
  desc1 text,
  CONSTRAINT pk_taskstatus PRIMARY KEY (id)
) WITH OIDS;

CREATE TABLE public.tasktype (
  id int4 NOT NULL,
  desc1 text,
  CONSTRAINT pk_tasktype PRIMARY KEY (id)
) WITH OIDS;

CREATE TABLE public.user1 (
  id int4 NOT NULL,
  username text,
  password text,
  email text,
  lastlogin timestamptz,
  noflogins int4 NOT NULL,
  lastproject_id int4,
  usertype_id int4,
  locale_id int4,
  CONSTRAINT pk_user1 PRIMARY KEY (id),
  CONSTRAINT fk_user1_lastproject FOREIGN KEY (lastproject_id) REFERENCES public.project (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_user1_locale FOREIGN KEY (locale_id) REFERENCES public.locale (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_user1_usertype FOREIGN KEY (usertype_id) REFERENCES public.usertype (id) ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH OIDS;

CREATE TABLE public.projectaccess (
  projectid int4 NOT NULL,
  userid int4 NOT NULL,
  access_id int4,
  CONSTRAINT pk_projectaccess PRIMARY KEY (projectid, userid),
  CONSTRAINT fk_projectaccess_access FOREIGN KEY (access_id) REFERENCES public.access1 (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_projectaccess_project FOREIGN KEY (projectid) REFERENCES public.project (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_projectaccess_user FOREIGN KEY (userid) REFERENCES public.user1 (id) ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH OIDS;

CREATE TABLE public.task (
  id int4 NOT NULL,
  title text,
  summary text,
  planneddate date,
  createdate date,
  comment1 text,
  progress int4,
  project_id int4,
  createdby_id int4,
  tasktype_id int4,
  difficulty_id int4,
  who_id int4,
  taskstatus_id int4,
  priority_id int4,
  CONSTRAINT pk_task PRIMARY KEY (id),
  CONSTRAINT fk_task_createdby FOREIGN KEY (createdby_id) REFERENCES public.user1 (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_task_difficulty FOREIGN KEY (difficulty_id) REFERENCES public.difficulty (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_task_priority FOREIGN KEY (priority_id) REFERENCES public.priority (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES public.project (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_task_taskstatus FOREIGN KEY (taskstatus_id) REFERENCES public.taskstatus (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_task_tasktype FOREIGN KEY (tasktype_id) REFERENCES public.tasktype (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_task_who FOREIGN KEY (who_id) REFERENCES public.user1 (id) ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH OIDS;







