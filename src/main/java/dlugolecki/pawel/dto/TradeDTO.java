package dlugolecki.pawel.dto;

import dlugolecki.pawel.exception.MyException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeDTO {

    private Long id;
    private String name;

    public TradeDTO(TradeDTOBuilder tradeDTOBuilder) {
        this.id = tradeDTOBuilder.id;
        this.name = tradeDTOBuilder.name;
    }

    public static TradeDTO.TradeDTOBuilder builder() {
        return new TradeDTOBuilder();
    }

    public static class TradeDTOBuilder {
        private Long id;
        private String name;

        private static final String NAME_REGEX = "[A-Z\\s]+";

        public TradeDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TradeDTOBuilder name(String name) {
            try {
                if (name == null || !name.matches(NAME_REGEX)) {
                    throw new MyException("TRADE", "Trade name is not correct");
                }
                this.name = name;
                return this;
            } catch (MyException e) {
                throw new MyException("TRADE", "Trade builder");
            }
        }

        public TradeDTO build() {
            return new TradeDTO(this);
        }
    }
}