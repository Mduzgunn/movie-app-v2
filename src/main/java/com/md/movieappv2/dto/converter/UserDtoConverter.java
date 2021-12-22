package com.md.movieappv2.dto.converter;

import com.md.movieappv2.dto.UserDto;
import com.md.movieappv2.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return new UserDto(
                from.getId(),
                from.getUsername(),
                from.getEmail(),
                from.getCreationDate(),
                from.getUpdatedDate()

        );
    }
    public List<UserDto> convertToUserDtoList(List<User> userList) {
        return userList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
