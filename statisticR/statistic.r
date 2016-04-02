setwd("~/r-project/")
install.packages("RJDBC")
library(RJDBC)
driver <- JDBC("oracle.jdbc.OracleDriver", classPath="~/r-project/ojdbc6.jar", " ")
connection <- dbConnect(driver, "jdbc:oracle:thin:@10.154.109.228:1521:cdb", "system", "welcome1")

#select top likes
topLikes<- dbGetQuery(connection, "select s.title, s.nameartist, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc")

#select top likes by artist name
topArtistByLikes <- dbGetQuery(connection, "select s.nameartist, s.title, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong and s.nameartist like '%Toussaint Morrison%' order by c.cn desc")

#select top artist by likes
select distinct s.nameartist, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc;

#select top likes by genre
topLikesByGenres <- dbGetQuery(connection, "select sd.nameartist, sd.title, sd.year, c.cn from genres_hive g, songs_hive s, songsdesc_hive sd, (select idsong, count(*) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong order by c.cn desc")

#select top active users
topActiveUsers <- dbGetQuery(connection, "select p.email, p.name, p.surname, sd.likes, sd.recommends from profiles_hive p, (select email, count(*)+sum(recommend) cn, count(*) likes, sum(recommend) recommends from likes_hive group by email) sd where p.email=sd.email order by sd.cn desc")

#select top recommends
topRecommends<- dbGetQuery(connection, "select s.title, s.nameartist, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc")

#select top recommends by artist name
topRecommendsByArtist<- dbGetQuery(connection, "select s.nameartist, s.title, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong and s.nameartist like '%The Polish%' order by c.cn desc")

#select top artist by recommends
topArtistByRecommends<- dbGetQuery(connection, "select distinct s.nameartist, sum(c.cn) cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong group by s.nameartist order by cn desc")

#select top recommens by genre
&topRecommendsByGenres<- dbGetQuery(connection, "select sd.nameartist, sd.title, sd.year, c.cn from genres_hive g, songs_hive s, songsdesc_hive sd, (select idsong, sum(recommend) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong order by c.cn desc")

#select top album
topAlbum <- dbGetQuery(connection, "select a.title, c.cn from albums_hive a, (select s.idalbum, count(*) cn from likes_hive ls, songs_hive s where ls.idsong=s.idsong group by s.idalbum) c where a.idalbum=c.idalbum order by c.cn desc")

ggplot(topAlbum, aes(TITLE, CN)) +
+ geom_bar(stat = "identity", position = "dodge",
+          fill = "grey50", colour = "black")

dbDisconnect(connection)
