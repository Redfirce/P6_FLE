<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix = "springTags" %>
<head>
	<title>
		<springTags:message code ="updateTopoForm.title"/>
	</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/cssFiles/07_01_pages/pageElmtCss.css" />
</head>
<div>
	<button onclick= "window.parent.location ='../callListBack?listType=toposMine'" class="pageButtonNormal">
		<springTags:message code ="back.name"/>
	</button>
</div>
<div class="updateTopoFormPageTitle" >
	<springTags:message code = "updateTopoForm.title"/>
</div>
<div class="deleteSiteButtonPositioning">
	<button class="pageButtonWarning"  id="deletion" onclick="deletionConfirm('${deleteURL}','${confirmMessage}')">
		<springTags:message code = "deleteTopo.name"/>
	</button>
</div>
<br>
<div>
	<%@ include file="/resources/02_templatesJsp/02_02_formJsp/02_02_01_formFiles/updateTopoTmplt.jsp" %>
</div>
<br>
<script type="text/javascript" src="${pageContext.request.contextPath}/toolBoxJavaScript/confirmAction.js"></script>
