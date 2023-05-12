package kz.itstep.model;

import kz.itstep.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table("room")
public class Room {
    private Long id;
    private Integer roomsCount;
    private Integer priceForOneRoom;
}
