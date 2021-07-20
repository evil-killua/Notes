create table APP_USER
(
    USER_ID           BIGINT,
    USER_NAME         VARCHAR(36) not null,
    ENCRYTED_PASSWORD VARCHAR(128) not null,
    ENABLED           Int not null
) ;

alter table APP_USER
    add constraint APP_USER_PK primary key (USER_ID);

alter table APP_USER
    add constraint APP_USER_UK unique (USER_NAME);

create table APP_ROLE
(
    ROLE_ID   BIGINT,
    ROLE_NAME VARCHAR(30) not null
) ;

alter table APP_ROLE
    add constraint APP_ROLE_PK primary key (ROLE_ID);

alter table APP_ROLE
    add constraint APP_ROLE_UK unique (ROLE_NAME);


create table USER_ROLE
(
    ID      BIGINT,
    USER_ID BIGINT not null,
    ROLE_ID BIGINT not null
);

alter table USER_ROLE
    add constraint USER_ROLE_PK primary key (ID);

alter table USER_ROLE
    add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);

alter table USER_ROLE
    add constraint USER_ROLE_FK1 foreign key (USER_ID)
        references APP_USER (USER_ID);

alter table USER_ROLE
    add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
        references APP_ROLE (ROLE_ID);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (2, 'user', '$2a$10$fGXE83iMB.wAzTaDvUsXRunXtBI/qhiy0kjc8x19dVmJ6hIHHoQ0i', 1);

insert into App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED)
values (1, 'admin', '$2a$10$/lIhX8M5l4KjelgOmC2XUel4VYvY5GESlZV0LlNJ0QEk9HffT9E7u', 1);



insert into app_role (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');

insert into app_role (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_USER');

insert into user_role (ID, USER_ID, ROLE_ID)
values (1, 1, 1);



insert into user_role (ID, USER_ID, ROLE_ID)
values (3, 2, 2);


create table APP_NOTE
(
    NOTE_ID   serial,-- BIGINT,
    NOTE_NAME VARCHAR(36) not null,
    NOTE_TEXT VARCHAR(200) not null,
    DATE_OF_CREATION timestamp not null,
    LAST_CHANGE timestamp not null
) ;

alter table APP_NOTE
    add constraint APP_NOTE_PK primary key (NOTE_ID);

alter table APP_NOTE
    add constraint APP_NOTE_UK unique (NOTE_NAME);

create table USER_NOTE
(
    ID      serial,-- BIGINT,
    USER_ID BIGINT not null,
    NOTE_ID BIGINT not null
);

alter table USER_NOTE
    add constraint USER_NOTE_PK primary key (ID);

alter table USER_NOTE
    add constraint USER_NOTE_UK unique (USER_ID, NOTE_ID);

alter table USER_NOTE
    add constraint USER_NOTE_FK1 foreign key (USER_ID)
        references APP_USER (USER_ID);

alter table USER_NOTE
    add constraint USER_NOTE_FK2 foreign key (NOTE_ID)
        references APP_NOTE (NOTE_ID);


Commit;