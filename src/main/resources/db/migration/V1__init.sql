drop table if exists with_json;
drop type if exists part;

create type part as enum ('FORD', 'VW');
create table with_json
(
    id      bigserial primary key not null,
    content jsonb,
    parts   part[]
);

