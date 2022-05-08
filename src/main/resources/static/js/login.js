$(function(){
    $('#submit').bind('click', checkLogin)
})

function checkLogin(){
    var user = $('#username').val()
    var password = $('#password').val()
    var url = getUrlParams("redirect")
    console.log(user)
    console.log(password)
    console.log(url)
    if(url==false)
        url = '/user/success'

    $.ajax({
        url:"/login",
        type:"post",
        cache: false,
        data: {
            'userName': user,
            'password': password,
            'url': url
        },
        dataType: 'json',
        success:function(data){
            var success = data.success
            var message = data.message
            if(success){
                console.log(url)
                console.log(message)
                window.location.href = url
            }else{
                console.log(message)
            }
        },
        error:function(){
            console.log("登录信息错误")
        }
    })
}

function getUrlParams(key) {
	var url = window.location.search.substring(1);
	if (url == '') {
		return false;
	}
	var paramsArr = url.split('&');
	for (var i = 0; i < paramsArr.length; i++) {
		var combina = paramsArr[i].split("=");
		if (combina[0] == key) {
			return combina[1];
		}
	}
	return false;
}