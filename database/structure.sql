NoSQL
	likes(email, idsong, recommend)
	comments(idcomment, idsong, email, commentbody, commentdate)
	genres(idgenre, title)
	artists(idartist, name)
	albums(idalbum, title)
	songs(idsong, idalbum, idgenre, idartist)

HIVE
	profiles(email, name, surname, birthday, gender, pass)
	predictions(email, idsong, probability)
	groups(idgroup, title)
    ptog(email, idgroup)
	external tables

HDFS
	songsdesc(idsong, namefile, nameartist, title, year)

SQL
	external tables

-- HDFS
hdfs dfs -mkdir /music
hdfs dfs -put songsdesc.csv /music
hdfs dfs -ls /music

-- NoSQL
java -Xmx256m -Xms256m -jar $KVHOME/lib/kvstore.jar kvlite
java -jar $KVHOME/lib/kvstore.jar runadmin -port 5000 -host bigdatalite.localdomain

connect store -name kvstore
execute 'create table likes(email string, idsong string, recommend integer, primary key(shard(email), idsong))'
execute 'create table comments(idcomment integer, idsong string, email string, commentbody string, commentdate string, primary key(idcomment))'
execute 'create table genres(idgenre integer, title string, primary key(idgenre))'
execute 'create table artists(idartist integer, name string, primary key(idartist))'
execute 'create table albums(idalbum integer, title string, primary key(idalbum))'
execute 'create table songs(idsong string, idalbum integer, idgenre integer, idartist integer, primary key(idsong))'
execute 'create index songsartists on songs(idartist)'
execute 'create index songsgenres on songs(idgenre)'
execute 'create index likessongs on likes(idsong)'

