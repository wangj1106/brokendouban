协同过滤推荐系统

# 烂豆瓣v5.0.0328_beta API说明书

---

 - 新增通过用户id更新用户(since@v4.0.0321_beta)
 - 添加查询列表排序规则(since@v4.0.0321_beta)
 - 修正分类标签下的排序规则bug(since@v4.0.0322_beta)
 - 完善delete user代码以及movie id问题(since@v4.0.0322_beta)

---

## 1.user模块

### 门户
####1.登录


**IP:PORT/user/login.do**  post

> request

```
username,password
```
> response

fail
```
{
    "status": 1,
    "msg": "密码错误"
}
```

success
```
{
	"status": 0,
	"msg": "登陆成功",
	"data": {
		"id": 6,
		"username": "wangj",
		"password": "",
		"email": "q@q.com",
		"phone": "123456789",
		"question": "我是谁",
		"answer": "节",
		"role": 0,
		"createTime": 1521324665000,
		"updateTime": 1521324665000,
		"age": "90后",
		"sex": 0
	}
}
```


-------

####2.注册
**/user/regist.do**

> request

```
username,password,email,phone,question,answer,age,sex
```


> response

success
```
{
    "status": 0,
    "msg": "校验成功"
}
```


fail
```
{
    "status": 1,
    "msg": "用户已存在"
}
```


--------

####3.检查用户名是否有效

**/user/check_valid.do**

/check_valid.do?str=admin&type=username就是检查用户名。



> request

```
str,type
str可以是用户名也可以是email。对应的type是username和email

```

>response

success
```
{
    "status": 0,
    "msg": "校验成功"
}

```

fail
```
{
    "status": 1,
    "msg": "用户已存在"
}

```


-----------


####4.获取登录用户信息
**/user/get_user_info.do**


> request

```
无参数
```
> response

success
```
{
	"status": 0,
	"data": {
		"id": 6,
		"username": "wangj",
		"password": "",
		"email": "q@q.com",
		"phone": "123456789",
		"question": "我是谁",
		"answer": "节",
		"role": 0,
		"createTime": 1521324665000,
		"updateTime": 1521324665000,
		"age": "90后",
		"sex": 0
		"portrait": "jskjdksjdksjdksjdk"
	}
}
```

fail
```
{
    "status": 1,
    "msg": "用户未登录,无法获取当前用户信息"
}

```


------

####5.忘记密码
**/user/forget_get_question.do**

/user/forget_get_question.do?username=wangj



> request

```
username
```
> response

success

```
{
    "status": 0,
    "data": "这里是问题"
}
```

fail
```
{
    "status": 1,
    "msg": "该用户未设置找回密码问题"
}
```


---------

####6.提交问题答案
**/user/forget_check_answer.do**

localhost:8080/user/forget_check_answer.do?username=aaa&question=aa&answer=sss


> request

```
username,question,answer
```

> response

正确的返回值里面有一个token，修改密码的时候需要用这个。传递给下一个接口



success

```
{
    "status": 0,
    "data": "531ef4b4-9663-4e6d-9a20-fb56367446a5"
}
```

fail

```
{
    "status": 1,
    "msg": "问题答案错误"
}
```


------

####7.忘记密码的重设密码
**/user/forget_reset_password.do**

localhost:8080/user/forget_reset_password.do?username=aaa&passwordNew=xxx&forgetToken=531ef4b4-9663-4e6d-9a20-fb56367446a5

> request

```
username,passwordNew,forgetToken
```

> response

success

```
{
    "status": 0,
    "msg": "修改密码成功"
}
```

fail
```
{
    "status": 1,
    "msg": "修改密码操作失效"
}
```
或
```
{
    "status": 1,
    "msg": "token已经失效"
}
```


------
####8.登录中状态重置密码
**/user/reset_password.do**

> request

```
passwordOld,passwordNew

```

> response

success

```
{
    "status": 0,
    "msg": "修改密码成功"
}
```

fail
```
{
    "status": 1,
    "msg": "旧密码输入错误"
}
```

------
####9.登录状态更新个人信息
**/user/update_information.do**

> request

