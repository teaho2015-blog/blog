/**
 * @author teaho2015@gmail.com
 * since 2017/5/11
 */
package com.tea.api.dto;

import com.tea.Builder;
import com.tea.api.url.DevelopmentPath;
import com.tea.api.url.Domain;

import java.util.ArrayList;
import java.util.List;

public class NotFoundDTO {

    private String documentation_url = new DevelopmentPath(new Domain()).getName();

    private String message = "";

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


    public static InnerBuilder newBuilder() {
        return new InnerBuilder();
    }

    public static class InnerBuilder implements Builder<NotFoundDTO> {

        private NotFoundDTO buildObj;

        public InnerBuilder() {
            buildObj = new NotFoundDTO();
        }

        public InnerBuilder documentation_url(String str) {
            buildObj.message = str;
            return this;
        }

        public InnerBuilder message(String str) {
            buildObj.message = str;
            return this;
        }

        public InnerBuilder defualtDocumentation_url() {
            buildObj.documentation_url = new DevelopmentPath(new Domain()).getName();
            return this;
        }

        public InnerBuilder addErrorMsg(String message) {
            buildObj.error.add(message);
            return this;
        }
        @Override
        public NotFoundDTO build() {
            return buildObj;
        }
    }
}
