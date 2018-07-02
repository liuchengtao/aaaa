<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>小说搜索</title>
    <script src="/js/echarts.js"></script>
    <script src="/js/worldcloud.js"></script>
    <style type="text/css">
                *{margin:0;padding:0;}
        body{width:100%;background:rgb(240,240,240);}
        a:link{color:rgb(41,31,193);text-decoration:none;}
        a:visited{color:rgb(41,31,193);}
        a:hover{text-decoration:underline;color:rgb(41,31,193);}
        .navitor{width:100%;background:rgb(100,100,100);}
        .navitor .navitor-main{margin-left:20px;}
        .navitor a{display:inline-block;margin:14px 5px;color:rgb(255,255,255);}
        .navitor a:hover{color:rgb(240,240,240);}
        .search{width:100%;text-align:center;padding:20px 0px;margin-top:30px;}
        .search .search-main{}
        .search .search-main .keyword{padding:10px;width:360px;outline:none;}
        .search .search-main button{padding:10px 30px;border:1px solid rgb(200,200,200);background:rgb(100,100,100);color:rgb(255,255,255);}
        .result-list{padding:80px 0px 0px 120px;}
        .result-list .result-item{float:left;clear:both;margin:10px 0px;}
        .result-list .result-item .result-game-item-pic{float:left;margin:0px 10px 0px 0px;}
        .result-list .result-item .result-game-item-pic img{width:110px;height:150px;}
        .result-list .result-item .result-game-item-detail{width:100%;font-size:14px;}
        .result-list .result-item .result-game-item-detail .result-item-title{font-size:18px;}
        .result-list .result-item .result-game-item-detail .result-item-title a{font-size:18px;text-decoration:none;color:rgb(50,50,50);font-weight:normal;}
        .result-list .result-item .result-game-item-detail .result-item-title a:visited{}
        .result-list .result-item .result-game-item-detail .result-item-title a:hover{}
        .result-list .result-item .result-game-item-detail .result-game-item-info-tag{line-height:22px;}
        .search-result-page{width:100%;float:left;clear:both;text-align:center;padding:20px 0px;}
        .search-result-page .search-result-page-main{}
        .search-result-page .search-result-page-main a{display:inline-block;padding:6px 10px;border:1px solid rgb(100,100,100);font-size:14px;margin:0px 3px;background:rgb(100,100,100);color:rgb(255,255,255);}
        .search-result-page .search-result-page-main a:visited{}
        .search-result-page .search-result-page-main a:hover{}
    </style>
     <%@include file="/resource.jsp"%>
       <script>
    $(function(){
        echartsCloud();//初始化echarts图
    })
function echartsCloud(){
    // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('novel'));

    myChart.setOption({
        title: {
            text: '知识就是力量'
        },
        tooltip: {
        show: true
    },
        series: [{
            type : 'wordCloud',  //类型为字符云
                shape:'smooth',  //平滑
              gridSize :0, //网格尺寸 每个对象的大小
            //    size : ['80%','80%'],
             size : ['100%','100%'],
                sizeRange : [ 20, 30 ],   //大小
             //   rotationRange : [ 0, 100 ], //旋转范围
                textStyle : {  
                    normal : {
                        fontFamily:'sans-serif',
                        color : function() {  
                            return 'rgb('  
                                    + [ Math.round(Math.random() * 160),  
                                            Math.round(Math.random() * 160),  
                                            Math.round(Math.random() * 160) ]  
                                            .join(',') + ')';  
                        }  
                    },  
                    emphasis : {  
                        shadowBlur : 5,  //阴影距离
                        shadowColor : '#333'  //阴影颜色
                    }  
                },
                data:[],
        }]
    });
    // 异步加载数据
    $.ajax({
           url:'/novel/loadList.do',
				        method:'get',
				        async : false,
				      //  data:{id:record[0].id},
				        success:function(data){
				             // 填入数据
           myChart.setOption({
            series: [{
                data: data.dataList,
            }]
        });
				         }
    });
}  


    </script>
</head>
<body>
<div class="navitor">
        <div class="navitor-main">
		<a>我们的征途是星辰大海</a>
        </div>
    </div>
    <div id="novel" style="width:1200px;height:400px;"></div>
<!--复制到这里-->
    <div class="search">
        <div class="search-main">
            <input type="text" name="keyword" id="keyword" class="keyword" value="请输入书名或作者搜索" onblur="if (value ==''){value='请输入书名或作者搜索'}" onfocus="if (value =='请输入书名或作者搜索'){value =''}" />
            <button type="button" name="search-submit" id="search-submit">搜索小说</button>
        </div>
    </div>
    <div class="result-list" id="list">
         <div class="result-item result-game-item">

            <div class="result-game-item-pic">
              <c:if test="${novel.id>0}">
                <img alt="暂无图片" src=" ${novel.pic}">
                </c:if>
            </div>
            <div class="result-game-item-detail">
                <h3 class="result-item-title result-game-item-title">
                    ${novel.name}
                </h3>
                <p class="result-game-item-desc">${novel.abstract_n}</p>
                <div class="result-game-item-info">

                    <p class="result-game-item-info-tag">
                        <span class="result-game-item-info-tag-title preBold"></span>
                        <span>
                            ${novel.user_no}
                        </span>
                    </p>
                    <p class="result-game-item-info-tag">
                    <c:if test="${novel.id>0}">
                        <span class="result-game-item-info-tag-title preBold">类型：</span>
                        </c:if>
                        <span class="result-game-item-info-tag-title">${novel.tag}</span>
                    </p>

                    <p class="result-game-item-info-tag">
                        <span class="result-game-item-info-tag-title preBold"></span>
                        <span class="result-game-item-info-tag-title">${novel.last_time}</span>
                    </p>

                    <p class="result-game-item-info-tag">
                        <c:if test="${novel.id>0}">
    <a href="newDown?action=downNovel&novelId=${novel.id}&novelName=${novel.name}">
                             下载</a>
    </c:if>
     <c:if test="${novel.id==0}">
     <h1>该小说暂未收入！！</h1>
                          
    </c:if>     
                       
                    </p>
                </div>
            </div>
        </div>

    </div>
    <div class="search-result-page">
        <div class="search-result-page-main">
                        
        </div>
    </div>
<script type="text/javascript">
        $(function () {
            $('#search-submit').on('click', function () {
            
           // alert("aa");
	    var url='/novel/query.do?keyword={key}'.replace('{key}',$('#keyword').val());
	     
	           window.location.href=url;
                //window.location.href ="down" ;
             /*  var text=   $.ajax({
                   url:url,
                   method:'get',
    		       async : false,
                   
                
                }); */
          // $("#list").html("ok");
            });
             
        });
    </script>
    <script>footer();</script>
</body>
</html>