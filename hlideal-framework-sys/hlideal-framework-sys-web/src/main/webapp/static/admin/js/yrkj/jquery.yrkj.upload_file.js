/**
 * Created by liu.y on 2016/12/21 0021.
 * Description: upload file
 */
var quence = new Array();//待上传的文件队列，包含切块的文件

//内部构造方法
define(function(require,exports,module){
    var $ = require('jquery');
    require('yrkj/jquery.yrkj.message');
    function UPLOAD_FILE() {
        this.version = 1.0;
        this.name = 'UPLOAD_FILE';
    }

    //定义基本对象
    $.UPLOAD_FILE = new UPLOAD_FILE();

    //定义
    $.fn.UPLOAD_FILE = function(options,param){
        //如果为string，表示是方法调用
        if (typeof options == 'string') {
            var method = $.fn.YTable.methods[options];
            if (method) {
                return method(this, param);
            }
        }
        options = options || {};
        var opts = $.extend({}, $.fn.UPLOAD_FILE.defaults, options);
        this.data("options", {options: opts});

        return this.each(function () {
            var method = $.fn.UPLOAD_FILE.methods["init"];
            method(this);
        });

    };

    $.fn.UPLOAD_FILE.methods = {
        init : function(jq){
            var opt = $(jq).data("options");
            if($(jq).find("_upload_form").length==0){
                $(jq).append('<form action="javascript:void(0);" id="_upload_form" enctype="multipart/form-data" METHOD="post" class="yui-form">'+
                    '<dl class="form-item">'+
                    '<dd class="detail">'+
                    '<label style="width:350px;">'+
                    '<input type="file" multiple=true onchange="showFileList(this.files)" id="file_" />'+
                    '</label>'+
                    '</dd>'+
                    '</dl>'+
                    '</form>'+
                    '<div style="float: right;margin-top: 37px;margin-right: 20px;">' +
                    '<input id="uploadBtn" type="button"  value="上传"  onclick="doUpload()" class="yrkj-btn btn-blue"></div>');
            }

            if(opt.options.url_address == null){
                $.YMessage.warning("警告", "上传路径不能为空",event.preventDefault());
            }

            var method = $.fn.UPLOAD_FILE.methods["valid"];
            method(opt.options);
        },
        valid : function(options){
            $('input[type=file]').each(function()
            {
                var max_size=options.file_size;
                $(this).change(function(evt)
                {
                    var files = evt.target.files; // 获得文件对象
                    for (var i = 0, f; f = files[i]; i++)
                    {
                        // if(f.size > max_size)
                        // {
                        //     $.YMessage.warning("警告", "上传文件过大");
                        //     $('input[type=file]').val('');
                        //     return;
                        // }
                        // var fileExtension = '.'+f.name.substring(f.name.lastIndexOf('.') + 1);
                        // if(options.ext.indexOf(fileExtension)==-1){
                        //     $.YMessage.warning("警告", "上传文件格式不对");
                        //     $('input[type=file]').val('');
                        //     return;
                        // }
                        // file_send(options.url_address);
                    }

                });
            });
        }
    };

    $.fn.UPLOAD_FILE.defaults = {
        auto : false,               //自动上传,默认为false
        multiple : false,           //多文件上传,默认为false
        progress_bar : false,       //显示进度条,默认为false
        url_address : null,         //后台服务器地址
        file_size : 1*1240*1240,         //文件大小,默认最大为1M
        ext : '.xls,.txt'                //默认支持文件格
    };
});

