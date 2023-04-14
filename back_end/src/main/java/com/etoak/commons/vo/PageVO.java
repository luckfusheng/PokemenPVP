package com.etoak.commons.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVO<T> {

    private Integer pageNumber;

    private Integer pageSize;

    private List<T>  rows;

    private long total;

}
