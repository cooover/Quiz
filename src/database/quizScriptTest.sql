use quizeDB;

# UZYTKOWNICY
insert into users (pass, login) values('123','user1');
insert into users (pass, login) values('456','user2');

#PYTANIA

#KATEGORIA db
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('db','pytanie1d','odpA','odpB','odpC','odpD','A');
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('db','pytanie2d','odpA','odpB','odpC','odpD','A');

#KATEGORIA python
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('python','pytanie1p','odpA','odpB','odpC','odpD','A');
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('python','pytanie2p','odpA','odpB','odpC','odpD','A');

#KATEGORIA fe
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('fe','pytanie1f','odpA','odpB','odpC','odpD','A');
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('fe','pytanie2f','odpA','odpB','odpC','odpD','A');

#KATEGORIA java
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('java','pytanie1j','odpA','odpB','odpC','odpD','A');
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('java','pytanie2j','odpA','odpB','odpC','odpD','A');

#KATEGORIA spring
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('spring','pytanie1s','odpA','odpB','odpC','odpD','A');
insert into questions (category, question, ans_a, ans_b, ans_c, ans_d, answer) values('spring','pytanie2s','odpA','odpB','odpC','odpD','A');

#WYNIK

#ZAPYTANIA
select * from users;
select * from questions;
select * from results;

select sum(result) as licznik from results where id_user=1;
select count(*) as mianownik from results where id_user=1;
