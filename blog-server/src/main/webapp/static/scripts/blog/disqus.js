/**
 * Created by teaho2015@gmail.com on 2020
 */

// global vars used by disqus
var pageDisqusConfig = {
    shortname: 'teas-blog',
    identifier: '', // made of post id &nbsp; guid
    url: '' // post permalink
};

(function () { // DON'T EDIT BELOW THIS LINE
    // disqus必须先存在disqus_thread再加载js不然报错
    var dsq = document.createElement('script');
    var head = document.getElementsByTagName('head')[0];
    var body = document.getElementsByTagName('body')[0];

    dsq.type = 'text/javascript';
    dsq.async = true;
    dsq.src = '//' + pageDisqusConfig.shortname + '.disqus.com/embed.js';

    (head || body).appendChild(dsq);
})();

function loadDisqus(source, identifier, url) {
    if (document.getElementById('disqus_thread') == null) {
        console.debug('init disqus.', source);
        $('<div id="disqus_thread"></div>').insertAfter(source);
    } else {
        $('#disqus_thread').insertAfter(source); // append the HTML to the control parent
    }
    if (!window.DISQUS) {
        console.debug('Disqus not loaded.');
        $(source).replaceWith('<p class="disqus_not_loaded">加载Disqus失败，烦请稍后再试或翻墙。</p>');
        return;
    }
    $(source).addClass('hidden');
    // if Disqus exists, call it's reset method with new parameters
    console.log(identifier, url);
    DISQUS.reset({
        reload: true,
        config: function () {
            this.page.identifier = identifier;
            this.page.url = url;
        }
    });
};











