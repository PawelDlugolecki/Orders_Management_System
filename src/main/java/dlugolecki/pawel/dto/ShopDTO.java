package dlugolecki.pawel.dto;

import dlugolecki.pawel.exception.MyException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {

    private Long id;
    private String name;
    private CountryDTO countryDTO;

    public ShopDTO(ShopDTOBuilder shopDTOBuilder) {
        this.id = shopDTOBuilder.id;
        this.name = shopDTOBuilder.name;
        this.countryDTO = shopDTOBuilder.countryDTO;
    }

    public static ShopDTO.ShopDTOBuilder builder() {
        return new ShopDTOBuilder();
    }

    public static class ShopDTOBuilder {
        private Long id;
        private String name;
        private CountryDTO countryDTO;

        private static final String NAME_REGEX = "[A-Z\\s]+";

        public ShopDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ShopDTOBuilder name(String name) {
            try {
                if (name == null || !name.matches(NAME_REGEX)) {
                    throw new MyException("SHOP", "Shop name is not correct");
                }
                this.name = name;
                return this;
            } catch (MyException e) {
                throw new MyException("SHOP", "Shop builder");
            }
        }

        public ShopDTOBuilder countryDTO(CountryDTO countryDTO) {
            this.countryDTO = countryDTO;
            return this;
        }

        public ShopDTO build() {
            return new ShopDTO(this);
        }
    }
}