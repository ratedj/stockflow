package com.cuscatlan.inventory_service.utils;

import com.cuscatlan.inventory_service.dto.MovementResponse;
import com.cuscatlan.inventory_service.models.Movement;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface MovementMapper {

    MovementResponse toDto(Movement movement);

    List<MovementResponse> toDtoList(List<Movement> movements);
}
