/**
 * Created with IntelliJ IDEA.
 * User: teaship
 * Date: 15-4-11
 * Time: 下午5:52
 * To change this template use File | Settings | File Templates.
 */

//window.onload = function() {
//    function adsorption() {
//        var headerWrap = document.getElementById('blog-sidebar');
//        /*var header = document.getElementById('header');*/
//        var scrollTop = 0;
//        window.onscroll = function() {
//            scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
//            /*alert(scrollTop);*/
//            if (scrollTop > 100) {
//                $(headerWrap).addClass("fixed_sideBar");
//            } else {
//                $(headerWrap).removeClass("fixed_sideBar");
//            }
//        }
//    }
//    adsorption();
//}

$(function(){

    $(".dropdown-menu .del").bind("click",function(){
        var $blogid= $(this).parent().siblings(".blogid");
        $.ajaxWithMask({
            url: getRootPath()+'/blog/article/'+$blogid.val(),
            type:'DELETE',
            data:{},
            success:function(result) {
                alert(result.msg);
                window.location.reload();
            },
            error:function(result) {
                alert("server busy,please try it later!");
            }
        });
    });
});
