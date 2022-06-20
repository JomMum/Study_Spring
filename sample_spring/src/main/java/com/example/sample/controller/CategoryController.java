package com.example.sample.controller;
import com.example.sample.controller.dto.CategoryDTO;
import com.example.sample.entity.Category;
import com.example.sample.repository.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController
{
    private final CategoryService categoryService;

    @PostMapping("")
    public Category createCategory(@RequestBody CategoryDTO categoryDTO)
    {
        return categoryService.createCategory(categoryDTO);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("")
    public Page<Category> getCategories(Pageable pageable, @RequestParam String keyword)
    {
        return categoryService.getCategories(pageable, keyword);
    }
}