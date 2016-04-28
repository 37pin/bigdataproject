setwd("~/r-project/")
install.packages("RJDBC")
library(RJDBC)
oracleDriver <- JDBC("oracle.jdbc.OracleDriver", classPath="C:/Users/parameeva/bigdataproject/statisticR/lib/ojdbc6.jar", " ")
oracleConnection <- dbConnect(oracleDriver, "jdbc:oracle:thin:@bigdatalite.localdomain:1521:cdb", "system","welcome1")

#select top likes
topLikes<- dbGetQuery(oracleConnection, "select s.title, s.nameartist, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc")
attach(topLikes)
ggplot(topLikes[1:10,], aes(TITLE, CN, fill = TITLE)) + geom_bar(stat = "identity") + scale_y_continuous("LIKES") + scale_x_discrete(name = "", labels = c()) + ggtitle("Top songs by likes")

#select top likes by artist name
topArtistNameByLikes <- dbGetQuery(oracleConnection, "select s.nameartist, s.title, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong and s.nameartist like '%The Polish Ambassador%' order by c.cn desc")
attach(topArtistNameByLikes)
ggplot(topArtistNameByLikes, aes(TITLE, CN, fill = TITLE)) + geom_bar(stat = "identity") + scale_y_continuous("LIKES") + scale_x_discrete(name = "", labels = c()) + ggtitle("Top songs for artist by likes")

#select top artist by likes
topArtistByLikes<- dbGetQuery(oracleConnection, "select distinct s.nameartist, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc")
attach(topArtistByLikes)
ggplot(topArtistByLikes, aes(NAMEARTIST, CN, fill = NAMEARTIST)) + geom_bar(stat = "identity") + scale_y_continuous("LIKES") + scale_x_discrete(name = "", labels = c()) + ggtitle("Top artists by likes")

#select top likes by genre
topLikesByGenres <- dbGetQuery(oracleConnection, "select sd.nameartist, sd.title, sd.year, c.cn from genres_hive g, songs_hive s, songsdesc_hive sd, (select idsong, count(*) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong order by c.cn desc")

#select top active users
topActiveUsers <- dbGetQuery(oracleConnection, "select p.email, p.name, p.surname, sd.likes, sd.recommends from profiles_hive p, (select email, count(*)+sum(recommend) cn, count(*) likes, sum(recommend) recommends from likes_hive group by email) sd where p.email=sd.email order by sd.cn desc")
attach(topActiveUsers)
ggplot(topActiveUsers, aes(EMAIL, CN, fill = EMAIL)) + geom_bar(stat = "identity") + scale_y_continuous("LIKES") + scale_x_discrete(name = "", labels = c()) + ggtitle("The most active users")

#select top recommends
topRecommends<- dbGetQuery(oracleConnection, "select s.title, s.nameartist, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc")

#select top recommends by artist name
topRecommendsByArtist<- dbGetQuery(oracleConnection, "select s.nameartist, s.title, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong and s.nameartist like '%The Polish%' order by c.cn desc")

#select top artist by recommends
topArtistByRecommends<- dbGetQuery(oracleConnection, "select distinct s.nameartist, sum(c.cn) cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong group by s.nameartist order by cn desc")

#select top recommens by genre
#topRecommendsByGenres<- dbGetQuery(oracleConnection, "select sd.nameartist, sd.title, sd.year, c.cn from genres_hive g, songs_hive s, songsdesc_hive sd, (select idsong, sum(recommend) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong order by c.cn desc")

#select top album
topAlbum <- dbGetQuery(oracleConnection, "select a.title, c.cn from albums_hive a, (select s.idalbum, count(*) cn from likes_hive ls, songs_hive s where ls.idsong=s.idsong group by s.idalbum) c where a.idalbum=c.idalbum order by c.cn desc")

attach(topAlbum)
ggplot(topAlbum, aes(TITLE, CN)) + geom_bar(stat = "identity", position = "dodge", fill= "grey50", colour = "black")
#dbDisconnect(oracleConnection)
allUsers<- dbGetQuery(oracleConnection, "select * from profiles_hive")
allSong <- dbGetQuery(oracleConnection, "select sd.*, g.title from song_hive s, genres_hive g, songsdesc_hive sd where g.idgenre=s.idgenre and s.idsong=sd.idsong")

