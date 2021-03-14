package ddulaev.rxgoods.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.Document;

@AllArgsConstructor
@Data
public class User {
    private final int id;
    private final String name;
    private final Currency currency;


    public User(Document doc) {
        this(doc.getInteger("id"), doc.getString("name"), Currency.valueOf(doc.getString("currency")));
    }

    public Document getDocument() {
        return new Document()
                .append("id", id)
                .append("name", name)
                .append("currency", currency.name());
    }
}
