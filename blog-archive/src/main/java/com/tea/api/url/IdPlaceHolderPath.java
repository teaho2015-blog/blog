/**
 * @author teaho2015@gmail.com
 * since 2017/5/15
 */
package com.tea.api.url;

public class IdPlaceHolderPath extends Path {
    public IdPlaceHolderPath(Material m) {
        this.material = m;
    }

    @Override
    public String getName() {
        return new StringBuffer()
                .append(material.getName())
                .append("/{id}")
                .toString();
    }
}
