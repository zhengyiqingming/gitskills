<%@page pageEncoding="utf-8" %>
<script type="text/javascript">
	$(function(){
		$('#guruTreegrid').treegrid({
			
		    url:'${pageContext.request.contextPath}/guru/queryAllGuru.do',    
		    idField:'title',    
		    treeField:'title',
		    fitColumns:true,
		    pagination:true,
		    
		    columns:[[    
		        {field:'title',title:'名字',width:130},    
		        {field:'content',title:'内容下载路径',width:130,align:'right'},    
		        {field:'publishDate',title:'发布时间',width:130}    
		    ]],
		    toolbar: [{
		    	text:'上师详情',
				iconCls: 'icon-tip',
				handler: function(){
					var row=$("#guruTreegrid").treegrid("getSelected");
					if(row==null){
						alert("请选择一行")
					}else{
						if(row.gender!=null){
							$("#guruDialog").dialog("open");
							$("#guruForm").form("load",row);
							$("#guruImg").prop("src","${pageContext.request.contextPath}"+row.headPic);
						}else{
							alert("请选择上师");
						}
					}
				}
			},'-',{
				text:'添加上师',
				iconCls: 'icon-add',
				handler: function(){
					$("#addGuruDialog").dialog("open");
			}
			},'-',{
				text:'添加文章',
				iconCls: 'icon-add',
				handler: function(){
					var row=$("#guruTreegrid").treegrid("getSelected");
					if(row==null){
						alert("请选择一行")
					}else{
						if(row.gender!=null){
							$("#addArticleDialog").dialog("open");
							
							$("#guruId").val(row.id);
							
						}else{
							alert("请选择上师");
						}
					}
			}
			}/* ,'-',{
				text:'下载文章',
				iconCls: 'icon-20130406125519344_easyicon_net_16',
				handler: function(){
					var row=$("#guruTreegrid").treegrid("getSelected");
					if(row==null){
						alert("请选择一行")
					}else{
						if(row.gender==null){
							location.href="${pageContext.request.contextPath}/article/downloadArticle.do?title="+row.title;
						}else{
							alert("请选择文章");
						}
					}
				}
			} */]
		        
		}); 
	});
		// 通过调用form的submit方法，提交表单
		function safeGuru(){	
			$("#addGuruForm").form("submit", {
				url:"${pageContext.request.contextPath }/guru/addGuru.do",// 提交到的资源
				onSubmit: function(){
					return $("#addGuruForm").form("validate");  // 调用form的validate方法 进行自动验证
				},// 提交前 表单验证
				success:function(){
					// 关对话框
					$("#addGuruDialog").dialog("close");
					$.messager.show({
						title:"系统提示",
						msg:"保存成功！"
					});
					$("#guruTreegrid").treegrid("load");
				}// 成功后的回调
			});
		}
		
		// 通过调用form的submit方法，提交表单
		function safeArticle(){	
			$("#addArticleForm").form("submit", {
				url:"${pageContext.request.contextPath }/article/addArticle.do",// 提交到的资源
				onSubmit: function(){
					return $("#addArticleForm").form("validate");  // 调用form的validate方法 进行自动验证
				},// 提交前 表单验证
				success:function(){
					// 关对话框
					$("#addArticleDialog").dialog("close");
					$.messager.show({
						title:"系统提示",
						msg:"保存成功！"
					});
					$("#guruTreegrid").treegrid("load");
				}// 成功后的回调
			});
		}
	
	
	
</script>

<table id="guruTreegrid" > </table>
<!-- 上师详情   -->
<div id="guruDialog" class="easyui-dialog" title="上师详情" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
     <form id="guruForm" class="easyui-form">
     	名称:<input type="text" name="title" ><br/>
     	性别:<input type="text" name="gender" ><br/>
     	头像:<img id="guruImg" src="">   
     </form>
</div>
		<!-- 添加上师对话框 --> 
		<div id="addGuruDialog" class="easyui-dialog" title="添加上师" style="width:400px;height:200px;"   
	        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
					text:'保存',
					handler:function(){
						safeGuru();
					}
				},{
					text:'关闭',
					handler:function(){
						$('#addGuruDialog').dialog('close')
					}
				}]">
	<!-- 添加上师   -->
	   <form id="addGuruForm" method="post" enctype="multipart/form-data">   
		    <div>   
		        <label for="title">上师名字:</label>   
		        <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true" />   
		    <div> 
		    性别:  
		    <select id="status" class="easyui-combobox" name="gender" style="width:200px;">   
			    <option value="男">男</option>   
			    <option value="女">女</option>   
			</select> <br/> 
		    
				上师头像:
			<input class="easyui-filebox" name="file" style="width:300px">
		</form>

	<!-- 添加文章对话框 --> 
		<div id="addArticleDialog" class="easyui-dialog" title="添加文章" style="width:400px;height:200px;"   
	        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
					text:'保存',
					handler:function(){
						safeArticle();
					}
				},{
					text:'关闭',
					handler:function(){
						$('#addArticleDialog').dialog('close')
					}
				}]">
	<!-- 添加文章   -->
	   <form id="addArticleForm" method="post" enctype="multipart/form-data">   
		    
		    
		    
		    <input id="guruId"  type="hidden" name="guruId" value=""/>   
		   
		    
				文章文件:
			<input class="easyui-filebox" name="file" style="width:300px">
		</form>
	