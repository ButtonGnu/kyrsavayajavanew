package com.example.kyrsavayajava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DamageDB {
    private String category;
    private String subCategory;
    private String subCategoryType;

    public DamageDB(String category, String subCategory, String subCategoryType) {
        this.category = category;
        this.subCategory = subCategory;
        this.subCategoryType = subCategoryType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubCategoryType() {
        return subCategoryType;
    }

    public void setSubCategoryType(String subCategoryType) {
        this.subCategoryType = subCategoryType;
    }

    @Override
    public String toString() {
        List<String> strings = Arrays.asList(category, subCategory, subCategoryType);
        return Stream.of(category, subCategory, subCategoryType)
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.joining(":"));
    }
}
