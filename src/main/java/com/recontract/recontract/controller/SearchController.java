package com.recontract.recontract.controller;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.dto.dtoSearch;
import com.recontract.recontract.dto.dtoNewSearch;
import com.recontract.recontract.dto.dtoSearchHiring;
import com.recontract.recontract.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping(value = "/freelancer")
    @PreAuthorize("hasRole('USER')")
    public List<dtoSearch> findSearchFreelancers() {
        return searchServiceImpl.findSearchFreelancer();
    }

    @GetMapping(value = "/hiring")
    @PreAuthorize("hasRole('USER')")
    public List<dtoSearchHiring> findSearchHiring() {
        return searchServiceImpl.findSearchHiring();
    }

    @GetMapping(value = "/long/id/{id}")
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
    public ResponseEntity<Object> updateSearch(@PathVariable ("id") Long searchId, @RequestBody dtoNewSearch dto) {
        searchServiceImpl.updateSearch(searchId,
                dto.functionTitle,
                dto.amount,
                dto.location,
                dto.headline,
                dto.email,
                dto.fullName);
        return ResponseEntity.ok("Search updated");
    }

    @GetMapping(value = "/boolean/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public boolean checkSearchIsPresentOnUser(@PathVariable ("id") Long userId) {
        return searchServiceImpl.checkSearchIsPresentOnUser(userId);
    }

    @PatchMapping(value="/profile-picture/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> uploadProfilePicture(@PathVariable ("id") long searchId, @RequestParam("file") MultipartFile file) throws IOException {
        searchServiceImpl.uploadProfilePicture(searchId, file);
        return ResponseEntity.ok("Profile picture uploaded");
    }

    @GetMapping(value = "/profile-picture/id/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable ("id") long searchId) {
        var picture = searchServiceImpl.getProfilePicture(searchId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"picture\"").body(picture);
    }
}
