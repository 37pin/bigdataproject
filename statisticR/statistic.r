writeLines("Initialization...")
libdir <- .libPaths()
.libPaths(c("rlibrary"))
install.packages("RJDBC", repos="https://cran.rstudio.com/")
install.packages("ggplot2", repos="https://cran.rstudio.com/")
install.packages("rpart", repos="https://cran.rstudio.com/")
library(RJDBC)
library(ggplot2)
library(rpart)

oracleDriver <- JDBC("oracle.jdbc.OracleDriver", classPath="lib/ojdbc6.jar", " ")
oracleConnection <- dbConnect(oracleDriver, "jdbc:oracle:thin:@bigdatalite.localdomain:1521:cdb", "system","welcome1")
pdf("plots.pdf")
writeLines("Initialization complete\n")

writeLines("Drawing plots...")
#select top likes
topLikes <- dbGetQuery(oracleConnection, "select s.title, s.nameartist, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong = s.idsong order by c.cn desc")
topLikes <- head(topLikes, 10)
attach(topLikes)
ggplot(topLikes, aes(TITLE, CN, fill = TITLE)) + geom_bar(stat = "identity") + scale_y_continuous("LIKES") + scale_x_discrete(name = "", labels = c()) + ggtitle("Top songs by likes")
detach(topLikes)

#select top likes by artist name
topArtistNameByLikes <- dbGetQuery(oracleConnection, "select s.nameartist, s.title, s.year, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong and s.nameartist like '%The Polish Ambassador%' order by c.cn desc")
topArtistNameByLikes <- head(topArtistNameByLikes, 10)
attach(topArtistNameByLikes)
ggplot(topArtistNameByLikes, aes(TITLE, CN, fill = TITLE)) + geom_bar(stat = "identity") + scale_y_continuous("LIKES") + scale_x_discrete(name = "", labels = c()) + ggtitle("Top songs for artist by likes")
detach(topArtistNameByLikes)

#select top artist by likes
topArtistByLikes <- dbGetQuery(oracleConnection, "select distinct s.nameartist, c.cn from (select idsong, count(*) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong = s.idsong order by c.cn desc")
topArtistByLikes <- head(topArtistByLikes, 10)
attach(topArtistByLikes)
ggplot(topArtistByLikes, aes(NAMEARTIST, CN, fill = NAMEARTIST)) + geom_bar(stat = "identity") + scale_y_continuous("LIKES") + scale_x_discrete(name = "", labels = c()) + ggtitle("Top artists by likes")
detach(topArtistByLikes)

#select top active users
topActiveUsers <- dbGetQuery(oracleConnection, "select p.email, p.name, p.surname, sd.cn from profiles_hive p, (select email, count(*)+sum(recommend) cn, count(*) likes, sum(recommend) recommends from likes_hive group by email) sd where p.email = sd.email order by sd.cn desc")
topActiveUsers <- head(topActiveUsers, 10)
attach(topActiveUsers)
ggplot(topActiveUsers, aes(EMAIL, CN, fill = EMAIL)) + geom_bar(stat = "identity") + scale_y_continuous("LIKES") + scale_x_discrete(name = "", labels = c())
detach(topActiveUsers)
writeLines("Drawing complite. Plots have been saved in the file plots.pdf\n")

writeLines("Statistic...")
#select top album
writeLines("Most liked albums:")
topAlbum <- dbGetQuery(oracleConnection, "select a.title, c.cn from albums_hive a, (select s.idalbum, count(*) cn from likes_hive ls, songs_hive s where ls.idsong = s.idsong group by s.idalbum) c where a.idalbum = c.idalbum order by c.cn desc")
topAlbum <- head(topAlbum, 10)
topAlbum[, 'TITLE', drop=FALSE]

#select top likes by genre
writeLines("\nMost liked RnB songs:")
topLikesByGenres <- dbGetQuery(oracleConnection, "select sd.nameartist, sd.title, sd.year, c.cn from genres_hive g, songs_hive s, songsdesc_hive sd, (select idsong, count(*) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong order by c.cn desc")
topLikesByGenres <- head(topLikesByGenres, 10)
topLikesByGenres[, c('NAMEARTIST', 'TITLE')]

#select top recommends
writeLines("\nMost recommended songs:")
topRecommends <- dbGetQuery(oracleConnection, "select s.title, s.nameartist, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong order by c.cn desc")
topRecommends <- head(topRecommends, 10)
topRecommends[, c('NAMEARTIST', 'TITLE')]

#select top recommends by artist name
writeLines("\nMost recommended songs of The Polish Ambassador:")
topRecommendsByArtist <- dbGetQuery(oracleConnection, "select s.nameartist, s.title, s.year, c.cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong and s.nameartist like '%The Polish%' order by c.cn desc")
topRecommendsByArtist <- head(topRecommendsByArtist, 10)
topRecommendsByArtist[, 'TITLE', drop=FALSE]

