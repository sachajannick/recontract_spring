package com.recontract.recontract.service;

import com.recontract.recontract.domain.Search;
import com.recontract.recontract.dto.dtoSearch;
import com.recontract.recontract.dto.dtoSearchHiring;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface SearchService {

    List<dtoSearch> findSearchFreelancer();
    List<dtoSearchHiring> findSearchHiring();
    Long findSearchIdByUserId(long id);
    void createSearch(Search search, long id);
    void updateSearch(Long searchId,
                      String functionTitle,
                      int amount,
                      String location,
                      String headline,
                      String email,
                      String fullName);
    boolean checkSearchIsPresentOnUser(long id);
    void uploadProfilePicture(Long searchId, MultipartFile file) throws IOException;
    byte[] getProfilePicture(Long searchId);
}
