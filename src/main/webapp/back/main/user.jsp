<%@page pageEncoding="utf-8" %>

<script>

    $(function () {
    $("#user").edatagrid({
        idField:"id",
        fit:true,
        fitColumns:true,//横向自适应
        pagination:true,    //以下三个是分页用的属性
		pageSize:3,
		pageList:[3,5,10],
        url:"${pageContext.request.contextPath}/user/queryBy.do",
        updateUrl:"${pageContext.request.contextPath}/user/modifyUser.do",
        destroyUrl:"${pageContext.request.contextPath}/user/removeUser.do",
        toolbar: [{
            iconCls: 'icon-add',
            text:'添加',
            handler: function(){
                $("#openUserSaveDialog").dialog('open');

            }
        },'-',{
            iconCls: 'icon-save',
            text:'保存',
            handler: function(){
               $("#user").edatagrid('saveRow');
            }
        },'-',{
            iconCls: 'icon-01',
            text:'删除',
            handler: function(){
                var row = $("#user").datagrid('getSelected');
                if(row==null){
                    $.messager.alert('我的消息','选择一行删除！','info');
                }else{
                    $('#user').edatagrid('destroyRow');
                }
            }
        },'-',{
            iconCls: 'icon-1012333',
            text:'自动刷新',
            handler: function(){
                $("#user").edatagrid('reload');
            }
        },'-',{
            iconCls:'icon-user',
			text:'导出excel',
			handler:function(){
                location.href= "${pageContext.request.contextPath}/user/download";
			}
		}],
        columns:[[ 
					{field:"phoneNum",title:"电话",width:100,align:"center",sortable:true},
					{field:"username",title:"用户名",width:100,align:"center",sortable:true},
					{field:"password",title:"密码",width:100,align:"center",sortable:true,editor:{
						type:"text",
						options:{
							required:true
						}
					}},
					{field:"salt",title:"加盐",width:100,align:"center",sortable:true},
					{field:"dharmName",title:"法名",width:100,align:"center",sortable:true},
					{field:"gender",title:"性别",width:100,align:"center",sortable:true},
					{field:"headPic",title:"头像路径",width:100,align:"center",sortable:true}, 
					{field:"province",title:"省",width:100,align:"center",sortable:true}, 
					{field:"city",title:"市",width:100,align:"center",sortable:true}, 
					{field:"sign",title:"标志",width:100,align:"center",sortable:true}, 
					{field:"status",title:"状态",width:80,align:"center",sortable:true,editor:{
						type:"text",
						options:{
							required:true
						}
					}},
					{field:"date",title:"时间",width:150,align:"center",sortable:true}, 
					
				]],
		view: detailview, 
		detailFormatter: function(rowIndex, rowData){ 
			return '<table><tr>' + 
			'<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath }/' + rowData.headPic + '" style="height:150px;"></td>' + 
			'<td style="border:0">' + 
			'<p>电话: ' + rowData.phoneNum + '</p>' + 
			'<p>用户名: ' + rowData.username + '</p>' + 
			'<p>密码: ' + rowData.password + '</p>' + 
			'<p>加盐: ' + rowData.salt + '</p>' + 
			'<p>法名: ' + rowData.dharmName + '</p>' + 
			'<p>性别: ' + rowData.gender + '</p>' + 
			'<p>头像路径: ' + rowData.headPic + '</p>' + 
			'<p>省: ' + rowData.province + '</p>' +
			'<p>市: ' + rowData.city + '</p>' +
			'<p>标志: ' + rowData.sign + '</p>' +
			'<p>状态: ' + rowData.status + '</p>' + 
			'</td>' + 
			'</tr></table>'; 
		}

     });

    /*用户提交数据出*/

        $('#btn').linkbutton({
            iconCls: 'icon-add',
            iconAlign:'bottom',
            onClick:function () {
                var rowkey = $("#poi").combotree('getText');
                var c = "";
                var fiedvalue=$("#poi").combotree('getValues');
               $.each(fiedvalue,function (idx,row) {
                   if(fiedvalue.length!=idx+1){
                        c+=row+",";
                   }else{
                       c+=row;
                   }
               })
                console.log(c);
               $("#userform").form('submit',{
                   url:'/spring_cmfz/user/customerExport',
                   queryParams:{
                       rowkey:rowkey,
                       fiedvalue:c,
                   }
               })
            }
        });
    });
	// 通过调用form的submit方法，提交表单
	function openUserSaveDialogsubmit(){	
		$("#userSaveInform1").form("submit", {
			url:"${pageContext.request.contextPath }/user/addUser.do",// 提交到的资源
			onSubmit: function(){
				return $("#userSaveInform1").form("validate");  // 调用form的validate方法 进行自动验证
			},// 提交前 表单验证
			success:function(){
				// 关对话框
				$("#openUserSaveDialog").dialog("close");
				$.messager.show({
					title:"系统提示",
					msg:"保存成功！"
				});
				$("#user").treegrid("load");
			}// 成功后的回调
		});
	}
  