```
email,phone,question,answer,sex,age
```

> response

success

```
{
    "status": 0,
    "msg": "更新个人信息成功"
}
```

fail
```
{
    "status": 1,
    "msg": "用户未登录"
}
```


------
####10.获取当前登录用户的详细信息，并强制登录
**/user/get_information.do**


> request

```
无参数
```
> response

success
```
{
	"status": 0,
	"data": {
		"id": 6,
		"username": "wangj",
		"password": "",
		"email": "q@q.com",
		"phone": "123456789",
		"question": "我是谁",
		"answer": "节",
		"role": 0,
		"createTime": 1521324665000,
		"updateTime": 1521324665000,
		"age": "90后",
		"sex": 0
		"portrait": "jskjdksjdksjdksjdk"
	}
}
```

fail
```
{
    "status": 10,
    "msg": "用户未登录,无法获取当前用户信息,status=10,强制登录"
}

```


------


####11.退出登录
**/user/logout.do**

> request

```
无
```

> response

success

```
{
    "status": 0,
    "msg": "退出成功"
}
```

fail
```
{
    "status": 1,
    "msg": "服务端异常"
}
```


------
####12.登录状态更新个人头像
**/user/update_information.do**

> request

```
portrait
```

> response

success

```
{
    "status": 0,
    "msg": "更新个人信息成功"
}
```

fail
```
{
    "status": 1,
    "msg": "用户未登录"
}
```
------

#### 图片服务器APIs
IP:PORT  20t445759t.imwork.net:14108
* GET  /files/{pageIndex}/{pageSize} : Paging query file list.(分页查询文件列表)
* GET  /files/{id} : Download file.(下载某个文件)
* GET  /view/{id} : View file online.(在线预览某个文件。比如，显示图片)
* POST /upload : Upload file.(上传文件)
* DELETE /{id} : Delete file.(删除文件)

前端示例
```
<form id= "uploadForm">
		    <p >上传文件： <input type="file" name="file"/></ p>
		    <input type="button" value="上传" onclick="doUpload()"/>
</form>
		
<script>
	function doUpload() { 
		var formData = new FormData($( "#uploadForm" )[0]); 
		$.ajax({ 
			url: 'http://localhost:8081/upload' , 
			type: 'POST', 
			data: formData, 
			async: false, 
			cache: false, 
			contentType: false, 
			processData: false, 
			success: function (res) { 
				alert(res); //res就是显示图片的url，回掉函数里再写个ajax上传图片url
			},
			error: function (res) { 
				alert(res); 
			} 
		}); 
	}
</script>
```
------
###后台
####1.后台管理员登录

**/manage/user/login.do**


> request

```
String username,
String password
```

> response

success

```
{
    "status": 0,
    "data": {
        "id": 12,
        "username": "aaa",
        "email": "aaa@163.com",
        "phone": null,
        "role": 0,
        "createTime": 1479048325000,
        "updateTime": 1479048325000
    }
}
```

fail
```
{
    "status": 1,
    "msg": "密码错误"
}
```

------


####2.用户列表

**/manage/user/list.do**


> request

```
pageSize(default=10)
pageNum(default=1)
```

> response

success

```
{
	"status": 0,
	"data": {
		"pageNum": 1,
		"pageSize": 10,
		"size": 2,
		"orderBy": null,
		"startRow": 1,
		"endRow": 2,
		"total": 2,
		"pages": 1,
		"list": [{
				"id": 7,
				"username": "admin",
				"password": "E10ADC3949BA59ABBE56E057F20F883E",
				"email": "admin@admin.com",
				"phone": "123456789",
				"question": "我是谁",
				"answer": "节",
				"role": 1,
				"createTime": 1521325512000,
				"updateTime": 1521325363000,
				"age": "90后",
				"sex": 0
			},
			{
				"id": 6,
				"username": "wangj",
				"password": "E10ADC3949BA59ABBE56E057F20F883E",
				"email": "q@q.com",
				"phone": "123456789",
				"question": "我是谁",
				"answer": "节",
				"role": 0,
				"createTime": 1521324665000,
				"updateTime": 1521324665000,
				"age": "90后",
				"sex": 0
			}
		],
		"firstPage": 1,
		"prePage": 0,
		"nextPage": 0,
		"lastPage": 1,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": false,
		"hasNextPage": false,
		"navigatePages": 8,
		"navigatepageNums": [
			1
		]
	}
}
```

