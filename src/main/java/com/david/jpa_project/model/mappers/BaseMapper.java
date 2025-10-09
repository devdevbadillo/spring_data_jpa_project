package com.david.jpa_project.model.mappers;

import com.david.jpa_project.controller.dto.out.PageOut;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BaseMapper<T, V> {
    default  PageOut<T> toPageOut(Page<V> page) {
        return PageOut.<T>builder()
                .content(toOutList(page.getContent()))
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    List<T> toOutList(List<V> addresses);

    T toOut(V address);
}
