package ddulaev.rxgoods.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.Document;

@Data
@RequiredArgsConstructor
public class Product {
    private final int id;
    private final double costUSD;
    private final String name;

    public double getCostWithCurrency(Currency currency) {
        return Currency.convertTo(costUSD, Currency.USD, currency);
    }

    public Document getDocument() {
        return new Document()
                .append("id", id)
                .append("cost_usd", costUSD)
                .append("name", name);
    }

}
