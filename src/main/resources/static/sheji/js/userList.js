layui.config({
    base : "../../js/"
})
var $=layui.jquery;
  $.get("../../static/json/uesrsList.json",function(data){
    var userList = '';
    for(var i=0; i<data.length; i++){
        if (data[i].userStatus=="管理员") {
            userList+='<tr>'
            userList+='<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>';
            userList+='<td class="hidden-xs">'+data[i].uesrsID+'</td>';
            userList+='<td class="hidden-xs">'+data[i].userName+'</td>';
            userList+='<td class="hidden-xs">'+data[i].userEmail+'</td>';
            userList+='<td class="hidden-xs">'+data[i].userStatus+'</td>';
            userList+='<td class="hidden-xs">'+data[i].school+'</td>';
            userList+='<td>'+data[i].zTime+'</td>';
            uesrList+='<td>';
            userList+='<div class="layui-inline">';
            userList+='<button class="layui-btn layui-btn-small layui-btn-danger del-btn" data-id="1" data-url="article-detail.html"><i class="layui-icon">&#xe640;</i></button>';
            userList+='</div>';
            userList+='</tr>';
        }
    }
    $(".usereList").html(userList);
})