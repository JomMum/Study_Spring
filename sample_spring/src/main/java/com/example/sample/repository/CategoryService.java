package com.example.sample.repository;

import com.example.sample.controller.dto.CategoryDTO;
import com.example.sample.entity.Category;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService
{
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(CategoryDTO categoryDTO)
    {
        // Repository에서 데이터 가져오기
        Optional<Category> findOne = categoryRepository.findByName(categoryDTO.getName());

        // 데이터가 이미 존재하면 Exception을 발생하고 종료
        if(findOne.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이름입니다.");
        }

        //아니면 category 생성
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        category = categoryRepository.save(category);

        return category;
    }

    public Category getCategoryById(Long id)
    {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "카테고리가 존재하지 않습니다."));
    }

    public Page<Category> getCategories(Pageable pageable, String keyword)
    {
        if(keyword == null)
        {
            return categoryRepository.findAll(pageable);
        }
        else
        {
            return categoryRepository.findByNameContains(pageable, keyword);
        }
    }
}
