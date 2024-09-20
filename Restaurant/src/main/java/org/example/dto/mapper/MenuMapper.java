package org.example.dto.mapper;

import org.example.dto.MenuDTO;
import org.example.model.Menu;
import org.example.model.Restaurant;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    @Mapping(source = "restaurant.id", target = "restaurantId")
    MenuDTO toMenuDTO(Menu menu);

    @Mapping(source = "restaurantId", target = "restaurant.id")
    Menu toMenu(MenuDTO menuDTO);
}