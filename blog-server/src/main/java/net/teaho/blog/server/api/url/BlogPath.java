/**
 * @author teaho2015@gmail.com
 * since 2017/5/15
 */
package net.teaho.blog.server.api.url;

public class BlogPath extends Path {

    public BlogPath(Material material) {
        this.material = material;
    }

    @Override
    public String getName() {
        return new StringBuffer()
                .append(material.getName())
                .append("/blog")
                .toString();
    }

}
