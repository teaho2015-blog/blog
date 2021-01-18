/**
 * Created by teaho2015@gmail.com on 2016/2/27
 */
$(function() {

    $("[data-toggle='tooltip']").tooltip({
    });

    $('.navbar-toggle-custom').click(function (e) {
        var _this = $(this);
        _this.toggleClass('navbar-toggle-custom-show');
        $('.navbar-collapse-menu').toggleClass('navbar-collapse-menu-show');
    });
   /* $('.contact-list>li').smoove({
        offset:'30%',
        opacity: 0,
        rotate:  '1800deg',
        moveX : '-900%',
        moveY : '-900%'

    });*/
});