fail
```
{
  "status": 10,
  "msg": "用户未登录,请登录"
}


或

{
  "status": 1,
  "msg": "没有权限"
}



```
------


####3.通过用户id查询用户详情

**/manage/user/get_information.do**


> request

```
userId
```

> response

success

```
{
	"status": 0,
	"data": {
		"id": 9,
		"username": "xuyuhang",
		"password": "",
		"email": "xuyuhang@qq.com",
		"phone": "18860902891",
		"question": "wo",
		"answer": "ni",
		"role": 0,
		"createTime": 1521342171000,
		"updateTime": 1521342171000,
		"age": "90后",
		"sex": 1
	}
}
```

fail
```
{
	"status": 10,
	"msg": "用户未登录，请登录管理员"
}
```
或
```
{
	"status": 1,
	"msg": "找不到当前用户"
}
```
------
####4.通过用户id删除用户

**/manage/user/delete.do**


> request

```
userId
```

> response

success

```
{
"status": 1,
"msg": "删除用户操作成功"
}
```

fail
```
{
	"status": 10,
	"msg": "用户未登录，请登录管理员"
}
```
或
```
{
	"status": 1,
	"msg": "找不到当前用户"
}
```
------
####5.通过用户id更新用户

**/manage/user/update_information.do**


> request

```
id,email,phone,question,answer,age,sex
```

> response

success

```
{
	"status": 0,
	"msg": "更新个人信息成功",
	"data": {
		"id": 11,
		"username": null,
		"password": null,
		"email": "modified2",
		"phone": "modified2",
		"question": "modified2",
		"answer": "modified2",
		"role": null,
		"createTime": null,
		"updateTime": null,
		"age": "modified2",
		"sex": 20
	}
}
```

fail
```
{
	"status": 10,
	"msg": "用户未登录，请登录管理员"
}
```
或
```
{
	"status": 1,
	"msg": "email已存在，请更新email再尝试更新"or"权限不足"
}
```
------
## 2.movie模块
###门户
####1.电影详情展示

**/manage/movie/get_information.do**

> request

```
movie_id
```

> response

success

```
{
	"status": 0,
	"data": {
    	"movie_id": 5794766,
    	"movie_name": "Sunspring",
    	"movie_director": "Oscar Sharp",
    	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
    	"movie_language": "0",
    	"type_id": 2,
    	"movie_district": "US",
    	"movie_date": 2016,
    	"movie_keyword": "Sci-Fi,Short",
    	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
    	"movie_imdbid": "tt5794766",
    	"movie_time": 9,
    	"movie_numVotes": 230,
    	"movie_rating": 5.7
    }
}
```

fail
```
{
    "status": 1,
    "msg": "获取失败，无该电影"
}
```
或
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```
------

####2.电影列表展示

**/movie/showlist.do**

> request

```
pageSize(default=10)
pageNum(default=1)
orderBy（default="null")//movie_date desc(按时间)、movie_rating desc(按评分)、movie_numVotes desc(按热度)
```

> response

success

```

