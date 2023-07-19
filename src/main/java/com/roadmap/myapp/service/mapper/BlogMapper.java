package com.roadmap.myapp.service.mapper;

import com.roadmap.myapp.domain.*;
import com.roadmap.myapp.service.dto.BlogDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Blog} and its DTO {@link BlogDTO}.
 */
@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface BlogMapper extends EntityMapper<BlogDTO, Blog> {
    @Mapping(target = "user", source = "user", qualifiedByName = "login")
    BlogDTO toDto(Blog s);

    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    BlogDTO toDtoName(Blog blog);
}