put table -name likes -json '{"email":"n.chernyshov@gmail.com", "idsong":"EYS7NQDOS3", "recommend":1}'
put table -name likes -json '{"email":"n.chernyshov@gmail.com", "idsong":"CFZZ08IAT6", "recommend":0}'
put table -name likes -json '{"email":"n.chernyshov@gmail.com", "idsong":"AIB4YK25MI", "recommend":1}'
put table -name likes -json '{"email":"n.chernyshov@gmail.com", "idsong":"YBINS73DXR", "recommend":1}'
put table -name likes -json '{"email":"n.chernyshov@gmail.com", "idsong":"ZUTZM4Z7EC", "recommend":0}'
put table -name likes -json '{"email":"n.chernyshov@gmail.com", "idsong":"98ZP7Q5E7A", "recommend":0}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"EYS7NQDOS3", "recommend":1}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"LP5JZFLEY0", "recommend":1}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"6TEYMI82DZ", "recommend":1}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"YBINS73DXR", "recommend":1}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"ZUTZM4Z7EC", "recommend":0}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"98ZP7Q5E7A", "recommend":0}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"3U9AG1MQB9", "recommend":0}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"X1RW4WSK2K", "recommend":1}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"0A9AAI0SDE", "recommend":1}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"4B7MAZ5YNB", "recommend":1}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"COYPC9WEYE", "recommend":1}'
put table -name likes -json '{"email":"a.bardin@gmail.com", "idsong":"J7KANYSBRF", "recommend":1}'
put table -name likes -json '{"email":"i.ivanov@gmail.com", "idsong":"EYS7NQDOS3", "recommend":0}'
put table -name likes -json '{"email":"i.ivanov@gmail.com", "idsong":"LP5JZFLEY0", "recommend":1}'
put table -name likes -json '{"email":"i.ivanov@gmail.com", "idsong":"23KIR7XI5W", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"QNKEJ6MGF1", "recommend":0}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"RW2N0P2IOR", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"23KIR7XI5W", "recommend":0}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"10YBBMBZ3I", "recommend":0}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"C9P0R1Y3C7", "recommend":0}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"CJ615FEQCA", "recommend":0}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"T5WHT3IDD5", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"SL1DUFVHFP", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"EYS7NQDOS3", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"L9EP65P6XW", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"K0YTJY260D", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"6JACJ1RDXF", "recommend":0}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"2L30CSF091", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"QNST2XQ8AW", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"3V66999M5O", "recommend":0}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"Z8HDY0FVD2", "recommend":0}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"1GERKP5JKK", "recommend":1}'
put table -name likes -json '{"email":"n.petrova@gmail.com", "idsong":"M41IJWTVNH", "recommend":1}'
put table -name likes -json '{"email":"v.kulikova@gmail.com", "idsong":"QNST2XQ8AW", "recommend":0}'
put table -name likes -json '{"email":"v.kulikova@gmail.com", "idsong":"3V66999M5O", "recommend":1}'
put table -name likes -json '{"email":"v.kulikova@gmail.com", "idsong":"Z8HDY0FVD2", "recommend":0}'
put table -name likes -json '{"email":"v.kulikova@gmail.com", "idsong":"1GERKP5JKK", "recommend":1}'
put table -name likes -json '{"email":"v.kulikova@gmail.com", "idsong":"M41IJWTVNH", "recommend":1}'
put table -name likes -json '{"email":"n.krivosheev@gmail.com", "idsong":"EYS7NQDOS3", "recommend":0}'
put table -name likes -json '{"email":"n.krivosheev@gmail.com", "idsong":"3V66999M5O", "recommend":0}'
put table -name likes -json '{"email":"n.krivosheev@gmail.com", "idsong":"Z8HDY0FVD2", "recommend":0}'
put table -name likes -json '{"email":"n.krivosheev@gmail.com", "idsong":"1GERKP5JKK", "recommend":0}'
put table -name likes -json '{"email":"n.krivosheev@gmail.com", "idsong":"LP5JZFLEY0", "recommend":0}'
put table -name likes -json '{"email":"v.baranova@gmail.com", "idsong":"EYS7NQDOS3", "recommend":0}'
put table -name likes -json '{"email":"v.baranova@gmail.com", "idsong":"LP5JZFLEY0", "recommend":0}'
put table -name likes -json '{"email":"k.zaharova@gmail.com", "idsong":"EYS7NQDOS3", "recommend":1}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"EYS7NQDOS3", "recommend":1}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"L9EP65P6XW", "recommend":0}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"K0YTJY260D", "recommend":1}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"6JACJ1RDXF", "recommend":1}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"2L30CSF091", "recommend":1}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"QNST2XQ8AW", "recommend":0}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"3V66999M5O", "recommend":1}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"Z8HDY0FVD2", "recommend":1}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"1GERKP5JKK", "recommend":1}'
put table -name likes -json '{"email":"i.ivanova@gmail.com", "idsong":"M41IJWTVNH", "recommend":0}'
put table -name likes -json '{"email":"n.gavrilov@gmail.com", "idsong":"EYS7NQDOS3", "recommend":0}'
put table -name likes -json '{"email":"n.gavrilov@gmail.com", "idsong":"L9EP65P6XW", "recommend":1}'
put table -name likes -json '{"email":"n.gavrilov@gmail.com", "idsong":"K0YTJY260D", "recommend":0}'
put table -name likes -json '{"email":"n.gavrilov@gmail.com", "idsong":"6JACJ1RDXF", "recommend":1}'
put table -name likes -json '{"email":"n.gavrilov@gmail.com", "idsong":"2L30CSF091", "recommend":1}'
put table -name likes -json '{"email":"d.kulikov@gmail.com", "idsong":"EYS7NQDOS3", "recommend":1}'
-- Vision Fiberoptics ft Sean Haefeli
put table -name likes -json '{"email":"user1", "idsong":"CJ615FEQCA", "recommend":0}'
put table -name likes -json '{"email":"user2", "idsong":"CJ615FEQCA", "recommend":0}'
put table -name likes -json '{"email":"user3", "idsong":"CJ615FEQCA", "recommend":0}'
put table -name likes -json '{"email":"user4", "idsong":"CJ615FEQCA", "recommend":0}'
put table -name likes -json '{"email":"user5", "idsong":"CJ615FEQCA", "recommend":0}'
put table -name likes -json '{"email":"user6", "idsong":"CJ615FEQCA", "recommend":0}'
put table -name likes -json '{"email":"user7", "idsong":"CJ615FEQCA", "recommend":0}'
put table -name likes -json '{"email":"user8", "idsong":"CJ615FEQCA", "recommend":0}'
-- Rise and Release ft Raashan Ahmad
put table -name likes -json '{"email":"user1", "idsong":"T5WHT3IDD5", "recommend":0}'
put table -name likes -json '{"email":"user2", "idsong":"T5WHT3IDD5", "recommend":0}'
put table -name likes -json '{"email":"user3", "idsong":"T5WHT3IDD5", "recommend":0}'
put table -name likes -json '{"email":"user4", "idsong":"T5WHT3IDD5", "recommend":0}'
put table -name likes -json '{"email":"user5", "idsong":"T5WHT3IDD5", "recommend":0}'
put table -name likes -json '{"email":"user6", "idsong":"T5WHT3IDD5", "recommend":0}'
put table -name likes -json '{"email":"user7", "idsong":"T5WHT3IDD5", "recommend":0}'
put table -name likes -json '{"email":"test@test.com", "idsong":"T5WHT3IDD5", "recommend":0}'
-- Koyelia ft Peia
put table -name likes -json '{"email":"user1", "idsong":"SL1DUFVHFP", "recommend":0}'
put table -name likes -json '{"email":"user2", "idsong":"SL1DUFVHFP", "recommend":0}'
-- The Juiceman Cometh ft Saqi
put table -name likes -json '{"email":"user1", "idsong":"EYS7NQDOS3", "recommend":0}'
put table -name likes -json '{"email":"user2", "idsong":"EYS7NQDOS3", "recommend":0}'
put table -name likes -json '{"email":"user3", "idsong":"EYS7NQDOS3", "recommend":0}'
put table -name likes -json '{"email":"user4", "idsong":"EYS7NQDOS3", "recommend":0}'
-- others
put table -name likes -json '{"email":"user1", "idsong":"L9EP65P6XW", "recommend":0}'
put table -name likes -json '{"email":"user1", "idsong":"K0YTJY260D", "recommend":0}'
put table -name likes -json '{"email":"user1", "idsong":"6JACJ1RDXF", "recommend":0}'
put table -name likes -json '{"email":"user1", "idsong":"2L30CSF091", "recommend":0}'
put table -name likes -json '{"email":"user1", "idsong":"J7KANYSBRF", "recommend":0}'

