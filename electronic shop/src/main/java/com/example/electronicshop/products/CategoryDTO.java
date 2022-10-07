package com.example.electronicshop.products;

/**
 * category data transfer object
 * which are the name of the category and id
 */
public class CategoryDTO {
    private String categoryName;
    private int categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDTO)) return false;

        CategoryDTO that = (CategoryDTO) o;

        if (getCategoryId() != that.getCategoryId()) return false;
        return getCategoryName() != null ? getCategoryName().equals(that.getCategoryName()) : that.getCategoryName() == null;
    }

    @Override
    public int hashCode() {
        int result = getCategoryName() != null ? getCategoryName().hashCode() : 0;
        result = 31 * result + getCategoryId();
        return result;
    }
}
