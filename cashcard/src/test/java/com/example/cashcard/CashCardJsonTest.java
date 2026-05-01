package com.example.cashcard;

import com.example.cashcard.model.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

/*
  The @JsonTest annotation marks the CashCardJsonTest as a test class which uses
  the Jackson framework (which is included as part of Spring).
  This provides extensive JSON testing and parsing support. 
  It also establishes all the related behavior to test JSON objects.
 */
@JsonTest
public class CashCardJsonTest {

    @Autowired
/* JacksonTester is a convenience wrapper to the Jackson JSON parsing library.
 It handles serialization and deserialization of JSON objects. */
    private JacksonTester<CashCard> json;

    @Test
    void cashCardSerializationTest() throws IOException {

        CashCard cashCard = new CashCard(99L, 123.45);

        // You need to create a file called expected.json in src/test/resources
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                                        .isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                                        .isEqualTo(123.45);
    }

/* Deserialization is the reverse process of serialization.
 It transforms data from a file or byte stream back into an object for your
 application. This makes it possible for an object serialized on one platform
 to be deserialized on a different platform. For example, your client
 application can serialize an object on Windows while the backend would
 deserialize it on Linux.
 Serialization and deserialization work together to transform/recreate
 data objects to/from a portable format. The most popular data format for
 serializing data is JSON. */
    @Test
    void cashCardDeserializationTest() throws IOException {

        String expected = """
                {
                    "id": 99,
                    "amount": 123.45
                }
                """;
        assertThat(json.parse(expected)).isEqualTo(new CashCard(99L, 123.45));
        assertThat(json.parseObject(expected).id()).isEqualTo(99);
        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
    }

}
