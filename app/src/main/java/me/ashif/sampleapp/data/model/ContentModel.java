package me.ashif.sampleapp.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by Ashif on 4/8/17,August,2017
 * github.com/SheikhZayed
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "content"
})
public class ContentModel {

    @JsonProperty("content")
    private List<ContentItems> content = null;

    @JsonProperty("content")
    public List<ContentItems> getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(List<ContentItems> content) {
        this.content = content;
    }
}
