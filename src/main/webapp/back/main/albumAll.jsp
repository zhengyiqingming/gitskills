<%@page pageEncoding="utf-8" %>

	<script type="text/javascript">
		var toolbar= [{
			iconCls: 'icon-edit',
			text:'专辑详情',
			handler: function(){
			var selectRow1=$("#album").treegrid("getSelected");
		if(selectRow1!=null){
			if(selectRow1.size==null){
				$("#album_dialog").dialog("open");
				$("#album_form").form("load",selectRow1);
				$("#album_img").prop("src",selectRow1.coverImg);
			}else{
				alert('请选择您要了解的专辑');
			}	
			}else{
				alert('请选择您要了解的专辑');
				}
			}
		},'-',
		{
			iconCls: 'icon-add',
			text:'添加专辑',
			handler: function(){
				$("#addAlbumDialog").dialog("open");
			}
		},'-',
		
		{
			text:'添加章节',
			iconCls: 'icon-add',
			handler: function(){
				
				var row=$("#album").treegrid("getSelected");
				
				if(row==null){
					alert("请选择一行")
				}else{
					if(row.size==null){
						$("#addChapterDialog").dialog("open");
						
						$("#albumId").val(row.id);
						
					}else{
						alert("请选择专辑");
					}
				}
			}
		},'-',
		{	
			
			text:'下载音频',
			iconCls: 'icon-20130406125519344_easyicon_net_16',
			handler: function(){
				var row=$("#album").treegrid("getSelected");
				if(row==null){
					alert("请选择一行")
				}else{
					if(row.author==null){
						location.href="${pageContext.request.contextPath}/chapter/download.do?filename="+row.downloadPath;
					}else{
						alert("请选择音频");
					}
				}
			}
		}]
		
		
		$(function(){
			$("#album").treegrid({
				onDblClickRow:function(row){
					$("#audioDialog").dialog("open");
					$("#audioForm").prop("src","${pageContext.request.contextPath}/chapter/download.do?filename="+row.downloadPath);
				},
				url:"${pageContext.request.contextPath}/album/findAll.do",     
			    idField:'title',    
			    treeField:'title',
			    fitColumns:true,
			    fit:true,
			    toolbar:toolbar,
			    columns:[[    
			        {title:'名称',field:'title',width:120},    
			        {field:'downloadPath',title:'下载路径',width:80},    
			        {field:'size',title:'章节大小',width:60},    
			        {field:'duration',title:'章节时长',width:60}    
			    ]]    
			});
		})
		
		//专辑
		// 通过调用form的submit方法，提交表单
		function safeAlbum(){	
			$("#addAlbumForm").form("submit", {
				url:"${pageContext.request.contextPath }/album/addAlbum.do",// 提交到的资源
				onSubmit: function(){
					return $("#addAlbumForm").form("validate");  // 调用form的validate方法 进行自动验证
				},// 提交前 表单验证
				success:function(){
					// 关对话框
					$("#addAlbumDialog").dialog("close");
					$.messager.show({
						title:"系统提示",
						msg:"保存成功！"
					});
					$("#album").treegrid("load");
				}// 成功后的回调
			});
		}
		
		
		//章节
		// 通过调用form的submit方法，提交表单
		function safeChapter(){	
			$("#addChapterForm").form("submit", {
				url:"${pageContext.request.contextPath }/chapter/addChapter.do",// 提交到的资源
				onSubmit: function(){
					return $("#addChapterForm").form("validate");  // 调用form的validate方法 进行自动验证
				},// 提交前 表单验证
				success:function(){
					// 关对话框
					$("#addChapterDialog").dialog("close");
					$.messager.show({
						title:"系统提示",
						msg:"保存成功！"
					});
					$("#album").treegrid("load");
				}// 成功后的回调
			});
		}
	</script>
	
	<table id="album"></table>
	
	<!-- 专辑的详情展示 -->
	<div id="album_dialog" class="easyui-dialog" title="专辑详情展示" style="width:500px;height:400px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
	    <form id="album_form" method="post">   
		    <div>   
		        <label for="name">专辑名称:</label>   
		        <input class="easyui-validatebox" type="text" id="name" name="title" />   
		    </div>   
		    <div>   
		        <label for="count">专辑集数:</label>   
		        <input class="easyui-validatebox" type="text" name="count" id="count" />   
		    </div>   
		    <div>   
		        <label for="album_img">专辑封面:</label>   
		        <img id="album_img" src=""/>   
		    </div>   
	    
		</form>   
	</div>  
	
	<!-- 添加专辑对话框 --> 
	<div id="addAlbumDialog" class="easyui-dialog" title="添加专辑" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
					safeAlbum();
				}
			},{
				text:'关闭',
				handler:function(){
					$('#addAlbumDialog').dialog('close')
				}
			}]">
	<!-- 添加专辑   -->
   <form id="addAlbumForm" method="post" enctype="multipart/form-data">   
	    <div>   
	        <label for="title">专辑名称:</label>   
	        <input id="title" class="easyui-validatebox" name="title" data-options="required:true" />   
	    <div>   
	    </div>   
	        <label for="score">评分:</label>   
	        <input id="score" class="easyui-numberbox" name="score" data-options="required:true" />   
	    </div> 
	    <div>   
	        <label for="author">作者:</label>   
	        <input id="author" class="easyui-validatebox" name="author" data-options="required:true" />   
	    </div>   
	    <div>   
	        <label for="broadCast">播音:</label>   
	        <input id="broadCast" class="easyui-validatebox" name="broadCast" data-options="required:true" />   
	    </div>   
	    <div>   
	        <label for="brief">简介:</label>   
	        <input id="brief" class="easyui-validatebox" name="brief" data-options="required:true" />   
	    </div>     
	    
			背景图片:
		<input class="easyui-filebox" name="file" style="width:300px">
	</form>

	<!-- 添加章节对话框 --> 
	<div id="addChapterDialog" class="easyui-dialog" title="添加章节" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
					safeChapter();
				}
			},{
				text:'关闭',
				handler:function(){
					$('#addChapterDialog').dialog('close')
				}
			}]">
	<!-- 添加章节   -->
   <form id="addChapterForm" method="post" enctype="multipart/form-data">   
	    <div>   
	        <label for="title">章节名:</label>   
	        <input id="title" class="easyui-validatebox" type="text" name="title" data-options="required:true" />   
	    <div>  
	    <input id="albumId"  type="hidden" name="albumId" value=""/>
		音频文件:
		<input class="easyui-filebox" name="file" style="width:300px">
	</form>
		
	<!-- 播放页面   -->
	<div id="audioDialog" class="easyui-dialog" title="音频播放" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
     	<!-- 播放控件   -->
     	<audio src="" id="audioForm" controls="controls">
     	</audio>
     </div>
	