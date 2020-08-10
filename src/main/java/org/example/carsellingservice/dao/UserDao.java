package org.example.carsellingservice.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserDao {
    private final String id;
    private final String name;
    private final String userpic;
    private final String email;
    private final String gender;
    private final String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime lastVisit;
}
