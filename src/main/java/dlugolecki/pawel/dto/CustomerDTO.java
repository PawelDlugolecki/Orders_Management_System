package dlugolecki.pawel.dto;

import dlugolecki.pawel.exception.MyException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long id;
    private String name;
    private String surname;
    private int age;
    private CountryDTO countryDTO;

    public CustomerDTO(CustomerDTOBuilder customerDTOBuilder) {
        this.id = customerDTOBuilder.id;
        this.name = customerDTOBuilder.name;
        this.surname = customerDTOBuilder.surname;
        this.age = customerDTOBuilder.age;
        this.countryDTO = customerDTOBuilder.countryDTO;
    }

    public static CustomerDTO.CustomerDTOBuilder builder() {
        return new CustomerDTOBuilder();
    }

    public static class CustomerDTOBuilder {
        private Long id;
        private String name;
        private String surname;
        private int age;
        private CountryDTO countryDTO;

        private static final String NAME_REGEX = "[A-Z\\s]+";
        private static final String SURNAME_REGEX = "[A-Z\\s]+";

        public CustomerDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CustomerDTOBuilder name(String name) {
            try {
                if (name == null || !name.matches(NAME_REGEX)) {
                    throw new MyException("CUSTOMER", "Customer name is not correct");
                }
                this.name = name;
                return this;
            } catch (MyException e) {
                throw new MyException("CUSTOMER", "Customer builder");
            }
        }

        public CustomerDTOBuilder surname(String surname) {
            try {
                if (surname == null || !surname.matches(SURNAME_REGEX)) {
                    throw new MyException("CUSTOMER", "Customer surname is not correct");
                }
                this.surname = surname;
                return this;
            } catch (MyException e) {
                throw new MyException("CUSTOMER", "Customer builder");
            }
        }

        public CustomerDTOBuilder age(int age) {
            try {
                if (age < 18) {
                    throw new MyException("CUSTOMER", "Customer age is not correct");
                }
                this.age = age;
                return this;
            } catch (MyException e) {
                throw new MyException("CUSTOMER", "Customer builder");
            }
        }

        public CustomerDTOBuilder country(CountryDTO countryDTO) {
            this.countryDTO = countryDTO;
            return this;
        }

        public CustomerDTO build() {
            return new CustomerDTO(this);
        }
    }
}