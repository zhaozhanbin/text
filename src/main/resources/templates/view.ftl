<html>
<head>
    <title>用户信息</title>
</head>
<body>
<center>
<a href="${request.contextPath}/user/tosave">新增</a>
</center>
<table border="1px" align="center">
    	<tr>
	    	<td>用户编号</td>
	    	<td>用户名称</td>
	    	<td>用户性别</td>
	    	<td>操作</td>
    	</tr>
	    <#list data as use>
	    	<tr>
		    	<td>${use.id }</td>
		    	<td>${use.name }</td>
		    	<td>${use.sex }</td>
		    	<td> 
 <a href="${request.contextPath}/user/todelete/${use.id}">删除</a>
 <a href="${request.contextPath}/user/toMainbyid/${use.id}">修改</a>
	
				 </td>
		    	
	    	</tr>
	    </#list>
    </table>
</body>
</html>
