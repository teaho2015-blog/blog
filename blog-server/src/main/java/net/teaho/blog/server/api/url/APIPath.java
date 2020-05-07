/**
 * @author teaho2015@gmail.com
 * since 2017/5/15
 */
package net.teaho.blog.server.api.url;

public class APIPath extends Path {
    public APIPath(Material material) {
        this.material = material;
    }

    @Override
    public String getName() {
        return new StringBuffer()
                .append(material.getName())
                .append("/api/v1")
                .toString();
    }

}
