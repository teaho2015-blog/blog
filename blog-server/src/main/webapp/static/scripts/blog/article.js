/*
      jQuery Setup                                                           
************************************************************************/ 
/*jQuery.ajaxSetup({
  cache: false
});*/

/*    
      ArticleAnimator Object                                                           
************************************************************************/ 
var ArticleAnimator = ArticleAnimator || {
  canScroll:          true,
  initialLoad:        true,
  animationDuration:  500,
  postCount:          5,
  currentPostIndex:   "DEFAULT",
  nextPostIndex:   {},
  postCache:          {},
  pageTemplate:       null
};

ArticleAnimator.constants = {
  ajax : {
    nextPostUrlAfterFix : "/elderOne"
  },
  css : {
    class : {
      hide : "hide"
    }
  },
  url : {
    prefix : window.location.pathname.slice(0, window.location.pathname.lastIndexOf("/"))
  },
  defaultTitle : "Tea's Artcle."

};

ArticleAnimator.load = function(){
  this.currentPostIndex = getURLIndex();
  this.makeSelections();

  $body.append( this.$current );
  $body.append( this.$next );

  var self = this;
  this.createPost({ type: 'current' }, function(){
    self.createPost({ type: 'next' }, function(){

      /* Selections. */
      self.refreshCurrentAndNextSelection();

      /* Push initial on to stack */
      //history.pushState(pageState(), "", "#" + self.currentPostIndex);
      history.pushState( pageState(), '', self.constants.url.prefix + "/" + self.currentPostIndex);
      $html.find("head>title").text(self.postCache[self.currentPostIndex].title || self.constants.defaultTitle);

      /* Bind to some events. */
      self.bindGotoNextClick();
      self.bindPopstate();
      self.bindWindowScroll();
    })
  })
};

ArticleAnimator.makeSelections = function(){
  this.$page         = $('.page');
  this.pageTemplate  = elementToTemplate( this.$page.clone() );
  this.$current      = this.currentElementClone();
  this.$next         = this.nextElementClone();
};

ArticleAnimator.getPost = function(index, callback){
  callback = callback || $.noop;

  if ( this.postCache[index] ){
    callback( this.postCache[index] );
    return;
  }

  var self = this;
  //use it in controller to create article content
  $.getJSON('/api/v1/blog/article/'+ index + "/attachElderId", function(d){
    self.postCache[index] = d;
    callback(d);
  });
};

ArticleAnimator.getNextPostIndex = function(index){
  return this.nextPostIndex[index];
};

ArticleAnimator.createPost = function(opts, callback){
  opts      = opts || {};
  var self  = this;
  var type  = opts['type'] || 'next';

  if ( opts['fromTemplate'] ){
    $body.append( this.nextElementClone() );
    this['$' + type] = $('.' + type)
  }

  var index = (type == 'next') ? this.getNextPostIndex( this.currentPostIndex) : this.currentPostIndex;
  //if id == null, hide next click trigger
  if(index == null || index == undefined || index == "") {
    self.$next.addClass(self.constants.css.class.hide);
    return;
  }
  this.getPost(index, function(d){
    self.contentizeElement(self['$' + type], d);
    if(type == 'current') {
      self.refreshTitle();
    }
    callback && callback();
  });

};

ArticleAnimator.contentizeElement = function($el, d){
  var self = this;
  $el.find('.big-image').css({ backgroundImage: "url(" + /*getRootPath() + */"" + d.image_url + ")" });
  $el.find('h1.title').html(d.title);
  $el.find('h2.description').html(d.title_secondary);
  $el.find('.content .text').html(d.content);
  $el.find('h3.byline time').html(moment().diff(moment(d.date),'months')>2?moment(d.date).locale("zh_cn").format('MMMM Do YYYY a'): moment(d.date).locale("zh_cn").fromNow());
  //$el.find('h3.byline .author').html(d.creator_name); // reserved
  this.nextPostIndex[d.id] = d.elderid;
  console.debug('Lazy load:' + $el.find('img.lazy').attr('data-original'));
  $el.find('img.lazy').lazyload();

  $el.find('.show_comment').click(function () {
    loadDisqus(this, self.currentPostIndex, window.location.href);
  });
};