</script>


<table id="user" class="easyui-edatagrid"></table>

<!-- 添加用户 -->
<div id="openUserSaveDialog" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'提交',
				handler:function(){
				   openUserSaveDialogsubmit();
				}
			},{
				text:'关闭',
				handler:function(){
				  $('#openUserSaveDialog').dialog('close')
				}
			}]">

    <form id="userSaveInform1" method="post" enctype="multipart/form-data">

		<div>   
	        <label for="phoneNum">电话:</label>   
	        <input id="phoneNum" class="easyui-validatebox" type="text" name="phoneNum" data-options="required:true" />   
	    </div>   
	    <div>   
	        <label for="username">用户名:</label>   
	        <input id="username" class="easyui-validatebox" type="text" name="username" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="password">密码:</label>   
	        <input id="password" class="easyui-validatebox" type="text" name="password" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="salt">加盐:</label>   
	        <input id="salt" class="easyui-validatebox" type="text" name="salt" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="dharmName">法名:</label>   
	        <input id="dharmName" class="easyui-validatebox" type="text" name="dharmName" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="gender">性别:</label>   
	        <input id="gender" class="easyui-validatebox" type="text" name="gender" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="province">省:</label>   
	        <input id="province" class="easyui-validatebox" type="text" name="province" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="city">市:</label>   
	        <input id="city" class="easyui-validatebox" type="text" name="city" data-options="required:true" />   
	    </div>
	    <div>   
	        <label for="sign">标志:</label>   
	        <input id="sign" class="easyui-validatebox" type="text" name="sign" data-options="required:true" />   
	    </div>    
	    	是否展示:
	    <select id="status" class="easyui-combobox" name="status" style="width:200px;">   
		    <option value=y>展示</option>   
		    <option value=n>不展示</option>   
		</select> <br/> 
			上传图片:
		<input class="easyui-filebox" name="file" style="width:600px">
    </form>

</div>














<!-- <div id="saveuser" class="easyui-dialog" title="My Dialog" style="width:400px;height:600px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">

    <select id="poi" class="easyui-combotree" style="width:200px;"
            data-options="url:'#',required:true,multiple:true,checkbox:true,onlyLeafCheck:true,data: [{
		text: '自定义属性',
		state: 'true',
		children: [{
		    id:'phoneNum',
			text: '电话号'
		},{
		    id:'username',
			text: '用户名'
		},{
		    id:'password',
			text: '密码'
		},{
		    id:'salt',
			text: '加盐'
		},{
		    id:'dharmName',
			text: '法名'
		},{
		    id:'gender',
			text: '性别'
		},{
		    id:'province',
			text: '省'
		},{
		    id:'city',
			text: '城市'
		},{
		    id:'sign',
			text: '标志'
		},{
		    id:'status',
			text: '状态'
		},{
		    id:'date',
			text: '创建时间'
		}]
	}]
">

    </select>
    <form id="userform" method="post">
        <div><a id="btn" href="#">提交</a></div>
    </form>


</div> -->

<!-- <div id="xxxxx" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
           data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'提交',
				handler:function(){
				submit();
				}
			},{
				text:'关闭',
				handler:function(){
				   close();
				}
			}]">

    <form id="ffff" method="post" enctype="multipart/form-data">

        <div>
            <label for="xx">上文:</label>
            <input id="xx" type="file" name="xxzz" style="width:300px">
        </div>

    </form>

</div> -->