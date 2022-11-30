package guide.you.backend.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("trip")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    @Builder.Default
    @Id
    private UUID id = UUID.randomUUID();
//    Guide's ID. According to this, we know who Lead belongs to.
    private UUID guideId;
    private UUID userReviewId;
    private String destination;
    private String title;
    private String content;
    private String category;
    private UUID housingId;
    private String image;
    private double duration;
    private BigDecimal price;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    private Timestamp finishDate;



}