put table -name comments -json '{"idcomment":1," idsong":"EYS7NQDOS3", "email":"n.chernyshov@gmail.com", "commentbody":"Very good!", "commentdate":"18/03/2016"}'

put table -name genres -json '{"idgenre":1, "title":"RnB"}'
put table -name genres -json '{"idgenre":2, "title":"Rock"}'
put table -name genres -json '{"idgenre":3, "title":"Rap"}'
put table -name genres -json '{"idgenre":4, "title":"Hip-Hop"}'
put table -name genres -json '{"idgenre":5, "title":"Classic"}'
put table -name genres -json '{"idgenre":6, "title":"Jazz"}'
put table -name genres -json '{"idgenre":7, "title":"Pop"}'
put table -name genres -json '{"idgenre":8, "title":"Electonic music"}'

put table -name artists -json '{"idartist":1, "name":"Aint No Love"}'
put table -name artists -json '{"idartist":2, "name":"Black Joe Lewis the Honeybears"}'
put table -name artists -json '{"idartist":3, "name":"Black Stax"}'
put table -name artists -json '{"idartist":4, "name":"Bogdan"}'
put table -name artists -json '{"idartist":5, "name":"Bonde Do Role"}'
put table -name artists -json '{"idartist":6, "name":"Charles Irvin"}'
put table -name artists -json '{"idartist":7, "name":"DJ Danger Mouse"}'
put table -name artists -json '{"idartist":8, "name":"Dylan Hekimian"}'
put table -name artists -json '{"idartist":9, "name":"Ergo Phizmiz"}'
put table -name artists -json '{"idartist":10, "name":"Jim Keller"}'
put table -name artists -json '{"idartist":11, "name":"Lazlo Supreme"}'
put table -name artists -json '{"idartist":12, "name":"MaiLiT"}'
put table -name artists -json '{"idartist":13, "name":"Mystic Morrison Visions"}'
put table -name artists -json '{"idartist":14, "name":"Reigning Sound"}'
put table -name artists -json '{"idartist":15, "name":"Satellite"}'
put table -name artists -json '{"idartist":16, "name":"Sunbyrn"}'
put table -name artists -json '{"idartist":17, "name":"Tha Silent Partner"}'
put table -name artists -json '{"idartist":18, "name":"The Polish Ambassador"}'
put table -name artists -json '{"idartist":19, "name":"Toussaint Morrison"}'
put table -name artists -json '{"idartist":20, "name":"Tracky Birthday"}'
put table -name artists -json '{"idartist":21, "name":"Zap Mama"}'
put table -name artists -json '{"idartist":22, "name":"ZoeLeelA"}'

