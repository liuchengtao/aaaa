var YDataGrid=function(config){
	var config=config || {}
	var dataGrid=config.dataGrid || {}
	//console.info(config.dataGird.title);
	//Grid DataList
	var Grid = $('#data-list');
	//Form
	var Form = {
				list:$("#listForm"),
				edit:$("#editForm"),
				}
	//窗口 add edit按钮 对应的弹出窗口
	var Win={
			edit:$("#edit-win")
	}
	//Action 按钮对应的url
	var ActionUrl={
			'save': 'save.do',
			'getId': 'getId.do',
			'remove':'delete.do'
	}
	//处理函数 
	var Handler={
			//刷新Grid 数据
			refresh:function(param){
				Grid.datagrid('reload',param);
			},
			add:function(){
				Win.edit.dialog('open');
				Form.edit.resetForm();  //resetForm() 更新错误信息
				
			},
			edit:function(){
				var records=GUtil.getCheckedRows();
				if(GUtil.checkSelectOne(records)){
					var data ={};
					var idKey = dataGrid.idField || 'id'; //主键名称
 					data[idKey] = (records[0][idKey]);
 			//		console.log(records);   //选中的所有记录
 				//	console.log(records[0]);    //选中的所有记录中的第一条
 				//	console.log(records[0][idKey]); //选中的所有记录中的第一条 idKey 对应的值
 				//	console.log(data[idKey]);   //值为idKey 对应的值
 				//	console.log(data);   //data  是一个Object idKey:值
					YiYa.getById(ActionUrl.getId,data,function(result){
						Form.edit.form('load',result.data);
						Win.edit.dialog('open'); 
						//回调函数
						
					});
				}
				
			},
			remove:function(){
				var records=GUtil.getCheckedRows();
				//有选中的行就行
				if(GUtil.checkSelect(records)){
					$.messager.confirm('Confirm','Are you sure you want to delete record?',
					function(r){
						if(r){
							var idKey = dataGrid.idField || 'id'; //主键名称
							//序列化字段
			var  data = $("input[name='"+idKey+"']", Form.list ).fieldSerialize(); 
			YiYa.deleteForm(ActionUrl.remove,data, function(result) {
				Handler.refresh();
			});
						}
					});
				}
			},
			save:function(){
				if(Form.edit.form('validate')){
					Form.edit.attr('action',ActionUrl.save);
					YiYa.saveForm(Form.edit,function(data){
						 Win.edit.dialog('close');
						 Handler.refresh();
					     Form.edit.resetForm();
					     //回调函数
					     if(data){
					    	 YiYa.confirm('title',data.msg,null)
					     }
						
				});
					
				}
			},
			close:function(){
				$.messager.confirm('Confirm','Are you sure you want close Window?',function(r){
					if(r){
						Win.edit.dialog('close');
				     	//回调函数
					}
					
				});
			}
			
			
	}
	//按钮控制 btnType 用来控制按钮是否显示,后台根据授权控制是否显示
	var bar_add ={	
					id:'btnadd',
					text:'Add',
					iconCls:'icon-add',
					btnType:'add',
					handler: Handler.add
				 };
	var bar_edit = {
						id:'btnedit',
						text:'Edit',
						iconCls:'icon-edit',
						btnType:'edit',
						handler: Handler.edit
					};
	var bar_remove = { id:'btnremove',
					text:'Remove',
					iconCls:'icon-remove',
					btnType:'remove',
					handler:Handler.remove
				   };
	var toolbarConfig = [bar_add,bar_edit,bar_remove];
	var getToolBar=function(){
		var tbars=[];
		if(bbb!=undefined){
		console.info('length'+bbb.length);
		}
		/*if(bbb!=undefined){
			for(var i=0;i<bbb.length;i++){
				var bar=bbb[i];
				if(!bar){
					continue;
				}
				if(bar.btnType='add'){
					tbars.push({id:bar.id || bar_add.id,text:bar.text || bar_add.text ,iconCls: bar.iconCls || bar_add.iconCls, btnType: bar.btnType || bar_add.btnType,handler:bar.handler || bar_add.handler});
					continue;
				}
				if(bar.btnType=='edit'){
					tbars.push({id:bar.id || bar_edit.id,text:bar.text || bar_edit.text ,iconCls: bar.iconCls || bar_edit.iconCls,btnType: bar.btnType || bar_edit.btnType,handler:bar.handler || bar_edit.handler});
					continue;
				}
				if(bar.btnType=='remove'){
					tbars.push({id:bar.id || bar_remove.id,text:bar.text || bar_remove.text ,iconCls: bar.iconCls || bar_remove.iconCls,btnType: bar.btnType || bar_remove.btnType,handler:bar.handler || bar_remove.handler});
					continue;
				}
				tbars.push({id:bar.id,text:bar.text,iconCls:bar.iconCls,btnType: bar.btnType,handler:bar.handler,disabled:bar.disabled});
			
				
			}
			
		}else{*/
			tbars = toolbarConfig;
		//}
		return tbars;
	}
	//DataGrid工具类
	var  GUtil={
			getCheckedRows : function(){
				return Grid.datagrid('getChecked');			
			},
			checkSelect:function(rows){         //检查grid是否有勾选的行, 有返回 true,没有返回false
				var records =  rows;
				if(records && records.length > 0){
					return true;
				}
				YiYa.alert('警告','未选中记录.','warning');  
				return false;
				
			},	
			checkSelectOne:function(rows){  //检查grid是否只勾选了一行,是返回 true,否返回true
				var records=rows;
			if(!GUtil.checkSelect(rows)){
				return false;
			}
			if(records.length==1){
				return true;
			}
			YiYa.alert('警告','只能选中一行.','warning');  
			return false;
				
			}
			
			
	}
	
	
	//初始化表格
	var initGrid=function(){
		var dataconfig={
				title: dataGrid.title || 'Data List',
				iconCls: dataGrid.iconCls || 'icon-save',
				height: dataGrid.height || 365,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				remoteSort: true,
				pagination:true,
				rownumbers:true,
				singleSelect:false,
				checkOnSelect:false,
				selectOnCheck:false,
				url: dataGrid.url,
				method: dataGrid.method || 'post',
				loadMsg: dataGrid.loadMsg || 'Loading in ...',
				idField: dataGrid.idField,
				columns: dataGrid.columns,
				onLoadSuccess: dataGrid.onLoadSuccess || function(){
					Grid.datagrid('unselectAll');
					Grid.datagrid('uncheckAll');
				},
				onSelect:function(rowIndex, rowData){
					//选择一行
					var rows = Grid.datagrid('getRows');
					$.each(rows,function(i){
						if(i != rowIndex){
							Grid.datagrid('uncheckRow',i);
							Grid.datagrid('unselectRow',i);
						}
					})
					Grid.datagrid('checkRow',rowIndex);
				}
		}
		Grid.datagrid(dataconfig);
	};
	//初始化Grid按钮 按钮控制
	var initTbar=function(){
	//	tbars=toolbarConfig ;
	//	tbars=getToolBar();
	//	tbars=dataGrid.toobar;
	//	Grid.datagrid({'toolbar':tbars});
		
	};
	//初始化Grid编辑框的按钮
	var initWin=function(){
		if(Win.edit && Win.edit[0]){
			//判断页面是否设置buttons，如果没有设置默认按钮
			var btns = Win.edit.attr("buttons");
			if(!btns){
				//设置 保存,关闭按钮
				Win.edit.dialog({
					buttons:[
						{
							text:'Save',
							handler:Handler.save
						},{
							text:'Close',
							handler:Handler.close
						}
					]
				});
			}
			
		}
		
	};
	//this 返回属性
	this.grid = Grid;
	this.handler = Handler;
	this.init=function(){
		 initGrid();
		 initTbar();
		 initWin();
	};
	return this;
}