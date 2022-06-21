package com.example.kyrsavayajava;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class DamageGenerator {
    private ThreadLocalRandom random;

    public DamageGenerator() {
        this.random = ThreadLocalRandom.current();
        ;
    }

    public Damage generateDamage(Damage[] damages) {
        Damage generatedDamage = new Damage();

        // выбираем основную категорию поломки
        int damageCategoryIndex = random.nextInt(0, damages.length);

        Damage currentDamage = damages[damageCategoryIndex];
        generatedDamage.setCategory(currentDamage.getCategory());

        // Выбираем подкатегорию поломки. Так как их может быть несколько, сначала определяем количество подкатегорий
        int damageSubCategoryCount = random.nextInt(1, currentDamage.getSubCategories().size());
        // генерируем подкатегории поломки
        Set<DamageSubCategory> generatedDamageSubCategories = new HashSet<>();
        while (generatedDamageSubCategories.size() != damageSubCategoryCount) {
            int damageSubCategoryIndex = random.nextInt(0, currentDamage.getSubCategories().size());

            DamageSubCategory generatedDamageSubCategory = new DamageSubCategory();
            DamageSubCategory currentDamageSubCategory   = currentDamage.getSubCategories().get(damageSubCategoryIndex);
            generatedDamageSubCategory.setType(currentDamageSubCategory.getType());

            // у некоторых подкатегорий поломок нет подкатегорий
            if (currentDamageSubCategory.getSubType() != null) {
                // так как у подкатегории поломки есть еще подкатегории и их может быть несколько,
                // генерируем список таких подкатегорий
                Set<String> generatedDamageSubCategorySubTypes = new HashSet<>();
                int         damageSubCategorySubTypesCount     = random.nextInt(1, currentDamageSubCategory.getSubType().size() + 1);
                while (generatedDamageSubCategorySubTypes.size() != damageSubCategorySubTypesCount) {
                    int nextIndex = random.nextInt(0, damageSubCategorySubTypesCount);
                    generatedDamageSubCategorySubTypes.add(currentDamageSubCategory.getSubType().get(nextIndex));
                }
                generatedDamageSubCategory.setSubType(generatedDamageSubCategorySubTypes.stream().toList());
            }
            generatedDamageSubCategories.add(generatedDamageSubCategory);
        }
        generatedDamage.setSubCategories(generatedDamageSubCategories.stream().toList());

        return generatedDamage;
    }
}
