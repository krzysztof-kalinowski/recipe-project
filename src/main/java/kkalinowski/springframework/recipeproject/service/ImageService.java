package kkalinowski.springframework.recipeproject.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Krzysztof Kalinowski on 05/12/2019.
 */

public interface ImageService {

    void saveImageFile(Long recipeId, MultipartFile file);
}
