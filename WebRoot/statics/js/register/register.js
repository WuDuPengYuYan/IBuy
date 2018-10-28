//注册
function register(){
	//alert("----->");
	//获取字段值
	var loginName = $("input[name='loginName']").val();
	var password = $("input[name='password']").val();
	var confirmPassword = $("input[name='confirmPassword']").val();
	var userName = $("input[name='userName']").val();
	var sex = $("input[name='sex']:checked").val();
	var identityCode = $("input[name='identityCode']").val();
	var email = $("input[name='email']").val();
	var mobile = $("input[name='mobile']").val();
	
	alert("---->"+identityCode);
	//前端校验
	if(loginName == null||loginName==""){
		showMessage("用户名不能为空！！");
		return;
	}
	
	if(loginName.length < 2||loginName >10){
		showMessage("用户名不能小于两个字符或者大于十个字符！！！");
		return;
	}
	
	if(userName == null||userName==""){
		showMessage("真实姓名不能为空！！");
		return;
	}
	
	if(password == ""){
		showMessage("密码不能为空！！！");
		return;
	}
	if(password != confirmPassword){
		showMessage("两次输入的密码不一致！！！");
		return;
	}
	
	/*if(!checkIdentityCode(checkIdentityCode)){
		showMessage("身份证格式不正确！！！");
		return;
	}*/
	if(email != null && email != ""&&!checkEmail(email)){
		showMessage("邮箱格式不正确！！！");
		return;
	}
	if(mobile != null && mobile != ""&&!checkMobile(mobile)){
		showMessage("手机格式不正确！！！");
		return;
	}
	
	$.ajax({
		url:contextPath+"/Register",
		method:"POST",
		data:{
				loginName:loginName,
				password:password,
				userName:userName,
				sex:sex,
				identityCode:identityCode,
				email:email,
				mobile:mobile,
				action:'doRegister'
			},
		//dataType:"JSON",	
		success:function(data){
			var result = eval("("+data+")");
        	if(result.status == 1){
        		showMessage(result.message);
        		window.location.href=contextPath+"/Login?action=toLogin";
        	}else{
        		showMessage(result.message);
        	}
		}	
	});
};


function checkEmail(email){
	var flag = /^\w+\@\w+(\.[a-zA-Z0-9]{2,3}){1,2}$/;
	if(flag.test(email)){
		return true;
	}else{
		return false;
	}
}
function checkMobile(phone){
	var flag = /^\d{11}$/;
	if(flag.test(phone)){
		return true;
	}else{
		return false;
	}
}
function checkIdentityCode(identityCode){
	var flag = /^\d+$/;
	if(flag.test(identityCode)){
		return true;
	}else{
		return false;
	}
	
}








