package dlugolecki.pawel.dto;

import dlugolecki.pawel.exception.MyException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {

    private Long id;
    private String name;

    public CountryDTO(CountryDTOBuilder countryDTOBuilder) {
        this.id = countryDTOBuilder.id;
        this.name = countryDTOBuilder.name;
    }

    public static CountryDTO.CountryDTOBuilder builder() {
        return new CountryDTOBuilder();
    }

    public static class CountryDTOBuilder {
        private Long id;
        private String name;

        private static final String NAME_REGEX = "[A-Z\\s]+";

        public CountryDTOBuilder id(Long id) {
            if (id != null) {
                this.id = id;
            }
            return this;
        }

        public CountryDTOBuilder name(String name) {
            try {
                if (name == null || !name.matches(NAME_REGEX)) {
                    throw new MyException("COUNTRY", "Country name is not correct");
                }
                this.name = name;
                return this;
            } catch (MyException e) {
                throw new MyException("COUNTRY", "Country name is not correct");
            }
        }

        public CountryDTO build() {
            return new CountryDTO(this);
        }
    }
}