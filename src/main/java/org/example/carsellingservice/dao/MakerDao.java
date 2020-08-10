package org.example.carsellingservice.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MakerDao {
    private final int id;
    private final String name;
}
