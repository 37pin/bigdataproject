--select top likes
select s.title, s.nameartist, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_ext s where c.idsong=s.idsong order by c.cn;

--select top likes by artist name
select s.nameartist, s.title, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_ext s where c.idsong=s.idsong and s.nameartist like '%Babylon%' order by c.cn;

--select top artist by likes
select distinct s.nameartist from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_ext s where c.idsong=s.idsong;

--select top likes by genre
select s.nameartist, s.title, s.year, c.cn from genres_hive g, song_hive s, songsdesc_ext sd, (select idsong, count(*) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong;

--select top active users
select p.email, p.name, p.surname from profiles_hive p, (select email, count(*)+sum(recommend) cn from likes_hive group by email) sd where p.email=sd.email;

--select top recommends
select s.title, s.nameartist, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_ext s where c.idsong=s.idsong order by c.cn;

--select top recommends by artist name
select s.nameartist, s.title, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_ext s where c.idsong=s.idsong and s.nameartist like '%Babylon%' order by c.cn;

--select top artist by recommends
select distinct s.nameartist from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_ext s where c.idsong=s.idsong;

--select top recommens by genre
select s.nameartist, s.title, s.year, c.cn from genres_hive g, song_hive s, songsdesc_ext sd, (select idsong, sum(recommend) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong;

--select top album
select a.title, c.cn from albums_hive a, (select s.idalbum, count() cn from likes_hive l, songs_hive s where l.idsong=s.idsong group by s.idalbum) c where a.idalbum=c.idalbum