package com.recontract.recontract.controller;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.service.SearchService;
import com.recontract.recontract.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/searches")
public class SearchController {

    private final SearchServiceImpl searchServiceImpl;

    @Autowired
    public SearchController(SearchServiceImpl searchServiceImpl) {
        this.searchServiceImpl = searchServiceImpl;
    }

    @GetMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public Search getAllSearches(@PathVariable ("id") Long userId) {
        return searchServiceImpl.getAllSearches(userId);
    }

    @PostMapping(value = "/new/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> newSearch(@PathVariable ("id") Long userId, @RequestBody Search search) {
        searchServiceImpl.createSearch(search, userId);
        return ResponseEntity.ok("Search created");
    }

    @GetMapping(value = "/boolean/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public boolean searchIsPresent(@PathVariable ("id") Long userId) {
        return searchServiceImpl.checkSearchIsPresentOnUser(userId);
    }





}
