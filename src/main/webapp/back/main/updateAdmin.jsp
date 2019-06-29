<%@ page pageEncoding="utf-8" %>
<script type="text/javascript">
	$(function(){
		$("#editAdminForm").form("load","${pageContext.request.contextPath }/admin/findOne.do?id="+${login.id});
		 $("#idui").textbox({
			   required:true,
			   readonly:true
		   });
		   
		   $("#nameui").textbox({    
		     required: true 
		   });
		   
		    $("#passwordui").textbox({    
		      required: true 
		    });
		    $("#editadminuibutton").linkbutton({
		    	
		    	iconCls:'icon-save',
		    	onClick:function(){//在普通的按钮事件中，调用表单form的提交方法submit
		    		$("#editAdminForm").form("submit",{
		    			url:"${pageContext.request.contextPath }/admin/modifyPassword.do",//要提交资源的路径
		    			onSubmit:function(){
		    				return $("#editAdminForm").form("validate");//调用表单的validate进行自动验证
		    			},
		    			success:function(){//成功后的回调
		    				//关对话框
		    				$("#editPWDiv").dialog("close");
			    			$.messager.show({
			    				title:"系统提示",
			    				msg:"保存成功！"
			    			});
		    			}
		    		});
		    	}
		    });
	});

</script>

<div>
	<h1>修改密码:</h1>
	<form action="" method="post" id="editAdminForm">
		<table cellpadding="0" cellspacing="0" border="0" class="form_table">
			<tr type="hidden">
				<td valign="middle" align="right">
					id:
				</td>
				<td valign="middle" align="left">
					<input name="id"  id="idui" />
				</td>
			</tr>
			
			<tr>
				<td valign="middle" align="right">
					name:
				</td>
				<td valign="middle" align="left">
					<input name="name"  id="nameui" />
				</td>
			</tr>
			
			<tr>
				<td valign="middle" align="right">
					password:
				</td>
				<td valign="middle" align="left">
					<input name="password"  id="passwordui" />
				</td>
			</tr>
		</table>
		<p>
			<a id="editadminuibutton">修改</a>
		</p>
	</form>
</div>