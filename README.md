# software and performance

## Create schema in Postgres through PgAdmin

1) Right click on "public" schema -> Query Tool
2) Copy-paste schema schema.sql 

## Import data in Postgres through PgAdmin

1) Right click on "flight" table -> Import/export
2) Delimiter="," , drop id, header=true, encoding=UTF-8


initdb -U postgres -D /Users/filippo/Documents/University/SPS/project/pgdata
pg_ctl -D /Users/filippo/Documents/University/SPS/project/pgdata st
art
pg_ctl -D /Users/filippo/Documents/University/SPS/project/pgdata stop