{
	"status": 0,
	"data": {
		"pageNum": 1,
		"pageSize": 2,
		"size": 2,
		"orderBy": null,
		"startRow": 0,
		"endRow": 1,
		"total": 2,
		"pages": 1,
		"list": [{
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
			{
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            }
		],
		"firstPage": 1,
		"prePage": 0,
		"nextPage": 0,
		"lastPage": 1,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": false,
		"hasNextPage": false,
		"navigatePages": 8,
		"navigatepageNums": [
			1
		]
	}
}
```
或
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```
------
####3.电影列表按类别展示

**/movie/showlistByCategory.do**

> request

```
pageSize(default=10)
pageNum(default=1)
orderBy（default="null")//movie_date desc(按时间)、movie_rating desc(按评分)、movie_numVotes desc(按热度)
movie_keyword
```

> response

success

```
{
	"status": 0,
	"data": {
		"pageNum": 1,
		"pageSize": 2,
		"size": 2,
		"orderBy": null,
		"startRow": 0,
		"endRow": 1,
		"total": 2,
		"pages": 1,
		"list": [{
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            }
		],
		"firstPage": 1,
		"prePage": 0,
		"nextPage": 0,
		"lastPage": 1,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": false,
		"hasNextPage": false,
		"navigatePages": 8,
		"navigatepageNums": [
			1
		]
	}
}
```
或
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```
-------
####4.电影搜索

**/movie/showlistByName.do**

> request

```
pageSize(default=10)
pageNum(default=1)
movie_name
```

> response

success

```

{
	"status": 0,
	"data": {
		"pageNum": 1,
		"pageSize": 2,
		"size": 2,
		"orderBy": null,
		"startRow": 0,
		"endRow": 1,
		"total": 2,
		"pages": 1,
		"list": [{
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
			{
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            }
		],
		"firstPage": 1,
		"prePage": 0,
		"nextPage": 0,
		"lastPage": 1,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": false,
		"hasNextPage": false,
		"navigatePages": 8,
		"navigatepageNums": [
			1
		]
	}
}
```
或
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```
-------
### 后台 //to test


####1.增加电影

**/manage/movie/add.do**

> request

```
moive_name,movie_director,movie_actor,movie_language,type_id,movie_distrit,movie_date,moive_keyword,pic_url,movie_imdbid,movie_time,movie_rating,movie_numVotes
```

> response

success

```
{
    "status": 0,
    "msg": "添加电影操作成功"
}
```

fail
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```
------


####2.删除电影

**/manage/movie/delete.do**

> request

```
movie_id
```

> response

success

```
{
    "status": 0,
    "msg": "删除成功"
}
```

fail
```
{
    "status": 1,
    "msg": "删除失败，无该电影"
}

```
或
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```
------
####3.获取电影信息

**/manage/movie/get_information.do**

> request

```
movie_id
```

> response

success

```
{
    "status": 0,
    "data": {
				"movie_id": 35423,
				"movie_name": "Kate & Leopold",
				"movie_director": "James Mangold",
				"movie_actor": "Breckin Meyer,Hugh Jackman,Liev Schreiber,Meg Ryan",
				"movie_language": "\\N,\\N,\\N,\\N,\\N",
				"type_id": 1,
				"movie_district": "GR,AR,AT,DE,US",
				"movie_date": 2001,
				"movie_keyword": "Comedy,Fantasy,Romance",
				"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p475932453.jpg",
				"movie_imdbid": "tt0035423",
				"movie_time": 118,
				"movie_numVotes": 70443,
				"movie_rating": 7
            }
}
```

fail
```
{
    "status": 1,
    "msg": "获取失败，无该电影"
}
```
或
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```
------

####4.提交电影更改信息

**/manage/movie/update_information.do**

> request

```
movie_id,moive_name,movie_director,movie_actor,movie_language,type_id,movie_distrit,movie_date,moive_keyword,pic_id,movie_imdbid,movie_time
```

> response

success

```
{
    "status": 0,
    "msg": "更改电影信息成功"
}
```

fail
```
{
    "status": 1,
    "msg": "更改失败，无该电影"
}
```
或
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```
------

####5.查询电影信息

**/manage/movie/list.do**

> request

```
无参数
```

> response

success

```

{
  "status": 0,
  "data": {
    "pageNum": 1,
    "pageSize": 4,
    "size": 4,
    "orderBy": null,
    "startRow": 0,
    "endRow": 3,
    "total": 4,
    "pages": 1,
    "list": [
      {
        "movie_id": 1,
        "movie_name": "blue",
        "movie_director": "I",
        "movie_actor": [
          "I"
        ],
        "movie_language": "cn",
        "type_id": 2,
        "movie_district": "cn",
        "movie_date": 1995,
        "movie_keyword": [
          "不好看"
        ],
        "pic_id": 2,
        "movie_imdbid": "tt005",
        "movie_time": 120
      },
      {
        "movie_id": 2,
        "movie_name": "blue",
        "movie_director": "我",
        "movie_actor": [
          "王节",
          "节节",
          "节哥"
        ],
        "movie_language": "cn",
        "type_id": 2,
        "movie_district": "china",
        "movie_date": 1995,
        "movie_keyword": [
          "没意思",
          "不好看"
        ],
        "pic_id": 12,
        "movie_imdbid": "tt456465",
        "movie_time": 120
      },
      {
        "movie_id": 3,
        "movie_name": "blue",
        "movie_director": "我",
        "movie_actor": [
          "王节节节"
        ],
        "movie_language": "cn",
        "type_id": 2,
        "movie_district": "china",
        "movie_date": 1995,
        "movie_keyword": [
          "没意思"
        ],
        "pic_id": 12,
        "movie_imdbid": "tt10002",
        "movie_time": 120
      },
      {
        "movie_id": 4,
        "movie_name": "blue",
        "movie_director": "我",
        "movie_actor": [
          "我",
          "你"
        ],
        "movie_language": "cn",
        "type_id": 2,
        "movie_district": "china",
        "movie_date": 179,
        "movie_keyword": [
          "没意思",
          "不好看"
        ],
        "pic_id": 12,
        "movie_imdbid": "tt10002",
        "movie_time": 120
      }
    ],
    "firstPage": 1,
    "prePage": 0,
    "nextPage": 0,
    "lastPage": 1,
    "isFirstPage": true,
    "isLastPage": true,
    "hasPreviousPage": false,
    "hasNextPage": false,
    "navigatePages": 8,
    "navigatepageNums": [
      1
    ]
  }
}
```
或
```
{
  "status": 10,
  "msg": "管理员未登录,请登录"
}
```

-------
##3.recommend模块

####1.获取当前登录用户的推荐信息(5个电影)

/user/get_recommend.do

> request

```
无参数
```

> response


success
```
{
	"status": 0,
	"data": [{
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
	    	{
			    "movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
		    {
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
		    {
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
		    {
			    "movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            }
	]
}
```


fail
```
{
    "status": 10,
    "msg": "未登录，需要强制登录status=10"
}
```
-------

####2.获取当前电影相似电影(5个电影)

/movie/showsimilarmovie.do

> request

```
movieId
```

> response


success
```
{
	"status": 0,
	"data": [{
			    "movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
		    {
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
		    {
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
		    {
            	"movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
	    	{
		        "movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            },
		    {
			    "movie_id": 5794766,
            	"movie_name": "Sunspring",
            	"movie_director": "Oscar Sharp",
            	"movie_actor": "Elisabeth Gray,Humphrey Ker,Thomas Middleditch",
            	"movie_language": "0",
            	"type_id": 2,
            	"movie_district": "US",
            	"movie_date": 2016,
            	"movie_keyword": "Sci-Fi,Short",
            	"pic_url": "https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2360154245.jpg",
            	"movie_imdbid": "tt5794766",
            	"movie_time": 9,
            	"movie_numVotes": 230,
            	"movie_rating": 5.7
            }
	]
}
```

-------
##4.rating模块
###门户
####1.用户添加评价

/rating/add.do

> request

```
movieId, rating, comment
```

> response


success
```
{
	"status": 0,
	"msg": "添加影评操作成功"
}
```


fail
```
{
    "status": 1,
    "msg": "用户未登录" or "添加影评操作失败，请检查"
}
```
-------

####2.查询用户的评价列表

/rating/userRatingList.do

> request

```
pageNum
pageSize

```

> response


success
```
{
	"status": 0,
	"data": {
		"pageNum": 1,
		"pageSize": 10,
		"size": 2,
		"orderBy": "create_time",
		"startRow": 1,
		"endRow": 2,
		"total": 2,
		"pages": 1,
		"list": [{
				"rating_id": 68542,
				"user_id": 9999,
				"movie_id": 163187,
				"rating": 3,
				"comment": "test",
				"create_time": 1472547052000,
				"username": "userTest",
				"movie_name": "Runaway Bride"
			},
			{
				"rating_id": 68548,
				"user_id": 9999,
				"movie_id": 999999,
				"rating": 3,
				"comment": "uyuyu",
				"create_time": 1521969834000,
				"username": "userTest",
				"movie_name": null
			}
		],
		"firstPage": 1,
		"prePage": 0,
		"nextPage": 0,
		"lastPage": 1,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": false,
		"hasNextPage": false,
		"navigatePages": 8,
		"navigatepageNums": [
			1
		]
	}
}
```
fail
```
{
    "status": 1,
    "msg": "用户未登录"
}
```

-------

####3.通过电影ID查询用户的评价单条

/rating/userRating.do

> request

```
movieId

```

> response


success
```
{
	"status": 0,
	"data": {
		"rating_id": 68542,
		"user_id": 9999,
		"movie_id": 163187,
		"rating": 3,
		"comment": "test",
		"create_time": 1472547052000,
		"username": "userTest",
		"movie_name": "Runaway Bride"
	}
}
```
fail
```
{
    "status": 1,
    "msg": "未评价"
}
```
-------
####4.查询电影的评价列表，电影详情页中展示

/rating/movieRatingList.do

> request

```
pageNum
pageSize
movieId
```

> response


success
```
{
	"status": 0,
	"data": {
		"pageNum": 1,
		"pageSize": 10,
		"size": 1,
		"orderBy": "create_time",
		"startRow": 1,
		"endRow": 1,
		"total": 1,
		"pages": 1,
		"list": [{
			"rating_id": 68542,
			"user_id": 9999,
			"movie_id": 163187,
			"rating": 3,
			"comment": "test",
			"create_time": 1472547052000,
			"username": "userTest",
			"movie_name": "Runaway Bride"
			"portrait": null
		}],
		"firstPage": 1,
		"prePage": 0,
		"nextPage": 0,
		"lastPage": 1,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": false,
		"hasNextPage": false,
		"navigatePages": 8,
		"navigatepageNums": [
			1
		]
	}
}
```
-------
###后台
####1.管理员查询评价列表

/manage/rating/ratingList.do

> request

```
pageNum
pageSize
```

> response


success
```
{
	"status": 0,
	"data": {
		"pageNum": 1,
		"pageSize": 4,
		"size": 4,
		"orderBy": "create_time",
		"startRow": 1,
		"endRow": 4,
		"total": 68545,
		"pages": 17137,
		"list": [{
				"rating_id": 2,
				"user_id": 68,
				"movie_id": 35423,
				"rating": 3.5,
				"comment": "notbad",
				"create_time": 1472547052000,
				"username": null,
				"movie_name": null
			},
			{
				"rating_id": 3,
				"user_id": 111,
				"movie_id": 35423,
				"rating": 3,
				"comment": "so so",
				"create_time": 1472547052000,
				"username": null,
				"movie_name": null
			},
			{
				"rating_id": 7,
				"user_id": 402,
				"movie_id": 35423,
				"rating": 4,
				"comment": "ok",
				"create_time": 1472547052000,
				"username": null,
				"movie_name": null
			},
			{
				"rating_id": 9,
				"user_id": 547,
				"movie_id": 35423,
				"rating": 0.5,
				"comment": "disgusting",
				"create_time": 1472547052000,
				"username": null,
				"movie_name": null
			}
		],
		"firstPage": 1,
		"prePage": 0,
		"nextPage": 2,
		"lastPage": 8,
		"isFirstPage": true,
		"isLastPage": false,
		"hasPreviousPage": false,
		"hasNextPage": true,
		"navigatePages": 8,
		"navigatepageNums": [1, 2, 3, 4, 5, 6, 7, 8]
	}
}
```


fail
```
{
  "status": 10,
  "msg": "用户未登录,请登录"
}
```

或
```
{
  "status": 1,
  "msg": "没有权限"
}
```
-------

####2.通过评价Id删除评价

/manage/rating/deleteRating.do

> request

```
ratingId

```

> response


success
```
{
	"status": 0,
	"msg": "删除评价操作成功"
}
```
fail
```
```
{
  "status": 10,
  "msg": "用户未登录,请登录"
}
```

或
```
{
  "status": 1,
  "msg": "没有权限"
}
```
```

-------
