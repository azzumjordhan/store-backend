package com.bencu.arcanstore.shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomPagination<T> {
    List<T> content;
    long totalElements;
    int itemsCount;
    int itemsPerPage;
    int totalPages;
    int currentPage;
}
