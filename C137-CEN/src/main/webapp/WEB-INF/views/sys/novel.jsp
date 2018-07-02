<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <%@include file="/resource.jsp" %>
  </head>
	<body>
<div class="warp easyui-panel" data-options="border:false">
	<!-- Search panel start -->
 	 <div class="easyui-panel ui-search-panel" title="Search box" data-options="striped: true,collapsible:true,iconCls:'icon-search'">  
 	 <form id="searchForm">
 	 	<p class="ui-fields">
 	 	    <label class="ui-label">小说名:</label><input name="name" class="easyui-box ui-text" style="width:100px;">
        </p>
        <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">Search</a>
      </form>  
     </div> 
     <!--  Search panel end -->
     <!-- DataList  -->
     <form id="listForm" method="post">
     <table id="data-list"></table>
     </form>
  
</div>

<div id="progressDlg" class="easyui-dialog" title="执行进度" style="width: 600px;hight:400px;" data-options="iconCls:'icon-save',closed:true,resizable:true,modal:true,collapsible:true">
	    <div style="padding: 10px 60px 20px 60px">
	      <div id="p" class="easyui-progressbar" style="width:400px;"></div>
	    </div>
	</div>



<script type="text/javascript" src="/js/ux/YDataGrid.js"></script>
<script type="text/javascript" src="/js/ux/novel.js"></script>
  </body>
</html>