// function uploadProgress(evt) {
//     $("input[type=file]").attr("disabled","disabled");
//     if (evt.lengthComputable) {
//         var progressId = 'proDownFile_' + Math.round(100);
//         var pUipId = 'pTip' + Math.round(100);
//         var percentComplete = Math.round(evt.loaded * 100 / evt.total);
//         var percent = evt.loaded;
//
//         var html = '<div>' +
//             '<progress value="0" max="'+evt.total+'" id="'+progressId+'"></progress>'+
//             '<span>'+$('#file_').get(0).files[0].name+'</span>'+
//             '<p id="'+pUipId+'">%</p>' +
//             '</div>';
//         if($("#_upload_form dl").find('div progress').length==0){
//             $("#_upload_form dl").after(html);
//         }else{
//             $("#_upload_form dl").lastChild.after(html);
//         }
//
//         document.getElementById(progressId).value = percent;
//         document.getElementById(pUipId).innerHTML = percentComplete==100?"已完成":"正在上传"+percentComplete+"%";
//     }
//     else {
//         document.getElementById('progressNumber').innerHTML = 'unable to compute';
//     }
// }

function showFileList(files) {
    if(!files) {
        return;
    }
    var chunkSize = 5 * 1024 * 1024;  //切块的阀值：5M
    $(files).each(function(idx,e){
        var uuid=getUuid();

        //文件列表
        var progressId = 'proDownFile_' + uuid;
        var pUipId = 'pTip' + uuid;

        var html = '<div>' +
            '<progress value="0" max="'+e.size+'" id="'+progressId+'"></progress>'+
            '&nbsp;&nbsp;<span>'+e.name+'</span>'+
            '&nbsp;&nbsp;<span id="'+pUipId+'">%</span>' +
            '</div>';
        if($("#_upload_form dl").find('div progress').length==0){
            $("#_upload_form dl").after(html);
        }else{
            $("#_upload_form dl").lastChild.after(html);
        }
        document.getElementById(pUipId).innerHTML = "准备上传";

        var fileSize = e.size;
        if(fileSize > chunkSize) {//文件大于阀值，进行切块
            //切块发送
            var chunks = Math.max(Math.floor(fileSize / chunkSize), 1)+1;//分割块数
            for(var i=0 ; i<chunks; i++) {
                var startIdx = i*chunkSize;//块的起始位置
                var endIdx = startIdx+chunkSize;//块的结束位置
                if(endIdx > fileSize) {
                    endIdx = fileSize;
                }
                var lastChunk = false;
                if(i == (chunks-1)) {
                    lastChunk = true;
                }
                //封装成一个task，入列
                var task = {
                    file:e,
                    uuid:uuid,      //避免文件的重名导致服务端无法定位文件，需要给每个文件生产一个UUID
                    chunked:true,
                    startIdx:startIdx,
                    endIdx:endIdx,
                    currChunk:i,
                    totalChunk:chunks,
                    progressId:progressId,
                    pUipId:pUipId

                };
                quence.push(task);

            }
        } else {//文件小于阀值

            var task = {
                file:e,
                uuid:uuid,
                chunked:false,
                progressId:progressId,
                pUipId:pUipId
            };
            quence.push(task);

        }
    });
}

