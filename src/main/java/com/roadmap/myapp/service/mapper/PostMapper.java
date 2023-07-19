package com.roadmap.myapp.service.mapper;

import com.roadmap.myapp.domain.*;
import com.roadmap.myapp.service.dto.PostDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Post} and its DTO {@link PostDTO}.
 */
@Mapper(componentModel = "spring", uses = { BlogMapper.class, TagMapper.class })
public interface PostMapper extends EntityMapper<PostDTO, Post> {
    @Mapping(target = "blog", source = "blog", qualifiedByName = "name")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "nameSet")
    PostDTO toDto(Post s);

    @Mapping(target = "removeTag", ignore = true)
    Post toEntity(PostDTO postDTO);
}
