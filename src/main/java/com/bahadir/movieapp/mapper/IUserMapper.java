package com.bahadir.movieapp.mapper;

import com.bahadir.movieapp.dto.request.RegisterRequestDto;
import com.bahadir.movieapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper( IUserMapper.class );

    User dtoToUser(RegisterRequestDto dto);
}