put table -name albums -json '{"idalbum":1, "title":"Gone Already - Single"}'
put table -name albums -json '{"idalbum":2, "title":"Cash"}'
put table -name albums -json '{"idalbum":3, "title":"Silent Album"}'
put table -name albums -json '{"idalbum":4, "title":"Electroalbum"}'
put table -name albums -json '{"idalbum":5, "title":"Different music"}'

put table -name songs -json '{"idsong":"70GKB82HGS", "idalbum":1, "idgenre":1, "idartist":1}'
put table -name songs -json '{"idsong":"SMI92RTCYZ", "idalbum":2, "idgenre":1, "idartist":1}'
put table -name songs -json '{"idsong":"9ZU1SN4IVF", "idalbum":2, "idgenre":1, "idartist":1}'
put table -name songs -json '{"idsong":"6Q7LQBA3KM", "idalbum":5, "idgenre":7, "idartist":2}'
put table -name songs -json '{"idsong":"YBINS73DXR", "idalbum":5, "idgenre":7, "idartist":3}'
put table -name songs -json '{"idsong":"CFZZ08IAT6", "idalbum":5, "idgenre":7, "idartist":4}'
put table -name songs -json '{"idsong":"AIB4YK25MI", "idalbum":5, "idgenre":7, "idartist":5}'
put table -name songs -json '{"idsong":"EFJIO4FUOH", "idalbum":5, "idgenre":7, "idartist":6}'
put table -name songs -json '{"idsong":"Y6Z7YE1UKZ", "idalbum":5, "idgenre":7, "idartist":7}'
put table -name songs -json '{"idsong":"UN02PGXK92", "idalbum":5, "idgenre":7, "idartist":8}'
put table -name songs -json '{"idsong":"0JGRPKA2TD", "idalbum":5, "idgenre":7, "idartist":9}'
put table -name songs -json '{"idsong":"WIZA5ICR5L", "idalbum":5, "idgenre":7, "idartist":10}'
put table -name songs -json '{"idsong":"OB3JM8IAGS", "idalbum":5, "idgenre":7, "idartist":10}'
put table -name songs -json '{"idsong":"ZUTZM4Z7EC", "idalbum":5, "idgenre":7, "idartist":10}'
put table -name songs -json '{"idsong":"98ZP7Q5E7A", "idalbum":5, "idgenre":7, "idartist":10}'
put table -name songs -json '{"idsong":"6HJHWMQQAM", "idalbum":5, "idgenre":7, "idartist":11}'
put table -name songs -json '{"idsong":"LP5JZFLEY0", "idalbum":5, "idgenre":7, "idartist":11}'
put table -name songs -json '{"idsong":"YO8QXY2HBQ", "idalbum":5, "idgenre":7, "idartist":11}'
put table -name songs -json '{"idsong":"FN15JOCHFH", "idalbum":5, "idgenre":7, "idartist":11}'
put table -name songs -json '{"idsong":"6FII0DOACP", "idalbum":5, "idgenre":7, "idartist":11}'
put table -name songs -json '{"idsong":"6TEYMI82DZ", "idalbum":5, "idgenre":7, "idartist":12}'
put table -name songs -json '{"idsong":"NYPSW25HOI", "idalbum":5, "idgenre":7, "idartist":13}'
put table -name songs -json '{"idsong":"GPKN6HDBJR", "idalbum":5, "idgenre":7, "idartist":14}'
put table -name songs -json '{"idsong":"3U9AG1MQB9", "idalbum":5, "idgenre":7, "idartist":14}'
put table -name songs -json '{"idsong":"X1RW4WSK2K", "idalbum":5, "idgenre":7, "idartist":15}'
put table -name songs -json '{"idsong":"0A9AAI0SDE", "idalbum":5, "idgenre":7, "idartist":16}'
put table -name songs -json '{"idsong":"4B7MAZ5YNB", "idalbum":5, "idgenre":7, "idartist":16}'
put table -name songs -json '{"idsong":"COYPC9WEYE", "idalbum":3, "idgenre":1, "idartist":17}'
put table -name songs -json '{"idsong":"J7KANYSBRF", "idalbum":3, "idgenre":1, "idartist":17}'
put table -name songs -json '{"idsong":"23KIR7XI5W", "idalbum":3, "idgenre":1, "idartist":17}'
put table -name songs -json '{"idsong":"QNKEJ6MGF1", "idalbum":3, "idgenre":1, "idartist":17}'
put table -name songs -json '{"idsong":"RW2N0P2IOR", "idalbum":3, "idgenre":1, "idartist":17}'
put table -name songs -json '{"idsong":"10YBBMBZ3I", "idalbum":3, "idgenre":1, "idartist":17}'
put table -name songs -json '{"idsong":"C9P0R1Y3C7", "idalbum":3, "idgenre":1, "idartist":17}'
put table -name songs -json '{"idsong":"CJ615FEQCA", "idalbum":4, "idgenre":8, "idartist":18}'
put table -name songs -json '{"idsong":"T5WHT3IDD5", "idalbum":4, "idgenre":8, "idartist":18}'
put table -name songs -json '{"idsong":"SL1DUFVHFP", "idalbum":4, "idgenre":8, "idartist":18}'
put table -name songs -json '{"idsong":"EYS7NQDOS3", "idalbum":4, "idgenre":8, "idartist":18}'
put table -name songs -json '{"idsong":"L9EP65P6XW", "idalbum":4, "idgenre":8, "idartist":18}'
put table -name songs -json '{"idsong":"K0YTJY260D", "idalbum":4, "idgenre":8, "idartist":18}'
put table -name songs -json '{"idsong":"6JACJ1RDXF", "idalbum":4, "idgenre":8, "idartist":18}'
put table -name songs -json '{"idsong":"2L30CSF091", "idalbum":4, "idgenre":8, "idartist":18}'
put table -name songs -json '{"idsong":"QNST2XQ8AW", "idalbum":5, "idgenre":7, "idartist":19}'
put table -name songs -json '{"idsong":"3V66999M5O", "idalbum":5, "idgenre":7, "idartist":19}'
put table -name songs -json '{"idsong":"Z8HDY0FVD2", "idalbum":5, "idgenre":7, "idartist":19}'
put table -name songs -json '{"idsong":"1GERKP5JKK", "idalbum":5, "idgenre":7, "idartist":19}'
put table -name songs -json '{"idsong":"M41IJWTVNH", "idalbum":5, "idgenre":7, "idartist":20}'
put table -name songs -json '{"idsong":"ZWA4UEFTJM", "idalbum":5, "idgenre":7, "idartist":21}'
put table -name songs -json '{"idsong":"XSGNKVY39Y", "idalbum":5, "idgenre":7, "idartist":22}'

