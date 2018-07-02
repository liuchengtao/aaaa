$package('YiYa.novel');
YiYa.novel = function(){
	var _box = null;
	var _this = {
			toSave:function(){
				YiYa.progress('Please waiting','Loading...');
				 //  var selectedRows=$("#data-list").datagrid('getSelections');
				//   console.log(selectedRows);
				 //   if(selectedRows.length==0){
				//	$.messager.alert("系统提示","请选择要解析的小说！");
				//	return;
				//}
				 // var row=selectedRows[0];
				var record=_box.utils.getCheckedRows();
				        
					console.log(record[0].id);
					$.ajax({
				        url:'getContents.do',
				        method:'get',
				        async : false,
				        data:{id:record[0].id},
				        success:function(data){
				          //  var timerId;
				        	YiYa.closeProgress();
				          $.messager.alert("系统提示",data.msg);
				         _box.grid.datagrid('reload',null);
				        //  $('#progressDlg').dialog('open');
				    	//  $('#p').progressbar({  
				    	////      value : 0,          //设置进度条值 默认0  
				    	//      text : '{value}%'  //设置进度条百分比模板 默认 {value}%  
				    	//  }); 
				    	// timerId = window.setInterval(getCheckProgress,1000);
				         }
				      
				        
				    });
					
				
				
				
			},
		toList:function(){
			$.ajax({
		        url:'save.do',
		        method:'get',
		        async : false,
		    //    data:{ids:ids,urls:urls,action:'getContents'},
		        success:function(data){
		          $.messager.alert("系统提示",data.msg);
		         }
		        
		    });
		},
		initForm:function(){
			//修改密码
			_this.editPwdWin().find("#btn-pwd-submit").click(function(){
				_this.savePwd();
			});
			_this.editPwdWin().find("#btn-pwd-close").click(function(){	
				$.messager.confirm('Confirm','Are you sure you want close Window?',function(r){  
				    if (r){  
				     	_this.editPwdWin().dialog('close');
				    }  
				});
			});
		},
		config:{
			event:{
				add:function(){
					_this.clearTreeData();
					_box.handler.add();
				}
			},
  			dataGrid:{
  				title:'小说列表',
	   			url:'dataList.do',
	   			columns:[[
						{field:'id',checkbox:true},
						{field:'name',title:'小说名',width:120,sortable:true},
						{field:'url',title:'小说URL',width:200,sortable:true},
						{field:'tag',title:'分类',width:80,align:'center',sortable:true,styler:function(value,row,index){
							if(value == '都市言情'){
							  return 'color:red;';  
							}
						},
					//	formatter:function(value,row,index){
					//		if(value == 0){
					//			return "可用";
					//		}
					//		if(value == 1){
					//			return "禁用";
					//		}
						},
						{field:'user_no',title:'作者',width:120,sortable:true},
						{field:'pic',title:'图片路径',align:'right',width:200,sortable:true},
						{field:'create_time',title:'create_time',width:120,sortable:true},
						{field:'update_time',title:'update_time',width:120,sortable:true},
						{field:'subCount',title:'本地章节数',width:120,sortable:true}
				]],
				toolbar:[
					{id:'btnadd',text:'新增小说',btnType:'add',
						iconCls:'icon-add',
						handler:function(){
							_this.toList();
						}},
					{id:'btnedit',text:'更新小说',btnType:'add',
						iconCls:'icon-add',
						handler:function(){
							_this.toSave();
						}}
					
				]
			}
		},
		init:function(){
			_box = new YDataGrid(_this.config); 
			_box.init();
		}
	}
	return _this;
}();

$(function(){
	YiYa.novel.init();
});		