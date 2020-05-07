/**
 * Created by teaho2015@gmail.com on 2016/11/15.
 */

$(function(){
    var editor = UE.getEditor('blog-ueditor');
    var blogControllers = [];
    $(".blog-editor-controller").each(function(){
        blogControllers.push(new blogController($(this).get(0), $(this).siblings(".blog-editor-header"), editor));
    });

});

;(function (window, document, $) {

    var Utils = {

        ajax : function(ajaxObj) {
            ajaxObj = ajaxObj || {};
            $.ajax(ajaxObj);

        }
    };


    var blogController = function (controller, jqObj_header, editor) {
        "use strict";

        var _blogController = {};
        var pager = {};

        //var utils = new Utils();

        _blogController.instance;

        var css = {
            class : {
                publisher : "publisher",
                clear : "clear"
            },
            id : {
                title : "postTitle",
                secondTitle : "postTitleSecondary",
                image : "photo"

            }

        };

        function init() {
            _blogController.instance = $(controller);

            /*            var ajaxobj = {};
             ajaxobj.url = "/api/allRegisterList";
             ajaxobj.type =  "GET";
             //async:false,
             ajaxobj.data = {};
             ajaxobj.success =  function (result) {
             //resultJSON = JSON.parse(result);
             //alert(result);
             //console.log();
             console.debug(result);
             //console.debug(JSON.parse(result));
             _regList.regList_instance.find("." + css.class.row_list).append(utils.resolveJsonList(result));
             };
             ajaxobj.error = function (result) {
             console.warn(result + "======>" + "server busy,please try it later!");
             };

             utils.ajaxJsonList(ajaxobj);*/
            //comment temporary
           /* _blogController.instance.find("." + css.class.publisher).click(function (e) {
                var ajaxObj = {};
                ajaxObj.url = "/manage/blog";
                ajaxObj.type = "post";
                //async:false,
                ajaxObj.contentType = "multipart/form-data";
                ajaxObj.processData = false;
                ajaxObj.data = {
                    title:jqObj_header.find("#" + css.id.title).val(),
                    title_secondary : jqObj_header.find("#" + css.id.secondTitle).val(),
                    image :  jqObj_header.find("#" + css.id.image).val(),
                    "content": editor.getContent()};
                ajaxObj.success = function (result) {
                    console.debug(result);
                    //console.debug(JSON.parse(result));
                    //_regList.regList_instance.find("." + css.class.row_list).append(utils.resolveJsonList(result));
                    alert("发布成功！" + result);
                };
                ajaxObj.error = function (result) {
                    console.warn(result + "======>" + "server busy,please try it later!");
                    alert("服务器发生未知错误了，请稍后尝试。");
                };

                console.debug(Utils);
                console.debug(Utils.ajax);
                Utils.ajax(ajaxObj);



            });*/


            _blogController.instance.find("." + css.class.clear).click(function (e) {
                editor.setContent('', false);
            });
        }

        init();
        return _blogController;


    };

    window.blogController = blogController;
    //window.pager = pager;
    //window.utils = utils;

})(window, document, $, undefined);
