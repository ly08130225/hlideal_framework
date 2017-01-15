$(function () {
    var baseUrl = $("#project_base_url").attr("content");
    $("#form").validate({
        rules: {
            "userName": {
                required: true,
                userName: true
            },
            "password": {
                required: true,
                password: true
            }
        },
        messages: {
        }
    });
    $("#submit").click(function () {
        if ($("#form").valid()) {
        	 $.ajax({
                 url:baseUrl + '/logincheck.htm',
                 type:'post',
                 data:{userName:$("#userName").val(), password:$("#password").val()},
                 dataType:'json',
                 success:function(data){
                     if(data.code=='1'){
                    	 location.href = baseUrl + "/backstage/home.htm";
                     }else{
                    	 alert(data.message);
                     }                     
                 },
                 error:function(){
                     alert('系统错误');                  
                 }
             });
        }
    });
});