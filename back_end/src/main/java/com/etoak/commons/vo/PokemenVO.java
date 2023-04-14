package com.etoak.commons.vo;

import com.etoak.entity.Panda;
import com.etoak.entity.Pokemen;
import lombok.Data;

import java.util.List;

@Data
public class PokemenVO extends Pokemen {
    private String attributes;
    private List<Integer> attributesLists;
    
}
