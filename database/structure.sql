NoSQL
	likes(email, idsong)
	recommends(email, idsong)
	comments(idcomment, idsong, email, commentbody, commentdate)
	genres(idgenre, title)
	artists(idartist, name)
	albums(idalbum, title)
	songs(idsong, idalbum, idgenre)
	atoa(idartist, idalbum)

HIVE
	profiles(email, name, surname, birthday, gender, pass)
	groups(idgroup, title)
    ptog(email, idgroup)
	external tables

HDFS
	songsdesc(idsong, namefile, title, year)

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
execute 'create table likes(email string, idsong string, primary key(email, idsong))'
execute 'create table recommends(email string, idsong string, primary key(email, idsong))'
execute 'create table comments(idcomment integer, idsong string, email string, commentbody string, commentdate string, primary key(idcomment))'
execute 'create table genres(idgenre integer, title string, primary key(idgenre))'
execute 'create table artists(idartist integer, name string, primary key(idartist))'
execute 'create table albums(idalbum integer, title string, primary key(idalbum))'
execute 'create table songs(idsong string, idalbum integer, idgenre integer, primary key(idsong))'
execute 'create table atoa(idartist integer, idalbum integer, primary key(idartist, idalbum))'

put table -name likes -json '{"email":"", "idsong":""}'

put table -name recommends -json '{"email":"", "idsong":""}'

put table -name comments -json '{"idcomment":," idsong":"", "email":"", "commentbody":"", "commentdate":""}'

put table -name genres -json '{"idgenre":1, "title":"RnB"}'
put table -name genres -json '{"idgenre":2, "title":"Rock"}'
put table -name genres -json '{"idgenre":3, "title":"Rap"}'
put table -name genres -json '{"idgenre":4, "title":"Hip-Hop"}'
put table -name genres -json '{"idgenre":5, "title":"Classic"}'
put table -name genres -json '{"idgenre":6, "title":"Jazz"}'
put table -name genres -json '{"idgenre":7, "title":"Pop"}'
put table -name genres -json '{"idgenre":8, "title":"
Electonic music"}'


put table -name artists -json '{"idartist":, "name":""}'

put table -name albums -json '{"idalbum":, "title":""}'

put table -name songs -json '{"idsong":"70GKB82HGS", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'
put table -name songs -json '{"idsong":"", "idalbum":, "idgenre":}'

put table -name atoa -json '{"idartist":, "idalbum":}'

-- HIVE
beeline

!connect jdbc:hive2://localhost:10000
oracle
welcome1
create table profiles(email string, name string, surname string, birthday string, gender int, pass string);

create table groups(idgroup int, title string);
create table ptog(email string, idgroup string);

create external table likes_nosql (
	email string,
	idsong string)
stored by 'oracle.kv.hadoop.hive.table.tablestoragehandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tablename" = "likes");

create external table recommends_nosql (
	email string,
	idsong string)
stored by 'oracle.kv.hadoop.hive.table.tablestoragehandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tablename" = "recommends");

create external table comments_nosql (
	idcomment int,
	idsong string, 
	email string,
	commentbody string,
	commentdate string)
stored by 'oracle.kv.hadoop.hive.table.tablestoragehandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tablename" = "comments");

create external table genres_nosql (
	idgenre int,
	title string)
stored by 'oracle.kv.hadoop.hive.table.tablestoragehandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tablename" = "genres");

create external table artists_nosql (
	idartist int,
	name string)
stored by 'oracle.kv.hadoop.hive.table.tablestoragehandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tablename" = "artists");

create external table albums_nosql (
	idalbum int,
	title string)
stored by 'oracle.kv.hadoop.hive.table.tablestoragehandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tablename" = "albums");

create external table songs_nosql (
	idsong string,
	idalbum int, 
	idgenre int)
stored by 'oracle.kv.hadoop.hive.table.tablestoragehandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tablename" = "songs");

create external table atoa_nosql (
	idatoa int,
	idartist int, 
	idalbum int)
stored by 'oracle.kv.hadoop.hive.table.tablestoragehandler' 
tblproperties 
("oracle.kv.kvstore" = "kvstore", 
"oracle.kv.hosts" = "bigdatalite.localdomain:5000", 
"oracle.kv.hadoop.hosts" = "bigdatalite.localdomain/127.0.0.1",
"oracle.kv.tablename" = "atoa");

!quit

-- SQL
sqlplus / as sysdba

startup
connect system/welcome1

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
	idsong varchar2(255)
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

CREATE TABLE recommends_hive (
	email varchar2(255),
	idsong varchar2(255)
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.recommends_nosql
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
	idgenre int
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

CREATE TABLE atoa_hive (
	atoa int,
	idartist int,
	idalbum int
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HIVE
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS
	(
		com.oracle.bigdata.tablename=default.atoa_nosql
	)
)
REJECT LIMIT UNLIMITED;

CREATE TABLE songsdesc_ext (
	idsong varchar2(255),
	namefile varchar2(255),
	idartist int,
	title varchar2(255),
	year number(4)
)
ORGANIZATION EXTERNAL (
	TYPE ORACLE_HDFS
	DEFAULT DIRECTORY ORACLE_BIGDATA_CONFIG
	ACCESS PARAMETERS (
		com.oracle.bigdata.fileformat:TEXTFILE
		com.oracle.bigdata.overflow:{"action":"truncate"}
		com.oracle.bigdata.erroropt:{"action":"setnull"}
		ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'
	)
	LOCATION ('hdfs:/music/songsdesc.csv')
);

exit

lsnrctl
start
exit
