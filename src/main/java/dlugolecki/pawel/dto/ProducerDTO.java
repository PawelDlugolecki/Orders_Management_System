package dlugolecki.pawel.dto;

import dlugolecki.pawel.exception.MyException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProducerDTO {

    private Long id;
    private String name;
    private CountryDTO countryDTO;
    private TradeDTO tradeDTO;

    public ProducerDTO(ProducerDTOBuilder producerDTOBuilder) {
        this.id = producerDTOBuilder.id;
        this.name = producerDTOBuilder.name;
        this.countryDTO = producerDTOBuilder.countryDTO;
        this.tradeDTO = producerDTOBuilder.tradeDTO;
    }

    public static ProducerDTO.ProducerDTOBuilder builder() {
        return new ProducerDTOBuilder();
    }

    public static class ProducerDTOBuilder {
        private Long id;
        private String name;
        private CountryDTO countryDTO;
        private TradeDTO tradeDTO;

        private static final String NAME_REGEX = "[A-Z\\s]+";

        public ProducerDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProducerDTOBuilder name(String name) {
            try {
                if (name == null || !name.matches(NAME_REGEX)) {
                    throw new MyException("PRODUCER", "Producer name is not correct");
                }
                this.name = name;
                return this;
            } catch (MyException e) {
                throw new MyException("PRODUCER", "Producer name is not correct");
            }
        }

        public ProducerDTOBuilder countryDTO(CountryDTO countryDTO) {
            this.countryDTO = countryDTO;
            return this;
        }

        public ProducerDTOBuilder tradeDTO(TradeDTO tradeDTO) {
            this.tradeDTO = tradeDTO;
            return this;
        }

        public ProducerDTO build() {
            return new ProducerDTO(this);
        }
    }
}