allSongsRecom_E <- dbGetQuery(oracleConnection, "select decode(gender, 1, 'M', 2, 'F') gender, to_number(substr(birthday, -4, 4)) year, decode(recommend, 0, 'N', 1, 'Y') recommend, g.title from profiles_hive p, likes_hive l, songs_hive s, genres_hive g where p.email=l.email and l.idsong=s.idsong and s.idgenre=g.idgenre")

install.packages("rpart")
library(rpart)
tree <- rpart(RECOMMEND~., allSongsRecom_E)
tree
plot(tree)
text(tree, pretty=0)
allSongsRecom <- dbGetQuery(oracleConnection, "select p.email, decode(gender, 1, 'M', 2, 'F') gender, to_number(substr(birthday, -4, 4)) year, g.title from profiles_hive p, (select g.title from genres_hive g where g.idgenre in (1, 7, 8)) g where p.email not in (select distinct email from likes_hive)")
prediction <- predict(tree.rp1, allSongsRecom)
prediction
View(prediction)
answer <- data.frame(allSongsRecom, prediction)
View(answer)

install.packages("rattle")
library(rattle)
attach(answer)
fit <-rpart(answer$GENDER ~ answer$Y, method = "class", answer)
plotcp(fit)
printcp(fit)
plot(fit, uniform=TRUE, main="tree")

allSongsLikes_E <- dbGetQuery(oracleConnection, "select s.year, s.gender, s.idsong, s.lk 
	from 
		(select 
			p.email, to_number(substr(p.birthday, -4, 4)) year, 
			decode(p.gender, 1, 'M', 2, 'F') gender, s.idsong, 
			decode(l.RECOMMEND, null, 'N', 'Y') lk 
		from 
			profiles_hive p, SONGS_HIVE s, genres_hive g, LIKES_HIVE l 
		where 
			s.IDGENRE=g.IDGENRE and l.IDSONG(+)=s.IDSONG and p.EMAIL=l.email(+)) s 
	where s.email in (select DISTINCT EMAIL from LIKES_HIVE)")

tree <- rpart(LK~., allSongsLikes_E)
tree
plot(tree)
text(tree, pretty=0)

allSongsLikes <- dbGetQuery(oracleConnection, "select s.email, s.year, s.gender, s.idsong 
	from 
		(select 
			p.email, to_number(substr(p.birthday, -4, 4)) year, 
			decode(p.gender, 1, 'M', 2, 'F') gender, s.idsong 
		from 
			profiles_hive p, SONGS_HIVE s) s")

prediction <- predict(tree, allSongsLikes, type="class")
table(prediction, allSongsLikes_E$LK)
prediction
View(prediction)
answerLikes <- data.frame(email = allSongsLikes$EMAIL, idsong = allSongsLikes$IDSONG, probability = prediction[,'Y'])
answerLikes <- answerLikes[order(email, -probability),]
answerLikesTest <- answerLikes[1:5,]
options(java.parameters = "-Xmx8g")
cp = c("C:/Users/parameeva/bigdataproject/statisticR/lib/hive-jdbc.jar",
	"C:/Users/parameeva/bigdataproject/statisticR/lib/hadoop-common.jar",
	"C:/Users/parameeva/bigdataproject/statisticR/lib/libthrift-0.9.2.jar",
	"C:/Users/parameeva/bigdataproject/statisticR/lib/hive-service.jar",
	"C:/Users/parameeva/bigdataproject/statisticR/lib/httpclient-4.2.5.jar",
	"C:/Users/parameeva/bigdataproject/statisticR/lib/httpcore-4.2.5.jar",
	"C:/Users/parameeva/bigdataproject/statisticR/lib/hive-jdbc-standalone.jar")
.jinit(classpath=cp)
.jclassPath()

hiveDriver <- JDBC("org.apache.hive.jdbc.HiveDriver", "C:/Users/parameeva/bigdataproject/statisticR/lib/hive-jdbc.jar", identifier.quote="`")
hiveConnection <- dbConnect(hiveDriver, "jdbc:hive2://bigdatalite.localdomain:10000/", "oracle", "welcome1")

dbSendUpdate(hiveConnection, 'drop table predictions')
dbSendUpdate(hiveConnection, 'create table predictions(email string, idsong string, probability double)')
for (i in 1:nrow(answerLikes)) {
    row <- answerLikes[i,]
    dbSendUpdate(hiveConnection, "insert into predictions values (?, ?, ?)", row[,1], row[,2], row[,3])
}
#dbDisconnect(hiveConnection)
