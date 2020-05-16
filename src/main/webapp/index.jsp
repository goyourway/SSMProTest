<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>

    <link rel="stylesheet" href="css.css">
    <link rel="stylesheet" href="bootstrap.css">
    <script src="jquery-3.5.0.js"></script>

    <script type="text/javascript">
        $(function(){
            //按钮单击时执行
            $("#testButton").click(function(){
                $.ajax({
                    type:'post',
                    url:'Test',
                    dataType:"json",//注意使用的是打他dataType，而不是Content-Type
                    async:true,
                    data:'',
                    success:function(res){
                        console.log("returned:",res.errMsg)
                    }

                 });
             });
        });



        $(function(){
            //按钮单击时执行
            $("#userLogin").click(function(){
                $.ajax({
                    type:'post',
                    url:'userLogin',
                    dataType:"json",//注意使用的是打他dataType，而不是Content-Type
                    async:true,
                    data:{
                        "username":'jyk',
                        "password":"jyk"},

                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });


        $(function(){
            //按钮单击时执行
            $("#userRegister").click(function(){
                $.ajax({
                    type:'post',
                    url:'userRegister',
                    dataType:"json",//注意使用的是打他dataType，而不是Content-Type
                    async:true,
                    data:{
                        "username":'123',
                        "password":"jyk"},

                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });


        $(function(){
            //按钮单击时执行
            $("#selectFiveWorksByDate").click(function(){
                $.ajax({
                    type:'post',
                    url:'selectFiveWorksByDate',
                    dataType:"json",//注意使用的是打他dataType，而不是Content-Type
                    async:true,
                    data:"",

                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });


        $(function(){
            //按钮单击时执行
            $("#selectWorkByTitle").click(function(){
                $.ajax({
                    type:'post',
                    url:'selectWorkByTitle',
                    dataType:"json",//注意使用的是打他dataType，而不是Content-Type
                    async:true,
                    data:{"title":"九"},

                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });


        $(function(){
            //按钮单击时执行
            $("#insertComment").click(function(){
                $.ajax({
                    type:'post',
                    url:'insertComment',
                    dataType:"json",//注意使用的是打他dataType，而不是Content-Type
                    async:true,
                    data:{
                        "workid":1,
                        "userid":1,
                        "content":"a movie about science"
                    },


                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });


        $(function(){
            //按钮单击时执行
            $("#dockertest").click(function(){
                $.ajax({
                    type:'post',
                    url:'http://101.200.182.169:5000/api/predict/',
                    dataType:"json",//dataType，而不是Content-Type
                    async:true,
                    data:{
                        "text":"str",

                    },


                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });


        $(function(){
            //按钮单击时执行
            $("#predict").click(function(){
                $.ajax({
                    type:'post',
                    url:'api/predict',
                    dataType:"json",//dataType，而不是Content-Type
                    async:true,
                    data:{
                        "text":"i love the world"
                    },


                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });

        $(function(){
            //按钮单击时执行
            $("#wordcount").click(function(){
                $.ajax({
                    type:'post',
                    //注意下方的url是发给后端的，不是发给docker的，真正的请求由后端转发给docker
                    url:'api/wordcount',
                    dataType:"json",//dataType，而不是Content-Type
                    async:true,
                    traditional:true,
                    data:{
                        "top":5,
                        "texts":
                            [
                                "i love the world",
                                "i love watching tv",
                                "agoodidea",
                                "这是一串中文测试"
                            ]
                    },


                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });


        $(function(){
            //按钮单击时执行
            $("#selectFiveWorksByTargetTextFromUserid").click(function(){
                $.ajax({
                    type:'post',
                    //注意下方的url是发给后端的，不是发给docker的，真正的请求由后端转发给docker
                    url:'selectFiveWorksByTargetTextFromUserid',
                    dataType:"json",//dataType，而不是Content-Type
                    async:true,
                    traditional:true,
                    data:{
                       "userid":1
                    },


                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });

        $(function(){
            //按钮单击时执行
            $("#insertWork").click(function(){
                $.ajax({
                    type:'post',
                    //注意下方的url是发给后端的，不是发给docker的，真正的请求由后端转发给docker
                    url:'insertWork',
                    dataType:"json",//dataType，而不是Content-Type
                    async:true,
                    traditional:true,
                    data:{
                        "title":$("#insertWork_title").val(),
                        "type":$("#insertWork_type").val(),
                        "texts":$("#insertWork_texts").val()
                    },


                    success:function(res){
                        console.log("returned:",res)
                    }

                });
            });
        });
    </script>
</head>
<body>


    <button id="testButton" type="button">测试<button/>


    <button id="userLogin" type="button">userLogin</button>

    <button id="userRegister" type="button">userRegister</button>

    <button id="selectFiveWorksByDate" type="button">selectFiveWorksByDate</button>


     <button id="selectWorkByTitle" type="button">selectWorkByTitle</button>

        <button id="insertComment" type="button">insertComment</button>


        <button id="predict" type="button">predict(前端请求后端，后端再发送post给docker)</button>

        <button id="wordcount" type="button">wordcount(前端请求后端，后端再发送post给docker)</button>

        <button id="selectFiveWorksByTargetTextFromUserid" type="button">selectFiveWorksByTargetTextFromUserid(个性化推荐)</button>

        <div class="insertWork_div">
            title(for example:"第九区")
            <div><input id="insertWork_title" type="text" placeholder="click to input title"></div>
            type(for example:"movie"、"novel")
            <div><input id="insertWork_type" type="text" placeholder="click to input type"></div>
            texts(和作品有关的文本，台词歌词等，"这是一部好看的电影，是科幻类型的....")
            <div><input id="insertWork_texts" type="text" placeholder="click to input texts"></div>
            <button id="insertWork" type="button">insertWork(插入作品)</button>
        </div>



            <div class="parent">
        <h1>1</h1>
    </div>




</body>

</html>
