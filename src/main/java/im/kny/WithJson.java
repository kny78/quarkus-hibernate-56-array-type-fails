package im.kny;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.array.EnumArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.array.UUIDArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

import static org.hibernate.id.enhanced.SequenceStyleGenerator.INCREMENT_PARAM;
import static org.hibernate.id.enhanced.SequenceStyleGenerator.SEQUENCE_PARAM;

@Entity
@Table(name = "with_json")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
        @TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class),
        @TypeDef(name = "pgsql_array_string", typeClass = StringArrayType.class),
        @TypeDef(name = "pgsql_array_part",
                defaultForType = Part[].class,
                typeClass = EnumArrayType.class,
                parameters = {@Parameter(name = EnumArrayType.SQL_ARRAY_TYPE, value = "part")}
        ),
        @TypeDef(name = "pgsql_array_uuid", typeClass = UUIDArrayType.class),
        @TypeDef(name = "pgsql_range", typeClass = PostgreSQLRangeType.class)
})

public class WithJson {
    @Id
    @GeneratedValue(generator = "with_json_id_seq")
    @GenericGenerator(
            name = "with_json_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = SEQUENCE_PARAM, value = "with_json_id_seq"),
                    @Parameter(name = INCREMENT_PARAM, value = "50")
            }
    )
    private Long id;


    @Column(name = "content", nullable = false, updatable = false)
    @Type(type = "jsonb")
    private JsonNode content;

    @Column(name="parts")
    @Type(type="pgsql_array_part")
    private Part[] parts;

    public WithJson() {
    }

    public WithJson(JsonNode content, Part[] parts) {
        this.content = content;
        this.parts=parts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JsonNode getContent() {
        return content;
    }

    public void setContent(JsonNode content) {
        this.content = content;
    }
}