-- HIVE
beeline

!connect jdbc:hive2://localhost:10000
oracle
welcome1
create table profiles(email string, name string, surname string, birthday string, gender int, pass string);
create table predictions(email string, idsong string, probability double);
insert into profiles values('n.chernyshov@gmail.com', 'Nitita', 'Chernyshov', '01/04/1999', 1, 'qwerty');
insert into profiles values('a.bardin@gmail.com', 'Alexander', 'Bardin', '02/04/2000', 1, 'user');
insert into profiles values('admin', 'admin', 'admin', '01/01/1970', 1, 'admin');
insert into profiles values('i.ivanov@gmail.com', 'Ivan', 'Ivanov', '21/04/1999', 1, 'qwerty');
insert into profiles values('n.petrova@gmail.com', 'Nadejda', 'Petrova', '12/07/1993', 2, 'user');
insert into profiles values('v.kulikova@gmail.com', 'Viktoria', 'Kulikova', '01/05/1985', 2, 'admin');
insert into profiles values('n.krivosheev@gmail.com', 'Nikolay', 'Krivosheev', '01/04/1991', 1, 'qwerty');
insert into profiles values('v.baranova@gmail.com', 'Vera', 'Baranova', '17/10/2000', 2, 'user');
insert into profiles values('k.zaharova@gmail.com', 'Kristina', 'Zaharova', '06/11/1996', 2, 'admin');
insert into profiles values('i.ivanova@gmail.com', 'Irina', 'Ivanova', '21/04/1997', 2, 'qwerty');
insert into profiles values('n.gavrilov@gmail.com', 'Nikita', 'Gavrilov', '12/07/1985', 1, 'user');
insert into profiles values('d.kulikov@gmail.com', 'Dmitriy', 'Kulikov', '31/05/1988', 1, 'admin');
insert into profiles values('v.kirov@gmail.com', 'Viktoria', 'Kirov', '01/05/1985', 2, 'admin');
insert into profiles values('n.shar@gmail.com', 'Nikolay', 'Shar', '01/04/1991', 1, 'qwerty');
insert into profiles values('v.krivih@gmail.com', 'Vera', 'Krivih', '17/10/2000', 2, 'user');
insert into profiles values('k.zaripov@gmail.com', 'Kristina', 'Zaripov', '06/11/1996', 2, 'admin');
insert into profiles values('e.ivanova@gmail.com', 'Elena', 'Ivanova', '21/04/1997', 2, 'qwerty');
insert into profiles values('v.gavrilov@gmail.com', 'Viktor', 'Gavrilov', '12/07/1985', 1, 'user');
insert into profiles values('o.kulikov@gmail.com', 'Oleg', 'Kulikov', '31/05/1988', 1, 'admin');
insert into profiles values('test@test.com', 'Test', 'Test', '14/05/1993', 1, 'test');

