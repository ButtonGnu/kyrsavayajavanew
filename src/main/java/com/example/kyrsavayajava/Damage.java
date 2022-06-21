package com.example.kyrsavayajava;

import java.util.List;
import java.util.Objects;

public class Damage {

    private String                  category;
    private List<DamageSubCategory> subCategories;

    public Damage() {
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public List<DamageSubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<DamageSubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Damage)) return false;
        Damage damage = (Damage) o;
        return Objects.equals(category, damage.category) && Objects.equals(subCategories, damage.subCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, subCategories);
    }

}
