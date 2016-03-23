--select top likes
select s.title, s.nameartist, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc;

--select top likes by artist name
select s.nameartist, s.title, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong and s.nameartist like '%Babylon%' order by c.cn desc;

--select top artist by likes
select distinct s.nameartist, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc;

--select top likes by genre
select sd.nameartist, sd.title, sd.year, c.cn from genres_hive g, songs_hive s, songsdesc_hive sd, (select idsong, count(*) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong order by c.cn desc;

--select top active users
select p.email, p.name, p.surname, sd.likes, sd.recommends from profiles_hive p, (select email, count(*)+sum(recommend) cn, count(*) likes, sum(recommend) recommends from likes_hive group by email) sd where p.email=sd.email order by sd.cn desc;

--select top recommends
select s.title, s.nameartist, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc;

--select top recommends by artist name
select s.nameartist, s.title, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong and s.nameartist like '%Babylon%' order by c.cn desc;

--select top artist by recommends
select distinct s.nameartist, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc;

--select top recommens by genre
select sd.nameartist, sd.title, sd.year, c.cn from genres_hive g, songs_hive s, songsdesc_hive sd, (select idsong, sum(recommend) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong order by c.cn desc;

--select top album
select a.title, c.cn from albums_hive a, (select s.idalbum, count(*) cn from likes_hive ls, songs_hive s where ls.idsong=s.idsong group by s.idalbum) c where a.idalbum=c.idalbum order by c.cn desc;
