package com.proyect.base.persistencia.dto.select;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectItem {
    private Long value;
    private String label;
}
