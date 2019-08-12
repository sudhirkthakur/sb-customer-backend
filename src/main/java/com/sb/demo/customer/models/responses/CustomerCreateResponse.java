package com.sb.demo.customer.models.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class CustomerCreateResponse {

	
	@ApiModelProperty(required = true, value = "ok", example = "5")
	@JsonProperty("ok")
	private String ok;
	
	@ApiModelProperty(required = true, value = "id", example = "5")
	@JsonProperty("id")
	private String id;

	@ApiModelProperty(required = true, value = "rev", example = "5")
	@JsonProperty("rev")
	private String rev;

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerCreateResponse [ok=");
		builder.append(ok);
		builder.append(", id=");
		builder.append(id);
		builder.append(", rev=");
		builder.append(rev);
		builder.append("]");
		return builder.toString();
	}


}
