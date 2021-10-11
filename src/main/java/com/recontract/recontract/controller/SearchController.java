package com.recontract.recontract.controller;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.dto.dtoSearch;
import com.recontract.recontract.dto.dtoTest;
import com.recontract.recontract.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/searches")
public class SearchController {

    private final SearchServiceImpl searchServiceImpl;

    @Autowired
    public SearchController(SearchServiceImpl searchServiceImpl) {
        this.searchServiceImpl = searchServiceImpl;
    }

    @GetMapping(value = "/id/all2/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public Long findSearchIdByUserId(@PathVariable ("id") Long userId) {
        return searchServiceImpl.findSearchIdByUserId(userId);
    }

    @GetMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public Search findSearchById(@PathVariable ("id") Long userId) {
        return searchServiceImpl.findSearchById(userId);
    }

    @PostMapping(value = "/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> createSearch(@PathVariable ("id") Long userId, @RequestBody Search search) {
        searchServiceImpl.createSearch(search, userId);
        return ResponseEntity.ok("Search created");
    }

    @PatchMapping(value="/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> updateSearch(@PathVariable ("id") Long searchId, @RequestBody dtoSearch dto) {
        searchServiceImpl.updateSearch(dto.newFunctionTitle,dto.newAmount, searchId);
        return ResponseEntity.ok("Search updated");
    }

    @GetMapping(value = "/boolean/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public boolean checkSearchIsPresentOnUser(@PathVariable ("id") Long userId) {
        return searchServiceImpl.checkSearchIsPresentOnUser(userId);
    }
}
