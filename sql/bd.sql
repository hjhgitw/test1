/* Create the clients table */
create database IF NOT EXISTS mailsystem;

use mailsystem;

/* Create the clients table */
Create Table users(
	id SERIAL PRIMARY KEY,
	name varchar(128) NOT NULL,
	login varchar(128) NOT NULL
)