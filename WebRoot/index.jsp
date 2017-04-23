<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>唐诗搜索</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet" media="all"
	href="css/defaults.css" />
<link type="text/css" rel="stylesheet" media="all" href="css/system.css" />
<link type="text/css" rel="stylesheet" media="all"
	href="css/system-menus.css" />
<link type="text/css" rel="stylesheet" media="all" href="css/style.css" />
</head>

<body>

	<div id="header-region" class="clear-block"></div>
	<div id="wrapper">
		<div id="container" class="clear-block">
			<div id="header">
				<div id="logo-floater"></div>
			</div>
			<div id="center">
				<div id="squeeze">
					<div class="right-corner">
						<div class="left-corner" style="text-align: center;">
							<h2>全唐诗搜索引擎</h2>
							<div class="clear-block">
								<div id="node-2" class="node">
									<div class="content clear-block">
										<form method="post" action="<%=path %>/manager/TangPoetry-show.action" name="glb">
											<%--<input type="hidden" name="q" value="quantangshi"> 
											--%>
											<select name="selects">
												<option value='lbl_Title'>题目
												<option value='lbl_Author'>作者
												<option value='lbl_Body'>诗歌内容
											</select> <input type="text" name="keyword" size="40" maxlength="60"
												value="">
											<button type="submit" id="DoSearch">搜&nbsp索</button>
										</form>
										<br>
										<%--<a href="" ><font color="bule" size="10px">查询全部唐诗</font></a>
									--%>
									</div>
									<div class="clear-block">
										<div class="meta"></div>
									</div>
								</div>
							</div>
							<div id="footer"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%
		Object message = request.getAttribute("message");
		if (message != null && !"".equals(message)) {
	%>
	<script type="text/javascript">
          alert("<%=message%>");
	</script>
	<%} %>

</body>
</html>
