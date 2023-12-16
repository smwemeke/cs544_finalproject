package edu.miu.cs.cs544.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public class Pageable {
    protected Integer page;
    protected Integer len;

    public Pageable(Integer page, Integer len) {
        this.page = page;
        this.len = len;
    }
}
