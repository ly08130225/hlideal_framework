/**
 *
 * @authors younger
 * @date    2016-10-13
 * @description
 */
~ function(window) {
  	seajs.config({
	    base: '/static/admin/js',
	    alias: {
	    	'yrkj':'yrkj',
	        'project': 'project',	        
	        'orijquery': 'vendor-modules/jquery',
	        'jquery': 'yrkj/myajax',
	        'datepicker': 'vendor-modules/datepicker/WdatePicker',
	        'ztree': 'vendor-modules/ztree/jquery.ztree.all-3.5',
	        'upload': 'vendor-modules/uploadify/jquery.uploadify.js',
	        'validate':'vendor-modules/jquery.validate.min',
	        'plugin': 'plugin'
	        	
	    },
	    charset: 'utf-8',
	    timeout: 20000,
	    debug: false
	});
}(window)