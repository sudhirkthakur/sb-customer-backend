package com.sb.demo.customer.models.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
	

	@ApiModelProperty(required = true, value = "total_rows", example = "5")
	@JsonProperty("total_rows")
	private String total_rows;
	
	@ApiModelProperty(required = true, value = "rows", example = "5")
	@JsonProperty("rows")
	private List<CustomerResponseRows> rows;


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerResponse [total_rows=");
		builder.append(total_rows);
		builder.append(", rows=");
		builder.append(rows);
		builder.append("]");
		return builder.toString();
	}

	public String getTotal_rows() {
		return total_rows;
	}

	public void setTotal_rows(String total_rows) {
		this.total_rows = total_rows;
	}

	public List<CustomerResponseRows> getRows() {
		return rows;
	}

	public void setRows(List<CustomerResponseRows> rows) {
		this.rows = rows;
	}


}
