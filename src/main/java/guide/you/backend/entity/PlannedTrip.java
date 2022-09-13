package guide.you.backend.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("planned_trip")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlannedTrip {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private UUID userId;
    private UUID guideId;
    private UUID tripId;
//    LocalDateTime or String?
    private LocalDateTime plannedDate;
    private String destination;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @CreatedDate
    private LocalDateTime createdDate;

}
