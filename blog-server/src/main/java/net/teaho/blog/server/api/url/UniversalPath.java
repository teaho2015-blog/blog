/**
 * @author teaho2015@gmail.com
 * since 2017/5/15
 */
package net.teaho.blog.server.api.url;

public class UniversalPath extends Path {

    private String universalWord;

    public UniversalPath(Material m, String s) {
        this.universalWord = s;
        this.material = m;
    }

    @Override
    public String getName() {
        return new StringBuffer()
                .append(material.getName())
                .append("/")
                .append(universalWord)
                .toString();
    }
}
