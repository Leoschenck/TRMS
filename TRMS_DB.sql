CREATE USER trmsuser IDENTIFIED BY p4ssw0rd
    DEFAULT TABLESPACE users
    TEMPORARY TABLESPACE temp
    QUOTA 10M ON users;

GRANT connect TO trms_db;

GRANT resource TO trms_db;

GRANT
    CREATE SESSION
TO trms_db;

GRANT
    CREATE TABLE
TO trms_db;

GRANT
    CREATE VIEW
TO trms_db;

CONN trmsuser / p4ssw0rd

CREATE TABLE "NOTIFICATION" (
    notificationid   NUMBER PRIMARY KEY,
    formid           NUMBER,
    "content"        VARCHAR2(350),
    "time"           TIMESTAMP,
    checked          NUMBER(1, 0),
    CONSTRAINT fk_formid FOREIGN KEY ( formid )
        REFERENCES "FORM" ( formid )
);

CREATE TABLE "USER" (
    userid              NUMBER PRIMARY KEY,
    firstname           VARCHAR2(30),
    lastname            VARCHAR2(30),
    phonenumber         VARCHAR2(30),
    email               VARCHAR2(50),
    rmnreinmbursement   NUMBER(10, 2),
    reportsto           NUMBER,
    username            VARCHAR2(30) UNIQUE,
    "PASSWORD"          VARCHAR2(30),
    CONSTRAINT fk_supervisor FOREIGN KEY ( reportsto )
        REFERENCES "USER" ( userid )
);

CREATE TABLE department (
    deptid     NUMBER PRIMARY KEY,
    deptname   VARCHAR2(30),
    depthead   NUMBER,
    CONSTRAINT fk_depthead FOREIGN KEY ( depthead )
        REFERENCES "USER" ( userid )
);

CREATE TABLE "FORM" (
    formid                     NUMBER PRIMARY KEY,
    opendatetime               TIMESTAMP,
    coursestart                TIMESTAMP,
    "location"                 VARCHAR2(40),
    "description"              VARCHAR2(300),
    "cost"                     NUMBER(10, 2),
    reasonforexceedingmax      VARCHAR2(300),
    gradingformat              VARCHAR2(40),
    typeofevent                VARCHAR2(50),
    workrelatedjustification   VARCHAR2(300),
    status                     NUMBER,
    deniedreason               VARCHAR2(300),
    openquestions              VARCHAR2(300),
    worktimemissed             NUMBER(10, 2),
    linktofiles                VARCHAR2(50),
    employeeid                 NUMBER,
    deptid                     NUMBER,
    CONSTRAINT fkempidforform FOREIGN KEY ( employeeid )
        REFERENCES "USER" ( userid ),
    CONSTRAINT fk_deptheadforform FOREIGN KEY ( deptid )
        REFERENCES department ( deptid )
);

CREATE TABLE benco (
    bencoid NUMBER PRIMARY KEY,
    CONSTRAINT fk_bencoid FOREIGN KEY ( bencoid )
        REFERENCES "USER" ( userid )
);

CREATE SEQUENCE userseq;

CREATE SEQUENCE departmentseq;

CREATE SEQUENCE formseq;

CREATE SEQUENCE notificationseq;

INSERT INTO "USER" VALUES (
    userseq.NEXTVAL,
    'Dean',
    'Smith',
    '312-555-5275',
    'proper_email_address@website.com',
    1000,
    userseq.CURRVAL,
    'deansmith',
    'password1'
);

INSERT INTO "USER" VALUES (
    userseq.NEXTVAL,
    'Levi',
    'Applebaum',
    '772-555-2345',
    'email_name@address.toplevel',
    1000,
    2,
    'ljapplebaum',
    'password2'
);

INSERT INTO "USER" VALUES (
    userseq.NEXTVAL,
    'Kyle',
    'Kolstad',
    '583-555-2054',
    'k_stad4@gmail.gov',
    1000,
    2,
    'kylekols',
    'password2'
);

INSERT INTO "USER" VALUES (
    userseq.NEXTVAL,
    'Leonardo',
    'Schenck',
    '392-555-5408',
    'leo_schneck@aol.gov',
    1000,
    2,
    'lschenckman',
    'password4'
);

INSERT INTO "USER" VALUES (
    userseq.NEXTVAL,
    'Justin',
    'Hua',
    '729-555-9820',
    'hua_j_32@icloud.net',
    1000,
    2,
    'justinhua32',
    'password5'
);

INSERT INTO "USER" VALUES (
    userseq.NEXTVAL,
    'Matt',
    'Knighten',
    '345-555-9205',
    'roll_tide@gmail.com',
    1000,
    5,
    'shutuplevi45',
    'password6'
);

INSERT INTO department VALUES (
    departmentseq.NEXTVAL,
    'Math',
    4
);

INSERT INTO department VALUES (
    departmentseq.NEXTVAL,
    'Computer Science',
    3
);

CREATE OR REPLACE PROCEDURE createform (
    coursest              TIMESTAMP,
    locat                 VARCHAR2,
    descript              VARCHAR2,
    costtot               NUMBER,
    gradingform           VARCHAR2,
    typeofev              VARCHAR2,
    workrelatedjustific   VARCHAR2,
    worktimemiss          NUMBER,
    linktofil             VARCHAR2,
    empid                 NUMBER,
    depid                 NUMBER
) AS
BEGIN
    INSERT INTO "FORM" VALUES (
        formseq.NEXTVAL,
        SYSDATE,
        coursest,
        locat,
        descript,
        costtot,
        NULL,
        gradingform,
        typeofev,
        workrelatedjustific,
        0,
        NULL,
        NULL,
        worktimemiss,
        linktofil,
        empid,
        depid
    );

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE change_status (
    fid       NUMBER,
    newstat   NUMBER
) AS
BEGIN
    UPDATE "FORM"
    SET
        status = newstat
    WHERE
        formid = fid;

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE create_notification (
    fid    NUMBER,
    cont   VARCHAR2
) AS
BEGIN
    INSERT INTO "NOTIFICATION" VALUES (
        notificationseq.NEXTVAL,
        fid,
        cont,
        (
            SELECT
                current_time
            FROM
                dual
        ),
        0
    );

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE hide_notification (
    nid NUMBER
) AS
BEGIN
    UPDATE "NOTIFICATION"
    SET
        checked = 1
    WHERE
        notificationid = nid;

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE setreimbursementamount (
    newamt   NUMBER,
    uid      NUMBER
) AS
BEGIN
    UPDATE "USER"
    SET
        rmnreinmbursement = newamt
    WHERE
        userid = uid;

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE resetreimbursementamount (
    newamt   NUMBER,
    uid      NUMBER
) AS
BEGIN
    UPDATE "USER"
    SET
        rmnreinmbursement = 1000;

    COMMIT;
END;
/

CREATE OR REPLACE FUNCTION current_time RETURN TIMESTAMP AS
    currtime TIMESTAMP;
BEGIN
    SELECT
        current_timestamp
    INTO currtime
    FROM
        dual;

    RETURN currtime;
END;
/