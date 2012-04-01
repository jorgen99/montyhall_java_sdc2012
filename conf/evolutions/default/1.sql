# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table game (
  id                        bigint not null,
  player_name               varchar(255),
  constraint pk_game primary key (id))
;

create sequence game_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists game;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists game_seq;

