package guide.you.backend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Table("completed_trip")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompletedTrip {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    @Builder.Default
    @Id
    private UUID id = UUID.randomUUID();
    private UUID guideId;
    private UUID userId;
    private UUID tripId;
    private Timestamp finishDate;

}
