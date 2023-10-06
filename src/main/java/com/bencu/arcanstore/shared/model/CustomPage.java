package com.bencu.arcanstore.shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {
    List<T> content;
    CustomPageable pageable;

    public CustomPage(Page<T> page) {
        this.content = page.getContent();
        this.pageable = new CustomPageable(
                page.getTotalElements(),
                page.getNumberOfElements(),
                page.getPageable().getPageSize(),
                page.getTotalPages(),
                page.getPageable().getPageNumber()
        );
    }

    @Data
    @AllArgsConstructor
    static class CustomPageable {
        long totalElements;
        int itemsCount;
        int itemsPerPage;
        int totalPages;
        int currentPage;
    }
}
