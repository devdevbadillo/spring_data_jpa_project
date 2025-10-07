package com.david.jpa_project.controller.dto.out;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PageOut <T>{

    private List<T> content;

    private boolean first;
    private boolean last;
    private long totalElements;
    private long totalPages;
}
