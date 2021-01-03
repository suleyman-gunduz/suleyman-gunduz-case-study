package com.suleymangunduz.casestudyapp.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListResponse<T> {
    private List<T> items;
    private Integer total;
    private Integer count;
    @JsonProperty("count_nolimit")
    private Integer countNoLimit;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCountNoLimit() {
        return countNoLimit;
    }

    public void setCountNoLimit(Integer countNoLimit) {
        this.countNoLimit = countNoLimit;
    }
}
