package dlugolecki.pawel.dto;

import dlugolecki.pawel.exception.MyException;
import lombok.*;

@Getter
@Setter
public class CategoryDTO {

    private Long id;
    private String name;

    public CategoryDTO(CategoryDTOBuilder categoryDTOBuilder) {
        this.id = categoryDTOBuilder.id;
        this.name = categoryDTOBuilder.name;
    }

    public static CategoryDTO.CategoryDTOBuilder builder() {
        return new CategoryDTOBuilder();
    }

    public static class CategoryDTOBuilder {
        private Long id;
        private String name;

        private static final String NAME_REGEX = "[A-Z\\s]+";

        public CategoryDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CategoryDTOBuilder name(String name) {
            try {
                if (name == null || !name.matches(NAME_REGEX)) {
                    throw new MyException("CATEGORY", "Category name is not correct");
                }
                this.name = name;
                return this;
            } catch (MyException e) {
                e.printStackTrace();
                throw new MyException("CATEGORY", "Category builder");
            }
        }

        public CategoryDTO build() {
            return new CategoryDTO(this);
        }
    }
}