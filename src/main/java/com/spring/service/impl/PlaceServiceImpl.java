package com.spring.service.impl;

import com.spring.entities.Place;
import com.spring.repositories.PlaceRepository;
import com.spring.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceRepository placeRepository;

    @Override
    public List<Place> findAllPlace() {
        return placeRepository.findAll();
    }
}
