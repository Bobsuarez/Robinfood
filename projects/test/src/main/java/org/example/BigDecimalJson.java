package org.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigDecimalJson {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();


        MyClass myObject = MyClass.builder().value(new BigDecimal("64300")).build();

        // Serializar el objeto
        String jsonString = mapper.writeValueAsString(myObject);
        System.out.println("Serialized JSON: " + jsonString);

        // Deserializar el objeto
        MyClass deserializedObject = mapper.readValue(jsonString, MyClass.class);
        System.out.println("Deserialized BigDecimal: " + deserializedObject.getValue());


        MyClass zeroObject = new MyClass(new BigDecimal("0"));
        String zeroJson = mapper.writeValueAsString(zeroObject);
        System.out.println("Serialized JSON with 0: " + zeroJson);

        MyClass deserializedZeroObject = mapper.readValue(zeroJson, MyClass.class);
        System.out.println(
                "Deserialized BigDecimal with 0: " + deserializedZeroObject.getValue());

    }
}

class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeObject(value.setScale(2, RoundingMode.HALF_UP));
    }
}

class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(
            JsonParser p, DeserializationContext ctxt
    ) throws IOException, JsonProcessingException {
        String value = p.getText();
        BigDecimal bd = new BigDecimal(value);
        return bd.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class MyClass {

    @JsonDeserialize(using = BigDecimalDeserializer.class)
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal value;


}
