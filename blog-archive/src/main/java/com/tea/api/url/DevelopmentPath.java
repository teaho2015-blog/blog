/**
 * @author teaho2015@gmail.com
 * since 2017/5/15
 */
package com.tea.api.url;

public class DevelopmentPath extends Path {
    public DevelopmentPath(Material material) {
        this.material = material;
    }

    @Override
    public String getName() {
        return new StringBuffer()
                .append(material.getName())
                .append("/development")
                .toString();
    }
}
