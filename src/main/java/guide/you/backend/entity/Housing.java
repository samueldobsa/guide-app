package guide.you.backend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("housing")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Housing {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    @Builder.Default
    @Id
    private UUID id = UUID.randomUUID();
    private UUID tripId;
    private int countBeds;
    private boolean inGuidesHome;

}
