<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
    <title>OPT列表页</title>
    <%@include file="../common/head.jsp" %>
</head>

<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>OPT列表</h2>
            <button type="button" class="btn btn-default btn-lg">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加
            </button>
        </div>

        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <td>站名</td>
                    <td>EFT终端号</td>
                    <td>PSAM卡终端号</td>
                    <td>IP地址</td>
                    <td>端口</td>
                    <td>创建时间</td>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td>${item.stationName}</td>
                        <td>${item.tid}</td>
                        <td id="psamTid_${item.tid}">${item.psamTid}</td>
                        <td id="ip_${item.tid}">${item.ip}</td>
                        <td id="port_${item.tid}">${item.port}</td>
                        <td>
                            <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <a class="btn btn-info editBtn" item="${item.tid}">编辑 </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>



</div>
<%--弹出层 修改记录--%>
<div id="editModal" class="modal fade">

    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon"> </span>修改信息
                </h3>
            </div>

            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon-tid">EFT终端号: </span>
                            <input type="text" class="form-control"  id="edit_tid" aria-describedby="basic-addon-tid">
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon-psamTid">PSAM卡终端号: </span>
                            <input type="text" class="form-control" id="edit_psam" aria-describedby="basic-addon-psamTid">
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon-ip">IP地址</span>
                            <input type="text" class="form-control" id="edit_ip" aria-describedby="basic-addon-ip">
                        </div>

                        <div class="input-group">
                            <span class="input-group-addon" id="basic-addon-port">端口</span>
                            <input type="text" class="form-control" id="edit_port" aria-describedby="basic-addon-port">

                        </div>

                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <%--验证信息--%>
                <span class="glyphicon"> </span>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="submitBtn" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>
                        提交
                    </button>
            </div>

        </div>
    </div>

</div>

</body>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="<%=path%>/resource/script/app.js" type="text/javascript"></script>

<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        app.list.init({
            baseUrl:'<%=path%>'
        });
    })
</script>
</html>