function Uploader(name) {
    this.url="/admin/backstage/upload/saveFile.htm";        //服务端处理url
    this.req = new XMLHttpRequest();
    this.tasks;         //任务队列
    this.taskIdx = 0;   //当前处理的tasks的下标
    this.name=name;
    this.status=0;      //状态，0：初始；1：所有任务成功；2：异常

    //上传 动作
    this.upload = function(uploader) {
        this.req.responseType = "json";
        //注册load事件（即一次异步请求收到服务端的响应）
        this.req.addEventListener("load", function(){
            //更新对应的进度条
            var percentComplete =this.response.totalSize==0?100:Math.round(this.response.fileSize * 100 / this.response.totalSize);
            progressUpdate(percentComplete, this.response.fileSize,this.response.file_progressId,this.response.pUipId);
            //从任务队列中取一个再次发送
            var task = uploader.tasks[uploader.taskIdx];
            if(task) {
                console.log(uploader.name + "：当前执行的任务编号：" +uploader.taskIdx);
                this.open("POST", uploader.url);
                this.send(uploader.buildFormData(task));
                uploader.taskIdx++;
            } else {
                console.log("处理完毕");
                uploader.status=1;
            }
        });

        //处理
        var task = this.tasks[this.taskIdx];
        if(task) {
            console.log(uploader.name + "：当前执行的任务编号：" +this.taskIdx);
            this.req.open("POST", this.url);
            this.req.send(this.buildFormData(task));
            this.taskIdx++;
        } else {
            uploader.status=1;
        }
    };

    //提交任务
    this.submit = function(tasks) {
        this.tasks = tasks;
    };

    //构造表单数据
    this.buildFormData = function(task) {
        var file = task.file;
        var formData = new FormData();
        formData.append("fileName", file.name);
        formData.append("fileSize", file.size);
        formData.append("uuid", task.uuid);
        formData.append("chunked",task.chunked);
        formData.append("progressId",task.progressId);
        formData.append("pUipId",task.pUipId);
        var chunked = task.chunked;
        if(chunked) {//分块
            formData.append("data", file.slice(task.startIdx, task.endIdx));//截取文件块
            formData.append("currChunk", task.currChunk);
            formData.append("totalChunk", task.totalChunk);
        } else {
            formData.append("data", file);
        }
        return formData;
    }

}

function progressUpdate(percentComplete,fileSize,progressId,pUipId) {
    document.getElementById(progressId).value = fileSize;
    document.getElementById(pUipId).innerHTML = percentComplete==100?"已完成":"正在上传"+percentComplete+"%";
}

function doUpload() {

    //创建4个Uploader上传器（4条线程）
    var uploader0 = new Uploader("uploader0");
    var task0 = new Array();

    // var uploader1 = new Uploader("uploader1");
    // var task1 = new Array();
    //
    // var uploader2 = new Uploader("uploader2");
    // var task2 = new Array();
    //
    // var uploader3 = new Uploader("uploader3");
    // var task3 = new Array();

    //将文件列表取模hash，分配给4个上传器
    for(var i=0 ; i<quence.length; i++) {
        // if(i%4==0) {
            task0.push(quence[i]);
        // } else if(i%4==1) {
        //     task1.push(quence[i]);
        // } else if(i%4==2) {
        //     task2.push(quence[i]);
        // } else if(i%4==3) {
        //     task3.push(quence[i]);
        // }
    }
    // //提交任务，启动线程上传
    // if(task0.length!=0){
        uploader0.submit(task0);
        uploader0.upload(uploader0);
    // }
    // if(task1.length!=0){
    //     uploader1.submit(task1);
    //     uploader1.upload(uploader1);
    // }
    // if(task2.length!=0){
    //     uploader2.submit(task2);
    //     uploader2.upload(uploader2);
    // }
    // if(task3.length!=0){
    //     uploader3.submit(task3);
    //     uploader3.upload(uploader3);
    // }





    //注册一个定时任务，每2秒监控文件是否都上传完毕
    // uploadCompleteMonitor = setInterval("uploadComplete()",2000);
}

function getUuid(){
    var len=32;//32长度
    var radix=16;//16进制
    var chars='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
    var uuid=[],i;radix=radix||chars.length;
    if(len){for(i=0;i<len;i++)uuid[i]=chars[0|Math.random()*radix];}
    else{var r;uuid[8]=uuid[13]=uuid[18]=uuid[23]='-';uuid[14]='4';
    for(i=0;i<36;i++){if(!uuid[i]){r=0|Math.random()*16;uuid[i]=chars[(i==19)?(r&0x3)|0x8:r];}}}
    return uuid.join('');
}

// function uploadComplete(evt) {
//     $("input[type=file]").removeAttr("disabled");
//
// }
//
// function uploadFailed(evt) {
//     $("input[type=file]").removeAttr("disabled");
//     alert("There was an error attempting to upload the file.");
// }
//
// function uploadCanceled(evt) {
//     $("input[type=file]").removeAttr("disabled");
//     alert("The upload has been canceled by the user or the browser dropped the connection.");
// }
