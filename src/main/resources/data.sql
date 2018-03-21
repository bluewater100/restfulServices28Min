/* We did not need to create a User table because we had the user model annotated with @Entity */
insert into user values(10001, sysdate(), 'AB');
insert into user values(10002, sysdate(), 'Jill');
insert into user values(10003, sysdate(), 'Jam');
insert into post values(11001, 'My first Post', 10001);
insert into post values(11002, 'My second Post', 10001);


create table person
(
   id integer not null,
   name varchar(255) not null,
   location varchar(255),
   birth_date timestamp,
   primary key(id)
);

insert into person values(20001, 'John Smith', 'Chicago', sysdate());
insert into person values(20002, 'Alex', 'Detroit', sysdate());
insert into person values(20003, 'Jeremy', 'LA', sysdate());