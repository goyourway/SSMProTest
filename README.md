# SSMProTest


# 接口

## http://101.200.182.169/Xiaochuang/Test
交互测试
POST

```json
{
   ""
}
```
Return 
```
{
   errMsg: "This is a suessful result!"
}
```

## http://101.200.182.169/Xiaochuang/userLogin
用户登录
POST

```json
{
   "username":'jyk',
   "password":"jyk"

}
```
Return 
```
{
   loggedUser: 
      {
	browsehistory: "none"
	password: "jyk"
	personaltarget: "science,love"
	userid: 1
	username: "jyk"
      }

   userTarget: 
      {
	agoodidea: 3
	i: 6
	love: 6
	the: 3
	tv: 3
      }

}
```

## http://101.200.182.169/Xiaochuang/userRegister
用户注册
POST

```json
{
   "username":'123',
   "password":"jyk"
   //目前测试用，没设置用户名不可重复

}
```
Return 
```
{
   message: "注册成功"
}
```

## http://101.200.182.169/Xiaochuang/selectFiveWorksByDate
查找五个作品的信息（后台根据时间顺序查找）
POST

```json
{
   
   ""
   
}
```
Return 
```
{
   FiveWorksByDate://数组
      [
	0:
	comments: [{commentid: 1, workid: 1, userid: 1, content: "好的电影", commenttime: 1588240180000},…]//作品的评论（数组）
	popular: "8"（作品的热度）
	publicationtime: 1250179200000（作品的出版时间，之后或舍去）
	source: "https://www.iqiyi.com/v_19rrifvp1k.html?vfm=2008_aldbd&fc=828fb30b722f3164&fv=p_02_01"（作品的来源）
	title: "第九区"（作品的标题）
	type: "movie"（作品类型）
	workid: 1（作品的id，主键唯一）
	worktarget: {agoodidea: 1, i: 2, love: 2, the: 1, tv: 1, watching: 1, world: 1, 一串: 1, 中文: 1, 测试: 1}（作品的标签，好几对对应的对象）

	1: 
	comments: [{commentid: 2, workid: 2, userid: 1, content: "好的书籍", commenttime: 1588251834000},…]
	popular: "9"
	publicationtime: 1146412800000
	source: "实体书"
	title: "三体"
	type: "novel"
	workid: 2
	worktarget: {agoodidea: 1, i: 2, love: 2, the: 1, tv: 1, watching: 1, world: 1, 一串: 1, 中文: 1, 测试: 1}
      ]

}
```


## http://101.200.182.169/Xiaochuang/selectWorkByTitle
根据标题、关键字搜索作品信息
POST

```json
{
   
   "title":"九"
   //测试的时候数据表里只有 第九区 一部作品，所以返回作品一个，结构和上面相同

}
```
Return 
```
{
   FiveWorksByDate://数组
      [
	0:
	comments: [{commentid: 1, workid: 1, userid: 1, content: "好的电影", commenttime: 1588240180000},…]//作品的评论（数组）
	popular: "8"（作品的热度）
	publicationtime: 1250179200000（作品的出版时间，之后或舍去）
	source: "https://www.iqiyi.com/v_19rrifvp1k.html?vfm=2008_aldbd&fc=828fb30b722f3164&fv=p_02_01"（作品的来源）
	title: "第九区"（作品的标题）
	type: "movie"（作品类型）
	workid: 1（作品的id，主键唯一）
	worktarget: {agoodidea: 1, i: 2, love: 2, the: 1, tv: 1, watching: 1, world: 1, 一串: 1, 中文: 1, 测试: 1}（作品的标签，好几对对应的对象）
      ]

}
```


## http://101.200.182.169/Xiaochuang/insertComment
给一个作品插入评论
POST

```json
{
   
   "workid":1,
   "userid":1,//这个是用户登录了的id，后面用cookie或session存储吧
   "content":"a movie about science"

}
```
Return 
```
{
  
   message: "评论成功"

}
```


## http://101.200.182.169/Xiaochuang/selectFiveWorksByTargetTextFromUserid
根据用户标签搜索相关作品，前端发送userid即可
POST

```json
{
   
   "userid":1
   //这的userid等前后端差不多了，准备放cookie或session里


}
```
Return 
```
{
  
   WorksByTargetText:
      [
	0:
	comments: [{commentid: 1, workid: 1, userid: 1, content: "好的电影", commenttime: 1588240180000},…]//作品的评论（数组）
	popular: "8"（作品的热度）
	publicationtime: 1250179200000（作品的出版时间，之后或舍去）
	source: "https://www.iqiyi.com/v_19rrifvp1k.html?vfm=2008_aldbd&fc=828fb30b722f3164&fv=p_02_01"（作品的来源）
	title: "第九区"（作品的标题）
	type: "movie"（作品类型）
	workid: 1（作品的id，主键唯一）
	worktarget: {agoodidea: 1, i: 2, love: 2, the: 1, tv: 1, watching: 1, world: 1, 一串: 1, 中文: 1, 测试: 1}（作品的标签，好几对对应的对象）

	1: 
	comments: [{commentid: 2, workid: 2, userid: 1, content: "好的书籍", commenttime: 1588251834000},…]
	popular: "9"
	publicationtime: 1146412800000
	source: "实体书"
	title: "三体"
	type: "novel"
	workid: 2
	worktarget: {agoodidea: 1, i: 2, love: 2, the: 1, tv: 1, watching: 1, world: 1, 一串: 1, 中文: 1, 测试: 1}
      ]
}
```
