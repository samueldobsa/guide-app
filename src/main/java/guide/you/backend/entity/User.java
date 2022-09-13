package guide.you.backend.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("user")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone_number;
    private BigDecimal money;
    private UUID completedTripId;
    private UUID favoriteTripId;

    @CreatedDate
    private LocalDateTime createdDate;

}
