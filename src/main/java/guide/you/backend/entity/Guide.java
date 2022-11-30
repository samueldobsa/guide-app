package guide.you.backend.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("guide")
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Guide {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    @Builder.Default
    @Id
    private UUID id = UUID.randomUUID();
    private String name;
    private String surname;
    private String userName;
    private String email;
    private String phone_number;
    private UUID userReviewId;
    private String description;
    private String language;
    private String destination;
    private boolean isActive;

    @CreatedDate
    private LocalDateTime createdDate;

}
