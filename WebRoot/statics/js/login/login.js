//登录的方法
function login(){
    var loginName=$("#loginName").val();
    var password=$("#password").val();
    alert("---login--->"+loginName+"--->"+password);
    $.ajax({
        url:contextPath+"/Login",
        method:"POST",
        //dataType: "json",
        data:{loginName:loginName,password:password,action:"login"},
        success:function(data){
        	var result = eval("("+data+")");
        	if(result.status == 1){
        		showMessage(result.message);
        		window.location.href=contextPath+"/Home?action=index";
        	}else{
        		showMessage(result.message);
        	}
        }
    })
}

/*
if(result.status==1){
window.location.href=contextPath+"/Home?action=index";
}else{
showMessage(result.message)
}
*/