create table groups(idgroup int, title string);
create table ptog(email string, idgroup string);

create external table likes_nosql (
email string,
idsong string,
recommend int)
stored by 'oracle.kv.hadoop.hive.table.TableStorageHandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tableName" = "likes");

create external table comments_nosql (
idcomment int,
idsong string, 
email string,
commentbody string,
commentdate string)
stored by 'oracle.kv.hadoop.hive.table.TableStorageHandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tableName" = "comments");

create external table genres_nosql (
idgenre int,
title string)
stored by 'oracle.kv.hadoop.hive.table.TableStorageHandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tableName" = "genres");

create external table artists_nosql (
idartist int,
name string)
stored by 'oracle.kv.hadoop.hive.table.TableStorageHandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tableName" = "artists");

create external table albums_nosql (
idalbum int,
title string)
stored by 'oracle.kv.hadoop.hive.table.TableStorageHandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tableName" = "albums");

create external table songs_nosql (
idsong string,
idalbum int, 
idgenre int,
idartist int)
stored by 'oracle.kv.hadoop.hive.table.TableStorageHandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tableName" = "songs");

create external table songsdesc_hdfs (
idsong string,
namefile string,
nameartist string,
title string,
year int)
row format delimited fields terminated by '|'
stored as textfile location 'hdfs:/music/songsdesc/';

!quit

-- SQL
sqlplus / as sysdba
startup
connect system/welcome1

CREATE OR REPLACE DIRECTORY ORACLE_BIGDATA_CONFIG AS '/u01/bigdatasql_config';
CREATE OR REPLACE DIRECTORY "ORA_BIGDATA_CL_bigdatalite" AS '';

CREATE TABLE profiles_hive (
	email varchar2(255),
	name varchar2(40),
	surname varchar2(40),
	birthday varchar2(10),
	gender number(1),
	pass varchar2(255)
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.profiles
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE groups_hive (
	idgroup int,
	title varchar2(255)
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.groups
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE ptog_hive (
	email varchar2(255),
	idgroup int
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.ptog
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE likes_hive (
	email varchar2(255),
	idsong varchar2(255),
	recommend int
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.likes_nosql
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE comments_hive (
	idcomment int,
	idsong varchar2(255),
	email varchar2(255),
	commentbody varchar2(255),
	commentdate varchar2(255)
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.comments_nosql
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE genres_hive (
	idgenre int,
	title varchar2(255)
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.genres_nosql
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE artists_hive (
	idartist int,
	name varchar2(255)
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.artists_nosql
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE albums_hive (
	idalbum int,
	title varchar2(255)
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.albums_nosql
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE songs_hive (
	idsong varchar2(255),
	idalbum int,
	idgenre int,
	idartist int
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.songs_nosql
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE songsdesc_hive (
	idsong varchar2(255),
	namefile varchar2(255),
	nameartist varchar2(255),
	title varchar2(255),
	year int
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.songsdesc_hdfs
	)
)
REJECT LIMIT UNLIMITED;

exit

lsnrctl
start
exit
