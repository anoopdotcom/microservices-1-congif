package com.CropDeal.AuthService.mapper;

import com.CropDeal.AuthService.dto.RegisterUserDto;
import com.CropDeal.AuthService.dto.UserDto;
import com.CropDeal.AuthService.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

    User mapToUser(RegisterUserDto registerDto);
}