#select top artist by recommends
writeLines("\nMost recommended artist:")
topArtistByRecommends <- dbGetQuery(oracleConnection, "select distinct s.nameartist, sum(c.cn) cn from (select idsong, sum(recommend) cn from likes_hive group by idsong) c, songsdesc_hive s where c.idsong=s.idsong group by s.nameartist order by cn desc")
topArtistByRecommends <- head(topArtistByRecommends, 10)
topArtistByRecommends[, 'NAMEARTIST', drop=FALSE]

#select top recommens by genre
writeLines("\nMost recommended RnB songs:")
topRecommendsByGenres <- dbGetQuery(oracleConnection, "select sd.nameartist, sd.title, sd.year, c.cn from genres_hive g, songs_hive s, songsdesc_hive sd, (select idsong, sum(recommend) cn from likes_hive group by idsong) c where g.idgenre=s.idgenre and s.idsong=sd.idsong and g.title like '%RnB%' and sd.idsong=c.idsong order by c.cn desc")
topRecommendsByGenres <- head(topRecommendsByGenres, 10)
topRecommendsByGenres[, c('NAMEARTIST', 'TITLE')]

writeLines("\nMaking prediction:")
cat("Getting data sample for tree... ")
allSongsLikes_E <- dbGetQuery(oracleConnection, "select s.year, s.gender, s.idsong, s.lk 
	from 
		(select 
			p.email, to_number(substr(p.birthday, -4, 4)) year, 
			decode(p.gender, 1, 'M', 2, 'F') gender, s.idsong, 
			decode(l.recommend, null, 'N', 'Y') lk 
		from 
			profiles_hive p, songs_hive s, genres_hive g, likes_hive l 
		where 
			s.idgenre=g.idgenre and l.idsong(+)=s.idsong and p.email=l.email(+)) s 
	where s.email in (select distinct email from likes_hive)")
attach(allSongsLikes_E)
writeLines("OK")

writeLines("Creating regression tree...")
tree <- rpart(LK~., allSongsLikes_E)
tree
detach(allSongsLikes_E)

cat("\nGetting data sample for prediction... ")
allSongsLikes <- dbGetQuery(oracleConnection, "select s.email, s.year, s.gender, s.idsong 
	from 
		(select 
			p.email, to_number(substr(p.birthday, -4, 4)) year, 
			decode(p.gender, 1, 'M', 2, 'F') gender, s.idsong 
		from 
			profiles_hive p, songs_hive s) s")
attach(allSongsLikes)
writeLines("OK")

cat("Calculating prediction... ")
prediction <- predict(tree, allSongsLikes)
answerLikes <- data.frame(email = EMAIL, idsong = IDSONG, probability = prediction[,'Y'])
detach(allSongsLikes)
attach(answerLikes)
answerLikes <- answerLikes[order(email, -probability),]
detach(answerLikes)
writeLines("OK")

writeLines("Saving result...")
answerLikes <- answerLikes[1:5,] # for test
options(java.parameters = "-Xmx8g")
cp = c("lib/hive-jdbc.jar",
	"lib/hadoop-common.jar",
	"lib/libthrift-0.9.2.jar",
	"lib/hive-service.jar",
	"lib/httpclient-4.2.5.jar",
	"lib/httpcore-4.2.5.jar",
	"lib/hive-jdbc-standalone.jar")
.jinit(classpath=cp)

hiveDriver <- JDBC("org.apache.hive.jdbc.HiveDriver", "lib/hive-jdbc.jar", identifier.quote="`")
hiveConnection <- dbConnect(hiveDriver, "jdbc:hive2://bigdatalite.localdomain:10000/", "oracle", "welcome1")

dbSendUpdate(hiveConnection, 'drop table predictions')
dbSendUpdate(hiveConnection, 'create table predictions(email string, idsong string, probability double)')
predictionsRowsCnt <- nrow(answerLikes)
for (i in 1:nrow(answerLikes)) {
    row <- answerLikes[i,]
    dbSendUpdate(hiveConnection, "insert into predictions values (?, ?, ?)", row[,1], row[,2], row[,3])
    writeLines(paste("Line", paste(paste(as.character(i), as.character(predictionsRowsCnt), sep="/"), "added", sep=" "), sep=" "))
}

writeLines("\nEnding...")
dbDisconnect(oracleConnection)
dbDisconnect(hiveConnection)
detach("package:RJDBC")
detach("package:ggplot2")
detach("package:rpart")
.libPaths(libdir)
writeLines("Complete!")