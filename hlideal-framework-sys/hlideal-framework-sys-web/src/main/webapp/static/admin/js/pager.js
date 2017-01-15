define(function(require, exports, module) {
	var $ = require('jquery');
	var Dropdown=require('management/drop_down');
	//构造分页器,并初始化
	function Pager(config){
	   this.config = $.extend({},Pager.defaultConfig,config);
	   this.init();
	};
	//默认属性
	Pager.defaultConfig = {
	  pager :'div.pager',//自定义分页标签对应类选择器
	  pagerForm:'pagerForm', //分页控件提交的表单Id
	  currentPage:1,       //默认当前页
	  pageSize:10,          //默认每页显示记录数
      pageUrl:''            //请求路径
	};
	Pager.prototype = {
	//插件初始化
    init:function(){
      var that = this;
      that.pager = $(that.config.pager);    
      that.config.pagerForm   = that.pager.attr("pagerForm")  ?that.pager.attr("pagerForm")  :that.config.pagerForm;  //分页控件对应的表单
      that.config.currentPage = parseInt(that.pager.attr("currentPage")?that.pager.attr("currentPage"):that.config.currentPage);//当前页号
      that.config.pageSize    = parseInt(that.pager.attr("pageSize")   ?that.pager.attr("pageSize")   :that.config.pageSize);  //每页显示记录数
      that.config.totalCount  = parseInt(that.pager.attr("totalCount") ?that.pager.attr("totalCount") :that.config.totalCount); //总记录数
      that.config.pagination = that.pager.attr("pagination") ?new Function(that.pager.attr("pagination")) :that.config.pagination; //提交的表单的回调函数
      //计算总页数
      if(that.config.totalCount%that.config.pageSize==0){
    	  that.config.totalPage = parseInt(that.config.totalCount/that.config.pageSize);
      }else{
    	  that.config.totalPage = parseInt(that.config.totalCount/that.config.pageSize+1); //总页数
      }
     var  html='<div class="fr">';
      html+='<span class="fl lh30 mr10">共'+that.config.totalCount+'记录</span>';
      html+='<div class="yrkj-page fl mt5 mr10 mb10">'
      //上一页	  
      if(that.config.currentPage>1 && that.config.currentPage<=that.config.totalPage){
    	  html+='<a class="page-btn" href="#" title="'+parseInt(that.config.currentPage-1)+'">上一页</a>';
      }
      //当前页面前面显示4页
      for(var i=4;i>0;i--){
	      if(that.config.currentPage>i){
	    	  html+='<a href="#" title="'+(parseInt(that.config.currentPage)-i)+'">'+(parseInt(that.config.currentPage)-i)+'</a>';
	      }
      }
      //当前选择页
	  html+='<span class="current" title="'+that.config.currentPage+'">'+that.config.currentPage+'</span>';
	  //当前页面后面显示4页
	  for(var i=1;i<4;i++){
    	  if(that.config.currentPage+i < that.config.totalPage){
    		  html+='<a href="#" title="'+(parseInt(that.config.currentPage)+i)+'">'+(parseInt(that.config.currentPage)+i)+'</a>';
    	  }
	  }
	  //总页数
	  if(that.config.totalPage > that.config.currentPage){
		  html+='<span>...</span>';
		  html+='<a href="#" title="'+that.config.totalPage+'">'+that.config.totalPage+'</a>';
	  }
	  //下一页
	  if(that.config.currentPage< that.config.totalPage && that.config.totalPage >1){
		  html+='<a class="page-btn" href="#" title="'+parseInt(that.config.currentPage+1)+'">下一页</a>';
	  }
	  html+='</div>';
	  html+='<div class="fl mb10 mr10">';
	  html+='<span class="fl mr5 lh30">到第</span>';
	  html+='<input name="currentPage" type="text" class="yrkj-input w50 center action-only-number fl mr5 h30 lh30" value="'+that.config.currentPage+'" maxlength="5">';
	  html+='<span class="fl mr5 lh30">页</span>';
	  html+=' <button type="button" class="yrkj-btn btn-grey fl h30 lh30 action-only-number">跳转</button>';
	  html+='</div>';
	  html+='</div>';
	  that.pager.append(html);
      //分页按钮单击
	  that.pager.find("a,:button").on({click:function(e){
		  if( !('placeholder' in document.createElement('input')) && $('.sreach-input').val() == $('.sreach-input').attr('placeholder') ){
			  $('.sreach-input').val('');
		  }
		  if($(this).is("a") && $(this).attr("title")){//如果选择的是a标签
			  that.config.currentPage=$(this).attr("title"); 
			  that.pager.find("input[name='currentPage']").val(that.config.currentPage);
		  }
		  if(that.config.pagination){
			  that.config.pagination.call(that,$("form#"+that.config.pagerForm).serialize());
		  }else{
			 // $("form#"+that.config.pagerForm).submit();//提交表单
              //修改成ajax请求
              $.ajax({
                  type: "get",//使用get方法访问后台
                  dataType: "json",//返回json格式的数据
                  url: that.config.pageUrl,//要访问的后台地址
                  data: $("form#"+that.config.pagerForm).serialize(),//要发送的数据
                  success: function(msg) {//msg为返回的数据，在这里做数据绑定
                      var data = msg.data;
                      $.each(data, function(i, n){
                          //遍历返回的JSON数据
                          //调用table提供的方法，完成数据的加载
                      });
                  }
              });
		  }
		  return false;
	  }});
	  //表单的上的清空按钮
	  $("form#"+that.config.pagerForm).find("button.clean").on({click:function(e){
		  $("form#"+that.config.pagerForm)[0].reset();
		  return false;
	  }});
    }
  }
  module.exports = Pager;
});
//插件使用方法:
//<div class="pager" pagerForm="frmProject" currentPage="2" pageSize="10" totalCount="500"/>