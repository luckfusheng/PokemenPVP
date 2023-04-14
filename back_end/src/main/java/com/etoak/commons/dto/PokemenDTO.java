package com.etoak.commons.dto;

import com.etoak.entity.Attributes;
import com.etoak.entity.Pokemen;
import lombok.Data;

import java.util.List;


@Data
public class PokemenDTO extends Pokemen {
    private List<Attributes> attributesList;
}
