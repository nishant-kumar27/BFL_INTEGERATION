package com.bfl.gencode.merchhier.OrderAttachment;

import java.sql.Blob;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "items",
    "hasMore",
    "limit",
    "offset",
    "count",
    "links",
    "attachment"
})
public class OrderAttachment {
	
    @JsonProperty("items")
    private List<Item> items;
    @JsonProperty("hasMore")
    private Boolean hasMore;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("links")
    private List<Link> links;
    @JsonProperty("attachment")
    private Blob file;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonProperty("hasMore")
    public Boolean getHasMore() {
        return hasMore;
    }

    @JsonProperty("hasMore")
    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("attachment")
	public Blob getFile() {
		return file;
	}

	@JsonProperty("attachment")
	public void setFile(Blob file) {
		this.file = file;
	}

}
