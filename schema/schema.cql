drop keyspace wishlists;

create keyspace wishlists WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};

use wishlists;

create table user ( userId varchar, firstName varchar, lastName varchar, country varchar, state varchar, zip varchar, primary key (userID ) ) WITH COMPACT STORAGE;
create table wishlist ( tenant varchar, country varchar, state varchar, zip varchar, userId varchar, wishlist set<text>, primary key (tenant, country, state, zip, userId) );

insert into user (userId, firstName, lastName, country, state, zip) values ('owen.oneill', 'Owen', 'O''Neill', 'IRL', 'D', 'EI33');
insert into user (userId, firstName, lastName, country, state, zip) values ('collin.oneill', 'Collin', 'O''Neill', 'IRL', 'D', 'EI33');
insert into user (userId, firstName, lastName, country, state, zip) values ('richie.rich', 'Richie', 'Rich', 'USA', 'CA', '94333');
insert into user (userId, firstName, lastName, country, state, zip) values ('johny.b.good', 'Johny', 'Good', 'USA', 'CA', '94333');
insert into user (userId, firstName, lastName, country, state, zip) values ('bart.simpson', 'Bart', 'Simpson', 'USA', 'CA', '94111');
insert into user (userId, firstName, lastName, country, state, zip) values ('dennis.menace', 'Dennis', 'Menace', 'USA', 'CA', '94222');
insert into user (userId, firstName, lastName, country, state, zip) values ('michael.myers', 'Michael', 'Myers', 'USA', 'PA', '18964');

insert into wishlist (tenant, country, state, zip, userId) values ('store1', 'IRL', 'D', 'EI33','owen.oneill');
insert into wishlist (tenant, country, state, zip, userId) values ('store1', 'IRL', 'D', 'EI33', 'collin.oneill');
insert into wishlist (tenant, country, state, zip, userId) values ('store1', 'USA', 'CA', '94333', 'richie.rich');
insert into wishlist (tenant, country, state, zip, userId) values ('store1', 'USA', 'CA', '94333', 'johny.b.good');
insert into wishlist (tenant, country, state, zip, userId) values ('store2', 'USA', 'CA', '94111', 'bart.simpson');
insert into wishlist (tenant, country, state, zip, userId) values ('store2', 'USA', 'CA', '94222', 'dennis.menace');
insert into wishlist (tenant, country, state, zip, userId) values ('store2', 'USA', 'PA', '18964', 'michael.myers');

