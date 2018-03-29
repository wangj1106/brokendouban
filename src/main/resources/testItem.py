#!/usr/bin/python
# coding:utf8

import pymysql
from operator import itemgetter
import sys

class ItemBasedCF():
    ''' TopN recommendation - ItemBasedCF '''
    def __init__(self):
        self.trainset = {}
        self.recommendedset={}

        # n_sim_movie: top 5个用户， n_rec_movie: top 5个推荐结果
        self.n_sim_movie = 5
        self.n_rec_movie = 5
        self.movie_sim_mat = {}
        self.recommended_movie={}

        print  ( 'Similar movie number = %d' % self.n_sim_movie)
        print  ( 'Recommended movie number = %d' % self.n_rec_movie)

    def loaddatebase(self,databasename):
        """loaddatabase(加载数据库数据，返回一个生成器)

        Args:
            filename   文件名
        Returns:
            line       行数据，去空格
        """

        db = pymysql.connect("bingodatabase.mysqldb.chinacloudapi.cn", "bingodatabase%bingo", "Brokendouban123",
                             databasename)
        cursor = db.cursor()
        cursor.execute("SELECT * FROM  simmovies")
        data = cursor.fetchall()
        print(enumerate(data))
        movie_sim_mat = {}
        for mid, simId, similarity in data:
            movie_sim_mat.setdefault(mid, {})
            movie_sim_mat[mid][simId]=similarity
        db.close();
        return movie_sim_mat
    def loaddatebase2(self,databasename,user):
        """loaddatabase(加载数据库数据，返回一个生成器)

        Args:
            filename   文件名
        Returns:
            line       行数据，去空格
        """

        db = pymysql.connect("bingodatabase.mysqldb.chinacloudapi.cn", "bingodatabase%bingo", "Brokendouban123",
                             databasename)
        cursor = db.cursor()
        cursor.execute("SELECT user_id,movie_id,rating FROM rating where user_id = %s" % user)
        data = cursor.fetchall()
        print(enumerate(data))
        watched_movie = {}
        for userId, movieId, rating in data:
            watched_movie.setdefault(userId, {})
            watched_movie[userId][movieId]=rating
        db.close();
        print(watched_movie)
        return watched_movie

    def loaddatebase3(self,databasename,user):
        """loaddatabase(加载数据库数据，返回一个生成器)

        Args:
            filename   文件名
        Returns:
            line       行数据，去空格
        """

        db = pymysql.connect("bingodatabase.mysqldb.chinacloudapi.cn", "bingodatabase%bingo", "Brokendouban123",
                             databasename)
        cursor = db.cursor()
        cursor.execute("SELECT uid,mid,rating FROM itemcf2 where uid = %s" % user)
        data = cursor.fetchall()
        recommended_movie = {}
        for userId, movieId, rating in data:
            recommended_movie.setdefault(userId, {})
            recommended_movie[userId][movieId]=rating
        db.close();
        print(recommended_movie)
        return recommended_movie

    def recommendnow(self,user,movie_sim):
        itemcf =ItemBasedCF()
        itemcf.movie_sim_mat=movie_sim
        itemcf.trainset=itemcf.loaddatebase2("test4",user)
        itemcf.recommendedset=itemcf.loaddatebase3("test4",user)
        itemcf.recommendedset.setdefault(user,())
        # if (itemcf.recommendedset[user]=={}):
        #     itemcf.recommendedset[user]=0;
        print(itemcf.recommendedset)
        result = itemcf.recommend(user)
        return result
    def recommend(self, user):
        """recommend(找出top K的电影，对电影进行相似度sum的排序，取出top N的电影数)

        Args:
            user       用户
        Returns:
            rec_movie  电影推荐列表，按照相似度从大到小的排序
        """
        ''' Find K similar movies and recommend N movies. '''
        K = self.n_sim_movie
        N = self.n_rec_movie
        rank = {}
        reason={}
        b=[]
        print(user)
        watched_movies = self.trainset[user]
        recommended_movies = self.recommendedset[user]

        # 计算top K 电影的相似度
        # rating=电影评分, w=不同电影出现的次数
        # 耗时分析：98.2%的时间在 line-154行

        for movie, rating in watched_movies.items():
            for related_movie, w in sorted(self.movie_sim_mat[movie].items(), key=itemgetter(1), reverse=True)[0:K]:
                if related_movie in watched_movies:
                    continue
                if related_movie in recommended_movies:
                    continue
                rank.setdefault(related_movie, 0)
                rank[related_movie] += w * rating
                reason.setdefault(related_movie, {})
                reason[related_movie].setdefault(movie, 0)
                reason[related_movie][movie] = w * rating
        a=sorted(rank.items(), key=itemgetter(1), reverse=True)[0:N]
        for str in a:
            c=sorted(reason[str[0]].items(), key=itemgetter(1), reverse=True)[0:1]
            print(c)
            b.append(c[0][0]   )
        print(b)
        return a,b
if __name__ == '__main__':
    currentuser = int(sys.argv[1])
    itemcf = ItemBasedCF()
    movie_sim=itemcf.loaddatebase("test4")
    result,reason=itemcf.recommendnow(currentuser,movie_sim)
    list=[]
    i=0
    for movie in result:
        list.append((movie[0],movie[1],reason[i]))
        i=i+1
    db = pymysql.connect("bingodatabase.mysqldb.chinacloudapi.cn", "bingodatabase%bingo", "Brokendouban123",
                             "test4")
    cursor = db.cursor()
    for text in list:


            cursor.execute("INSERT INTO itemcf2(uid,mid,reasonid,rating)  VALUES ('%d', '%d','%d','%.9f')" % (
            currentuser, text[0], text[2], text[1]))
            db.commit()
    db.close()

