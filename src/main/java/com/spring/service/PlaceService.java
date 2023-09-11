package com.spring.service;

import com.spring.entities.Place;
import com.spring.entities.Users;

import java.util.List;

public interface PlaceService {
    List<Place> findAllPlace();
}
