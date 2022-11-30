package guide.you.backend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("user_review")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserReview {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    @Builder.Default
    @Id
    private UUID id = UUID.randomUUID();
    private UUID userId;
    private String title;
    private String content;
    private double rate;

}
