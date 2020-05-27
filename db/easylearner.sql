--------------------------------------------------------
--  File created - Sunday-May-24-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence LESSON_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EN"."LESSON_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 56 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence RATING_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EN"."RATING_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 12 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Sequence USERID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "EN"."USERID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 29 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
--------------------------------------------------------
--  DDL for Table LEARNERS
--------------------------------------------------------

  CREATE TABLE "EN"."LEARNERS" 
   (	"USERID" NUMBER(*,0), 
	"NAME" VARCHAR2(50 BYTE), 
	"IDCARDNUMBER" VARCHAR2(50 BYTE), 
	"PROFILEIMAGE" VARCHAR2(20 BYTE), 
	"USERNAME" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table LECTURE
--------------------------------------------------------

  CREATE TABLE "EN"."LECTURE" 
   (	"ID" NUMBER, 
	"TEACHERID" NUMBER, 
	"STUDENTID" NUMBER, 
	"INFO" NVARCHAR2(250), 
	"STARTTIME" DATE, 
	"ENDTIME" DATE, 
	"LONGITUDE" VARCHAR2(20 BYTE), 
	"LATITUDE" VARCHAR2(20 BYTE), 
	"CAPACITY" NUMBER, 
	"TOPICID" NUMBER, 
	"MIMIMUMPAYMENT" NUMBER, 
	"LEVELID" NUMBER, 
	"PAYMENTPERSTUDENT" NUMBER
   ) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table LESSON
--------------------------------------------------------

  CREATE TABLE "EN"."LESSON" 
   (	"ID" NUMBER, 
	"TEACHERID" NUMBER, 
	"STUDENTID" NUMBER, 
	"INFO" VARCHAR2(250 BYTE), 
	"STARTTIME" NUMBER, 
	"ENDTIME" NUMBER, 
	"LONGITUDE" VARCHAR2(20 BYTE), 
	"LATITUDE" VARCHAR2(20 BYTE), 
	"PAYMENT" NUMBER, 
	"LEVELID" NUMBER, 
	"TOPICID" NUMBER, 
	"OWNERID" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table LEVELS
--------------------------------------------------------

  CREATE TABLE "EN"."LEVELS" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table QUALIFICATION
--------------------------------------------------------

  CREATE TABLE "EN"."QUALIFICATION" 
   (	"ID" NUMBER, 
	"TEACHERID" NUMBER, 
	"TOPICID" NUMBER, 
	"LEVELID" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table RATING
--------------------------------------------------------

  CREATE TABLE "EN"."RATING" 
   (	"ID" NUMBER, 
	"TEACHERID" NUMBER, 
	"TOPICID" NUMBER, 
	"PUNCTUALITY" NUMBER, 
	"COMMUNICATION" NUMBER, 
	"KNOWLEDGE" NUMBER, 
	"TEXT" VARCHAR2(250 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TOPICS
--------------------------------------------------------

  CREATE TABLE "EN"."TOPICS" 
   (	"ID" NUMBER, 
	"NAME" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into EN.LEARNERS
SET DEFINE OFF;
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (22,'Meno Pala','1234567','246169997','amVsc3pv',null);
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (23,'fullname','idcard','1331805594','cGFzc3dvcmQ=',null);
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (24,'fullname','idcard','profim','usernm','paswd');
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (25,'Proba Tanar','id12345','-1531934242','probatanar','cHJvYmFqZWxzem8=');
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (28,'asd','123','96882','asd','YXNk');
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (1,'Hajas','1234567','asd','hajas','cHJvYmE=');
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (2,'Nev','4567','asd','nev','cHJvYmE=');
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (26,'Proba User','123','1336102911','proba','cHJvYmE=');
Insert into EN.LEARNERS (USERID,NAME,IDCARDNUMBER,PROFILEIMAGE,USERNAME,PASSWORD) values (27,'Test','123','2603186','test','dGVzdA==');
REM INSERTING into EN.LECTURE
SET DEFINE OFF;
REM INSERTING into EN.LESSON
SET DEFINE OFF;
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (50,0,1,'idoproba',61547647380000,null,null,null,500,1,1,1);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (51,2,22,'asd',61547647560000,null,null,null,500,1,1,22);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (7,0,1,'booked proba',61547021040000,null,null,null,500,1,1,1);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (53,22,1,'idporba',61547648940000,null,null,null,500,1,1,22);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (45,1,0,'created as teacher',61546852080000,null,null,null,100,1,1,1);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (46,null,1,'created as student',61546852860000,null,null,null,100,1,1,1);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (35,23,22,null,61546371480000,null,null,null,500,1,1,22);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (38,1,null,null,61638816300000,null,null,null,500,1,1,1);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (52,1,22,'idoproba',61547648880000,null,null,null,500,1,1,22);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (6,0,2,'created by student',61638816300000,null,null,null,1000,1,3,null);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (1,25,2,'asd',null,null,'12','23',45,2,3,null);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (3,22,null,'Hazi feladatok',null,null,null,null,500,1,1,null);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (36,1,null,null,61546817460000,null,null,null,500,1,1,1);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (37,1,null,null,61598283780000,null,null,null,500,1,1,1);
Insert into EN.LESSON (ID,TEACHERID,STUDENTID,INFO,STARTTIME,ENDTIME,LONGITUDE,LATITUDE,PAYMENT,LEVELID,TOPICID,OWNERID) values (49,null,1,'owner proba',61547021040000,null,null,null,500,1,1,1);
REM INSERTING into EN.LEVELS
SET DEFINE OFF;
Insert into EN.LEVELS (ID,NAME) values (1,'ELEMENTARY');
Insert into EN.LEVELS (ID,NAME) values (2,'HIGH SCHOOL');
Insert into EN.LEVELS (ID,NAME) values (3,'GRADUATED');
REM INSERTING into EN.QUALIFICATION
SET DEFINE OFF;
Insert into EN.QUALIFICATION (ID,TEACHERID,TOPICID,LEVELID) values (1,1,3,3);
REM INSERTING into EN.RATING
SET DEFINE OFF;
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (6,2,1,3,3,3,null);
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (9,1,1,3,3,3,null);
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (10,1,1,3,3,3,null);
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (8,1,1,3,3,3,null);
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (7,1,1,3,3,3,null);
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (11,1,1,5,3,5,null);
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (1,1,1,4,5,3,'eleg jo volt');
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (2,2,2,3,3,3,'kozepes ora volt');
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (3,1,3,5,5,5,'nagyon hosszu leiras proba aasdasdasdasdasdasdasdasdasdasdasdasdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (4,1,1,3,4,5,'proba');
Insert into EN.RATING (ID,TEACHERID,TOPICID,PUNCTUALITY,COMMUNICATION,KNOWLEDGE,TEXT) values (5,1,2,3,3,3,'proba ertekeles');
REM INSERTING into EN.TOPICS
SET DEFINE OFF;
Insert into EN.TOPICS (ID,NAME) values (1,'MATH');
Insert into EN.TOPICS (ID,NAME) values (2,'PHYSICS');
Insert into EN.TOPICS (ID,NAME) values (3,'CHEMISTRY');
Insert into EN.TOPICS (ID,NAME) values (4,'IT');
Insert into EN.TOPICS (ID,NAME) values (5,'LITERATURE');
Insert into EN.TOPICS (ID,NAME) values (6,'BIOLOGY');
--------------------------------------------------------
--  DDL for Index LEARNERS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EN"."LEARNERS_PK" ON "EN"."LEARNERS" ("USERID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index LESSON_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EN"."LESSON_PK" ON "EN"."LESSON" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index LEVELS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EN"."LEVELS_PK" ON "EN"."LEVELS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index QUALIFICATION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EN"."QUALIFICATION_PK" ON "EN"."QUALIFICATION" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index RATING_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EN"."RATING_PK" ON "EN"."RATING" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index TABLE1_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EN"."TABLE1_PK" ON "EN"."LECTURE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index TOPICS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "EN"."TOPICS_PK" ON "EN"."TOPICS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Procedure ADDLEARNER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "EN"."ADDLEARNER" (fullName in VARCHAR2, idCardNumber in VARCHAR2 DEFAULT '-', profImage in VARCHAR2 DEFAULT '-', userName in VARCHAR2, password in VARCHAR2)
AS
BEGIN
INSERT INTO LEARNERS(USERID, NAME, idcardnumber, profileimage, username, password) VALUES (USERID_SEQ.nextval,fullName, idCardNumber, profImage, userName, password);
END AddLearner;

/
--------------------------------------------------------
--  DDL for Procedure ADDLESSONASSTUDENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "EN"."ADDLESSONASSTUDENT" (IdOfStudent in NUMBER, LessonInfo in VARCHAR2 DEFAULT '-',LessonStartTime in NUMBER DEFAULT 0, PaymentValue in NUMBER DEFAULT 0, IdOfLevel in NUMBER DEFAULT 1, IdOfTopic in NUMBER DEFAULT 1)
AS
BEGIN
INSERT INTO LESSON(ID, STUDENTID, INFO,STARTTIME, PAYMENT, LEVELID, TOPICID, OWNERID) VALUES (LESSON_SEQ.nextval,IdOfStudent, LessonInfo,LessonStartTime, PaymentValue, IdOfLevel, IdOfTopic, IdOfStudent);
END AddLessonAsStudent;

/
--------------------------------------------------------
--  DDL for Procedure ADDLESSONASTEACHER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "EN"."ADDLESSONASTEACHER" (IdOfTeacher in NUMBER, LessonInfo in VARCHAR2 DEFAULT '-',LessonStartTime in NUMBER DEFAULT 0, PaymentValue in NUMBER DEFAULT 0, IdOfLevel in NUMBER DEFAULT 1, IdOfTopic in NUMBER DEFAULT 1)
AS
BEGIN
INSERT INTO LESSON(ID, TEACHERID, INFO,STARTTIME, PAYMENT, LEVELID, TOPICID, OWNERID) VALUES (LESSON_SEQ.nextval,IdOfTeacher, LessonInfo,LessonStartTime, PaymentValue, IdOfLevel, IdOfTopic, IdOfTeacher);
END AddLessonAsTeacher;

/
--------------------------------------------------------
--  DDL for Procedure ADDRATING
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "EN"."ADDRATING" (IdOfTeacher in NUMBER, IdOfTopic in NUMBER, punc in NUMBER, comm in NUMBER, know in NUMBER, rateText in VARCHAR2)
AS
BEGIN
INSERT INTO RATING(ID, TEACHERID, TOPICID, PUNCTUALITY, COMMUNICATION, KNOWLEDGE, TEXT) VALUES (RATING_SEQ.nextval, IdOfTeacher, IdOfTopic, punc, comm, know, rateText);
END AddRating;

/
--------------------------------------------------------
--  DDL for Procedure BOOKLESSONASSTUDENT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "EN"."BOOKLESSONASSTUDENT" (LessonId in NUMBER, NewId in NUMBER)
AS
BEGIN
UPDATE Lesson SET StudentID = NewId WHERE ID = LessonId; 
END BookLessonAsStudent;

/
--------------------------------------------------------
--  DDL for Procedure BOOKLESSONASTEACHER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "EN"."BOOKLESSONASTEACHER" (LessonId in NUMBER, NewId in NUMBER)
AS
BEGIN
UPDATE Lesson SET TeacherID = NewId WHERE ID = LessonId; 
END BookLessonAsTeacher;

/
--------------------------------------------------------
--  DDL for Procedure GETLEVELBYID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "EN"."GETLEVELBYID" (LevelId in NUMBER)
IS
c1 SYS_REFCURSOR;
BEGIN
open c1 For
SELECT Levels.Name FROM Levels WHERE Levels.ID = LevelId;
DBMS_SQL.RETURN_RESULT(c1);
END GetLevelById;

/
--------------------------------------------------------
--  Constraints for Table TOPICS
--------------------------------------------------------

  ALTER TABLE "EN"."TOPICS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "EN"."TOPICS" ADD CONSTRAINT "TOPICS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table LEARNERS
--------------------------------------------------------

  ALTER TABLE "EN"."LEARNERS" MODIFY ("USERID" NOT NULL ENABLE);
  ALTER TABLE "EN"."LEARNERS" ADD CONSTRAINT "LEARNERS_PK" PRIMARY KEY ("USERID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table LECTURE
--------------------------------------------------------

  ALTER TABLE "EN"."LECTURE" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "EN"."LECTURE" ADD CONSTRAINT "TABLE1_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table QUALIFICATION
--------------------------------------------------------

  ALTER TABLE "EN"."QUALIFICATION" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "EN"."QUALIFICATION" ADD CONSTRAINT "QUALIFICATION_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table RATING
--------------------------------------------------------

  ALTER TABLE "EN"."RATING" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "EN"."RATING" ADD CONSTRAINT "RATING_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table LESSON
--------------------------------------------------------

  ALTER TABLE "EN"."LESSON" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "EN"."LESSON" ADD CONSTRAINT "LESSON_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table LEVELS
--------------------------------------------------------

  ALTER TABLE "EN"."LEVELS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "EN"."LEVELS" ADD CONSTRAINT "LEVELS_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
