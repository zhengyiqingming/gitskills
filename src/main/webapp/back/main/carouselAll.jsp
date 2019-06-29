<%@page pageEncoding="utf-8" %>

	<script type="text/javascript">
		
		$(function(){
			$("#carDatagrid").edatagrid({ 
				fit:true,
				width:500, 
				height:250, 
				remoteSort:false, 
				singleSelect:true, 
				nowrap:false,
				pagination:true,    //以下三个是分页用的属性
				pageSize:3,
				pageList:[3,5,10], 
				fitColumns:true, 
				toolbar:"#carBtns",
				updateUrl:"${pageContext.request.contextPath}/carousel/modifyCarousel.do",
				url:"${pageContext.request.contextPath}/carousel/queryBy.do", 
				columns:[[ 
					
					{field:"title",title:"名字",width:100,align:"center",sortable:true}, 
					{field:"status",title:"状态",width:80,align:"center",sortable:true,editor:{
						type:"text",
						options:{
							required:true
						}
					}}, 
					{field:"imgPath",title:"路径",width:80,align:"center",sortable:true}, 
					{field:"date",title:"时间",width:150,align:"center",sortable:true}, 
					
				]], 
				view: detailview, 
				detailFormatter: function(rowIndex, rowData){ 
					return '<table><tr>' + 
					'<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath }/' + rowData.imgPath + '" style="height:50px;"></td>' + 
					'<td style="border:0">' + 
					'<p>路径: ' + rowData.imgPath + '</p>' + 
					'<p>说明: ' + rowData.intro + '</p>' +
					'<p>是否显示: ' + rowData.status + '</p>' + 
					'</td>' + 
					'</tr></table>'; 
				} 
			});
			//初始化添加按钮
			$("#addCarBtn").linkbutton({
				iconCls:"icon-add",
				
				onClick:function(){
					$("#addCarDialog").dialog("open")
				}
			});
			
			//初始化修改按钮
			$("#editCarBtn").linkbutton({
				iconCls:'icon-edit',
				
				onClick:function(){
					var row=$("#carDatagrid").edatagrid("getSelected");
					if(row!=null){
						var index=$("#carDatagrid").edatagrid("getRowIndex",row);
						$("#carDatagrid").edatagrid("editRow",index);
					}else{
						alert("请先选中行")
					}
				}
			});
			
			//初始化删除按钮
			$("#delCarBtn").linkbutton({
				iconCls:'icon-01',
				
				onClick:function(){
					var row=$("#carDatagrid").edatagrid("getSelected");
					if(row!=null){
						$.get(
							"${pageContext.request.contextPath }/carousel/removeCarousel.do",
							"id="+row.id,
							function(){
								$.messager.show({
									title:"系统提示",
									msg:"删除成功！"
								});
								$("#carDatagrid").edatagrid("load");
							}
							)
					}else{
						alert("请先选中行")
					}
				}
			});
			//初始化保存按钮
			$("#saveCarBtn").linkbutton({
				iconCls:'icon-attach',
				
				onClick:function(){
					$("#carDatagrid").edatagrid("saveRow");
				}
			});
			
		});
			// 通过调用form的submit方法，提交表单
		function safeCar(){	
			$("#addCarForm").form("submit", {
				url:"${pageContext.request.contextPath }/carousel/addCarousel.do",// 提交到的资源
				onSubmit: function(){
					return $("#addCarForm").form("validate");  // 调用form的validate方法 进行自动验证
				},// 提交前 表单验证
				success:function(){
					// 关对话框
					$("#addCarDialog").dialog("close");
					$.messager.show({
						title:"系统提示",
						msg:"保存成功！"
					});
					$("#carDatagrid").datagrid("load");
				}// 成功后的回调
			});
		}
		
		
	</script>


	<table id="carDatagrid" >
	</table>
	
	
	<!-- 添加轮播图对话框 --> 
	<div id="addCarDialog" class="easyui-dialog" title="添加轮播图" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
					safeCar();
				}
			},{
				text:'关闭',
				handler:function(){
					$('#addCarDialog').dialog('close')
				}
			}]">
         
	  <!-- 添加轮播图表单 --> 
	   <form id="addCarForm" method="post" enctype="multipart/form-data">   
		    <div>   
		        <label for="title">图片名:</label>   
		        <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true" />   
		    </div>   
		    <div>   
		        <label for="intro">说明:</label>   
		        <input id="intro" class="easyui-validatebox" type="text" name="intro" data-options="required:true" />   
		    </div>   
		    	是否展示:
		    <select id="status" class="easyui-combobox" name="status" style="width:200px;">   
			    <option value=y>展示</option>   
			    <option value=n>不展示</option>   
			</select> <br/> 
				上传图片:
			<input class="easyui-filebox" name="file" style="width:300px">
			
		</form>  
	</div>   
	<div id="editCarDialog"></div>  
    
	<div id="carBtns">
		<a id="addCarBtn">添加</a>
		<a id="editCarBtn">修改</a>
		<a id="delCarBtn">删除</a>
		<a id="saveCarBtn">保存</a>
	</div>