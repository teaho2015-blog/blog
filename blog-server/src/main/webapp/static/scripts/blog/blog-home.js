/**
 * Created by teaho2015@gmail.com on 2016/12/2.
 */
$(function() {
    // One Page Smooth Scrolling
    $('.page-scroller a').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: $($anchor.attr('href')).offset().top
        }, 1500, 'easeInOutExpo');
        event.preventDefault();
    });


    //replace by jqPaginator jq plugin
   /* var blogPagers = [];
    $("nav.pager").each(function(){
        blogPagers.push(new pager($(this).get(0), "/page"));
    });
*/
    var blogPagers = new pager($("nav.pager"));

});


;(function (window, document, $) {
    "use strict";
    var Utils = {
        //var _control = {};
        ajax : function(ajaxObj) {
            ajaxObj = ajaxObj || {};
            $.ajax(ajaxObj);

        },
        elementToTemplate: function($element) {
            return $element.get(0).outerHTML;
        }
        //return _control;
    };

    var constants = {
        url : {
            pathnamePrefix : window.location.pathname.lastIndexOf("/")==0? (window.location.pathname.slice(0,  window.location.pathname.length==1? 0 : window.location.pathname.length )+"/page") : window.location.pathname.slice(0,  window.location.pathname.lastIndexOf("/"))

        },css : {
            class : {
                hide : "hide",
                disabled : "disabled",
                active : "active",
                prePage : "pre-page",
                nextPage : "next-page",
                pagination : "pagination"
            },
            id : {
                mainWrapper : "main-wrapper"
            }
        }
    };


    var pager = function (controller, path_prefix) {

        var _blogPager = {
            canScroll : true,
            initialLoad : true,
            postCache : {},
            currentPageNum : 1,
            $articleWrapper : $("#article-wrapper"),
            $itemTemplate : $(".template").clone()
        };
        //var pager = {};

        //var utils = new Utils();

        _blogPager.instance;

        var totalPageNum;

        var pageSize; //page bar items amount
        /*
        var PAGE_BAR_PRE_RANGE = 2;*/


        function bindPopstate() {
            //var self = this;
            $(window).on('popstate', function (e) {
                if (!history.state /*|| _blogPager.initialLoad*/) {
                    return;
                }
                //_blogPager.initialLoad = false;
                NProgress.start();


                _blogPager.currentPostIndex = history.state.index;
                _blogPager.$articleWrapper.replaceWith(history.state.articleWrapper);

                //_blogPager.refreshCurrentSelection();

                //self.createPost({type: 'next'});

                _blogPager.instance.jqPaginator('option', {
                    currentPage: history.state.index
                });
                refreshSelection();
                NProgress.done(true);
            });
        };

        function getPost(index, callback) {
            callback = callback || $.noop;

            if ( _blogPager.postCache[index] ){
                callback( _blogPager.postCache[index] );
                return;
            }

            //use it in back-end controller to create article content
            $.getJSON('/api/v1/blog/page/'+ index , function(result){
                _blogPager.postCache[index] = result;
                callback(result);
            });
        }

        function createPost(opts, callback){
            opts      = opts || {};
            var self  = this;
            var type  = opts['type'] || 'next';
            var index = opts['index'];

            //if ( opts['fromTemplate'] ){
            //    $body.append( this.nextElementClone() );
            //    this['$' + type] = $('.' + type)
            //}

            getPost(index, function(result){
                _blogPager.contentizeElement(/*_blogPager['$' + type],*/ result);
                callback && callback();
            });

        }

        function refreshSelection(){
            _blogPager.$articleWrapper = $("#article-wrapper");
        }

        function pageState(){
            return { index: _blogPager.currentPageNum, articleWrapper : Utils.elementToTemplate(_blogPager.$articleWrapper) }
        }

        _blogPager.getURLIndex = function() {
            //console.log((history.state && history.state.index) ||window.location.hash.replace('#', "") || _blogPager.currentPageNum);
            return ((history.state && history.state.index) || window.location.pathname.slice(window.location.pathname.lastIndexOf("/")*1+1) || _blogPager.currentPageNum);
        }


        _blogPager.contentizeElement = function (result) {
            _blogPager.setProgressBarRate(.5);

            _blogPager.$articleWrapper.find(".post-preview").remove();
            _blogPager.$articleWrapper.find("hr").remove();
            var elementBuffer = "";
            for(var i in result.data) {
                var article = result.data[i];
                var $itemTemplateClone = _blogPager.$itemTemplate.clone();
                $itemTemplateClone.find(".post-title").text(article.title);
                $itemTemplateClone.find(".post-subtitle").text(article.title_secondary);
                $itemTemplateClone.find(".post-meta").attr("data-time", article.date );
                $itemTemplateClone.find(".post-meta").text("发布于 "+ moment().diff(moment(article.date),'months')>2?moment(article.date).locale("zh_cn").format('MMMM Do YYYY a'): moment(article.date).locale("zh_cn").fromNow());
                $itemTemplateClone.find(".post-preview>a").attr("href", $itemTemplateClone.find(".post-preview>a").attr("data-context-path")+"/article/"+article.id);
                elementBuffer += $itemTemplateClone.get(0).innerHTML;
            }

            _blogPager.$articleWrapper.append(elementBuffer);

        };


        _blogPager.setProgressBarRate = function (degree) {
            if(NProgress.isStarted() && typeof degree == "number") {
                NProgress.set(degree);
            }
        };


        function init() {
            controller = controller || {};
            _blogPager.instance = $(controller);
            _blogPager.currentPageNum = _blogPager.instance.attr("data-current-page-num")*1;
            totalPageNum = _blogPager.instance.attr("data-total-page-num")*1;
            pageSize = _blogPager.instance.attr("data-pagesize")*1;

            _blogPager.instance.jqPaginator({
                totalPages: totalPageNum,
                visiblePages: pageSize,
                currentPage: _blogPager.currentPageNum,
                wrapper : "<ul class=\"pagination\"></ul>",
                // first: '<li class="first"><a href="javascript:;">第一页</a></li>',
                prev: '<li class="prev"><a href="javascript:;"><</a></li>',
                next: '<li class="next"><a href="javascript:;">></a></li>',
                // last: '<li class="last"><a href="javascript:;">最后一页</a></li>',
                page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
                onPageChange: function (num, type) {
                    //$('#text').html('当前第' + num + '页');
                    console.log("num : "+ num);
                    console.log("type : "+ type);
                    _blogPager.currentPageNum = num;
                    NProgress.start();
                    createPost({
                        index : num
                    }, function () {
                        console.debug(pageState());
                        history.pushState( pageState(), '', constants.url.pathnamePrefix + "/" + num);
                        setTimeout("NProgress.done()", .3 * 1000);
                        if(type == "change") {
                            $('html, body').stop().animate({
                                scrollTop: $("#" + constants.css.id.mainWrapper).offset().top
                            }, 700, 'easeOutQuart');
                        }
                    });


                    if(type == "init") {
                        bindPopstate();
                    }





                }
            });


        }



        _blogPager.nextPage = function() {
            console.log("netx Page");
        };

        init();
        return _blogPager;


    };

    window.pager = pager;
    //window.utils = utils;

})(window, document, $, undefined);








