package kkalinowski.springframework.recipeproject.service;

import kkalinowski.springframework.recipeproject.domain.Recipe;
import kkalinowski.springframework.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Krzysztof Kalinowski on 05/12/2019.
 */

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(Long recipeId, MultipartFile file){
        log.debug("Received a file");
        try {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;
            for(byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);

        }
        catch (IOException e){
            log.error("Error ocurred ", e);

            e.printStackTrace();
        }
    }
}
