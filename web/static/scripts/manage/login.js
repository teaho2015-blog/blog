/**
 * Created by teaho2015@gmail.com on 2016/10/20.
 */
function setReadOnly() {
//        $("#inputPassword2").attr("readonly",true);
    $("#inputPassword").val(hex_md5($("#inputPassword").val()));
}

$(function(){
    $('[data-toggle="tooltip"]').tooltip();
});