ArticleAnimator.animatePage = function(callback){
  var self              = this;
  var translationValue  = this.$next.get(0).getBoundingClientRect().top;
  this.canScroll        = false;

  this.$current.addClass('fade-up-out');

  this.$next.removeClass('content-hidden next')
       .addClass('easing-upward')
       .css({ "transform": "translate3d(0, -"+ translationValue +"px, 0)" });

  setTimeout(function(){
      scrollTop();
      self.$next.removeClass('easing-upward');
      self.$current.remove();

      self.$next.css({ "transform": "" });
      self.$current = self.$next.addClass('current');
      
      self.canScroll = true;
      self.currentPostIndex = self.getNextPostIndex( self.currentPostIndex );

      callback();
  }, self.animationDuration );
};

ArticleAnimator.bindGotoNextClick = function(){
  var self  = this;
  // for mobile
  var e = 'ontouchend' in window ? 'touchend' : 'click';

  this.$next.find('.big-image').on(e, function(e){
    e.preventDefault();
    $(this).unbind(e);

    self.animatePage(function(){
      self.createPost({ fromTemplate: true, type: 'next' });
      self.bindGotoNextClick();
      //history.pushState( pageState(), '', "#" + self.currentPostIndex);
      history.pushState( pageState(), '', self.constants.url.prefix + "/" + self.currentPostIndex);

      self.refreshTitle();
    });
  });
};

ArticleAnimator.bindPopstate = function(){
  var self = this;
  $window.on('popstate', function(e){

    if( !history.state /*|| self.initialLoad*/ ){
      //self.initialLoad = false;
      return;
    }

    self.currentPostIndex = history.state.index;
    //self.nextPostIndex[self.currentPostIndex] = history.state.nextIndex;
    self.$current.replaceWith( history.state.current );
    self.$next.replaceWith( history.state.next );
    self.refreshCurrentAndNextSelection();
    self.refreshTitle();
    self.rebindCurrentDisqus();
    self.createPost({ type: 'next' });
    self.bindGotoNextClick();
  });
};

ArticleAnimator.bindWindowScroll = function(){
  var self = this;
  $window.on('mousewheel', function(ev){
    if ( !self.canScroll ) 
      ev.preventDefault()
  })
};

ArticleAnimator.refreshCurrentAndNextSelection = function(){
  this.$current      = $('.page.current');
  this.$next         = $('.page.next');
};

ArticleAnimator.refreshTitle = function() {
  $html.find("head>title").text(this.postCache[this.currentPostIndex].title || this.constants.defaultTitle);
};

ArticleAnimator.rebindCurrentDisqus = function() {
  var self = this;
  this.$current.find('.show_comment').click(function () {
    console.debug('loadDisqus, page index=' + self.currentPostIndex)
    loadDisqus(this, self.currentPostIndex, window.location.href);
  });;
};

ArticleAnimator.nextElementClone = function(){
  return this.$page.clone().removeClass('hidden').addClass('next content-hidden');
};

ArticleAnimator.currentElementClone = function(){
  return this.$page.clone().removeClass('hidden').addClass('current');
};

/*    
      Helper Functions.                                                      
************************************************************************/ 
function elementToTemplate($element){
  return $element.get(0).outerHTML;
}

function scrollTop(){
  $body.add($html).scrollTop(0);
}

function pageState(){
  return { index: ArticleAnimator.currentPostIndex, current: elementToTemplate(ArticleAnimator.$current), next: elementToTemplate(ArticleAnimator.$next) }
}

function getURLIndex(){
  console.log((history.state && history.state.index) ||window.location.hash.replace('#', "") || ArticleAnimator.currentPostIndex);
  //return parseInt( (history.state && history.state.index) ||window.location.hash.replace('#', "") || ArticleAnimator.currentPostIndex );
  //return ((history.state && history.state.index) ||window.location.hash.replace('#', "") || ArticleAnimator.currentPostIndex );
  return ((history.state && history.state.index) || window.location.pathname.slice(window.location.pathname.lastIndexOf("/")*1+1) || ArticleAnimator.currentPostIndex );
}


/*    
      Document ready.                                                         
************************************************************************/ 
$(document).ready(function(){

  /* A couple of selections. */
  $body         = $(document.body);
  $window       = $(window);
  $html         = $(document.documentElement);

  /* Let's get it started. */
  ArticleAnimator.load();

});