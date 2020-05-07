/**
 * @author teaho2015@gmail.com
 * since 2017/5/11
 */
package net.teaho.blog.server.api.dto;

import net.teaho.blog.server.api.url.DevelopmentPath;
import net.teaho.blog.server.api.url.Domain;

import java.util.ArrayList;
import java.util.List;

public class SimpleDataDTO<T> {

    private String documentation_url = new DevelopmentPath(new Domain()).getName();

    private String message = "";

    private T data;

    private List<String> error = new ArrayList<>();

    public String getDocumentation_url() {
        return documentation_url;
    }

    public void setDocumentation_url(String documentation_url) {
        this.documentation_url = documentation_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
